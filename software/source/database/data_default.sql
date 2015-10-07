-- Table view_modules:
select * from view_modules;
-- INSERT INTO view_modules (type, symbolic_name, active, name, context_path, icon, position, parent) VALUES (2, 'br.com.smom.home.view', true, 'Início', '/modules/home/#/', 'fa fa-home', 1, null);
INSERT INTO view_modules (type, symbolic_name, active, name, context_path, icon, position, parent) VALUES (1, 'br.com.smom.customer', true, 'Clientes', '#', 'fa fa-users', 2, NULL);
INSERT INTO view_modules (type, symbolic_name, active, name, context_path, icon, position, parent) VALUES (1, 'br.com.smom.financial', true, 'Financeiro', '#', 'glyphicon glyphicon-piggy-bank', 3, NULL);
INSERT INTO view_modules (type, symbolic_name, active, name, context_path, icon, position, parent) VALUES (1, 'br.com.smom.user', true, 'Usuários', '#', 'fa fa-user', 4, NULL);
-- INSERT INTO view_modules (type, symbolic_name, active, name, context_path, icon, position, parent) VALUES (2, 'br.com.smom.customer.register', true, 'Cadastro', '/modules/customer/register/#/', 'fa fa-pencil-square-o', 1, 3);
-- INSERT INTO view_modules (type, symbolic_name, active, name, context_path, icon, position, parent) VALUES (2, 'br.com.smom.financial.releases', true, 'Lançamentos', '/modules/financial/releases/#/', 'fa fa-money', 1, 4);
-- INSERT INTO view_modules (type, symbolic_name, active, name, context_path, icon, position, parent) VALUES (2, 'br.com.smom.financial.reports', true, 'Relatórios', '/modules/financial/reports/#/', 'fa fa-line-chart', 2, 4);
-- INSERT INTO view_modules (type, symbolic_name, active, name, context_path, icon, position, parent) VALUES (2, 'br.com.smom.user.register', true, 'Cadastro', '/modules/user/register/#/', 'fa fa-pencil', 1, 5);