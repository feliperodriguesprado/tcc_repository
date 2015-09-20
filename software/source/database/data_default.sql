-- Table view_modules:

INSERT INTO view_modules VALUES (1, 2, 'br.com.smom.home.view', true, 'Início', '/modules/home/#/', 'fa fa-home', 1, NULL);
INSERT INTO view_modules VALUES (2, 1, 'br.com.smom.customer', true, 'Clientes', '#', 'fa fa-users', 2, NULL);
INSERT INTO view_modules VALUES (3, 2, 'br.com.smom.customer.register', true, 'Cadastro', '/modules/customer/register/#/', 'fa fa-pencil-square-o', 1, 2);
INSERT INTO view_modules VALUES (4, 1, 'br.com.smom.financial', true, 'Financeiro', '#', 'glyphicon glyphicon-piggy-bank', 3, NULL);
INSERT INTO view_modules VALUES (5, 2, 'br.com.smom.financial.releases', true, 'Lançamentos', '/modules/financial/releases/#/', 'fa fa-money', 1, 4);
INSERT INTO view_modules VALUES (6, 2, 'br.com.smom.financial.reports', true, 'Relatórios', '/modules/financial/reports/#/', 'fa fa-line-chart', 2, 4);
INSERT INTO view_modules VALUES (7, 1, 'br.com.smom.user', true, 'Usuários', '#', 'fa fa-user', 4, NULL);
INSERT INTO view_modules VALUES (8, 2, 'br.com.smom.user.register', true, 'Cadastro', '/modules/user/register/#/', 'fa fa-pencil', 1, 7);