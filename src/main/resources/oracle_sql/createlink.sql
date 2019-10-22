
declare
	v_rowcount integer;
	v_sql VARCHAR(2000);
  
begin
	SELECT count(*) into v_rowcount FROM dba_db_links where db_link = UPPER('dblink_name') and username = UPPER('username');
	-- 存在则删除db_link连接
	if v_rowcount > 0 then
		v_sql := 'DROP PUBLIC DATABASE LINK dblink_name';
		execute immediate v_sql;
		dbms_output.put_line('删除db_link连接成功v_sql:'||v_sql||'');
	else 
		dbms_output.put_line('db_link dblink_name not existed');
	end if;
end;
/

CREATE PUBLIC DATABASE LINK dblink_name CONNECT TO username IDENTIFIED BY password USING '(DESCRIPTION =
            (ADDRESS_LIST =
              (ADDRESS = (PROTOCOL = TCP)(HOST = ip_address)(PORT = ora_port))
            )
            (CONNECT_DATA =
              (SERVICE_NAME = ora_context)
            )
          )';