USE netxms_db;
-- google_account
DROP TABLE IF EXISTS `google_account`;
CREATE TABLE `google_account` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `UserName` varchar(250) COLLATE utf8_unicode_ci NOT NULL,
  `Api` varchar(250) COLLATE utf8_unicode_ci NOT NULL,
  `ClientSecret` varchar(250) COLLATE utf8_unicode_ci NOT NULL,
  `ClientId` varchar(150) COLLATE utf8_unicode_ci NOT NULL,
  `AccountType` int(11) DEFAULT NULL,
  `AppName` varchar(30) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`Id`,`UserName`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;


-- home_channel_list
DROP TABLE IF EXISTS `home_channel_list`;
CREATE TABLE `home_channel_list` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `ChannelId` varchar(30) NOT NULL,
  `ChannelName` varchar(25) DEFAULT NULL,
  `GoogleAccount` varchar(250) NOT NULL,
  `AccountID` int(11) DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;


-- monitor_channel_list
DROP TABLE IF EXISTS `monitor_channel_list`;
CREATE TABLE `monitor_channel_list` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `ChannelId` varchar(30) NOT NULL,
  `ChannelName` varchar(25) DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;


-- channel_mapping
DROP TABLE IF EXISTS `channel_mapping`;
CREATE TABLE `channel_mapping` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `HomeChannelId` varchar(30) DEFAULT NULL,
  `MonitorChannelId` varchar(30) DEFAULT NULL,
  `TimeIntervalSync` int(11) DEFAULT '100',
  `StatusSync` int(1) DEFAULT '0',
  `LastSyncTime` int(11) DEFAULT '0',
  `DownloadClusterId` varchar(50) DEFAULT NULL,
  `ProcessClusterId` varchar(50) DEFAULT NULL,
  `UploadClusterId` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;


-- video_container
DROP TABLE IF EXISTS `video_container`;
CREATE TABLE `video_container` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `VideoId` varchar(15) COLLATE utf8_unicode_ci NOT NULL,
  `Title` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `Tag` varchar(1024) COLLATE utf8_unicode_ci DEFAULT NULL,
  `Description` varchar(4096) COLLATE utf8_unicode_ci DEFAULT NULL,
  `Thumbnail` varchar(250) COLLATE utf8_unicode_ci DEFAULT NULL,
  `VDownloadedPath` varchar(250) CHARACTER SET utf8 DEFAULT NULL,
  `VRenderedPath` varchar(250) CHARACTER SET utf8 DEFAULT NULL,
  `MappingId` int(11) DEFAULT NULL,
  `ProcessStatus` int(11) DEFAULT '0',
  `License` tinyint(1) DEFAULT NULL COMMENT 'Video license or not',
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;


-- download_cluster
DROP TABLE IF EXISTS `download_cluster`;
CREATE TABLE `download_cluster` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `ClusterId` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  `ClusterName` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `IpAddress` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `Port` int(5) DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;


-- render_cluster
DROP TABLE IF EXISTS `render_cluster`;
CREATE TABLE `render_cluster` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `ClusterId` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  `ClusterName` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `IpAddress` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `Port` int(5) DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;


-- upload_cluster
DROP TABLE IF EXISTS `upload_cluster`;
CREATE TABLE `upload_cluster` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `ClusterId` varchar(150) CHARACTER SET utf8 NOT NULL,
  `ClusterName` varchar(150) CHARACTER SET utf8 DEFAULT NULL,
  `IpAddress` varchar(150) CHARACTER SET utf8 DEFAULT NULL,
  `Port` int(5) DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- spider_mapping_config
DROP TABLE IF EXISTS `spider_mapping_config`;
CREATE TABLE `spider_mapping_config` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `MappingId` int(11) NOT NULL,
  `VideoIntro` varchar(150) CHARACTER SET utf8 DEFAULT NULL,
  `VideoOutro` varchar(150) CHARACTER SET utf8 DEFAULT NULL,
  `Logo` varchar(150) CHARACTER SET utf8 DEFAULT NULL,
  `TitleTemplate` varchar(250) CHARACTER SET utf8 DEFAULT NULL,
  `DescTemplate` varchar(250) CHARACTER SET utf8 DEFAULT NULL,
  `TagTemplate` varchar(250) CHARACTER SET utf8 DEFAULT NULL,
  `LogoPosition` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `EnableIntro` int(11) DEFAULT NULL,
  `EnableOutro` int(11) DEFAULT NULL,
  `EnableLogo` int(11) DEFAULT NULL,
  `EnableTitle` int(11) DEFAULT NULL,
  `EnableDesc` int(11) DEFAULT NULL,
  `EnableTag` int(11) DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

