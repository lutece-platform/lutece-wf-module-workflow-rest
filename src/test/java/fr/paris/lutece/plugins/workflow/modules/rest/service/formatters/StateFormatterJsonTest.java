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
package fr.paris.lutece.plugins.workflow.modules.rest.service.formatters;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import fr.paris.lutece.plugins.workflowcore.business.state.State;
import fr.paris.lutece.plugins.workflowcore.business.workflow.Workflow;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

import org.junit.jupiter.api.Test;

/**
 * Tests the JSON representation of the workflow states.
 */
public class StateFormatterJsonTest
{
    private static final ObjectMapper MAPPER = new ObjectMapper( );

    /**
     * Builds a state of a workflow.
     * 
     * @param nId
     *            the state id
     * @param strName
     *            the state name
     * @return the state
     */
    private static State state( int nId, String strName )
    {
        Workflow workflow = new Workflow( );
        workflow.setId( 7 );

        State state = new State( );
        state.setId( nId );
        state.setName( strName );
        state.setDescription( "Description of " + strName );
        state.setWorkflow( workflow );
        state.setInitialState( nId == 1 );
        state.setRequiredWorkgroupAssigned( false );

        return state;
    }

    /**
     * A list of states is an array of JSON objects, never an array of strings.
     * 
     * @throws Exception
     *             if the answer is not JSON
     */
    @Test
    public void testListIsAnArrayOfObjects( ) throws Exception
    {
        JsonNode array = MAPPER.readTree( new StateFormatterJson( ).format( List.of( state( 1, "Draft \"one\"" ), state( 2, "Published" ) ) ) );

        assertTrue( array.isArray( ) );
        assertEquals( 2, array.size( ) );
        assertTrue( array.get( 0 ).isObject( ) );
        assertEquals( 1, array.get( 0 ).get( "id-state" ).asInt( ) );
        assertEquals( "Draft \"one\"", array.get( 0 ).get( "name" ).asText( ) );
        assertEquals( 7, array.get( 1 ).get( "id-workflow" ).asInt( ) );
        assertEquals( "true", array.get( 0 ).get( "is-initial-state" ).asText( ) );
    }

    /**
     * A single state is the JSON object the list is made of.
     * 
     * @throws Exception
     *             if the answer is not JSON
     */
    @Test
    public void testSingleIsTheSameObject( ) throws Exception
    {
        StateFormatterJson formatter = new StateFormatterJson( );
        State state = state( 2, "Published" );

        assertEquals( MAPPER.readTree( formatter.format( state ) ), MAPPER.readTree( formatter.format( List.of( state ) ) ).get( 0 ) );
    }
}
