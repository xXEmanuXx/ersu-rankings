/*M!999999\- enable the sandbox mode */ 
-- MariaDB dump 10.19-11.4.3-MariaDB, for Win64 (AMD64)
--
-- Host: localhost    Database: ranking_first_years_scholarship
-- ------------------------------------------------------
-- Server version	11.4.3-MariaDB

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*M!100616 SET @OLD_NOTE_VERBOSITY=@@NOTE_VERBOSITY, NOTE_VERBOSITY=0 */;

--
-- Current Database: `ranking_first_years_scholarship`
--

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `ranking_first_years_scholarship` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci */;

USE `ranking_first_years_scholarship`;

--
-- Table structure for table `participant`
--

DROP TABLE IF EXISTS `participant`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `participant` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `request_number` char(10) NOT NULL,
  `isee` decimal(7,2) DEFAULT NULL,
  `ispe` decimal(7,2) DEFAULT NULL,
  `score` decimal(5,2) NOT NULL,
  `notes` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `request_number` (`request_number`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `participant`
--

LOCK TABLES `participant` WRITE;
/*!40000 ALTER TABLE `participant` DISABLE KEYS */;
/*!40000 ALTER TABLE `participant` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'ranking_first_years_scholarship'
--
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
/*!50003 DROP PROCEDURE IF EXISTS `reset_rankings` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_unicode_ci */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `reset_rankings`()
BEGIN

delete from ranking_first_years_scholarship.participant;

alter table ranking_first_years_scholarship.participant auto_increment = 1;



delete from ranking_first_years_housing.participant;

alter table ranking_first_years_housing.participant auto_increment = 1;



delete from ranking_subsequent_years_scholarship.year;

alter table ranking_subsequent_years_scholarship.year auto_increment = 1;

delete from ranking_subsequent_years_scholarship.cdl;

alter table ranking_subsequent_years_scholarship.cdl auto_increment = 1;

alter table ranking_subsequent_years_scholarship.participant auto_increment = 1;



delete from ranking_subsequent_years_housing.year;

alter table ranking_subsequent_years_housing.year auto_increment = 1;

delete from ranking_subsequent_years_housing.cdl;

alter table ranking_subsequent_years_housing.cdl auto_increment = 1;

alter table ranking_subsequent_years_housing.participant auto_increment = 1;

END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Current Database: `ranking_first_years_housing`
--

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `ranking_first_years_housing` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci */;

USE `ranking_first_years_housing`;

--
-- Table structure for table `participant`
--

DROP TABLE IF EXISTS `participant`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `participant` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `request_number` char(10) NOT NULL,
  `isee` decimal(7,2) DEFAULT NULL,
  `ispe` decimal(7,2) DEFAULT NULL,
  `score` decimal(5,2) NOT NULL,
  `notes` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `request_number` (`request_number`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `participant`
--

LOCK TABLES `participant` WRITE;
/*!40000 ALTER TABLE `participant` DISABLE KEYS */;
/*!40000 ALTER TABLE `participant` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'ranking_first_years_housing'
--
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
/*!50003 DROP PROCEDURE IF EXISTS `reset_rankings` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_unicode_ci */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `reset_rankings`()
BEGIN

delete from ranking_first_years_scholarship.participant;

alter table ranking_first_years_scholarship.participant auto_increment = 1;



delete from ranking_first_years_housing.participant;

alter table ranking_first_years_housing.participant auto_increment = 1;



delete from ranking_subsequent_years_scholarship.year;

alter table ranking_subsequent_years_scholarship.year auto_increment = 1;

delete from ranking_subsequent_years_scholarship.cdl;

alter table ranking_subsequent_years_scholarship.cdl auto_increment = 1;

alter table ranking_subsequent_years_scholarship.participant auto_increment = 1;



delete from ranking_subsequent_years_housing.year;

alter table ranking_subsequent_years_housing.year auto_increment = 1;

delete from ranking_subsequent_years_housing.cdl;

alter table ranking_subsequent_years_housing.cdl auto_increment = 1;

alter table ranking_subsequent_years_housing.participant auto_increment = 1;

END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Current Database: `ranking_subsequent_years_scholarship`
--

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `ranking_subsequent_years_scholarship` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci */;

USE `ranking_subsequent_years_scholarship`;

--
-- Table structure for table `cdl`
--

DROP TABLE IF EXISTS `cdl`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cdl` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(128) NOT NULL,
  `type` varchar(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `cdl` (`name`,`type`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cdl`
--

LOCK TABLES `cdl` WRITE;
/*!40000 ALTER TABLE `cdl` DISABLE KEYS */;
/*!40000 ALTER TABLE `cdl` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cdl_year`
--

DROP TABLE IF EXISTS `cdl_year`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cdl_year` (
  `cdl` int(11) NOT NULL,
  `year` int(11) NOT NULL,
  PRIMARY KEY (`cdl`,`year`),
  KEY `cdl` (`cdl`),
  KEY `year` (`year`),
  CONSTRAINT `cdl_year_ibfk_1` FOREIGN KEY (`cdl`) REFERENCES `cdl` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `cdl_year_ibfk_2` FOREIGN KEY (`year`) REFERENCES `year` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cdl_year`
--

LOCK TABLES `cdl_year` WRITE;
/*!40000 ALTER TABLE `cdl_year` DISABLE KEYS */;
/*!40000 ALTER TABLE `cdl_year` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `participant`
--

DROP TABLE IF EXISTS `participant`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `participant` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `request_number` char(10) NOT NULL,
  `cfu` smallint(6) DEFAULT NULL,
  `average` decimal(4,2) DEFAULT NULL,
  `honors` tinyint(4) DEFAULT NULL,
  `bonus` tinyint(4) NOT NULL,
  `score` decimal(5,2) NOT NULL,
  `notes` varchar(255) DEFAULT NULL,
  `isee` decimal(7,2) DEFAULT NULL,
  `cdl` int(11) NOT NULL,
  `year` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `request_number` (`request_number`),
  KEY `cdl` (`cdl`),
  KEY `year` (`year`),
  CONSTRAINT `participant_ibfk_1` FOREIGN KEY (`cdl`) REFERENCES `cdl` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `participant_ibfk_2` FOREIGN KEY (`year`) REFERENCES `year` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `participant`
--

LOCK TABLES `participant` WRITE;
/*!40000 ALTER TABLE `participant` DISABLE KEYS */;
/*!40000 ALTER TABLE `participant` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `year`
--

DROP TABLE IF EXISTS `year`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `year` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `year` varchar(3) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `year` (`year`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `year`
--

LOCK TABLES `year` WRITE;
/*!40000 ALTER TABLE `year` DISABLE KEYS */;
/*!40000 ALTER TABLE `year` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'ranking_subsequent_years_scholarship'
--
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
/*!50003 DROP PROCEDURE IF EXISTS `reset_rankings` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_unicode_ci */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `reset_rankings`()
BEGIN

delete from ranking_first_years_scholarship.participant;

alter table ranking_first_years_scholarship.participant auto_increment = 1;



delete from ranking_first_years_housing.participant;

alter table ranking_first_years_housing.participant auto_increment = 1;



delete from ranking_subsequent_years_scholarship.year;

alter table ranking_subsequent_years_scholarship.year auto_increment = 1;

delete from ranking_subsequent_years_scholarship.cdl;

alter table ranking_subsequent_years_scholarship.cdl auto_increment = 1;

alter table ranking_subsequent_years_scholarship.participant auto_increment = 1;



delete from ranking_subsequent_years_housing.year;

alter table ranking_subsequent_years_housing.year auto_increment = 1;

delete from ranking_subsequent_years_housing.cdl;

alter table ranking_subsequent_years_housing.cdl auto_increment = 1;

alter table ranking_subsequent_years_housing.participant auto_increment = 1;

END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Current Database: `ranking_subsequent_years_housing`
--

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `ranking_subsequent_years_housing` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci */;

USE `ranking_subsequent_years_housing`;

--
-- Table structure for table `cdl`
--

DROP TABLE IF EXISTS `cdl`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cdl` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(128) NOT NULL,
  `type` varchar(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `cdl` (`name`,`type`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cdl`
--

LOCK TABLES `cdl` WRITE;
/*!40000 ALTER TABLE `cdl` DISABLE KEYS */;
/*!40000 ALTER TABLE `cdl` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cdl_year`
--

DROP TABLE IF EXISTS `cdl_year`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cdl_year` (
  `cdl` int(11) NOT NULL,
  `year` int(11) NOT NULL,
  PRIMARY KEY (`cdl`,`year`),
  KEY `cdl` (`cdl`),
  KEY `year` (`year`),
  CONSTRAINT `cdl_year_ibfk_1` FOREIGN KEY (`cdl`) REFERENCES `cdl` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `cdl_year_ibfk_2` FOREIGN KEY (`year`) REFERENCES `year` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cdl_year`
--

LOCK TABLES `cdl_year` WRITE;
/*!40000 ALTER TABLE `cdl_year` DISABLE KEYS */;
/*!40000 ALTER TABLE `cdl_year` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `participant`
--

DROP TABLE IF EXISTS `participant`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `participant` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `request_number` char(10) NOT NULL,
  `cfu` smallint(6) DEFAULT NULL,
  `average` decimal(4,2) DEFAULT NULL,
  `honors` tinyint(4) DEFAULT NULL,
  `bonus` tinyint(4) NOT NULL,
  `score` decimal(5,2) NOT NULL,
  `notes` varchar(255) DEFAULT NULL,
  `isee` decimal(7,2) DEFAULT NULL,
  `cdl` int(11) NOT NULL,
  `year` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `request_number` (`request_number`),
  KEY `cdl` (`cdl`),
  KEY `year` (`year`),
  CONSTRAINT `participant_ibfk_1` FOREIGN KEY (`cdl`) REFERENCES `cdl` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `participant_ibfk_2` FOREIGN KEY (`year`) REFERENCES `year` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `participant`
--

LOCK TABLES `participant` WRITE;
/*!40000 ALTER TABLE `participant` DISABLE KEYS */;
/*!40000 ALTER TABLE `participant` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `year`
--

DROP TABLE IF EXISTS `year`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `year` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `year` varchar(3) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `year` (`year`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `year`
--

LOCK TABLES `year` WRITE;
/*!40000 ALTER TABLE `year` DISABLE KEYS */;
/*!40000 ALTER TABLE `year` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'ranking_subsequent_years_housing'
--
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
/*!50003 DROP PROCEDURE IF EXISTS `reset_rankings` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_unicode_ci */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `reset_rankings`()
BEGIN

delete from ranking_first_years_scholarship.participant;

alter table ranking_first_years_scholarship.participant auto_increment = 1;



delete from ranking_first_years_housing.participant;

alter table ranking_first_years_housing.participant auto_increment = 1;



delete from ranking_subsequent_years_scholarship.year;

alter table ranking_subsequent_years_scholarship.year auto_increment = 1;

delete from ranking_subsequent_years_scholarship.cdl;

alter table ranking_subsequent_years_scholarship.cdl auto_increment = 1;

alter table ranking_subsequent_years_scholarship.participant auto_increment = 1;



delete from ranking_subsequent_years_housing.year;

alter table ranking_subsequent_years_housing.year auto_increment = 1;

delete from ranking_subsequent_years_housing.cdl;

alter table ranking_subsequent_years_housing.cdl auto_increment = 1;

alter table ranking_subsequent_years_housing.participant auto_increment = 1;

END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

CREATE USER IF NOT EXISTS 'emanu'@'%' IDENTIFIED WITH 'mysql_native_password' USING PASSWORD('balls123.');
GRANT ALL PRIVILEGES ON *.* TO 'emanu'@'%' REQUIRE NONE WITH GRANT OPTION MAX_QUERIES_PER_HOUR 0 MAX_CONNECTIONS_PER_HOUR 0 MAX_UPDATES_PER_HOUR 0 MAX_USER_CONNECTIONS 0;

/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*M!100616 SET NOTE_VERBOSITY=@OLD_NOTE_VERBOSITY */;

-- Dump completed on 2024-08-11  2:55:34
