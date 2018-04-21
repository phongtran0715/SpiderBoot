#!/bin/sh
path=`pwd`
dirVideo="video"
dirLog="logs"
# creat folder $dirVideo
if [[ ! -e ../$dirVideo ]]; then
    mkdir ../$dirVideo
                echo "$dirVideo created!"
elif [[ ! -d ../$dirVideo ]]; then
    echo "$dirVideo already exists but is not a directory"
fi

`touch ../$dirVideo/app.properties`
echo "VIDEO_FILE_FORMAT=video/*" >> ../$dirVideo/app.properties 
echo "# link youtube authen" >> ../$dirVideo/app.properties 
echo "AUTH_LINK=https://www.googleapis.com/auth/youtube.upload" >> ../$dirVideo/app.properties 
echo "# duong link file client_sceret.json" >> ../$dirVideo/app.properties 
echo "CLIENT_SCERET=$path/etc/client_secrets.json" >> ../$dirVideo/app.properties 
echo "# so luong retry upload file" >> ../$dirVideo/app.properties 
echo "UPLOAD_RETRY=2" >> ../$dirVideo/app.properties 
echo "# Api key" >> ../$dirVideo/app.properties 
echo "API_KEY=2" >> ../$dirVideo/app.properties 
echo "# Folder luu video download" >> ../$dirVideo/app.properties 
echo "VIDEO_FOLDER=video" >> ../$dirVideo/app.properties 
echo "# So luong video tra ra khi research" >> ../$dirVideo/app.properties 
echo "NUMBER_OF_VIDEOS_RETURNED=25" >> ../$dirVideo/app.properties 
echo "# Thong tin ket noi db" >> ../$dirVideo/app.properties 
echo "DBSERVER=localhost:3306" >> ../$dirVideo/app.properties 
echo "DBNAME=spiderboot" >> ../$dirVideo/app.properties 
echo "USERNAME=root" >> ../$dirVideo/app.properties 
echo "****=" >> ../$dirVideo/app.properties 

#cat ../$dirVideo/app.properties
# creat folder $dirLog
if [[ ! -e ../$dirLog ]]; then
    mkdir $dirLog
                echo "$dirLog created!"
elif [[ ! -d ../$dirLog ]]; then
    echo "$dirLog already exists but is not a directory"
fi

`touch ../$dirVideo/log4j.properties`
echo "#declare two logger" >> ../$dirVideo/log4j.properties 
echo "#log4j.logger.postbill=debug,logcommon" >> ../$dirVideo/log4j.properties 
echo "log4j.logger.com.ictvn=debug" >> ../$dirVideo/log4j.properties 
echo "log4j.logger.org.springframework=info" >> ../$dirVideo/log4j.properties 
echo "log4j.logger.postbill=info,logcommon" >> ../$dirVideo/log4j.properties 
echo "log4j.logger.errorLogger=info,error" >> ../$dirVideo/log4j.properties 
echo "log4j.rootLogger=info,stdout,file" >> ../$dirVideo/log4j.properties 
echo "" >> ../$dirVideo/log4j.properties 
echo "#stdout appender" >> ../$dirVideo/log4j.properties 
echo "log4j.appender.stdout=org.apache.log4j.ConsoleAppender" >> ../$dirVideo/log4j.properties 
echo "log4j.appender.stdout.layout=org.apache.log4j.PatternLayout" >> ../$dirVideo/log4j.properties 
echo "log4j.appender.stdout.layout.ConversionPattern=%d{dd/MM/yyyy HH:mm:ss:SSS} %5p [%t] %C{1}(%M(%L)): %m%n" >> ../$dirVideo/log4j.properties 
echo "" >> ../$dirVideo/log4j.properties 
echo "" >> ../$dirVideo/log4j.properties 
echo "#interface appender" >> ../$dirVideo/log4j.properties 
echo "log4j.appender.logcommon=org.apache.log4j.DailyRollingFileAppender" >> ../$dirVideo/log4j.properties 
echo "log4j.appender.logcommon.File=../log/interface/interface.log" >> ../$dirVideo/log4j.properties 
echo "log4j.appender.logcommon.layout=org.apache.log4j.PatternLayout" >> ../$dirVideo/log4j.properties 
echo "log4j.appender.logcommon.layout.ConversionPattern=%d{dd/MM/yyyy HH:mm:ss:SSS} %5p [%t] %C{1}(%M(%L)): %m%n" >> ../$dirVideo/log4j.properties 
echo "" >> ../$dirVideo/log4j.properties 
echo "" >> ../$dirVideo/log4j.properties 
echo "# file appender" >> ../$dirVideo/log4j.properties 
echo "log4j.appender.file=org.apache.log4j.DailyRollingFileAppender" >> ../$dirVideo/log4j.properties 
echo "log4j.appender.file.File=$path/logs/full.log" >> ../$dirVideo/log4j.properties 
echo "log4j.appender.file.DatePattern='.'yyyy-MM-dd" >> ../$dirVideo/log4j.properties 
echo "log4j.appender.file.layout=org.apache.log4j.PatternLayout" >> ../$dirVideo/log4j.properties 
echo "log4j.appender.file.layout.ConversionPattern=%d{dd/MM/yyyy HH:mm:ss:SSS} %5p [%t] %C{1}(%M(%L)): %m%n" >> ../$dirVideo/log4j.properties 
echo "" >> ../$dirVideo/log4j.properties 
echo "" >> ../$dirVideo/log4j.properties 
echo "#log4j.appender.errorFilter=com.viettel.mmserver.log.appender.ErrorFilterJDBCAppender" >> ../$dirVideo/log4j.properties 
echo "#log4j.appender.errorFilter.maxQueue=5000" >> ../$dirVideo/log4j.properties 
echo "" >> ../$dirVideo/log4j.properties 
echo "" >> ../$dirVideo/log4j.properties 
echo "#Define appender MM_SOCK" >> ../$dirVideo/log4j.properties 
echo "#log4j.appender.MM_SOCK=com.viettel.mmserver.log.appender.AdvanceSocketHubAppender" >> ../$dirVideo/log4j.properties 
echo "#log4j.appender.MM_SOCK.port=9170" >> ../$dirVideo/log4j.properties 
echo "#log4j.appender.MM_SOCK.maxConnection=30" >> ../$dirVideo/log4j.properties 
echo "#log4j.appender.MM_SOCK.maxQueue=5000" >> ../$dirVideo/log4j.properties 
echo "#log4j.appender.MM_SOCK.offlineMaxQueue=100" >> ../$dirVideo/log4j.properties 
echo "" >> ../$dirVideo/log4j.properties 
echo "#errorLogger" >> ../$dirVideo/log4j.properties 
echo "log4j.appender.error=org.apache.log4j.DailyRollingFileAppender" >> ../$dirVideo/log4j.properties 
echo "log4j.appender.error.File=$path/logs/ERROR.log" >> ../$dirVideo/log4j.properties 
echo "log4j.appender.error.DatePattern='.'yyyy-MM-dd" >> ../$dirVideo/log4j.properties 
echo "log4j.appender.error.layout=org.apache.log4j.PatternLayout" >> ../$dirVideo/log4j.properties 
echo "log4j.appender.error.layout.ConversionPattern=%m%n" >> ../$dirVideo/log4j.properties 
#cat ../$dirVideo/log4j.properties