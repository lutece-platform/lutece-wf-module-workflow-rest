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
package fr.paris.lutece.plugins.workflow.modules.rest.util.constants;


/**
 *
 * WorkflowRestConstants
 *
 */
public final class WorkflowRestConstants
{
    // CONSTANTS
    public static final String SLASH = "/";

    // PATHS
    public static final String PATH_WADL = "wadl";
    public static final String PATH_ID_WORKFLOW = "{id_workflow}";
    public static final String PATH_STATE = "/state";
    public static final String PATH_ID_STATE = "{id_state}";
    public static final String PATH_ACTION = "/action";
    public static final String PATH_ID_ACTION = "{id_action}";
    public static final String PATH_RESOURCE_WORKFLOW = "/resource_workflow";
    public static final String PATH_ID_RESOURCE = "{id_resource}";
    public static final String PATH_RESOURCE_TYPE = "{resource_type}";
    public static final String PATH_DO = "do";

    // TAGS
    public static final String TAG_WORKFLOWS = "workflows";
    public static final String TAG_WORKFLOW = "workflow";
    public static final String TAG_ID_WORKFLOW = "id-workflow";
    public static final String TAG_NAME = "name";
    public static final String TAG_DESCRIPTION = "description";
    public static final String TAG_CREATION_DATE = "creation-date";
    public static final String TAG_IS_ENABLE = "is-enable";
    public static final String TAG_WORKGROUP_KEY = "workgroup-key";
    public static final String TAG_STATES = "states";
    public static final String TAG_STATE = "state";
    public static final String TAG_ID_STATE = "id-state";
    public static final String TAG_IS_INITIAL_STATE = "is-initial-state";
    public static final String TAG_IS_REQUIRED_WORKGROUP_ASSIGNED = "is-required-workgroup-assigned";
    public static final String TAG_ACTIONS = "actions";
    public static final String TAG_ACTION = "action";
    public static final String TAG_ID_ACTION = "id-action";
    public static final String TAG_ID_STATE_BEFORE = "id-state-before";
    public static final String TAG_ID_STATE_AFTER = "id-state-after";
    public static final String TAG_IS_AUTOMATIC_STATE = "is-automatic-state";
    public static final String TAG_IS_MASS_ACTION = "is-mass-action";
    public static final String TAG_RESOURCE_WORKFLOWS = "resource-workflows";
    public static final String TAG_RESOURCE_WORKFLOW = "resource-workflow";
    public static final String TAG_ID_RESOURCE = "id-resource";
    public static final String TAG_RESOURCE_TYPE = "resource-type";
    public static final String TAG_ID_EXTERNAL_PARENT = "id-external-parent";
    public static final String TAG_WORKGROUPS = "workgroups";
    public static final String TAG_IS_ASSOCIATED_WITH_WORKGROUP = "is-associated-with-workgroup";
    public static final String TAG_ACTION_RESULTS = "action-results";
    public static final String TAG_ACTION_RESULT = "action-result";
    public static final String TAG_IS_SUCCESSFUL = "is-successful";
    public static final String TAG_MESSAGE = "message";

    // PARAMETERS
    public static final String PARAMETER_ID_WORKFLOW = "id_workflow";
    public static final String PARAMETER_ID_STATE = "id_state";
    public static final String PARAMETER_ID_ACTION = "id_action";
    public static final String PARAMETER_ID_RESOURCE = "id_resource";
    public static final String PARAMETER_RESOURCE_TYPE = "resource_type";

    // MARKS
    public static final String MARK_BASE_URL = "base_url";

    // MESSAGES
    public static final String MESSAGE_SUCCESS = "SUCCESS";
    public static final String MESSAGE_ERROR_WORKFLOW_NOT_AVAILABLE = "ERROR : Workflow not available";
    public static final String MESSAGE_ERROR_ACTION_NEEDS_INTERMEDIATE_STEP = "ERROR : The action needs an intermediate step";
    public static final String MESSAGE_ERROR_ACTION_NONEXISTENT = "ERROR : The action does not exist";
    public static final String MESSAGE_ERROR_RESOURCE_NONEXISTENT = "ERROR : The resource does not exist";
    public static final String MESSAGE_ERROR_RESOURCE_STATE = "ERROR : The resource is not in the correct state to perform the action";

    // TEMPLATES
    public static final String TEMPLATE_WADL = "admin/plugins/workflow/modules/rest/wadl.xml";

    /**
     * Private constructor
     */
    private WorkflowRestConstants(  )
    {
    }
}
