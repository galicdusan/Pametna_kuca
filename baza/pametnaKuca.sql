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
-- Table structure for table `alarm`
--

DROP TABLE IF EXISTS `alarm`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `alarm` (
  `alarm_id` int(11) NOT NULL AUTO_INCREMENT,
  `vreme_aktivacije` datetime NOT NULL,
  `perioda` int(11) DEFAULT NULL,
  `idP` int(11) NOT NULL,
  PRIMARY KEY (`alarm_id`),
  KEY `pesma_id_idx` (`idP`),
  CONSTRAINT `FK_pesma_id` FOREIGN KEY (`idP`) REFERENCES `pesma` (`pesma_id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `alarm`
--

LOCK TABLES `alarm` WRITE;
/*!40000 ALTER TABLE `alarm` DISABLE KEYS */;
INSERT INTO `alarm` VALUES (1,'2019-02-11 17:00:00',NULL,1),(2,'2019-02-11 18:00:00',NULL,1),(3,'2019-02-11 17:30:00',3600,2),(4,'2019-02-11 19:00:00',300,3),(5,'2019-02-11 19:30:00',NULL,4),(6,'2019-02-12 14:50:00',0,13),(7,'2019-02-12 15:06:00',0,14),(8,'2019-02-12 15:09:00',0,14),(9,'2019-02-12 16:25:00',0,14),(10,'2019-02-12 17:02:00',0,14),(11,'2019-02-12 23:21:00',0,14);
/*!40000 ALTER TABLE `alarm` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `destinacija`
--

DROP TABLE IF EXISTS `destinacija`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `destinacija` (
  `destinacija_id` int(11) NOT NULL AUTO_INCREMENT,
  `x_koordinata` decimal(11,8) NOT NULL,
  `y_koordinata` decimal(11,8) NOT NULL,
  `naziv` varchar(45) NOT NULL,
  PRIMARY KEY (`destinacija_id`),
  UNIQUE KEY `naziv_UNIQUE` (`naziv`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `destinacija`
--

LOCK TABLES `destinacija` WRITE;
/*!40000 ALTER TABLE `destinacija` DISABLE KEYS */;
INSERT INTO `destinacija` VALUES (1,5.00000000,5.00000000,'Big Fashion'),(2,-1.00000000,0.00000000,'Cuburski park'),(3,-6.00000000,2.00000000,'Hotel Hajat'),(4,-3.00000000,5.00000000,'Terazije'),(5,-5.00000000,8.00000000,'Kalemegdan'),(6,4.00000000,2.00000000,'Zvezdarska Suma'),(7,6.00000000,0.00000000,'Mirijevo'),(8,1.00000000,-2.00000000,'Sumice'),(9,-6.00000000,-1.00000000,'Sajam'),(10,8.00000000,0.00000000,'Lesce'),(11,6.00000000,5.00000000,'Visnjicka Banja'),(12,6.00000000,-4.00000000,'Mali Mokri Lug'),(13,200.00000000,300.00000000,'Akapulko');
/*!40000 ALTER TABLE `destinacija` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `korisnik`
--

DROP TABLE IF EXISTS `korisnik`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `korisnik` (
  `korisnik_id` int(11) NOT NULL AUTO_INCREMENT,
  `ime` varchar(45) NOT NULL,
  PRIMARY KEY (`korisnik_id`),
  UNIQUE KEY `ime_UNIQUE` (`ime`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `korisnik`
--

LOCK TABLES `korisnik` WRITE;
/*!40000 ALTER TABLE `korisnik` DISABLE KEYS */;
INSERT INTO `korisnik` VALUES (1,'Dusan'),(3,'Marija'),(4,'Milica'),(6,'Milos'),(5,'Sinisa'),(2,'Zarko');
/*!40000 ALTER TABLE `korisnik` ENABLE KEYS */;
UNLOCK TABLES;

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

--
-- Table structure for table `pesma`
--

DROP TABLE IF EXISTS `pesma`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `pesma` (
  `pesma_id` int(11) NOT NULL AUTO_INCREMENT,
  `naziv` varchar(45) NOT NULL,
  PRIMARY KEY (`pesma_id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pesma`
--

LOCK TABLES `pesma` WRITE;
/*!40000 ALTER TABLE `pesma` DISABLE KEYS */;
INSERT INTO `pesma` VALUES (1,'Toma Zdravkovic - Prokleta nedelja (1973)'),(2,'Electric Wizard - I the witchfinder'),(3,'Melvins-A History Of Bad Men'),(4,'Alice In Chains - Would?'),(5,'Slowdive - Souvlaki Space Station'),(6,'Daft Punk- Ouverture'),(11,'Daft Punk- Ouverture'),(13,'Toma Zdravkovic - Prokleta nedelja'),(14,'Toma Zdravkovic - Prokleta nedelja');
/*!40000 ALTER TABLE `pesma` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `slusao`
--

DROP TABLE IF EXISTS `slusao`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `slusao` (
  `slusao_id` int(11) NOT NULL AUTO_INCREMENT,
  `idP` int(11) NOT NULL,
  `idK` int(11) NOT NULL,
  PRIMARY KEY (`slusao_id`),
  KEY `pesma_id_idx` (`idP`) /*!80000 INVISIBLE */,
  KEY `korisnik_id_idx` (`idK`),
  CONSTRAINT `korisnik_id` FOREIGN KEY (`idK`) REFERENCES `korisnik` (`korisnik_id`),
  CONSTRAINT `pesma_id` FOREIGN KEY (`idP`) REFERENCES `pesma` (`pesma_id`)
) ENGINE=InnoDB AUTO_INCREMENT=93 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `slusao`
--

LOCK TABLES `slusao` WRITE;
/*!40000 ALTER TABLE `slusao` DISABLE KEYS */;
INSERT INTO `slusao` VALUES (1,1,2),(2,2,1),(3,3,1),(4,4,4),(5,5,3),(6,13,1),(7,14,1),(8,14,1),(9,14,1),(10,14,1),(11,14,1),(12,14,1),(13,14,1),(14,14,1),(15,14,1),(16,14,1),(17,14,1),(18,14,1),(19,14,1),(20,14,1),(21,14,1),(22,14,1),(23,14,1),(24,14,1),(25,14,1),(26,14,1),(27,14,1),(28,14,1),(29,14,1),(30,14,1),(31,14,1),(32,14,1),(33,14,1),(34,14,1),(35,14,1),(36,14,1),(37,14,1),(38,14,1),(39,14,1),(40,14,1),(41,14,1),(42,14,1),(43,14,1),(44,14,1),(45,14,1),(46,14,1),(47,14,1),(48,14,1),(49,14,1),(50,14,1),(51,14,1),(52,14,1),(53,14,1),(54,14,1),(55,14,1),(56,14,1),(57,14,1),(58,14,1),(59,14,1),(60,14,1),(61,14,1),(62,14,1),(63,14,1),(64,14,1),(65,14,1),(66,14,1),(67,14,1),(68,14,1),(69,14,1),(70,14,1),(71,14,1),(72,14,1),(73,14,1),(74,14,1),(75,14,1),(76,14,1),(77,14,1),(78,14,1),(79,14,1),(80,14,1),(81,14,1),(82,14,1),(83,14,1),(84,14,1),(85,14,1),(86,14,1),(87,14,1),(88,14,1),(89,14,1),(90,14,1),(91,14,1),(92,14,1);
/*!40000 ALTER TABLE `slusao` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-02-13  2:19:38
