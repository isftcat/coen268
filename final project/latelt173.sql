-- MySQL dump 10.13  Distrib 5.7.21, for Win32 (AMD64)
--
-- Host: localhost    Database: woofdb
-- ------------------------------------------------------
-- Server version	5.7.21-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `dog_details`
--

DROP TABLE IF EXISTS `dog_details`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dog_details` (
  `dog_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  `DOB` date DEFAULT NULL,
  `pic` varchar(100) DEFAULT NULL,
  `owner_id` int(11) DEFAULT NULL,
  `breed` varchar(50) DEFAULT NULL,
  `gender` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`dog_id`),
  KEY `owner_id` (`owner_id`),
  CONSTRAINT `dog_details_ibfk_1` FOREIGN KEY (`owner_id`) REFERENCES `owner_details` (`owner_id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dog_details`
--

LOCK TABLES `dog_details` WRITE;
/*!40000 ALTER TABLE `dog_details` DISABLE KEYS */;
INSERT INTO `dog_details` VALUES (1,'dog1','2018-02-01','a.jpg',1,'breed1',NULL),(2,'dog 2','2018-02-11','b.jpg',2,'breed2',NULL),(3,'dog 3','2018-03-13','b.jpg',3,'breed3',NULL),(4,'dog 4','2018-04-14','a.jpg',4,'breed4',NULL),(5,'dog 5','2018-05-15','aa.jpg',5,'breed5',NULL),(9,NULL,NULL,NULL,NULL,NULL,NULL),(10,NULL,NULL,NULL,NULL,NULL,NULL),(11,NULL,NULL,NULL,NULL,NULL,NULL),(12,NULL,NULL,NULL,NULL,NULL,NULL),(13,NULL,NULL,NULL,NULL,NULL,NULL),(14,NULL,NULL,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `dog_details` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dog_pics`
--

DROP TABLE IF EXISTS `dog_pics`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dog_pics` (
  `dog_pic_id` int(11) NOT NULL AUTO_INCREMENT,
  `dog_id` int(11) DEFAULT NULL,
  `pic` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`dog_pic_id`),
  KEY `dog_id` (`dog_id`),
  CONSTRAINT `dog_pics_ibfk_1` FOREIGN KEY (`dog_id`) REFERENCES `dog_details` (`dog_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dog_pics`
--

LOCK TABLES `dog_pics` WRITE;
/*!40000 ALTER TABLE `dog_pics` DISABLE KEYS */;
/*!40000 ALTER TABLE `dog_pics` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mate_info`
--

DROP TABLE IF EXISTS `mate_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `mate_info` (
  `mate_info_id` int(11) NOT NULL AUTO_INCREMENT,
  `dog_id` int(11) DEFAULT NULL,
  `mate_date` date DEFAULT NULL,
  `dog_id_2` int(11) DEFAULT NULL,
  PRIMARY KEY (`mate_info_id`),
  KEY `dog_id` (`dog_id`),
  KEY `dog_id_2` (`dog_id_2`),
  CONSTRAINT `mate_info_ibfk_1` FOREIGN KEY (`dog_id`) REFERENCES `dog_details` (`dog_id`),
  CONSTRAINT `mate_info_ibfk_2` FOREIGN KEY (`dog_id_2`) REFERENCES `dog_details` (`dog_id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mate_info`
--

LOCK TABLES `mate_info` WRITE;
/*!40000 ALTER TABLE `mate_info` DISABLE KEYS */;
INSERT INTO `mate_info` VALUES (1,1,'2018-02-05',2),(2,1,'2018-03-03',3),(5,2,'2018-03-02',3),(6,1,'2018-03-15',NULL),(7,1,'2018-03-19',NULL),(12,4,'2018-03-18',NULL),(13,4,'2018-03-22',NULL),(14,3,'2018-03-21',NULL);
/*!40000 ALTER TABLE `mate_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mate_req`
--

DROP TABLE IF EXISTS `mate_req`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `mate_req` (
  `mate_req_id` int(11) NOT NULL AUTO_INCREMENT,
  `req_id` int(11) DEFAULT NULL,
  `mate_req_date` date DEFAULT NULL,
  `dog_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`mate_req_id`),
  KEY `dog_id` (`dog_id`),
  KEY `req_id` (`req_id`),
  CONSTRAINT `mate_req_ibfk_1` FOREIGN KEY (`dog_id`) REFERENCES `dog_details` (`dog_id`),
  CONSTRAINT `mate_req_ibfk_2` FOREIGN KEY (`req_id`) REFERENCES `mate_info` (`mate_info_id`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mate_req`
--

LOCK TABLES `mate_req` WRITE;
/*!40000 ALTER TABLE `mate_req` DISABLE KEYS */;
INSERT INTO `mate_req` VALUES (24,13,'2018-12-04',3),(25,12,'2018-03-16',2),(26,12,'2018-03-16',2),(27,12,'2018-03-16',2),(28,12,'2018-03-16',2),(29,12,'2018-03-16',2),(30,12,'2018-03-16',2);
/*!40000 ALTER TABLE `mate_req` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `owner_details`
--

DROP TABLE IF EXISTS `owner_details`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `owner_details` (
  `owner_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  `address` varchar(150) DEFAULT NULL,
  `city` varchar(50) DEFAULT NULL,
  `state` varchar(50) DEFAULT NULL,
  `country` varchar(50) DEFAULT NULL,
  `profilepic` varchar(100) DEFAULT NULL,
  `owner_email` varchar(20) DEFAULT NULL,
  `owner_mobile` varchar(20) DEFAULT NULL,
  `password` varchar(20) DEFAULT NULL,
  `zipcode` int(20) DEFAULT NULL,
  `token` varchar(65000) DEFAULT NULL,
  PRIMARY KEY (`owner_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `owner_details`
--

LOCK TABLES `owner_details` WRITE;
/*!40000 ALTER TABLE `owner_details` DISABLE KEYS */;
INSERT INTO `owner_details` VALUES (1,'owner1','address1 ','95050','state1','country1','1.jpg','email1','mobile1','pass2',NULL,'deL74ZmeWEY:APA91bG7vnVULxRWTbmlLjLf4RRS5FO6QajXLWHgrYmse0dByRLbmk-6eURdJWPeGaceYk55W-pSf4eR3PXtleP8iCpzL3GIjLHPMqkgJ2l18NwaeSW0Juj9gxePatV0n0c6hH_cYCtB'),(2,'owner2','address2','95050','state2',NULL,'1.jpg','email2','obile2','pass2',NULL,'deL74ZmeWEY:APA91bG7vnVULxRWTbmlLjLf4RRS5FO6QajXLWHgrYmse0dByRLbmk-6eURdJWPeGaceYk55W-pSf4eR3PXtleP8iCpzL3GIjLHPMqkgJ2l18NwaeSW0Juj9gxePatV0n0c6hH_cYCtB'),(3,'owner3','address3','95050','state3','country3','3.jpg','email3','mobile3','pass2',NULL,'deL74ZmeWEY:APA91bG7vnVULxRWTbmlLjLf4RRS5FO6QajXLWHgrYmse0dByRLbmk-6eURdJWPeGaceYk55W-pSf4eR3PXtleP8iCpzL3GIjLHPMqkgJ2l18NwaeSW0Juj9gxePatV0n0c6hH_cYCtB'),(4,'owner4','address4','95050','state4','country4','4.jpg','email4','mobile4','pass2',NULL,'deL74ZmeWEY:APA91bG7vnVULxRWTbmlLjLf4RRS5FO6QajXLWHgrYmse0dByRLbmk-6eURdJWPeGaceYk55W-pSf4eR3PXtleP8iCpzL3GIjLHPMqkgJ2l18NwaeSW0Juj9gxePatV0n0c6hH_cYCtB'),(5,'owner5','address5','95050','state5','country5','5.jpg','email5','mobile5','pass2',NULL,'deL74ZmeWEY:APA91bG7vnVULxRWTbmlLjLf4RRS5FO6QajXLWHgrYmse0dByRLbmk-6eURdJWPeGaceYk55W-pSf4eR3PXtleP8iCpzL3GIjLHPMqkgJ2l18NwaeSW0Juj9gxePatV0n0c6hH_cYCtB'),(6,'owner6','address6','95050','state6','country6','6.jpg','email6','mobile6','pass2',123,'deL74ZmeWEY:APA91bG7vnVULxRWTbmlLjLf4RRS5FO6QajXLWHgrYmse0dByRLbmk-6eURdJWPeGaceYk55W-pSf4eR3PXtleP8iCpzL3GIjLHPMqkgJ2l18NwaeSW0Juj9gxePatV0n0c6hH_cYCtB');
/*!40000 ALTER TABLE `owner_details` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `walk_info`
--

DROP TABLE IF EXISTS `walk_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `walk_info` (
  `walk_info_id` int(11) NOT NULL AUTO_INCREMENT,
  `walk_info_date` date DEFAULT NULL,
  `dog_id` int(11) DEFAULT NULL,
  `walker_id` int(11) DEFAULT NULL,
  `from_time` varchar(30) DEFAULT NULL,
  `to_time` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`walk_info_id`),
  KEY `dog_id` (`dog_id`),
  KEY `walker_id` (`walker_id`),
  CONSTRAINT `walk_info_ibfk_1` FOREIGN KEY (`dog_id`) REFERENCES `dog_details` (`dog_id`),
  CONSTRAINT `walk_info_ibfk_2` FOREIGN KEY (`walker_id`) REFERENCES `owner_details` (`owner_id`)
) ENGINE=InnoDB AUTO_INCREMENT=53 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `walk_info`
--

LOCK TABLES `walk_info` WRITE;
/*!40000 ALTER TABLE `walk_info` DISABLE KEYS */;
INSERT INTO `walk_info` VALUES (1,'2018-02-01',1,2,'2018-03-16 00:00:00','2018-03-16 00:00:00'),(30,'2018-02-01',1,2,'2018-03-16 00:00:00','2018-03-16 00:00:00'),(37,'2018-03-16',1,NULL,'10:10','22:20'),(38,'2018-03-17',2,NULL,'11:00','22:20'),(40,'2018-03-17',2,NULL,'5:15','20:15'),(42,'2018-03-17',2,4,'5:18','20:18'),(46,'2018-03-24',2,NULL,'3:20','6:38'),(50,'2018-03-29',2,NULL,'12:23','14:23'),(51,'2018-03-17',2,NULL,'5:33','6:33'),(52,'2018-03-17',2,NULL,'6:37','9:37');
/*!40000 ALTER TABLE `walk_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `walk_req`
--

DROP TABLE IF EXISTS `walk_req`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `walk_req` (
  `walk_req_id` int(11) NOT NULL AUTO_INCREMENT,
  `walk_req_date` date DEFAULT NULL,
  `req_id` int(11) DEFAULT NULL,
  `walker_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`walk_req_id`),
  KEY `req_id` (`req_id`),
  KEY `walker_id` (`walker_id`),
  CONSTRAINT `walk_req_ibfk_1` FOREIGN KEY (`req_id`) REFERENCES `walk_info` (`walk_info_id`),
  CONSTRAINT `walk_req_ibfk_2` FOREIGN KEY (`walker_id`) REFERENCES `owner_details` (`owner_id`)
) ENGINE=InnoDB AUTO_INCREMENT=165 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `walk_req`
--

LOCK TABLES `walk_req` WRITE;
/*!40000 ALTER TABLE `walk_req` DISABLE KEYS */;
INSERT INTO `walk_req` VALUES (149,'2018-03-16',42,4),(150,'2018-03-16',42,3),(151,'2018-03-16',42,5),(164,'2018-03-17',37,2);
/*!40000 ALTER TABLE `walk_req` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-03-17  5:52:09
