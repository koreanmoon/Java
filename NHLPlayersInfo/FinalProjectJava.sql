-- MariaDB dump 10.19  Distrib 10.8.3-MariaDB, for osx10.17 (arm64)
--
-- Host: localhost    Database: nhl
-- ------------------------------------------------------
-- Server version	10.8.3-MariaDB

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `player`
--

DROP TABLE IF EXISTS `player`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `player` (
  `player_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `first` varchar(100) NOT NULL,
  `last` varchar(100) NOT NULL,
  `games_played` int(10) unsigned NOT NULL DEFAULT 0,
  `goals` int(10) unsigned NOT NULL DEFAULT 0,
  `assists` int(10) unsigned NOT NULL DEFAULT 0,
  PRIMARY KEY (`player_id`),
  UNIQUE KEY `player_un` (`first`,`last`)
) ENGINE=InnoDB AUTO_INCREMENT=44 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `player`
--

LOCK TABLES `player` WRITE;
/*!40000 ALTER TABLE `player` DISABLE KEYS */;
INSERT INTO `player` VALUES
(1,'Wayne','Gretzky',1664,1200,3000),
(2,'Gordie','Howe',1767,801,1049),
(3,'Alex','Ovechkin',1278,782,633),
(4,'Jaromir','Jagr',1733,766,1155),
(5,'Brett','Hull',1269,751,650),
(6,'Marcel','Dionne',1348,731,1040),
(7,'Phil','Esposito',1282,717,873),
(8,'Mike','Gartner',1432,708,627);
/*!40000 ALTER TABLE `player` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `player_team`
--

DROP TABLE IF EXISTS `player_team`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `player_team` (
  `player_team_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `player_id` int(10) unsigned NOT NULL,
  `team_id` int(10) unsigned NOT NULL,
  PRIMARY KEY (`player_team_id`),
  KEY `player_team_FK` (`player_id`),
  KEY `player_team_FK_1` (`team_id`),
  CONSTRAINT `player_team_FK` FOREIGN KEY (`player_id`) REFERENCES `player` (`player_id`),
  CONSTRAINT `player_team_FK_1` FOREIGN KEY (`team_id`) REFERENCES `team` (`team_id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `player_team`
--

LOCK TABLES `player_team` WRITE;
/*!40000 ALTER TABLE `player_team` DISABLE KEYS */;
INSERT INTO `player_team` VALUES
(1,1,12),
(2,1,14),
(3,1,26),
(4,1,20),
(5,2,11),
(6,2,33),
(7,3,14),
(8,3,18),
(9,4,31),
(10,4,29),
(11,5,13),
(12,5,16),
(13,6,1),
(14,6,2),
(15,7,3),
(16,7,4),
(17,8,5),
(18,8,6);
/*!40000 ALTER TABLE `player_team` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `salary`
--

DROP TABLE IF EXISTS `salary`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `salary` (
  `salary_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `player_id` int(10) unsigned NOT NULL,
  `amount` decimal(10,2) NOT NULL,
  PRIMARY KEY (`salary_id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `salary`
--

LOCK TABLES `salary` WRITE;
/*!40000 ALTER TABLE `salary` DISABLE KEYS */;
INSERT INTO `salary` VALUES
(1,1,9750000.00),
(2,2,9583333.00),
(3,3,9500000.00),
(4,4,10000000.00),
(5,5,10500000.00),
(6,6,9200000.00),
(7,7,9250000.00),
(8,8,8460250.00);
/*!40000 ALTER TABLE `salary` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `team`
--

DROP TABLE IF EXISTS `team`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `team` (
  `team_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  PRIMARY KEY (`team_id`)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `team`
--

LOCK TABLES `team` WRITE;
/*!40000 ALTER TABLE `team` DISABLE KEYS */;
INSERT INTO `team` VALUES
(1,'Anaheim Ducks'),
(2,'Arizona Coyotes'),
(3,'Boston Bruins'),
(4,'Buffalo Sabres'),
(5,'Calgary Flames'),
(6,'Carolina Hurricanes'),
(7,'Chicago Blackhawks'),
(8,'Colorado Avalanche'),
(9,'Columbus Blue Jackets'),
(10,'Dallas Stars'),
(11,'Detroit Red Wings'),
(12,'Edmonton Oilers'),
(13,'Florida Panthers'),
(14,'Los Angeles Kings'),
(15,'Minnesota Wild'),
(16,'Montreal Canadiens'),
(17,'Nashville Predators'),
(18,'New Jersey Devils'),
(19,'New York Islanders'),
(20,'New York Rangers'),
(21,'Ottawa Senators'),
(22,'Philadelphia Flyers'),
(23,'Pittsburgh Penguins'),
(24,'San Jose Sharks'),
(25,'Seattle Kraken'),
(26,'St. Louis Blues'),
(27,'Tampa Bay Lightning'),
(28,'Toronto Maple Leafs'),
(29,'Vancouver Canucks'),
(30,'Vegas Golden Knights'),
(31,'Washington Capitals'),
(32,'Winnipeg Jets'),
(33,'Hartford Whalers');
/*!40000 ALTER TABLE `team` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'nhl'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-11-03 13:07:00
