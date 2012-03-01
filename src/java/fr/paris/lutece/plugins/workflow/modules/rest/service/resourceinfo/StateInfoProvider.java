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
package fr.paris.lutece.plugins.workflow.modules.rest.service.resourceinfo;

import fr.paris.lutece.plugins.rest.business.resourceinfo.IResourceInfo;
import fr.paris.lutece.plugins.rest.service.resourceinfo.AbstractResourceInfoProvider;
import fr.paris.lutece.plugins.workflow.modules.rest.business.resourceinfo.StateInfo;
import fr.paris.lutece.plugins.workflow.modules.rest.util.constants.WorkflowRestConstants;
import fr.paris.lutece.plugins.workflow.service.WorkflowService;
import fr.paris.lutece.portal.business.workflow.State;

import org.apache.commons.lang.StringUtils;

import java.util.Map;


/**
 *
 * StateResourceInfoProvider
 *
 */
public class StateInfoProvider extends AbstractResourceInfoProvider
{
    /**
     * {@inheritDoc}
     */
    public IResourceInfo getResourceInfo( Map<String, String> mapParams )
    {
        String strIdResource = mapParams.get( WorkflowRestConstants.PARAMETER_ID_RESOURCE );
        String strResourceType = mapParams.get( WorkflowRestConstants.PARAMETER_RESOURCE_TYPE );
        String strIdWorkflow = mapParams.get( WorkflowRestConstants.PARAMETER_ID_WORKFLOW );

        int nIdResource = Integer.parseInt( strIdResource );
        int nIdWorkflow = Integer.parseInt( strIdWorkflow );
        IResourceInfo resourceInfo = null;

        State state = WorkflowService.getInstance(  ).getState( nIdResource, strResourceType, nIdWorkflow, null, null );

        if ( state != null )
        {
            StateInfo stateInfo = new StateInfo(  );
            stateInfo.setResourceInfo( state );
            resourceInfo = stateInfo;
        }

        return resourceInfo;
    }

    /**
     * {@inheritDoc}
     */
    public boolean isInvoked( Map<String, String> mapParams )
    {
        boolean bIsInvoked = false;

        if ( ( mapParams != null ) && ( mapParams.size(  ) == 3 ) )
        {
            String strIdResource = mapParams.get( WorkflowRestConstants.PARAMETER_ID_RESOURCE );
            String strResourceType = mapParams.get( WorkflowRestConstants.PARAMETER_RESOURCE_TYPE );
            String strIdWorkflow = mapParams.get( WorkflowRestConstants.PARAMETER_ID_WORKFLOW );

            if ( StringUtils.isNotBlank( strIdResource ) && StringUtils.isNumeric( strIdResource ) &&
                    StringUtils.isNotBlank( strIdWorkflow ) && StringUtils.isNumeric( strIdWorkflow ) &&
                    StringUtils.isNotBlank( strResourceType ) )
            {
                bIsInvoked = true;
            }
        }

        return bIsInvoked;
    }
}
