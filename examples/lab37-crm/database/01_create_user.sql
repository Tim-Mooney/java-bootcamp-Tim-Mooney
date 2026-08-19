-- TODO: create application role CRM_APP with LOGIN and limited grants (no SUPERUSER)
-- Example shape (adjust for your shared vs local policy):
CREATE USER crm_app WITH PASSWORD 'CrmLab_Strong1';
CREATE SCHEMA IF NOT EXISTS crm_app AUTHORIZATION crm_app;
GRANT CONNECT ON DATABASE crm TO crm_app;
GRANT USAGE, CREATE ON SCHEMA crm_app TO crm_app;
