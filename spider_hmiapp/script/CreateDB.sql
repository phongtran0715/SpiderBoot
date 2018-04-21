CREATE DATABASE  IF NOT EXISTS `spiderboot` /*!40100 DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci */;
USE `spiderboot`;
-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: localhost    Database: spiderboot
-- ------------------------------------------------------
-- Server version	5.7.20-log


-- [CR-001] phapnd, cap nhat procedure INSERTNEWMO

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
-- Table structure for table `download_cluster`
--

DROP TABLE IF EXISTS `download_cluster`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `download_cluster` (
  `ClusterId` int(11) NOT NULL AUTO_INCREMENT,
  `IpAddress` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  `Port` int(11) NOT NULL,
  `ClusterName` varchar(60) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`ClusterId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `download_cluster`
--

LOCK TABLES `download_cluster` WRITE;
/*!40000 ALTER TABLE `download_cluster` DISABLE KEYS */;
/*!40000 ALTER TABLE `download_cluster` ENABLE KEYS */;
UNLOCK TABLES;

-- Create procedure
DELIMITER $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `GETCHANNEL` (IN `i_batchWaitTime` INT(15))  BEGIN
        DECLARE EXIT HANDLER FOR SQLEXCEPTION 
		BEGIN
			GET DIAGNOSTICS condition 1
			@SQLState = RETURNED_SQLSTATE, @SQLMessage = MESSAGE_TEXT; 
			CALL log_debug(CONCAT('GETCHANNEL - Database error occurred, state - ',@SQLState, '; error msg - ', @SQLMessage));
            
		END;
		SELECT b.CHANNELID FROM HOME_MONITOR_CHANNEL_MAPPING a, MONITOR_CHANNEL_LIST b WHERE a.MONITORCHANNELID = b.CHANNELID AND a.action = 1 AND a.statussync = 1 AND (i_batchWaitTime>=UNIX_TIMESTAMP(a.lastsynctime));
    END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `INSERTNEWMO`(
 IN `i_videoID` VARCHAR(15)
 , IN `i_title` VARCHAR(100)
 , IN `i_tag` VARCHAR(1024)
 , IN `i_desc` VARCHAR(4096)
 , IN `i_thumbnail` VARCHAR(250)
 , IN `i_location` VARCHAR(250)
 , IN `i_homeID` VARCHAR(30)
 , IN `i_monitorID` VARCHAR(30)
 , IN `i_datetime` DATE
 )
BEGIN
        DECLARE EXIT HANDLER FOR SQLEXCEPTION
		BEGIN
			GET DIAGNOSTICS condition 1
			@SQLState = RETURNED_SQLSTATE, @SQLMessage = MESSAGE_TEXT; 
			CALL log_debug(CONCAT('INSERTNEWMO - Database error occurred, state - ',@SQLState, '; error msg - ', @SQLMessage));
            END;
		INSERT INTO video_container (VideoId, Title, Tag, Description, Thumbnail,VideoLocation, HomeChannelId, MonitorChannelId, DownloadDate)
        VALUES (i_videoID,i_title,i_tag,i_desc,i_thumbnail,i_location,i_homeID,i_monitorID,i_datetime);
		commit;
    
    END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `log_debug` (IN `lastMsg` VARCHAR(512))  BEGIN
		DECLARE EXIT HANDLER FOR SQLEXCEPTION
		BEGIN
			GET DIAGNOSTICS condition 1
			@SQLState = RETURNED_SQLSTATE, @SQLMessage = MESSAGE_TEXT; 
			CALL log_debug(CONCAT('Database error occurred, state - ',@SQLState, '; error msg - ', @SQLMessage));
			CALL log_debug('Exiting now');
		END;
    BEGIN
        INSERT INTO debug_log (msg)  VALUES (lastMsg);
    END;
    END$$

DELIMITER ;

--
-- Table structure for table `debug_log`
--

CREATE TABLE `debug_log` (
  `debug_log_id` int(10) UNSIGNED NOT NULL,
  `msg` varchar(512) COLLATE utf8_unicode_ci NOT NULL,
  `created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;



--
-- Table structure for table `google_account`
--

DROP TABLE IF EXISTS `google_account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `google_account` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `UserName` varchar(250) COLLATE utf8_unicode_ci NOT NULL,
  `Api` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  `ClientId` varchar(30) COLLATE utf8_unicode_ci NOT NULL,
  `ClientSecret` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `AccountType` varchar(15) COLLATE utf8_unicode_ci DEFAULT NULL,
  `AppName` varchar(30) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `google_account`
--

LOCK TABLES `google_account` WRITE;
/*!40000 ALTER TABLE `google_account` DISABLE KEYS */;
/*!40000 ALTER TABLE `google_account` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `home_channel_list`
--

DROP TABLE IF EXISTS `home_channel_list`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `home_channel_list` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `ChannelId` varchar(30) COLLATE utf8_unicode_ci NOT NULL,
  `ChannelName` varchar(25) COLLATE utf8_unicode_ci DEFAULT NULL,
  `GoogleAccount` varchar(250) COLLATE utf8_unicode_ci DEFAULT NULL,
  `VideoIntro` varchar(250) COLLATE utf8_unicode_ci DEFAULT NULL,
  `VideoOutro` varchar(250) COLLATE utf8_unicode_ci DEFAULT NULL,
  `Logo` varchar(250) COLLATE utf8_unicode_ci DEFAULT NULL,
  `DescriptionTemplate` varchar(250) COLLATE utf8_unicode_ci DEFAULT NULL,
  `TitleTemplate` varchar(250) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `home_channel_list`
--

LOCK TABLES `home_channel_list` WRITE;
/*!40000 ALTER TABLE `home_channel_list` DISABLE KEYS */;
INSERT INTO `home_channel_list` VALUES (1,'UCgGttbDvptiImN1GJkmPdWA','Top Hay La','phongtran0715@gmail.com','video intro 1','video outro 1','logo 1','description 1','title 1');
/*!40000 ALTER TABLE `home_channel_list` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `home_monitor_channel_mapping`
--

DROP TABLE IF EXISTS `home_monitor_channel_mapping`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `home_monitor_channel_mapping` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `HomeChannelId` varchar(30) COLLATE utf8_unicode_ci DEFAULT NULL,
  `MonitorChannelId` varchar(30) COLLATE utf8_unicode_ci DEFAULT NULL,
  `TimeIntervalSync` int(11) DEFAULT '100',
  `StatusSync` int(11) DEFAULT '0',
  `Action` int(11) DEFAULT '0',
  `LastSyncTime` datetime DEFAULT NULL,
  `DownloadClusterId` int(11) DEFAULT NULL,
  `ProcessClusterId` int(11) DEFAULT NULL,
  `UploadClusterId` int(11) DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;/*!40101 SET character_set_client = @saved_cs_client */;

INSERT INTO `home_monitor_channel_mapping` (`Id`, `HomeChannelId`, `MonitorChannelId`, `TimeIntervalSync`, `StatusSync`, `Action`, `LastSyncTime`, `DownloadClusterId`, `ProcessClusterId`, `UploadClusterId`) VALUES
(1, NULL, 'UCCylCCM6AhnK-BmalWuVBJg', 100000, 1, 1, '2018-01-20 01:22:08', NULL, NULL, NULL),
(2, NULL, 'UC-ApbP8o-FFhNnEFad8JNAw', 100000, 1, 1, '2018-01-20 01:22:08', NULL, NULL, NULL);

--
-- Dumping data for table `home_monitor_channel_mapping`
--

LOCK TABLES `home_monitor_channel_mapping` WRITE;
/*!40000 ALTER TABLE `home_monitor_channel_mapping` DISABLE KEYS */;
/*!40000 ALTER TABLE `home_monitor_channel_mapping` ENABLE KEYS */;
UNLOCK TABLES;
--
-- Table structure for table `monitor_channel_list`
--

DROP TABLE IF EXISTS `monitor_channel_list`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `monitor_channel_list` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `ChannelId` varchar(30) COLLATE utf8_unicode_ci NOT NULL,
  `ChannelName` varchar(25) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;


--
-- Dumping data for table `monitor_channel_list`
--

LOCK TABLES `monitor_channel_list` WRITE;
/*!40000 ALTER TABLE `monitor_channel_list` DISABLE KEYS */;
INSERT INTO `monitor_channel_list` (`Id`, `ChannelId`, `ChannelName`) VALUES
(1, 'UCCylCCM6AhnK-BmalWuVBJg', 'DLP movie edit'),
(2, 'UC-ApbP8o-FFhNnEFad8JNAw', 'DLP Weird');
/*!40000 ALTER TABLE `monitor_channel_list` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `process_cluster`
--

DROP TABLE IF EXISTS `process_cluster`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `process_cluster` (
  `ClusterId` int(11) NOT NULL,
  `IpAddress` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  `Port` int(11) NOT NULL,
  `ClusterName` varchar(60) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`ClusterId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `process_cluster`
--

-- --------------------------------------------------------

--
-- Table structure for table `utblcfg`
--

CREATE TABLE `utblcfg` (
  `ID` int(254) UNSIGNED NOT NULL COMMENT 'ID',
  `NLUp` int(255) UNSIGNED NOT NULL COMMENT 'So luong kenh upload',
  `NLDown` int(255) UNSIGNED NOT NULL COMMENT 'So luong kenh download',
  `Thread` int(100) UNSIGNED NOT NULL COMMENT 'So luong thread ',
  `Core` int(50) UNSIGNED NOT NULL COMMENT 'So luong core',
  `DirUp` varchar(200) COLLATE utf8_unicode_ci NOT NULL COMMENT 'Duong dan file up',
  `DirDown` varchar(200) COLLATE utf8_unicode_ci NOT NULL COMMENT 'Duong dan file down',
  `Retry` int(50) NOT NULL COMMENT 'So lan retry',
  `Timeout` int(255) NOT NULL COMMENT 'Timeout',
  `DirDownTemp` varchar(554) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT 'Download Directory ',
  `Qlity` varchar(254) COLLATE utf8_unicode_ci NOT NULL DEFAULT 'pMaxQuality' COMMENT 'chat luong video 240, 360, 480, 720,..',
  `Vtype` varchar(254) COLLATE utf8_unicode_ci NOT NULL DEFAULT 'MP4' COMMENT 'loai video mp4, avi, ...'
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='Bang cau hinh tham so up/down';


INSERT INTO `utblcfg` (`ID`, `NLUp`, `NLDown`, `Thread`, `Core`, `DirUp`, `DirDown`, `Retry`, `Timeout`, `DirDownTemp`, `Qlity`, `Vtype`) VALUES
(1, 2, 3, 5, 5, 'C:\\\\Users\\\\hanht\\\\OneDrive\\\\Documents\\\\NetBeansProjects\\\\DlVideo\\\\target\\\\jsw\\\\dlvideo\\\\videos', 'C:\\\\Users\\\\hanht\\\\OneDrive\\\\Documents\\\\NetBeansProjects\\\\DlVideo\\\\target\\\\jsw\\\\dlvideo\\\\videos', 3, 1000, 'C:\\\\Users\\\\hanht\\\\OneDrive\\\\Documents\\\\NetBeansProjects\\\\DlVideo\\\\target\\\\jsw\\\\dlvideo\\\\videotmp', '720p', 'MP4');

-- --------------------------------------------------------

LOCK TABLES `process_cluster` WRITE;
/*!40000 ALTER TABLE `process_cluster` DISABLE KEYS */;
/*!40000 ALTER TABLE `process_cluster` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `spider_account`
--

DROP TABLE IF EXISTS `spider_account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `spider_account` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `UserName` varchar(250) COLLATE utf8_unicode_ci NOT NULL,
  `Password` varchar(60) COLLATE utf8_unicode_ci DEFAULT NULL,
  `Email` varchar(250) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `spider_account`
--

LOCK TABLES `spider_account` WRITE;
/*!40000 ALTER TABLE `spider_account` DISABLE KEYS */;
INSERT INTO `spider_account` VALUES (1,'sys','admin',NULL),(2,'phong','','phongtran0715@gmail.com');
/*!40000 ALTER TABLE `spider_account` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `upload_cluster`
--

DROP TABLE IF EXISTS `upload_cluster`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `upload_cluster` (
  `ClusterId` int(11) NOT NULL,
  `IpAddress` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  `Port` int(11) NOT NULL,
  `ClusterName` varchar(60) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`ClusterId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `upload_cluster`
--

LOCK TABLES `upload_cluster` WRITE;
/*!40000 ALTER TABLE `upload_cluster` DISABLE KEYS */;
/*!40000 ALTER TABLE `upload_cluster` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `video_container`
--

DROP TABLE IF EXISTS `video_container`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
select * from home_monitor_channel_mapping;CREATE TABLE `video_container` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `VideoId` varchar(15) COLLATE utf8_unicode_ci NOT NULL,
  `Title` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `Tag` varchar(1024) COLLATE utf8_unicode_ci DEFAULT NULL,
  `Description` varchar(4096) COLLATE utf8_unicode_ci DEFAULT NULL,
  `Thumbnail` varchar(250) COLLATE utf8_unicode_ci DEFAULT NULL,
  `VideoLocation` varchar(250) COLLATE utf8_unicode_ci DEFAULT NULL,
  `HomeChannelId` varchar(30) COLLATE utf8_unicode_ci DEFAULT NULL,
  `DownloadDate` datetime DEFAULT NULL,
  `ProcessStatus` int(11) DEFAULT '0',
  `LICENSE` tinyint(1) DEFAULT NULL COMMENT 'Video license or not',
  `MonitorChannelId` varchar(30) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=81 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `video_container`
--

LOCK TABLES `video_container` WRITE;
/*!40000 ALTER TABLE `video_container` DISABLE KEYS */;
/*!40000 ALTER TABLE `video_container` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-01-07 12:00:14
