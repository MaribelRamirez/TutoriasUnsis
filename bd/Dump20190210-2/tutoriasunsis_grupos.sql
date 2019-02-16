CREATE DATABASE  IF NOT EXISTS `tutoriasunsis` /*!40100 DEFAULT CHARACTER SET utf8 COLLATE utf8_spanish_ci */;
USE `tutoriasunsis`;
-- MySQL dump 10.13  Distrib 5.6.17, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: tutoriasunsis
-- ------------------------------------------------------
-- Server version	5.6.40-log

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
-- Table structure for table `grupos`
--

DROP TABLE IF EXISTS `grupos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `grupos` (
  `idGrupo` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `grupo` varchar(10) DEFAULT NULL,
  `idPeriodo` int(11) DEFAULT NULL,
  `idLicenciatura` int(11) DEFAULT NULL,
  PRIMARY KEY (`idGrupo`)
) ENGINE=InnoDB AUTO_INCREMENT=75 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `grupos`
--

LOCK TABLES `grupos` WRITE;
/*!40000 ALTER TABLE `grupos` DISABLE KEYS */;
INSERT INTO `grupos` VALUES (1,'101',1,1),(2,'301',1,1),(3,'501',1,1),(4,'701',1,1),(5,'901',1,1),(6,'103-A',1,2),(7,'103-B',1,2),(8,'103-C',1,2),(9,'103-D',1,2),(10,'103-E',1,2),(11,'103-F',1,2),(12,'103-G',1,2),(13,'103-H',1,2),(14,'103-I',1,2),(15,'303-A',1,2),(16,'303-B',1,2),(17,'303-C',1,2),(18,'303-D',1,2),(19,'303-E',1,2),(20,'303-F',1,2),(21,'303-G',1,2),(22,'303-H',1,2),(23,'503-A',1,2),(24,'503-B',1,2),(25,'503-C',1,2),(26,'503-D',1,2),(27,'503-E',1,2),(28,'703-A',1,2),(29,'703-B',1,2),(30,'703-C',1,2),(31,'703-D',1,2),(32,'703-E',1,2),(33,'903-A',1,2),(34,'903-B',1,2),(35,'903-C',1,2),(36,'903-D',1,2),(37,'903-E',1,2),(38,'104-A',1,3),(39,'104-B',1,3),(40,'104-C',1,3),(41,'304',1,3),(42,'504',1,3),(43,'704',1,3),(44,'904',1,3),(45,'105',1,4),(46,'305',1,4),(47,'505',1,4),(48,'705',1,4),(49,'905',1,4),(50,'106-A',1,5),(51,'106-B',1,5),(52,'306',1,5),(53,'506',1,5),(54,'706',1,5),(55,'906',1,5),(56,'107-A',1,6),(57,'107-B',1,6),(58,'307',1,6),(59,'507-A',1,6),(60,'507-B',1,6),(61,'707-A',1,6),(62,'707-B',1,6),(63,'907',1,6),(64,'113-A',1,7),(65,'113-B',1,7),(66,'113-C',1,7),(67,'313-A',1,7),(68,'313-B',1,7),(69,'114-A',1,8),(70,'114-B',1,8),(71,'114-C',1,8),(72,'114-D',1,8),(73,'114-E',1,8),(74,'114-F',1,8);
/*!40000 ALTER TABLE `grupos` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-02-10 15:15:25
