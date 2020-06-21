SELECT rn, consult_id, category, send, recipient, title, cons_date, today_date, accept, read
				FROM (SELECT rownum rn, consult_id, category, send, recipient, title, cons_date, today_date, accept, read
				FROM (SELECT * from consult WHERE send = ? ORDER BY consult_id desc)
				WHERE rownum <= ? order by consult_id desc )
				WHERE rn > ?;
				

				
SELECT rn, consult_id, category, send, recipient, title, cons_date, today_date, accept, read FROM (SELECT rownum rn, consult_id, category, send, recipient, title, cons_date, today_date, accept, read FROM (select * from consult where recipient = 's20160000' ORDER BY today_date desc) WHERE rownum <= 5 order by consult_id desc) WHERE rn > 0;

select * from consult where recipient = 'P11111111 ' order by consult_id desc;
select * from consult where send = 'P11111111 ' order by consult_id desc;

select prof_id, day, time, title, cons_date from p_schedule where prof_id = 'P11111111 ' and cons_date > sysdate;