/*
 * Copyright (c) 2002-2014, Mairie de Paris
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *  1. Redistributions of source code must retain the above copyright notice
 *     and the following disclaimer.
 *
 *  2. Redistributions in binary form must reproduce the above copyright notice
 *     and the following disclaimer in the documentation and/or other materials
 *     provided with the distribution.
 *
 *  3. Neither the name of 'Mairie de Paris' nor 'Lutece' nor the names of its
 *     contributors may be used to endorse or promote products derived from
 *     this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDERS OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 *
 * License 1.0
 */
package fr.paris.lutece.plugins.workflow.modules.rest.rs;

import fr.paris.lutece.plugins.rest.service.RestConstants;
import fr.paris.lutece.plugins.workflow.modules.rest.business.actionresult.FailedActionResult;
import fr.paris.lutece.plugins.workflow.modules.rest.business.actionresult.IActionResult;
import fr.paris.lutece.plugins.workflow.modules.rest.business.actionresult.SuccessfulActionResult;
import fr.paris.lutece.plugins.workflow.modules.rest.service.WorkflowRestService;
import fr.paris.lutece.plugins.workflow.modules.rest.util.constants.WorkflowRestConstants;
import fr.paris.lutece.plugins.workflow.service.WorkflowPlugin;
import fr.paris.lutece.plugins.workflowcore.business.action.Action;
import fr.paris.lutece.plugins.workflowcore.business.resource.ResourceWorkflow;
import fr.paris.lutece.portal.service.workflow.WorkflowService;

import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;


/**
 *
 * ActionRest
 *
 */
@Path( RestConstants.BASE_PATH + WorkflowPlugin.PLUGIN_NAME + WorkflowRestConstants.PATH_ACTION )
public class ActionRest
{
    private WorkflowRestService _workflowRestService;

    // SET

    /**
     * Set the workflow rest service
     * @param workflowRestService the workflow rest service
     */
    public void setWorkflowRestService( WorkflowRestService workflowRestService )
    {
        _workflowRestService = workflowRestService;
    }

    // GET

    /**
     * Get the action
     * @param nIdAction the id action
     * @return the state
     */
    @GET
    @Path( WorkflowRestConstants.PATH_ID_ACTION )
    @Produces( {MediaType.APPLICATION_JSON,
        MediaType.APPLICATION_XML
    } )
    public List<Action> getAction( @PathParam( WorkflowRestConstants.PARAMETER_ID_ACTION )
    int nIdAction )
    {
        Action action = _workflowRestService.getAction( nIdAction );

        if ( action != null )
        {
            List<Action> listActions = new ArrayList<Action>(  );
            listActions.add( action );

            return listActions;
        }

        return null;
    }

    /**
     * Get the actions list
     * @return the list of states
     */
    @GET
    @Path( StringUtils.EMPTY )
    @Produces( {MediaType.APPLICATION_JSON,
        MediaType.APPLICATION_XML
    } )
    public List<Action> getActionsList(  )
    {
        return _workflowRestService.getActionsList(  );
    }

    /**
     * Execute the workflow action
     * @param nIdAction the id action
     * @param nIdResource the id resource
     * @param strResourceType the resource type
     * @param request the HTTP request
     * @return the action result
     */
    @POST
    @Path( WorkflowRestConstants.PATH_DO )
    @Consumes( MediaType.APPLICATION_FORM_URLENCODED )
    @Produces( {MediaType.APPLICATION_JSON,
        MediaType.APPLICATION_XML
    } )
    public List<IActionResult> doAction( @FormParam( WorkflowRestConstants.PARAMETER_ID_ACTION )
    int nIdAction, @FormParam( WorkflowRestConstants.PARAMETER_ID_RESOURCE )
    int nIdResource, @FormParam( WorkflowRestConstants.PARAMETER_RESOURCE_TYPE )
    String strResourceType, @Context
    HttpServletRequest request )
    {
        List<IActionResult> listResults = new ArrayList<IActionResult>(  );
        IActionResult failedResult = checkDoAction( nIdAction, nIdResource, strResourceType, request );

        if ( failedResult != null )
        {
            listResults.add( failedResult );

            return listResults;
        }

        Action action = _workflowRestService.getAction( nIdAction );
        ResourceWorkflow resource = _workflowRestService.getResourceWorkflow( nIdResource, strResourceType,
                action.getWorkflow(  ).getId(  ) );

        WorkflowService.getInstance(  )
                       .doProcessAction( nIdResource, strResourceType, nIdAction, resource.getExternalParentId(  ),
            request, request.getLocale(  ), true );

        listResults.add( new SuccessfulActionResult( nIdAction, nIdResource, strResourceType ) );

        return listResults;
    }

    /**
     * Check if the action can be executed or not
     * @param nIdAction the id action
     * @param nIdResource the id resource
     * @param strResourceType the resource type
     * @param request the HTTP request
     * @return null if the action can be executed, a {@link FailedActionResult} object otherwise
     */
    private IActionResult checkDoAction( int nIdAction, int nIdResource, String strResourceType,
        HttpServletRequest request )
    {
        // Check the availability of the workflow service
        if ( !WorkflowService.getInstance(  ).isAvailable(  ) )
        {
            new FailedActionResult( nIdAction, nIdResource, strResourceType,
                WorkflowRestConstants.MESSAGE_ERROR_WORKFLOW_NOT_AVAILABLE );
        }

        // Check if the action does not require intermediate step
        if ( WorkflowService.getInstance(  ).isDisplayTasksForm( nIdAction, request.getLocale(  ) ) )
        {
            return new FailedActionResult( nIdAction, nIdResource, strResourceType,
                WorkflowRestConstants.MESSAGE_ERROR_ACTION_NEEDS_INTERMEDIATE_STEP );
        }

        // Check the existence of the action
        Action action = _workflowRestService.getAction( nIdAction );

        if ( action == null )
        {
            return new FailedActionResult( nIdAction, nIdResource, strResourceType,
                WorkflowRestConstants.MESSAGE_ERROR_ACTION_NONEXISTENT );
        }

        // Check the existence of the resource
        ResourceWorkflow resource = _workflowRestService.getResourceWorkflow( nIdResource, strResourceType,
                action.getWorkflow(  ).getId(  ) );

        if ( resource == null )
        {
            return new FailedActionResult( nIdAction, nIdResource, strResourceType,
                WorkflowRestConstants.MESSAGE_ERROR_RESOURCE_NONEXISTENT );
        }

        // Check if the resource has the right state to perform the action
        if ( !WorkflowService.getInstance(  )
                                 .canProcessAction( nIdResource, strResourceType, nIdAction,
                    resource.getExternalParentId(  ), request, true ) )
        {
            return new FailedActionResult( nIdAction, nIdResource, strResourceType,
                WorkflowRestConstants.MESSAGE_ERROR_RESOURCE_STATE );
        }

        return null;
    }
}
