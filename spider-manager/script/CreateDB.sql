-- MySQL dump 10.13  Distrib 5.7.20, for Linux (x86_64)
--
-- Host: 127.0.01    Database: spiderboot
-- ------------------------------------------------------
-- Server version	5.7.20-0ubuntu0.16.04.1

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

CREATE DATABASE `spiderboot` /*!40100 DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci */;
USE `spiderboot`;

--
-- Table structure for table `backup_info`
--

DROP TABLE IF EXISTS `backup_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `backup_info` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `backup_info`
--

LOCK TABLES `backup_info` WRITE;
/*!40000 ALTER TABLE `backup_info` DISABLE KEYS */;
/*!40000 ALTER TABLE `backup_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `google_account`
--

DROP TABLE IF EXISTS `google_account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `google_account` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `UserName` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `Password` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `Api` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `ClientId` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `ClientSecret` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `AccountType` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `AppName` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
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
  `ChannelId` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `ChannelName` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `GoogleAccount` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `VideoIntro` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `VideoOutro` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `Logo` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `DescriptionTemplate` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `TitleTemplate` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
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
  `HomeChannelId` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `MonitorChannelId` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `TimeIntervalSync` int(11) DEFAULT '100',
  `StatusSync` int(11) DEFAULT '0',
  `Action` int(11) DEFAULT '0',
  `LastSyncTime` datetime DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `home_monitor_channel_mapping`
--

LOCK TABLES `home_monitor_channel_mapping` WRITE;
/*!40000 ALTER TABLE `home_monitor_channel_mapping` DISABLE KEYS */;
INSERT INTO `home_monitor_channel_mapping` VALUES (1,'UCgGttbDvptiImN1GJkmPdWA','UCCylCCM6AhnK-BmalWuVBJg',1000,0,1,'2000-01-01 00:00:01');
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
  `ChannelId` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `ChannelName` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `IntervalTimeSync` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `monitor_channel_list`
--

LOCK TABLES `monitor_channel_list` WRITE;
/*!40000 ALTER TABLE `monitor_channel_list` DISABLE KEYS */;
INSERT INTO `monitor_channel_list` VALUES (1,'UCCylCCM6AhnK-BmalWuVBJg','DLP movie edit','100');
/*!40000 ALTER TABLE `monitor_channel_list` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `spider_account`
--

DROP TABLE IF EXISTS `spider_account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `spider_account` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `UserName` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `Password` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `Email` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
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
-- Table structure for table `video_container`
--

DROP TABLE IF EXISTS `video_container`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `video_container` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `video_containercol` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `VideoId` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `Title` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `Tag` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `Description` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `Thumbnail` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `VideoLocation` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `HomeChannelId` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `MonitorChannelId` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `DownloadDate` datetime DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
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

-- Dump completed on 2017-11-19 17:04:30
