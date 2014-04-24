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
package fr.paris.lutece.plugins.workflow.modules.rest.business.resourceinfo;

import fr.paris.lutece.plugins.rest.business.resourceinfo.AbstractResourceInfo;
import fr.paris.lutece.plugins.rest.business.resourceinfo.IResourceInfo;
import fr.paris.lutece.plugins.rest.business.resourceinfo.ResourceInfo;
import fr.paris.lutece.plugins.workflow.modules.rest.util.constants.WorkflowRestConstants;
import fr.paris.lutece.plugins.workflowcore.business.state.State;

import java.util.ArrayList;
import java.util.List;


/**
 *
 * StateInfo
 *
 */
public class StateInfo extends AbstractResourceInfo<State>
{
    /**
     * {@inheritDoc}
     */
    @Override
    public void setResourceInfo( State state )
    {
        setKey( WorkflowRestConstants.TAG_STATE );

        List<IResourceInfo> listChildren = new ArrayList<IResourceInfo>(  );

        IResourceInfo idState = new ResourceInfo( WorkflowRestConstants.TAG_ID_STATE,
                Integer.toString( state.getId(  ) ) );
        listChildren.add( idState );

        IResourceInfo name = new ResourceInfo( WorkflowRestConstants.TAG_NAME, state.getName(  ) );
        listChildren.add( name );

        IResourceInfo description = new ResourceInfo( WorkflowRestConstants.TAG_DESCRIPTION, state.getDescription(  ) );
        listChildren.add( description );

        if ( state.getWorkflow(  ) != null )
        {
            IResourceInfo idWorkflow = new ResourceInfo( WorkflowRestConstants.TAG_ID_WORKFLOW,
                    Integer.toString( state.getWorkflow(  ).getId(  ) ) );
            listChildren.add( idWorkflow );
        }

        IResourceInfo isInitialState = new ResourceInfo( WorkflowRestConstants.TAG_IS_INITIAL_STATE,
                Boolean.toString( state.isInitialState(  ) ) );
        listChildren.add( isInitialState );

        IResourceInfo isRequiredWorkgroupAssigned = new ResourceInfo( WorkflowRestConstants.TAG_IS_REQUIRED_WORKGROUP_ASSIGNED,
                Boolean.toString( state.isRequiredWorkgroupAssigned(  ) ) );
        listChildren.add( isRequiredWorkgroupAssigned );

        setListChildren( listChildren );
    }
}
