-- phpMyAdmin SQL Dump
-- version 4.7.4
-- https://www.phpmyadmin.net/
--
-- Máy chủ: 127.0.0.1
-- Thời gian đã tạo: Th1 22, 2018 lúc 06:28 PM
-- Phiên bản máy phục vụ: 10.1.28-MariaDB
-- Phiên bản PHP: 5.6.32

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Cơ sở dữ liệu: `spiderboot`
--

DELIMITER $$
--
-- Thủ tục
--
CREATE DEFINER=`root`@`localhost` PROCEDURE `GETCHANNEL` (IN `i_batchWaitTime` INT(15))  BEGIN
        DECLARE EXIT HANDLER FOR SQLEXCEPTION 
		BEGIN
			GET DIAGNOSTICS condition 1
			@SQLState = RETURNED_SQLSTATE, @SQLMessage = MESSAGE_TEXT; 
			CALL log_debug(CONCAT('GETCHANNEL - Database error occurred, state - ',@SQLState, '; error msg - ', @SQLMessage));
            
		END;
		SELECT b.CHANNELID FROM HOME_MONITOR_CHANNEL_MAPPING a, MONITOR_CHANNEL_LIST b WHERE a.MONITORCHANNELID = b.CHANNELID AND a.action = 1 AND a.statussync = 1 AND (i_batchWaitTime>=UNIX_TIMESTAMP(a.lastsynctime));
    END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `GETVIDEO` ()  BEGIN
        DECLARE EXIT HANDLER FOR SQLEXCEPTION 
		BEGIN
			GET DIAGNOSTICS condition 1
			@SQLState = RETURNED_SQLSTATE, @SQLMessage = MESSAGE_TEXT; 
			CALL log_debug(CONCAT('GETVIDEO - Database error occurred, state - ',@SQLState, '; error msg - ', @SQLMessage));
            
		END;
		SELECT id, videoid, title, tag, description, thumbnail, videolocation, homechannelid, monitorchannelid, downloaddate
        from video_container where processstatus = 1;
    END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `INSERTNEWMO` (IN `i_videoID` VARCHAR(15), IN `i_title` VARCHAR(100), IN `i_tag` VARCHAR(1024), IN `i_desc` VARCHAR(4096), IN `i_thumbnail` VARCHAR(250), IN `i_location` VARCHAR(250), IN `i_homeID` VARCHAR(30), IN `i_monitorID` VARCHAR(30), IN `i_datetime` DATE)  BEGIN
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

CREATE DEFINER=`root`@`localhost` PROCEDURE `UPLOADSTATUS` (IN `i_id` INT(11), IN `i_code` INT(2))  BEGIN
        DECLARE EXIT HANDLER FOR SQLEXCEPTION 
		BEGIN
			GET DIAGNOSTICS condition 1
			@SQLState = RETURNED_SQLSTATE, @SQLMessage = MESSAGE_TEXT; 
			CALL log_debug(CONCAT('UPLOADSTATUS - Database error occurred, state - ',@SQLState, '; error msg - ', @SQLMessage));
            
		END;
		UPDATE video_container set processstatus = i_code where id = i_id;
        END$$

DELIMITER ;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `debug_log`
--

CREATE TABLE `debug_log` (
  `debug_log_id` int(10) UNSIGNED NOT NULL,
  `msg` varchar(512) NOT NULL,
  `created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Đang đổ dữ liệu cho bảng `debug_log`
--

INSERT INTO `debug_log` (`debug_log_id`, `msg`, `created`) VALUES
(0, 'GETVIDEO - Database error occurred, state - 42000; error msg - Result consisted of more than one row', '2018-01-21 17:31:51'),
(0, 'GETVIDEO - Database error occurred, state - 42000; error msg - Result consisted of more than one row', '2018-01-21 18:36:15'),
(0, 'GETVIDEO - Database error occurred, state - 42000; error msg - Result consisted of more than one row', '2018-01-21 18:39:38'),
(0, 'GETVIDEO - Database error occurred, state - 42000; error msg - Result consisted of more than one row', '2018-01-21 18:40:43'),
(0, 'GETVIDEO - Database error occurred, state - 42000; error msg - Result consisted of more than one row', '2018-01-21 18:41:14'),
(0, 'GETVIDEO - Database error occurred, state - 42000; error msg - Result consisted of more than one row', '2018-01-21 18:47:20'),
(0, 'GETVIDEO - Database error occurred, state - 42000; error msg - Result consisted of more than one row', '2018-01-21 18:48:39');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `download_cluster`
--

CREATE TABLE `download_cluster` (
  `ClusterId` int(11) NOT NULL,
  `IpAddress` varchar(45) NOT NULL,
  `Port` int(11) NOT NULL,
  `ClusterName` varchar(60) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `google_account`
--

CREATE TABLE `google_account` (
  `Id` int(11) NOT NULL,
  `UserName` varchar(250) NOT NULL,
  `Api` varchar(45) NOT NULL,
  `ClientId` varchar(30) NOT NULL,
  `ClientSecret` varchar(100) NOT NULL,
  `AccountType` int(11) DEFAULT NULL,
  `AppName` varchar(30) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `home_channel_list`
--

CREATE TABLE `home_channel_list` (
  `Id` int(11) NOT NULL,
  `ChannelId` varchar(30) NOT NULL,
  `ChannelName` varchar(25) DEFAULT NULL,
  `GoogleAccount` varchar(250) DEFAULT NULL,
  `VideoIntro` varchar(250) DEFAULT NULL,
  `VideoOutro` varchar(250) DEFAULT NULL,
  `Logo` varchar(250) DEFAULT NULL,
  `DescriptionTemplate` varchar(250) DEFAULT NULL,
  `TitleTemplate` varchar(250) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Đang đổ dữ liệu cho bảng `home_channel_list`
--

INSERT INTO `home_channel_list` (`Id`, `ChannelId`, `ChannelName`, `GoogleAccount`, `VideoIntro`, `VideoOutro`, `Logo`, `DescriptionTemplate`, `TitleTemplate`) VALUES
(1, 'UCgGttbDvptiImN1GJkmPdWA', 'Top Hay La', 'phongtran0715@gmail.com', 'video intro 1', 'video outro 1', 'logo 1', 'description 1', 'title 1');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `home_monitor_channel_mapping`
--

CREATE TABLE `home_monitor_channel_mapping` (
  `Id` int(11) NOT NULL,
  `HomeChannelId` varchar(30) DEFAULT NULL,
  `MonitorChannelId` varchar(30) DEFAULT NULL,
  `TimeIntervalSync` int(11) DEFAULT '100',
  `StatusSync` int(11) DEFAULT '0',
  `Action` int(11) DEFAULT '0',
  `LastSyncTime` datetime DEFAULT NULL,
  `DownloadClusterId` int(11) DEFAULT NULL,
  `ProcessClusterId` int(11) DEFAULT NULL,
  `UploadClusterId` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Đang đổ dữ liệu cho bảng `home_monitor_channel_mapping`
--

INSERT INTO `home_monitor_channel_mapping` (`Id`, `HomeChannelId`, `MonitorChannelId`, `TimeIntervalSync`, `StatusSync`, `Action`, `LastSyncTime`, `DownloadClusterId`, `ProcessClusterId`, `UploadClusterId`) VALUES
(1, NULL, 'UCy-T9yQDJRyn91tDHEeqxXg', 60, 1, 1, '2010-01-21 02:10:43', NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `monitor_channel_list`
--

CREATE TABLE `monitor_channel_list` (
  `Id` int(11) NOT NULL,
  `ChannelId` varchar(30) NOT NULL,
  `ChannelName` varchar(25) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Đang đổ dữ liệu cho bảng `monitor_channel_list`
--

INSERT INTO `monitor_channel_list` (`Id`, `ChannelId`, `ChannelName`) VALUES
(1, 'UCCylCCM6AhnK-BmalWuVBJg', 'DLP movie edit'),
(2, 'UC-ApbP8o-FFhNnEFad8JNAw', 'DLP Weird');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `process_cluster`
--

CREATE TABLE `process_cluster` (
  `ClusterId` int(11) NOT NULL,
  `IpAddress` varchar(45) NOT NULL,
  `Port` int(11) NOT NULL,
  `ClusterName` varchar(60) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `spider_account`
--

CREATE TABLE `spider_account` (
  `Id` int(11) NOT NULL,
  `UserName` varchar(250) NOT NULL,
  `Password` varchar(60) DEFAULT NULL,
  `Email` varchar(250) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Đang đổ dữ liệu cho bảng `spider_account`
--

INSERT INTO `spider_account` (`Id`, `UserName`, `Password`, `Email`) VALUES
(1, 'sys', 'admin', NULL),
(2, 'phong', '', 'phongtran0715@gmail.com');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `upload_cluster`
--

CREATE TABLE `upload_cluster` (
  `ClusterId` int(11) NOT NULL,
  `IpAddress` varchar(45) NOT NULL,
  `Port` int(11) NOT NULL,
  `ClusterName` varchar(60) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `utblcfg`
--

CREATE TABLE `utblcfg` (
  `ID` int(254) UNSIGNED NOT NULL COMMENT 'ID',
  `NLUp` int(255) UNSIGNED NOT NULL COMMENT 'So luong kenh upload',
  `NLDown` int(255) UNSIGNED NOT NULL COMMENT 'So luong kenh download',
  `Thread` int(100) UNSIGNED NOT NULL COMMENT 'So luong thread ',
  `Core` int(50) UNSIGNED NOT NULL COMMENT 'So luong core',
  `DirUp` varchar(200) NOT NULL COMMENT 'Duong dan file up',
  `DirDown` varchar(200) NOT NULL COMMENT 'Duong dan file down',
  `Retry` int(50) NOT NULL COMMENT 'So lan retry',
  `Timeout` int(255) NOT NULL COMMENT 'Timeout',
  `DirDownTemp` varchar(554) DEFAULT NULL COMMENT 'Download Directory ',
  `Qlity` varchar(254) NOT NULL DEFAULT 'pMaxQuality' COMMENT 'chat luong video 240, 360, 480, 720,..',
  `Vtype` varchar(254) NOT NULL DEFAULT 'MP4' COMMENT 'loai video mp4, avi, ...'
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='Bang cau hinh tham so up/down';

--
-- Đang đổ dữ liệu cho bảng `utblcfg`
--

INSERT INTO `utblcfg` (`ID`, `NLUp`, `NLDown`, `Thread`, `Core`, `DirUp`, `DirDown`, `Retry`, `Timeout`, `DirDownTemp`, `Qlity`, `Vtype`) VALUES
(1, 2, 3, 5, 5, 'C:\\\\Users\\\\hanht\\\\OneDrive\\\\Documents\\\\NetBeansProjects\\\\DlVideo\\\\target\\\\jsw\\\\dlvideo\\\\videos', 'C:\\\\Users\\\\hanht\\\\OneDrive\\\\Documents\\\\NetBeansProjects\\\\DlVideo\\\\target\\\\jsw\\\\dlvideo\\\\videos', 3, 1000, 'C:\\\\Users\\\\hanht\\\\OneDrive\\\\Documents\\\\NetBeansProjects\\\\DlVideo\\\\target\\\\jsw\\\\dlvideo\\\\videotmp', '720p', 'MP4');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `video_container`
--

CREATE TABLE `video_container` (
  `Id` int(11) NOT NULL,
  `VideoId` varchar(15) NOT NULL,
  `Title` varchar(100) DEFAULT NULL,
  `Tag` varchar(1024) DEFAULT NULL,
  `Description` varchar(4096) DEFAULT NULL,
  `Thumbnail` varchar(250) DEFAULT NULL,
  `VideoLocation` varchar(250) DEFAULT NULL,
  `HomeChannelId` varchar(30) DEFAULT NULL,
  `DownloadDate` datetime DEFAULT NULL,
  `ProcessStatus` int(11) DEFAULT '0',
  `LICENSE` tinyint(1) DEFAULT NULL COMMENT 'Video license or not',
  `MonitorChannelId` varchar(30) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Đang đổ dữ liệu cho bảng `video_container`
--

INSERT INTO `video_container` (`Id`, `VideoId`, `Title`, `Tag`, `Description`, `Thumbnail`, `VideoLocation`, `HomeChannelId`, `DownloadDate`, `ProcessStatus`, `LICENSE`, `MonitorChannelId`) VALUES
(110, 'OnqJstWDsOg', 'Good Movies To Watch   Top 10 Best Mafia Movies of All Time You Should Check Out', NULL, 'Good Movies To Watch | Top 10 Best Mafia Movies of All Time You Should Check Out.\" Don\'t forget to subcribe our channel : https://goo.gl/x56UvJ --------- CONTENT --------- The...', 'https://i.ytimg.com/vi/OnqJstWDsOg/default.jpg', 'D:\\vas\\spiderboot\\SpiderBoot\\DlVideo\\target\\jsw\\dlvideo\\video\\null-UCCylCCM6AhnK-BmalWuVBJg\\Good_Movies_To_Watch___Top_10_Best_Mafia_Movies_of_All_Time_You_Should_Check_Out.mp4', NULL, '2018-01-22 00:00:00', 2, NULL, 'UCCylCCM6AhnK-BmalWuVBJg'),
(111, 'oPg_b-uraOM', 'Good movies to watch   Top 10 Best Tearjerker Movies That Make Men Cry', NULL, 'Good movies to watch | Top 10 best Tearjerker Movies That Make Men Cry Don\'t forget to subcribe our channel : https://goo.gl/x56UvJ --------- CONTENT --------- Some classic, well-loved...', 'https://i.ytimg.com/vi/oPg_b-uraOM/default.jpg', 'D:\\vas\\spiderboot\\SpiderBoot\\DlVideo\\target\\jsw\\dlvideo\\video\\null-UCCylCCM6AhnK-BmalWuVBJg\\Good_movies_to_watch___Top_10_Best_Tearjerker_Movies_That_Make_Men_Cry.mp4', NULL, '2018-01-22 00:00:00', 1, NULL, 'UCCylCCM6AhnK-BmalWuVBJg'),
(112, 'BueX3JIMMrw', 'Good movies to watch  Top 10 Best Romance Anime Ever Made To Warm Your Heart', NULL, 'Good movies to watch | Top 10 Best Romance Anime Ever Made To Warm Your Heart Don\'t forget to subcribe our channel : https://goo.gl/x56UvJ --------- CONTENT --------- It\'s time...', 'https://i.ytimg.com/vi/BueX3JIMMrw/default.jpg', 'D:\\vas\\spiderboot\\SpiderBoot\\DlVideo\\target\\jsw\\dlvideo\\video\\null-UCCylCCM6AhnK-BmalWuVBJg\\Good_movies_to_watch__Top_10_Best_Romance_Anime_Ever_Made_To_Warm_Your_Heart.mp4', NULL, '2018-01-22 00:00:00', 0, NULL, 'UCCylCCM6AhnK-BmalWuVBJg'),
(113, 'lgQJ2dVToEI', 'Good Movies To Watch   Top 10 Best Inspirational Movies That Can Make You A Better Person', NULL, 'Good Movies To Watch | Top 10 Best Inspirational Movies That Can Make You A Better Person\" Don\'t forget to subcribe our channel : https://goo.gl/x56UvJ --------- CONTENT ---------...', 'https://i.ytimg.com/vi/lgQJ2dVToEI/default.jpg', 'D:\\vas\\spiderboot\\SpiderBoot\\DlVideo\\target\\jsw\\dlvideo\\video\\null-UCCylCCM6AhnK-BmalWuVBJg\\Good_Movies_To_Watch___Top_10_Best_Inspirational_Movies_That_Can_Make_You_A_Better_Person.mp4', NULL, '2018-01-22 00:00:00', 0, NULL, 'UCCylCCM6AhnK-BmalWuVBJg'),
(114, 'XZZHISSfHv4', '???? ??????. ???????...', NULL, '??????? - ??????: ?? ????????, ?????: ????? ???????? (??????? ?????: ?. ???????), ?????????: ???? ??????.', 'https://i.ytimg.com/vi/XZZHISSfHv4/default.jpg', 'D:\\vas\\spiderboot\\SpiderBoot\\DlVideo\\target\\jsw\\dlvideo\\video\\null-UCy-T9yQDJRyn91tDHEeqxXg\\????_??????._???????...mp4', NULL, '2018-01-23 00:00:00', 0, NULL, 'UCy-T9yQDJRyn91tDHEeqxXg');

--
-- Chỉ mục cho các bảng đã đổ
--

--
-- Chỉ mục cho bảng `download_cluster`
--
ALTER TABLE `download_cluster`
  ADD PRIMARY KEY (`ClusterId`);

--
-- Chỉ mục cho bảng `google_account`
--
ALTER TABLE `google_account`
  ADD PRIMARY KEY (`Id`);

--
-- Chỉ mục cho bảng `home_channel_list`
--
ALTER TABLE `home_channel_list`
  ADD PRIMARY KEY (`Id`);

--
-- Chỉ mục cho bảng `home_monitor_channel_mapping`
--
ALTER TABLE `home_monitor_channel_mapping`
  ADD PRIMARY KEY (`Id`);

--
-- Chỉ mục cho bảng `monitor_channel_list`
--
ALTER TABLE `monitor_channel_list`
  ADD PRIMARY KEY (`Id`);

--
-- Chỉ mục cho bảng `process_cluster`
--
ALTER TABLE `process_cluster`
  ADD PRIMARY KEY (`ClusterId`);

--
-- Chỉ mục cho bảng `spider_account`
--
ALTER TABLE `spider_account`
  ADD PRIMARY KEY (`Id`);

--
-- Chỉ mục cho bảng `upload_cluster`
--
ALTER TABLE `upload_cluster`
  ADD PRIMARY KEY (`ClusterId`);

--
-- Chỉ mục cho bảng `video_container`
--
ALTER TABLE `video_container`
  ADD PRIMARY KEY (`Id`);

--
-- AUTO_INCREMENT cho các bảng đã đổ
--

--
-- AUTO_INCREMENT cho bảng `download_cluster`
--
ALTER TABLE `download_cluster`
  MODIFY `ClusterId` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT cho bảng `google_account`
--
ALTER TABLE `google_account`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT cho bảng `home_channel_list`
--
ALTER TABLE `home_channel_list`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT cho bảng `home_monitor_channel_mapping`
--
ALTER TABLE `home_monitor_channel_mapping`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT cho bảng `monitor_channel_list`
--
ALTER TABLE `monitor_channel_list`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT cho bảng `spider_account`
--
ALTER TABLE `spider_account`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT cho bảng `video_container`
--
ALTER TABLE `video_container`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=115;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
