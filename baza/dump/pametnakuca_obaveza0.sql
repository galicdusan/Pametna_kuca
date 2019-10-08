-- MySQL dump 10.13  Distrib 8.0.13, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: pametnakuca
-- ------------------------------------------------------
-- Server version	8.0.13

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `obaveza`
--

DROP TABLE IF EXISTS `obaveza`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `obaveza` (
  `obaveza_id` int(11) NOT NULL AUTO_INCREMENT,
  `vreme_pocetka` datetime NOT NULL,
  `naziv` varchar(45) NOT NULL,
  `idD` int(11) DEFAULT NULL,
  `idA` int(11) DEFAULT NULL,
  PRIMARY KEY (`obaveza_id`),
  KEY `destinacija_id_idx` (`idD`),
  KEY `alarm_id_idx` (`idA`),
  CONSTRAINT `alarm_id` FOREIGN KEY (`idA`) REFERENCES `alarm` (`alarm_id`),
  CONSTRAINT `destinacija_id` FOREIGN KEY (`idD`) REFERENCES `destinacija` (`destinacija_id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `obaveza`
--

LOCK TABLES `obaveza` WRITE;
/*!40000 ALTER TABLE `obaveza` DISABLE KEYS */;
INSERT INTO `obaveza` VALUES (1,'2020-05-13 09:00:00','Bacanje smeca',13,1),(3,'2019-02-11 19:00:00','Setanje parkom',2,2),(4,'2019-02-11 19:30:00','Poslovni rucak',3,4),(5,'2019-02-11 21:00:00','Gledanje sajma',9,5),(6,'2019-02-11 22:00:00','Shopping',1,3),(7,'2019-02-12 17:00:00','Joga',10,10),(8,'2019-02-12 23:25:00','Ispracaj na autobusku stanicu',12,11);
/*!40000 ALTER TABLE `obaveza` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-02-13  2:20:12
