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
package fr.paris.lutece.plugins.workflow.modules.rest.service.writers;

import fr.paris.lutece.plugins.rest.service.writers.AbstractWriter;
import fr.paris.lutece.plugins.workflowcore.business.resource.ResourceWorkflow;

import java.lang.annotation.Annotation;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import java.util.List;

import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.ext.Provider;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import java.util.HashMap;
import java.util.Map;
import fr.paris.lutece.plugins.rest.service.formatters.IFormatter;
import fr.paris.lutece.plugins.workflow.modules.rest.service.formatters.ResourceWorkflowFormatterXml;
import fr.paris.lutece.plugins.workflow.modules.rest.service.formatters.ResourceWorkflowFormatterJson;

/**
 *
 * ResourceWorkflowWriter
 *
 */
@ApplicationScoped
@Provider
@Produces( {
        MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON
} )
public class ResourceWorkflowWriter extends AbstractWriter<ResourceWorkflow>
{
    @Inject
    private ResourceWorkflowFormatterXml _formatterXml;
    @Inject
    private ResourceWorkflowFormatterJson _formatterJson;

    /**
     * Wires the formatters the writer serves, one per media type, as the Spring context used to.
     */
    @PostConstruct
    void initFormatters( )
    {
        Map<String, IFormatter<ResourceWorkflow>> mapFormatters = new HashMap<>( );
        mapFormatters.put( MediaType.APPLICATION_XML, _formatterXml );
        mapFormatters.put( MediaType.APPLICATION_JSON, _formatterJson );
        setFormatters( mapFormatters );
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isWriteable( Class<?> type, Type genericType, Annotation [ ] annotations, MediaType mediaType )
    {
        // Ensure that we're handling only List<ResourceWorkflow> objects.
        boolean isWritable = false;

        if ( ResourceWorkflow.class.equals( genericType ) )
        {
            isWritable = true;
        }

        if ( List.class.isAssignableFrom( type ) && genericType instanceof ParameterizedType )
        {
            ParameterizedType parameterizedType = (ParameterizedType) genericType;
            Type [ ] actualTypeArgs = ( parameterizedType.getActualTypeArguments( ) );
            isWritable = ( ( actualTypeArgs.length == 1 ) && actualTypeArgs [0].equals( ResourceWorkflow.class ) );
        }

        return isWritable;
    }
}
