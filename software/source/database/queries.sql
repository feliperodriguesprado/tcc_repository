select datname, count(*) from pg_stat_activity group by datname;
select * from pg_stat_activity;

select datname, usename, application_name, client_addr, client_hostname, backend_start from pg_stat_activity;

delete from financial_releases;
delete from address; 
delete from phones;
delete from peoples;