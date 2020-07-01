create or replace procedure delete_member_auto
is
begin
	delete
	from
		personal_project_member
	where
		sysdate-deletedate > 7;
	commit;
end;

--프로시저 생성

variable jobno number;
begin
	dbms_job.submit(
		:jobno,
		(delete_member_auto;),
		sysdate,
		'sysdate+10/24/60/60'
	);
	commit;
end;
-- jobno으로 job번호 주고 10초마다 해당 프로시저를 반복해서 진행하도록 함.

BEGIN
    DBMS_JOB.BROKEN(1, FALSE); -- 비활성화
    COMMIT;
END;
--프로시저 비활성화