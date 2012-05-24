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
package fr.paris.lutece.plugins.workflow.modules.rest.service.formatters;

import fr.paris.lutece.plugins.rest.service.formatters.IFormatter;
import fr.paris.lutece.plugins.rest.util.xml.XMLUtil;
import fr.paris.lutece.plugins.workflow.modules.rest.business.actionresult.IActionResult;
import fr.paris.lutece.plugins.workflow.modules.rest.util.constants.WorkflowRestConstants;
import fr.paris.lutece.plugins.workflow.utils.WorkflowUtils;
import fr.paris.lutece.portal.service.util.AppPropertiesService;
import fr.paris.lutece.util.xml.XmlUtil;

import org.apache.commons.lang.StringUtils;

import java.util.List;


/**
 *
 * ActionResultFormatterXml
 *
 */
public class ActionResultFormatterXml implements IFormatter<IActionResult>
{
    /**
     * {@inheritDoc}
     */
    @Override
    public String formatError( String strCode, String strMessage )
    {
        int nIdCode = WorkflowUtils.CONSTANT_ID_NULL;

        if ( StringUtils.isNotBlank( strCode ) && StringUtils.isNumeric( strCode ) )
        {
            nIdCode = Integer.parseInt( strCode );
        }

        return XMLUtil.formatError( strMessage, nIdCode );
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String format( IActionResult actionResult )
    {
        StringBuffer sbXml = new StringBuffer( AppPropertiesService.getProperty( XmlUtil.PROPERTIES_XML_HEADER ) );
        formatActionResult( sbXml, actionResult );

        return sbXml.toString(  );
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String format( List<IActionResult> listActionsResult )
    {
        StringBuffer sbXml = new StringBuffer( AppPropertiesService.getProperty( XmlUtil.PROPERTIES_XML_HEADER ) );
        XmlUtil.beginElement( sbXml, WorkflowRestConstants.TAG_ACTION_RESULTS );

        for ( IActionResult actionResult : listActionsResult )
        {
            formatActionResult( sbXml, actionResult );
        }

        XmlUtil.endElement( sbXml, WorkflowRestConstants.TAG_ACTION_RESULTS );

        return sbXml.toString(  );
    }

    /**
     * Format the action result
     * @param sbXml the XML
     * @param actionResult the action result
     */
    private void formatActionResult( StringBuffer sbXml, IActionResult actionResult )
    {
        XmlUtil.beginElement( sbXml, WorkflowRestConstants.TAG_ACTION_RESULT );

        XmlUtil.addElement( sbXml, WorkflowRestConstants.TAG_IS_SUCCESSFUL,
            Boolean.toString( actionResult.isSuccessful(  ) ) );
        XmlUtil.addElement( sbXml, WorkflowRestConstants.TAG_ID_ACTION, actionResult.getIdAction(  ) );
        XmlUtil.addElement( sbXml, WorkflowRestConstants.TAG_ID_RESOURCE, actionResult.getIdResource(  ) );
        XmlUtil.addElement( sbXml, WorkflowRestConstants.TAG_RESOURCE_TYPE, actionResult.getResourceType(  ) );
        XmlUtil.addElement( sbXml, WorkflowRestConstants.TAG_MESSAGE, actionResult.getMessage(  ) );

        XmlUtil.endElement( sbXml, WorkflowRestConstants.TAG_ACTION_RESULT );
    }
}
