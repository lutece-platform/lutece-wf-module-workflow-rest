-- liquibase formatted sql
-- changeset workflow-rest:update_db_workflow_rest-2.1.1-3.0.0.sql
-- preconditions onFail:MARK_RAN onError:WARN
-- precondition-sql-check expectedResult:0 SELECT COUNT(*) FROM core_admin_right WHERE id_right='WORKFLOW_REST_TEST'
INSERT INTO core_admin_right (id_right,name,level_right,admin_url,description,is_updatable,plugin_name,id_feature_group,icon_url,documentation_url,id_order) VALUES ('WORKFLOW_REST_TEST','module.workflow.rest.adminFeature.testWorkflowRest.name',0,'jsp/admin/plugins/workflow/modules/rest/TestWorkflowRest.jsp','module.workflow.rest.adminFeature.testWorkflowRest.description',0,'workflow-rest','SYSTEM','ti ti-api',NULL,NULL);
INSERT INTO core_user_right (id_right,id_user) VALUES ('WORKFLOW_REST_TEST',1);
