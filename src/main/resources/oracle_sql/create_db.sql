-- 创建用户前若在则删除
declare
  v_rowcount integer;
  v_sid number;
  v_serialno number;
  v_sql VARCHAR(2000);
  CURSOR cur_session is select SID,serial# from v$session where username='test';

begin
  select count(*)
  into v_rowcount
  from dual
  where exists
    (
      select * from all_users a where a.username = 'test'
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
    execute immediate 'DROP USER test CASCADE';
  end if;
end;
/

-- 创建表空间
declare
  v_rowcount integer;
  v_path VARCHAR(256);
  v_sql VARCHAR(2000);
begin
  select count(*)
  into v_rowcount
  from dual
  where exists
    (
      select * from dba_tablespaces where TABLESPACE_NAME='test'
    );
  if v_rowcount > 0 then
    execute immediate 'drop tablespace test including contents and datafiles';
  end if;
  select CONCAT(substr(name,1,instr(name,'/',-1)),'test.dbf') into v_path from v$tempfile;
  dbms_output.put_line('v_path:'||v_path||'');
  if v_path is not NULL then 
    v_sql := 'create tablespace test datafile '''||v_path||''' size 500M extent management local segment space management auto';
    dbms_output.put_line('v_sql1:'||v_sql||'');
    execute immediate v_sql;
    v_sql := 'ALTER DATABASE DATAFILE '''||v_path||''' AUTOEXTEND ON';
	dbms_output.put_line('v_sql2:'||v_sql||'');
    execute immediate v_sql;
    v_sql :=  'ALTER DATABASE DATAFILE '''||v_path||''' AUTOEXTEND ON NEXT 500M';
	dbms_output.put_line('v_sql3:'||v_sql||'');
    execute immediate v_sql;
  end if; 
end;
/

-- 创建用户 
CREATE USER test IDENTIFIED BY password default tablespace test TEMPORARY TABLESPACE TEMP;
-- 创建数据库会话
grant create session to test;
-- 授权表
grant create any table to test;
-- 授权视图
grant create any view to test;
-- 授权存储过程
grant create any procedure to test;
-- 授权资源
grant resource to test;
-- 所有权限
grant all privileges to test;