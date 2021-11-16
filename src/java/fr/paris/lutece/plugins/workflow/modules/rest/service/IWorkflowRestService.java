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
package fr.paris.lutece.plugins.workflow.modules.rest.service;

import fr.paris.lutece.plugins.workflowcore.business.action.Action;
import fr.paris.lutece.plugins.workflowcore.business.action.ActionFilter;
import fr.paris.lutece.plugins.workflowcore.business.resource.ResourceWorkflow;
import fr.paris.lutece.plugins.workflowcore.business.resource.ResourceWorkflowFilter;
import fr.paris.lutece.plugins.workflowcore.business.state.State;
import fr.paris.lutece.plugins.workflowcore.business.workflow.Workflow;

import java.util.List;

/**
 *
 * IWorkflowRestService
 *
 */
public interface IWorkflowRestService
{
    /**
     * Get the workflow
     * 
     * @param nIdWorkflow
     *            the id workflow
     * @return the workflow
     */
    Workflow getWorkflow( int nIdWorkflow );

    /**
     * Get the list of workflows
     * 
     * @return the list of workflows
     */
    List<Workflow> getWorkflowsList( );

    /**
     * Get the state
     * 
     * @param nIdState
     *            the id state
     * @return the state
     */
    State getState( int nIdState );

    /**
     * Get the list of states
     * 
     * @return the list of states
     */
    List<State> getStatesList( );

    /**
     * Get the action
     * 
     * @param nIdAction
     *            the id action
     * @return the action
     */
    Action getAction( int nIdAction );

    /**
     * Get the list of actions
     * 
     * @return the list of actions
     */
    List<Action> getActionsList( );

    /**
     * Get the resource workflow
     * 
     * @param nIdResource
     *            the id resource
     * @param strResourceType
     *            the resource type
     * @param nIdWorkflow
     *            the id workflow
     * @return the resource workflow
     */
    ResourceWorkflow getResourceWorkflow( int nIdResource, String strResourceType, int nIdWorkflow );

    /**
     * Select ResourceWorkflow by filter
     * 
     * @param resourceWorkflowFilter
     *            the filter
     * @return ResourceWorkflow List
     */
    List<ResourceWorkflow> getListResourceWorkflowByFilter( ResourceWorkflowFilter resourceWorkflowFilter );

    /**
     * Select actions by filter
     * 
     * @param filter
     *            the action filter
     * @return Action List
     */
    List<Action> getListActionByFilter( ActionFilter filter );
}
