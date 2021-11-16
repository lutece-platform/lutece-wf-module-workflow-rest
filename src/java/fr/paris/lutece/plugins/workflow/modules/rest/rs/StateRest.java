/*
 * Copyright (c) 2002-2021, City of Paris
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
import fr.paris.lutece.plugins.workflowcore.business.state.State;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * StateRest
 *
 */
@Path( RestConstants.BASE_PATH + WorkflowPlugin.PLUGIN_NAME + WorkflowRestConstants.PATH_STATE )
public class StateRest
{
    private WorkflowRestService _workflowRestService;

    // SET

    /**
     * Set the workflow rest service
     * 
     * @param workflowRestService
     *            the workflow rest service
     */
    public void setWorkflowRestService( WorkflowRestService workflowRestService )
    {
        _workflowRestService = workflowRestService;
    }

    // GET

    /**
     * Get the state
     * 
     * @param nIdState
     *            the id state
     * @return the state
     */
    @GET
    @Path( WorkflowRestConstants.PATH_ID_STATE )
    @Produces( {
            MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML
    } )
    public List<State> getState( @PathParam( WorkflowRestConstants.PARAMETER_ID_STATE ) int nIdState )
    {
        State state = _workflowRestService.getState( nIdState );

        if ( state != null )
        {
            List<State> listStates = new ArrayList<State>( );
            listStates.add( state );

            return listStates;
        }

        return null;
    }

    /**
     * Get the states list
     * 
     * @return the list of states
     */
    @GET
    @Path( StringUtils.EMPTY )
    @Produces( {
            MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML
    } )
    public List<State> getStatesList( )
    {
        return _workflowRestService.getStatesList( );
    }
}
