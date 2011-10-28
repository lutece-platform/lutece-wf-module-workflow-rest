/*
 * Copyright (c) 2002-2011, Mairie de Paris
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
import fr.paris.lutece.plugins.workflow.modules.rest.service.WorkflowRestService;
import fr.paris.lutece.plugins.workflow.modules.rest.util.constants.WorkflowRestConstants;
import fr.paris.lutece.plugins.workflow.service.WorkflowPlugin;
import fr.paris.lutece.portal.business.workflow.Workflow;
import fr.paris.lutece.portal.service.template.AppTemplateService;
import fr.paris.lutece.portal.service.util.AppPathService;
import fr.paris.lutece.util.html.HtmlTemplate;

import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;


/**
 *
 * DirectoryRest
 *
 */
@Path( RestConstants.BASE_PATH + WorkflowPlugin.PLUGIN_NAME )
public class WorkflowRest
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

    /**
     * Get the wadl.xml content
     * @param request {@link HttpServletRequest}
     * @return the content of wadl.xml
     */
    @GET
    @Path( WorkflowRestConstants.PATH_WADL )
    @Produces( MediaType.APPLICATION_XML )
    public String getWADL( @Context
    HttpServletRequest request )
    {
        StringBuilder sbBase = new StringBuilder( AppPathService.getBaseUrl( request ) );

        if ( sbBase.toString(  ).endsWith( WorkflowRestConstants.SLASH ) )
        {
            sbBase.deleteCharAt( sbBase.length(  ) - 1 );
        }

        sbBase.append( RestConstants.BASE_PATH + WorkflowPlugin.PLUGIN_NAME );

        Map<String, Object> model = new HashMap<String, Object>(  );
        model.put( WorkflowRestConstants.MARK_BASE_URL, sbBase.toString(  ) );

        HtmlTemplate t = AppTemplateService.getTemplate( WorkflowRestConstants.TEMPLATE_WADL, request.getLocale(  ),
                model );

        return t.getHtml(  );
    }

    // GET

    /**
     * Get the workflow
     * @param nIdWorkflow the id workflow
     * @return the workflow
     */
    @GET
    @Path( WorkflowRestConstants.PATH_ID_WORKFLOW )
    @Produces( {MediaType.APPLICATION_JSON,
        MediaType.APPLICATION_XML
    } )
    public List<Workflow> getWorkflow( @PathParam( WorkflowRestConstants.PARAMETER_ID_WORKFLOW )
    int nIdWorkflow )
    {
        Workflow workflow = _workflowRestService.getWorkflow( nIdWorkflow );

        if ( workflow != null )
        {
            List<Workflow> listWorkflows = new ArrayList<Workflow>(  );
            listWorkflows.add( workflow );

            return listWorkflows;
        }

        return null;
    }

    /**
     * Get the workflows list
     * @return the list of workflows
     */
    @GET
    @Path( StringUtils.EMPTY )
    @Produces( {MediaType.APPLICATION_JSON,
        MediaType.APPLICATION_XML
    } )
    public List<Workflow> getWorkflowsList(  )
    {
        return _workflowRestService.getWorkflowsList(  );
    }
}
