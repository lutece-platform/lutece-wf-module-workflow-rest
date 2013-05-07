/*
 * Copyright (c) 2002-2013, Mairie de Paris
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
package fr.paris.lutece.plugins.workflow.modules.rest.business.actionresult;


/**
 *
 * AbstractActionResult
 *
 */
public class AbstractActionResult implements IActionResult
{
    private boolean _bIsSuccessful;
    private String _strMessage;
    private int _nIdAction;
    private int _nIdResource;
    private String _strResourceType;

    /**
     * Constructor
     * @param nIdAction the id action
     * @param nIdResource the id resource
     * @param strResourceType the resource type
     */
    public AbstractActionResult( int nIdAction, int nIdResource, String strResourceType )
    {
        _nIdAction = nIdAction;
        _nIdResource = nIdResource;
        _strResourceType = strResourceType;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setSuccessful( boolean bIsSuccessful )
    {
        _bIsSuccessful = bIsSuccessful;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isSuccessful(  )
    {
        return _bIsSuccessful;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setMessage( String strMessage )
    {
        _strMessage = strMessage;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getMessage(  )
    {
        return _strMessage;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setIdAction( int nIdAction )
    {
        _nIdAction = nIdAction;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getIdAction(  )
    {
        return _nIdAction;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setIdResource( int nIdResource )
    {
        _nIdResource = nIdResource;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getIdResource(  )
    {
        return _nIdResource;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setResourceType( String strResourceType )
    {
        _strResourceType = strResourceType;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getResourceType(  )
    {
        return _strResourceType;
    }
}
