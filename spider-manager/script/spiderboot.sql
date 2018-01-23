-- phpMyAdmin SQL Dump
-- version 4.7.4
-- https://www.phpmyadmin.net/
--
-- Máy chủ: 127.0.0.1
-- Thời gian đã tạo: Th1 23, 2018 lúc 06:35 PM
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
(119, 'OnqJstWDsOg', 'Good Movies To Watch   Top 10 Best Mafia Movies of All Time You Should Check Out', NULL, 'Good Movies To Watch | Top 10 Best Mafia Movies of All Time You Should Check Out.\" Don\'t forget to subcribe our channel : https://goo.gl/x56UvJ --------- CONTENT --------- The...', 'https://i.ytimg.com/vi/OnqJstWDsOg/default.jpg', 'D:\\vas\\spiderboot\\SpiderBoot\\DlVideo\\target\\jsw\\dlvideo\\video\\null-UCCylCCM6AhnK-BmalWuVBJg\\video_0.mp4', NULL, '2018-01-23 00:00:00', 0, NULL, 'UCCylCCM6AhnK-BmalWuVBJg'),
(128, 'OnqJstWDsOg', 'Good Movies To Watch   Top 10 Best Mafia Movies of All Time You Should Check Out', NULL, 'Good Movies To Watch | Top 10 Best Mafia Movies of All Time You Should Check Out.\" Don\'t forget to subcribe our channel : https://goo.gl/x56UvJ --------- CONTENT --------- The...', 'https://i.ytimg.com/vi/OnqJstWDsOg/default.jpg', 'D:\\vas\\spiderboot\\SpiderBoot\\DlVideo\\target\\jsw\\dlvideo\\video\\null-UCCylCCM6AhnK-BmalWuVBJg\\video_128.mp4', NULL, '2018-01-24 00:00:00', 0, NULL, 'UCCylCCM6AhnK-BmalWuVBJg'),
(129, 'OnqJstWDsOg', 'Good Movies To Watch   Top 10 Best Mafia Movies of All Time You Should Check Out', NULL, 'Good Movies To Watch | Top 10 Best Mafia Movies of All Time You Should Check Out.\" Don\'t forget to subcribe our channel : https://goo.gl/x56UvJ --------- CONTENT --------- The...', 'https://i.ytimg.com/vi/OnqJstWDsOg/default.jpg', 'D:\\vas\\spiderboot\\SpiderBoot\\DlVideo\\target\\jsw\\dlvideo\\video\\null-UCCylCCM6AhnK-BmalWuVBJg\\video_129.mp4', NULL, '2018-01-24 00:00:00', 0, NULL, 'UCCylCCM6AhnK-BmalWuVBJg');

--
-- Bẫy `video_container`
--
DELIMITER $$
CREATE TRIGGER `BI_PATHVIDEO` BEFORE INSERT ON `video_container` FOR EACH ROW BEGIN
DECLARE strVideoID VARCHAR(250);
DECLARE lastSeq INT(15);
DECLARE lastSeqStr VARCHAR(15);

SELECT AUTO_INCREMENT INTO lastSeq
FROM information_schema.TABLES
WHERE TABLE_SCHEMA = "spiderboot"
AND TABLE_NAME = "video_container";

SELECT CONVERT(lastSeq,CHAR) INTO lastSeqStr;
SELECT CONCAT(new.VideoLocation , 'video_', lastSeqStr, '.mp4') INTO strVideoID;
SET new.VideoLocation = strVideoID;
END
$$
DELIMITER ;

--
-- Chỉ mục cho các bảng đã đổ
--

--
-- Chỉ mục cho bảng `video_container`
--
ALTER TABLE `video_container`
  ADD PRIMARY KEY (`Id`);

--
-- AUTO_INCREMENT cho các bảng đã đổ
--

--
-- AUTO_INCREMENT cho bảng `video_container`
--
ALTER TABLE `video_container`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=130;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
