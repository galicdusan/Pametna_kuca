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

-- Dump completed on 2019-02-13  2:20:11
