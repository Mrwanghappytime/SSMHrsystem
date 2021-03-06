create database hrsystem1;
use hrsystem1;
DROP TABLE IF EXISTS `application`;
SET character_set_client = utf8mb4 ;
CREATE TABLE `application` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `attend_id` int(11) DEFAULT NULL,
  `reason` varchar(255) DEFAULT NULL,
  `result` tinyint(1) DEFAULT NULL,
  `attendtype_id` int(11) DEFAULT NULL,
  `results` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `attend_id` (`attend_id`),
  KEY `a1_idx` (`attend_id`),
  KEY `a2_idx` (`attendtype_id`),
  CONSTRAINT `FK_DNO` FOREIGN KEY (`attend_id`) REFERENCES `attend` (`id`),
  CONSTRAINT `a2` FOREIGN KEY (`attendtype_id`) REFERENCES `attendtype` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

UNLOCK TABLES;

DELIMITER ;;

DELIMITER ;


DROP TABLE IF EXISTS `application_view`;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8mb4;
SET character_set_client = @saved_cs_client;


DROP TABLE IF EXISTS `attend`;
SET character_set_client = utf8mb4 ;
CREATE TABLE `attend` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `duty_date` date DEFAULT NULL,
  `punch_time` varchar(9) DEFAULT NULL,
  `commed` tinyint(1) DEFAULT NULL,
  `attendtype_id` int(11) DEFAULT NULL,
  `emp_id` int(11) unsigned NOT NULL,
  `isman` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `a4_idx` (`attendtype_id`),
  KEY `a5_idx` (`emp_id`),
  CONSTRAINT `a4` FOREIGN KEY (`attendtype_id`) REFERENCES `attendtype` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20143 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

LOCK TABLES `attend` WRITE;
UNLOCK TABLES;



DROP TABLE IF EXISTS `attendtype`;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `attendtype` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `amerce` double NOT NULL,
  `name` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


LOCK TABLES `attendtype` WRITE;
INSERT INTO `attendtype` VALUES (1,0,'正常'),(2,-20,'事假'),(3,-10,'病假'),(4,-10,'迟到'),(5,-10,'早退'),(6,-30,'旷工'),(7,10,'出差');
UNLOCK TABLES;

DROP TABLE IF EXISTS `checkback`;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `checkback` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `application_id` int(11) NOT NULL,
  `result` tinyint(1) NOT NULL,
  `reason` varchar(45) DEFAULT NULL,
  `manager_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `a6_idx` (`application_id`),
  KEY `a7_idx` (`manager_id`),
  CONSTRAINT `a6` FOREIGN KEY (`application_id`) REFERENCES `application` (`id`),
  CONSTRAINT `a7` FOREIGN KEY (`manager_id`) REFERENCES `manager` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


LOCK TABLES `checkback` WRITE;
UNLOCK TABLES;


DROP TABLE IF EXISTS `employee`;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `employee` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `salary` double NOT NULL,
  `manager_id` int(11) DEFAULT NULL,
  `email` varchar(36) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `emp_name` (`name`),
  KEY `a8_idx` (`manager_id`),
  CONSTRAINT `a8` FOREIGN KEY (`manager_id`) REFERENCES `manager` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

LOCK TABLES `employee` WRITE;
INSERT INTO `employee` VALUES (3,'mysql','mysql',3000,1,'xxxxx@qq.com'),(4,'hsql','hsql',3200,1,'xxxxx@qq.com'),(5,'tomcat','tomcat',2800,2,'xxxxx@qq.com'),(6,'jetty','jetty',2560,2,'xxxxx@qq.com'),(9,'aaa','aaaa',1500,1,'xxxxx@qq.com'),(10,'jjj','jjjj',1500,1,'xxxxx@qq.com');
UNLOCK TABLES;

DROP TABLE IF EXISTS `manager`;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `manager` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `depth` varchar(45) DEFAULT NULL,
  `name` varchar(45) DEFAULT NULL,
  `password` varchar(45) DEFAULT NULL,
  `salary` double DEFAULT NULL,
  `email` varchar(36) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


LOCK TABLES `manager` WRITE;
INSERT INTO `manager` VALUES (1,'java','oracle','oracle',6000,"xxxxx@qq.com"),(2,'.net','work','work',6000,'xxxxx@qq.com');
UNLOCK TABLES;



DROP TABLE IF EXISTS `payment`;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `payment` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `month` int(11) DEFAULT NULL,
  `amount` double NOT NULL,
  `employee` tinyint(4) NOT NULL,
  `eid` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=62773 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;




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


