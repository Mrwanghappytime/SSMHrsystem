

drop procedure  if exists  updateAttendauto;
delimiter //
create procedure updateAttendAuto()
BEGIN
	DECLARE s int DEFAULT 0;
    DECLARE em_id int;
    DECLARE counts int;
    
	declare report cursor for select id from employee;
    declare report1 cursor for select id from manager;
    declare continue handler for not found set s = 1;    
    open report;
		 fetch report into em_id;
		 while s!=1 do
			select count(*) into counts from attend where duty_date = DATE_SUB(CURDATE(),INTERVAL 1 DAY) and commed = 1 and isman = 0 and emp_id = em_id ;
            if counts < 1 then
				insert into attend(duty_date,punch_time,commed,attendtype_id,emp_id,isman) values(DATE_SUB(CURDATE(),INTERVAL 1 DAY),'08:00:00',true,6,em_id,false);
            end if;
            select count(*) into counts from attend where duty_date = DATE_SUB(CURDATE(),INTERVAL 1 DAY)  and commed = 0 and isman = 0 and emp_id = em_id ;
             if counts < 1 then
				insert into attend(duty_date,punch_time,commed,attendtype_id,emp_id,isman) values(DATE_SUB(CURDATE(),INTERVAL 1 DAY),'13:00:00',false,6,em_id,false);
            end if;
			fetch report into em_id;
		 end while;
    close report;
    set s = 0;
    open report1;
		fetch report1 into em_id;
         while s!=1 do
			select count(*) into counts from attend where duty_date = DATE_SUB(CURDATE(),INTERVAL 1 DAY) and commed = 1 and isman = 1 and emp_id = em_id ;
            if counts < 1 then
				insert into attend(duty_date,punch_time,commed,attendtype_id,emp_id,isman) values(DATE_SUB(CURDATE(),INTERVAL 1 DAY),'08:00:00',true,6,em_id,true);
            end if;
            select count(*) into counts from attend where duty_date = DATE_SUB(CURDATE(),INTERVAL 1 DAY)  and commed = 0 and isman = 1 and emp_id = em_id ;
             if counts < 1 then
				insert into attend(duty_date,punch_time,commed,attendtype_id,emp_id,isman) values(DATE_SUB(CURDATE(),INTERVAL 1 DAY),'13:00:00',false,6,em_id,true);
            end if;
			fetch report1 into em_id;
		 end while;
    close report1; 
	update attend set attendtype_id = 1 where duty_date = DATE_SUB(CURDATE(),INTERVAL 1 DAY) and punch_time <= '09:00:00' and commed = 1 and attendtype_id != 6 ;
    update attend set attendtype_id = 4 where duty_date = DATE_SUB(CURDATE(),INTERVAL 1 DAY) and punch_time > '09:00:00' and punch_time < '11:00:00' and commed = 1 and attendtype_id != 6 ;
    update attend set attendtype_id = 5 where duty_date = DATE_SUB(CURDATE(),INTERVAL 1 DAY) and punch_time >= '11:00:00' and punch_time < '12:00:00' and commed = 0 and attendtype_id != 6 ;
    update attend set attendtype_id = 1 where duty_date = DATE_SUB(CURDATE(),INTERVAL 1 DAY) and punch_time >= '12:00:00' and commed = 0 and attendtype_id != 6 ;
END //
drop procedure if exists updateSalaryAuto;
create procedure updateSalaryAuto()
BEGIN
	DECLARE s int DEFAULT 0;
	DECLARE em_id int;
	DECLARE salarys double;
	DECLARE payments double;
	declare payment1 cursor for select id ,salary from employee;
	declare payment2 cursor for select id ,salary from manager;
	declare continue handler for not found set s = 1;
	open payment1;
		fetch payment1 into em_id ,salarys;
		repeat
			select sum(b.amerce) into payments   from attend as a left outer join attendtype as b on a.attendtype_id = b.id where a.emp_id = em_id and a.isman = false and  month(a.duty_date) = (month(curdate()) -1);
            if payments <> 0 then
				insert into payment(month,amount,employee,eid) values((month(curdate())-1),(salarys+payments),true,em_id); 
			else 
                insert into payment(month,amount,employee,eid) values((month(curdate())-1),salarys,true,em_id);
			end if;
            fetch payment1 into em_id ,salarys;
        until s = 1 end repeat;
	close payment1;
    
    
    set s = 0;
    
    
    open payment2;
		fetch payment2 into em_id ,salarys;
		repeat
			select sum(b.amerce) into payments   from attend as a left outer join attendtype as b on a.attendtype_id = b.id where a.emp_id = em_id and a.isman = true and  month(a.duty_date) = (month(curdate()) -1);
            if payments <> 0 then
				insert into payment(month,amount,employee,eid) values((month(curdate())-1),(salarys+payments),false,em_id); 
			else 
                insert into payment(month,amount,employee,eid) values((month(curdate())-1),salarys,false,em_id);
			end if;
            fetch payment2 into em_id ,salarys;
        until s = 1 end repeat;
    close payment2;
END //
delimiter ;
create trigger updateAttend after update on application for each row update attend set attendtype_id = old.attendtype_id where new.results = true;