-- 查询db连接
SELECT * FROM dba_db_links;
SELECT * FROM dba_db_links where db_link = UPPER('DBLINK') and username = UPPER('test');
SELECT count(*) FROM dba_db_links where db_link = UPPER('DBLINK') and username = UPPER('test');
-- 查看所有用户
select * from all_users a where a.username = 'DBLINK';
-- 参看表空间
select * from dba_tablespaces where TABLESPACE_NAME='DBLINK';
-- 删除db连接
DROP PUBLIC DATABASE LINK O32dblink;

-- 创建用户前若在则删除
declare
	v_rowcount integer;
	v_sid number;
	v_serialno number;
	v_sql VARCHAR(2000);
	CURSOR cur_session is select SID,serial# from v$session where username='DBLINK';
begin
	select count(*)
	into v_rowcount
	from dual
	where exists
		(
			select * from all_users a where a.username = 'DBLINK'
		);
	if v_rowcount > 0 then
		-- 删除连接中的Session
  	OPEN cur_session;
		LOOP
		FETCH cur_session INTO v_sid,v_serialno;
		EXIT WHEN cur_session%NOTFOUND OR cur_session%NOTFOUND IS NULL;
		if v_serialno is not NULL and v_sid is not NULL then
			v_sql := 'alter system kill session '||''''||v_sid||','||v_serialno||'''';
			execute immediate v_sql;
			v_sql := 'ALTER SYSTEM DISCONNECT SESSION ' || ''''||v_sid||','||v_serialno||''''|| ' IMMEDIATE';
			execute immediate v_sql;
		end if;
		END LOOP;
		close cur_session;
		execute immediate 'DROP USER DBLINK CASCADE';
end if;
end;
/

-- 创建表空间
declare
	v_rowcount integer;
begin
	select count(*)
	into v_rowcount
	from dual
	where exists
		(
			select * from dba_tablespaces where TABLESPACE_NAME='DBLINK'
		);
	if v_rowcount > 0 then
		execute immediate 'drop tablespace DBLINK including contents and datafiles';
	end if;
end;
/

