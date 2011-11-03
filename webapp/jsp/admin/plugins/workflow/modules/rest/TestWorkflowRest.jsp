<%@page import="fr.paris.lutece.portal.service.util.AppPathService"%>

<html>
    <head>
        <title>Workflow - REST webservices test page</title>
        <base href="<%= AppPathService.getBaseUrl( request ) %>" />
        <link rel="stylesheet" type="text/css" href="css/portal_admin.css" title="lutece_admin" />
        <script type="text/javascript">
            function onWorkflowView(  ) {
                var idWorkflow = document.formGetWorkflow.id_workflow.value;
                var format = document.formGetWorkflow.format.value;
                if ( typeof( idWorkflow ) == 'undefined' || idWorkflow == '' ) {
                	document.location= 'rest/workflow' + format;
                } else {
                	document.location= 'rest/workflow/' + idWorkflow + format;
                }
            }

            function onStateView(  ) {
                var idState = document.formGetState.id_state.value;
                var format = document.formGetState.format.value;
                if ( typeof( idState ) == 'undefined' || idState == '' ) {
                	document.location= 'rest/workflow/state' + format;
                } else {
                	document.location= 'rest/workflow/state/' + idState + format;
                }
            }

            function onActionView(  ) {
                var idAction = document.formGetAction.id_action.value;
                var format = document.formGetAction.format.value;
                if ( typeof( idAction ) == 'undefined' || idAction == '' ) {
                	document.location= 'rest/workflow/action' + format;
                } else {
                	document.location= 'rest/workflow/action/' + idAction + format;
                }
            }

            function onResourceWorkflowView(  ) {
                var idResource = document.formGetResourceWorkflow.id_resource.value;
                var resourceType = document.formGetResourceWorkflow.resource_type.value;
                var idWorkflow = document.formGetResourceWorkflow.id_workflow.value;
                var format = document.formGetResourceWorkflow.format.value;
                
                document.location= 'rest/workflow/resource_workflow/' + idWorkflow + '/' + resourceType + '/' + idResource + format;
            }
        </script>
    </head>
    <body>
        <div id="content" >
            <h1>Workflow - REST webservices test page </h1>
            <div class="content-box">
	            <div class="highlight-box">
	                <h2>View WADL</h2>
	                <form action="rest/workflow/wadl">
	                    <br/>
	                    <input class="button" type="submit" value="View WADL" />
	                </form>
	            </div>
	            
	            <div class="highlight-box">
	                <h2>View workflow</h2>
	                <form name="formGetWorkflow">
	                    <label for="id_workflow">ID workflow : </label>
	                    <input type="text" name="id_workflow" size="10" maxlength="255" />
	                    <br/>
	                    <label for="format">Format :</label>
	                    <select name="format">
	                        <option value=".xml">XML</option>
	                        <option value=".json">JSON</option>
	                    </select>
	                    <br/>
	                    <input class="button" type="button" value="View" onclick="javascript:onWorkflowView(  )"/>
	                </form>
	            </div>
	            
	            <div class="highlight-box">
	                <h2>View state</h2>
	                <form name="formGetState">
	                    <label for="id_state">ID state : </label>
	                    <input type="text" name="id_state" size="10" maxlength="255" />
	                    <br/>
	                    <label for="format">Format :</label>
	                    <select name="format">
	                        <option value=".xml">XML</option>
	                        <option value=".json">JSON</option>
	                    </select>
	                    <br/>
	                    <input class="button" type="button" value="View" onclick="javascript:onStateView(  )"/>
	                </form>
	            </div>
	            
	            <div class="highlight-box">
	                <h2>View action</h2>
	                <form name="formGetAction">
	                    <label for="id_action">ID action : </label>
	                    <input type="text" name="id_action" size="10" maxlength="255" />
	                    <br/>
	                    <label for="format">Format :</label>
	                    <select name="format">
	                        <option value=".xml">XML</option>
	                        <option value=".json">JSON</option>
	                    </select>
	                    <br/>
	                    <input class="button" type="button" value="View" onclick="javascript:onActionView(  )"/>
	                </form>
	            </div>
	            
	            <div class="highlight-box">
	                <h2>View resource workflow</h2>
	                <form name="formGetResourceWorkflow">
	                    <label for="id_resource">ID resource * : </label>
	                    <input type="text" name="id_resource" size="10" maxlength="255" />
	                    <br/>
	                    <label for="resource_type">Resource type * : </label>
	                    <input type="text" name="resource_type" size="10" maxlength="255" />
	                    <br/>
	                    <label for="id_workflow">ID workflow * : </label>
	                    <input type="text" name="id_workflow" size="10" maxlength="255" />
	                    <br/>
	                    <label for="format">Format :</label>
	                    <select name="format">
	                        <option value=".xml">XML</option>
	                        <option value=".json">JSON</option>
	                    </select>
	                    <br/>
	                    <input class="button" type="button" value="View" onclick="javascript:onResourceWorkflowView(  )"/>
	                </form>
	            </div>
	            
	            <div class="highlight-box">
	                <h2>Do action</h2>
	                <p>
	                	To have the response in JSON, add ".json" at the end of the attribute action of the form.
	                </p>
	                <form name="formDoAction" action="rest/workflow/action/do" method="post">
	                	<label for="id_resource">ID action * : </label>
	                    <input type="text" name="id_action" size="10" maxlength="255" />
	                    <br/>
	                    <label for="id_resource">ID resource * : </label>
	                    <input type="text" name="id_resource" size="10" maxlength="255" />
	                    <br/>
	                    <label for="resource_type">Resource type * : </label>
	                    <input type="text" name="resource_type" size="10" maxlength="255" />
	                    <br/>
	                    <input class="button" type="submit" value="Do Action" />
	                </form>
	            </div>
        	</div>
        </div>
    </body>
</html>
