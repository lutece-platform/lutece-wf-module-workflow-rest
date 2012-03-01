/*
 * Copyright (c) 2002-2012, Mairie de Paris
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

import fr.paris.lutece.plugins.workflow.business.ActionFilter;
import fr.paris.lutece.plugins.workflow.business.ActionHome;
import fr.paris.lutece.plugins.workflow.business.ResourceWorkflow;
import fr.paris.lutece.plugins.workflow.business.ResourceWorkflowHome;
import fr.paris.lutece.plugins.workflow.business.StateFilter;
import fr.paris.lutece.plugins.workflow.business.StateHome;
import fr.paris.lutece.plugins.workflow.business.WorkflowFilter;
import fr.paris.lutece.plugins.workflow.business.WorkflowHome;
import fr.paris.lutece.plugins.workflow.service.WorkflowPlugin;
import fr.paris.lutece.portal.business.workflow.Action;
import fr.paris.lutece.portal.business.workflow.State;
import fr.paris.lutece.portal.business.workflow.Workflow;
import fr.paris.lutece.portal.service.plugin.Plugin;
import fr.paris.lutece.portal.service.plugin.PluginService;

import java.util.List;


/**
 *
 * WorkflowRestService
 *
 */
public class WorkflowRestService
{
    private Plugin _pluginWorkflow = PluginService.getPlugin( WorkflowPlugin.PLUGIN_NAME );

    /**
     * Get the workflow
     * @param nIdWorkflow the id workflow
     * @return the workflow
     */
    public Workflow getWorkflow( int nIdWorkflow )
    {
        return WorkflowHome.findByPrimaryKey( nIdWorkflow, _pluginWorkflow );
    }

    /**
     * Get the list of workflows
     * @return the list of workflows
     */
    public List<Workflow> getWorkflowsList(  )
    {
        return WorkflowHome.getListWorkflowsByFilter( new WorkflowFilter(  ), _pluginWorkflow );
    }

    /**
     * Get the state
     * @param nIdState the id state
     * @return the state
     */
    public State getState( int nIdState )
    {
        return StateHome.findByPrimaryKey( nIdState, _pluginWorkflow );
    }

    /**
     * Get the list of states
     * @return the list of states
     */
    public List<State> getStatesList(  )
    {
        return StateHome.getListStateByFilter( new StateFilter(  ), _pluginWorkflow );
    }

    /**
     * Get the action
     * @param nIdAction the id action
     * @return the action
     */
    public Action getAction( int nIdAction )
    {
        return ActionHome.findByPrimaryKey( nIdAction, _pluginWorkflow );
    }

    /**
     * Get the list of actions
     * @return the list of actions
     */
    public List<Action> getActionsList(  )
    {
        return ActionHome.getListActionByFilter( new ActionFilter(  ), _pluginWorkflow );
    }

    /**
     * Get the resource workflow
     * @param nIdResource the id resource
     * @param strResourceType the resource type
     * @param nIdWorkflow the id workflow
     * @return the resource workflow
     */
    public ResourceWorkflow getResourceWorkflow( int nIdResource, String strResourceType, int nIdWorkflow )
    {
        return ResourceWorkflowHome.findByPrimaryKey( nIdResource, strResourceType, nIdWorkflow, _pluginWorkflow );
    }
}
