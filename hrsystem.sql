create database hrsystem1;
use hrsystem1;
DROP TABLE IF EXISTS `application`;
SET character_set_client = utf8mb4 ;
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
  CONSTRAINT `a4` FOREIGN KEY (`attendtype_id`) REFERENCES `attendtype` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20143 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

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



