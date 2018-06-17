#include "nxcore.h"

#ifdef HAVE_STD
#  include <iostream>
using namespace std;
#else
#  include <iostream.h>
#endif

void AgentSide_i::onDownloadStartup(const ::CORBA::WChar* downloadClusterId)
{
	DbgPrintf(1, _T("AgentSide_i::[onDownloadStartup] Download cluster id : %s"), downloadClusterId);
	createDownloadTimerByMapping(downloadClusterId, TYPE_MAPPING_CHANNEL);
}

void AgentSide_i::createDownloadTimerByMapping(const ::CORBA::WChar* downloadClusterId, INT32 mappingType)
{
	DbgPrintf(6, _T("AgentSide_i::createDownloadTimerByMapping : downloadClusterId = %s , mapping type = %d"), downloadClusterId, mappingType);
	TCHAR query [MAX_DB_STRING];
	DB_RESULT hResult;
	UINT32 i, dwNumRecords;
	TCHAR* mappingTable = getMappingTableNameByType(mappingType);
	if (mappingTable == nullptr)
	{
		DbgPrintf(1, _T("AgentSide_i::[createDownloadTimerByMapping] Can not get mapping table by mapping type = %d"), mappingType);
		return;
	}

	DB_HANDLE hdb = DBConnectionPoolAcquireConnection();
	_sntprintf(query, sizeof query, _T("SELECT Id, HomeChannelId, MonitorChannelId, TimeIntervalSync, StatusSync ")
	           _T(" FROM %s WHERE StatusSync = 1 AND DownloadClusterId = '%s'"), mappingTable, (const TCHAR*)downloadClusterId);
	DbgPrintf(6, _T("AgentSide_i::[createDownloadTimerByMapping] SQL query = %s"), query);
	//TODO: check another filed for another mapping type
	DB_STATEMENT hStmt = DBPrepare(hdb, query);
	if (hStmt != NULL)
	{
		hResult = DBSelectPrepared(hStmt);
		if (hResult != NULL)
		{
			dwNumRecords = DBGetNumRows(hResult);
			DbgPrintf(1, _T("AgentSide_i::[createDownloadTimerByMapping] numRecord = %d"), dwNumRecords);
			for (i = 0; i < dwNumRecords; i++)
			{
				INT32 jobId = DBGetFieldInt64(hResult, i, 0);
				TCHAR* cHomeId = DBGetField(hResult, i, 1, NULL, 0);
				TCHAR* cMonitorId = DBGetField(hResult, i, 2, NULL, 0);
				INT64 timeSync = DBGetFieldInt64(hResult, i, 3);
				INT64 statusSync = DBGetFieldInt64(hResult, i, 4);
				SpiderDownloadClient* downloadClient = new SpiderDownloadClient((const TCHAR*)downloadClusterId);
				if (downloadClient->initSuccess)
				{
					if (downloadClient->mDownloadRef != NULL)
					{
						try
						{
							::SpiderCorba::SpiderDefine::DownloadConfig downloadCfg;
							downloadCfg.cHomeId = ::CORBA::wstring_dup(cHomeId);
							downloadCfg.cMonitorId = ::CORBA::wstring_dup(cMonitorId);
							downloadCfg.timerInterval = timeSync;
							downloadClient->mDownloadRef->createDownloadTimer(jobId, mappingType, downloadCfg);
						}
						catch (CORBA::TRANSIENT&) {
							DbgPrintf(1, _T("AgentSide_i::[createDownloadTimerByMapping] : Caught system exception TRANSIENT -- unable to contact the server"));
						}
						catch (CORBA::SystemException& ex) {
							DbgPrintf(1, _T("AgentSide_i::[createDownloadTimerByMapping] : Caught a CORBA:: %s"), ex._name());
						}
						catch (CORBA::Exception& ex)
						{
							DbgPrintf(1, _T("AgentSide_i::[createDownloadTimerByMapping] : Caught a CORBA:: %s"), ex._name());
						}
					}
				} else {
				}
			}
			DBFreeResult(hResult);
		}
	}
	DBConnectionPoolReleaseConnection(hdb);

}

void AgentSide_i::onRenderStartup(const ::CORBA::WChar* renderClusterId)
{
	DbgPrintf(6, _T("AgentSide_i::[onRenderStartup] >>>> renderClusterId = %s"), renderClusterId);
	createRenderJobByMapping(renderClusterId, TYPE_MAPPING_CHANNEL);
	DbgPrintf(6, _T("AgentSide_i::[onRenderStartup] <<<< "));
}

void AgentSide_i::createRenderJobByMapping(const ::CORBA::WChar* renderClusterId, INT32 mappingType)
{
	DbgPrintf(6, _T("AgentSide_i::[createRenderJobByMapping] renderClusterId = %s"), renderClusterId);
	DB_RESULT hResult;
	TCHAR query [MAX_DB_STRING];
	UINT32 i, dwNumRecords;
	TCHAR* mappingTable = getMappingTableNameByType(mappingType);
	SpiderRenderClient* renderClient = new SpiderRenderClient((const TCHAR*)renderClusterId);
	if (mappingTable == nullptr)
	{
		DbgPrintf(1, _T("AgentSide_i::[createRenderJobByMapping] Can not get mapping table by mapping type = %d"), mappingType);
		return;
	}
	DB_HANDLE hdb = DBConnectionPoolAcquireConnection();
	_sntprintf(query, sizeof query,  _T("SELECT Id, VideoId, Title, Tag, Description, Thumbnail, VDownloadedPath, ")
	           _T(" VRenderedPath, MappingId, MappingType, ProcessStatus, License FROM video_container WHERE MappingId IN ")
	           _T(" (SELECT Id FROM %s WHERE RenderClusterId = '%s') AND MappingType = %d AND ProcessStatus = 1"),
	           mappingTable, (TCHAR*) renderClusterId, mappingType);
	DbgPrintf(6, _T("AgentSide_i::[createRenderJobByMapping] SQL query = %s"), query);

	DB_STATEMENT hStmt = DBPrepare(hdb, query);
	if (hStmt != NULL)
	{
		hResult = DBSelectPrepared(hStmt);
		if (hResult != NULL)
		{
			dwNumRecords = DBGetNumRows(hResult);
			DbgPrintf(6, _T("AgentSide_i::[createRenderJobByMapping] numRecord = %d"), dwNumRecords);

			for (i = 0; i < dwNumRecords; i++)
			{
				::SpiderCorba::SpiderDefine::VideoInfo vInfo;

				INT32 jobId = DBGetFieldInt64(hResult, i, 0);
				vInfo.videoId = ::CORBA::wstring_dup(DBGetField(hResult, i, 1, NULL, 0));
				vInfo.title = ::CORBA::wstring_dup(DBGetField(hResult, i, 2, NULL, 0));
				vInfo.tags = ::CORBA::wstring_dup(DBGetField(hResult, i, 3, NULL, 0));
				vInfo.description = ::CORBA::wstring_dup(DBGetField(hResult, i, 4, NULL, 0));
				vInfo.thumbnail = ::CORBA::wstring_dup(DBGetField(hResult, i, 5, NULL, 0));
				vInfo.vDownloadPath = ::CORBA::wstring_dup(DBGetField(hResult, i, 6, NULL, 0));
				vInfo.vRenderPath = ::CORBA::wstring_dup(_T(""));
				vInfo.mappingId = DBGetFieldInt64(hResult, i, 8);
				vInfo.mappingType = DBGetFieldInt64(hResult, i, 9);
				vInfo.processStatus = DBGetFieldInt64(hResult, i, 10);
				vInfo.license = DBGetFieldInt64(hResult, i, 11);

				DbgPrintf(6, _T("AgentSide_i::[createRenderJobByMapping] create videoinfo OK"));

				if (renderClient->initSuccess)
				{
					if (renderClient->mRenderRef != NULL)
					{
						try
						{
							renderClient->mRenderRef->createRenderJob(jobId, vInfo);
							DbgPrintf(6, _T("AgentSide_i::[createRenderJobByMapping] create render job id : %d OK"), jobId);
						}
						catch (CORBA::TRANSIENT&) {
							DbgPrintf(1, _T("AgentSide_i::[createRenderJobByMapping] : Caught system exception TRANSIENT -- unable to contact the server"));
						}
						catch (CORBA::SystemException& ex) {
							DbgPrintf(1, _T("AgentSide_i::[createRenderJobByMapping] : Caught a CORBA:: %s"), ex._name());
						}
						catch (CORBA::Exception& ex)
						{
							DbgPrintf(1, _T("AgentSide_i::[createRenderJobByMapping] : Caught a CORBA:: %s"), ex._name());
						}
					}
				} else {
				}
			}
			DBFreeResult(hResult);
		}
	}
	DBConnectionPoolReleaseConnection(hdb);
}

void AgentSide_i::onUploadStartup(const ::CORBA::WChar* uploadClusterId)
{
	DbgPrintf(6, _T("AgentSide_i::onUploadStartup : uploadId = %s"), uploadClusterId);
	//create upload timer
	createUploadTimerByMapping(uploadClusterId, TYPE_MAPPING_CHANNEL);
	//create upload job
	createUploadJobByMapping(uploadClusterId, TYPE_MAPPING_CHANNEL);
}

void AgentSide_i::createUploadTimerByMapping(const ::CORBA::WChar* uploadClusterId, INT32 mappingType)
{
	DbgPrintf(6, _T("AgentSide_i::createUploadTimerByMapping : uploadClusterId = %s , mapping type = %d"), uploadClusterId, mappingType);
	TCHAR* mappingTable = getMappingTableNameByType(mappingType);
	if (mappingTable == nullptr)
	{
		DbgPrintf(1, _T("AgentSide_i::[createUploadTimerByMapping] Can not get mapping table by mapping type = %d"), mappingType);
		return;
	}
	DB_RESULT hResult;
	UINT32 i, dwNumRecords;
	SpiderUploadClient* uploadClient = new SpiderUploadClient((const TCHAR*)uploadClusterId);
	DB_HANDLE hdb = DBConnectionPoolAcquireConnection();
	TCHAR query [MAX_DB_STRING];
	_sntprintf(query, sizeof query, _T("SELECT DISTINCT Id FROM %s WHERE StatusSync = 1 AND UploadClusterId = '%s'"), mappingTable, uploadClusterId);
	DbgPrintf(6, _T("AgentSide_i::[createUploadTimerByMapping] SQL query = %s"), query);
	DB_STATEMENT hStmt = DBPrepare(hdb, query);
	if (hStmt != NULL)
	{
		hResult = DBSelectPrepared(hStmt);
		if (hResult != NULL)
		{
			dwNumRecords = DBGetNumRows(hResult);
			DbgPrintf(6, _T("AgentSide_i::[createUploadTimerByMapping] dwNumRecords = %d"), dwNumRecords);
			for (i = 0; i < dwNumRecords; i++)
			{
				INT32 mappingId = DBGetFieldInt64(hResult, i, 0);
				if (uploadClient->initSuccess)
				{
					if (uploadClient->mUploadRef != NULL)
					{
						try
						{
							uploadClient->mUploadRef->createUploadTimer(mappingId, mappingType);
						}
						catch (CORBA::TRANSIENT&) {
							DbgPrintf(1, _T("AgentSide_i::[createUploadTimerByMapping] : Caught system exception TRANSIENT -- unable to contact the server"));
						}
						catch (CORBA::SystemException& ex) {
							DbgPrintf(1, _T("AgentSide_i::[createUploadTimerByMapping] : Caught a CORBA:: %s"), ex._name());
						}
						catch (CORBA::Exception& ex)
						{
							DbgPrintf(1, _T("AgentSide_i::[createUploadTimerByMapping] : Caught a CORBA:: %s"), ex._name());
						}
					}
				} else {
					DbgPrintf(1, _T("AgentSide_i::[createUploadTimerByMapping] : Can not create corba connection"));
				}
			}
			DBFreeResult(hResult);
		}
	}
	DBConnectionPoolReleaseConnection(hdb);
}

void AgentSide_i::createUploadJobByMapping(const ::CORBA::WChar* uploadClusterId, INT32 mappingType)
{
	DbgPrintf(6, _T("AgentSide_i::[createUploadJobByMapping] CSQL query : uploadClusterId = %s , mappingType = %d"), uploadClusterId, mappingType);
	TCHAR* mappingTable = getMappingTableNameByType(mappingType);
	if (mappingTable == nullptr)
	{
		DbgPrintf(1, _T("AgentSide_i::[createUploadJobByMapping] Can not get mapping table by mapping type = %d"), mappingType);
		return;
	}
	DB_RESULT hResult;
	UINT32 i, dwNumRecords;
	DB_HANDLE hdb = DBConnectionPoolAcquireConnection();
	TCHAR query [MAX_DB_STRING];
	//TODO: set id for another mapping type (palylist / keyword, ...)
	_sntprintf(query, sizeof query,  _T("SELECT Id, VideoId, Title, Tag, Description, Thumbnail, ")
	           _T(" VRenderedPath, MappingId, License FROM video_container WHERE MappingId IN ")
	           _T(" (SELECT Id FROM %s WHERE UploadClusterId = '%s') AND MappingType = %d AND ProcessStatus = %d"),
	           mappingTable, (const TCHAR*)uploadClusterId, mappingType, 2);
	DbgPrintf(6, _T("AgentSide_i::[createUploadJobByMapping] SQL query : %s"), query);

	DB_STATEMENT hStmt = DBPrepare(hdb, query);
	if (hStmt != NULL)
	{
		hResult = DBSelectPrepared(hStmt);
		if (hResult != NULL)
		{
			dwNumRecords = DBGetNumRows(hResult);
			DbgPrintf(6, _T("AgentSide_i::onUploadStartup : number record = %d"), dwNumRecords);

			for (i = 0; i < dwNumRecords; i++)
			{
				INT32 id = DBGetFieldInt64(hResult, i, 0);
				TCHAR* videoId = DBGetField(hResult, i, 1, NULL, 0);
				TCHAR* vTitle = DBGetField(hResult, i, 2, NULL, 0);
				TCHAR* vTags = DBGetField(hResult, i, 3, NULL, 0);
				TCHAR* vDesc = DBGetField(hResult, i, 4, NULL, 0);
				TCHAR* vThumb = DBGetField(hResult, i, 5, NULL, 0);
				TCHAR* vRenderedPath = DBGetField(hResult, i, 6, NULL, 0);
				INT32 mappingId = DBGetFieldInt64(hResult, i, 7);
				INT32 license = DBGetFieldInt64(hResult, i, 8);

				SpiderUploadClient* uploadClient = new SpiderUploadClient((const TCHAR*)uploadClusterId);
				if (uploadClient->initSuccess)
				{
					if (uploadClient->mUploadRef != NULL)
					{
						try
						{
							::SpiderCorba::SpiderDefine::VideoInfo vInfo;
							vInfo.videoId = ::CORBA::wstring_dup(videoId);
							vInfo.title = ::CORBA::wstring_dup(vTitle);
							vInfo.title = ::CORBA::wstring_dup(vTitle);
							vInfo.tags = ::CORBA::wstring_dup(vTags);
							vInfo.description = ::CORBA::wstring_dup(vDesc);
							vInfo.thumbnail = ::CORBA::wstring_dup(vThumb);
							vInfo.vRenderPath = ::CORBA::wstring_dup(vRenderedPath);
							vInfo.mappingId = mappingId;
							vInfo.mappingType = mappingType;
							vInfo.license = license;
							uploadClient->mUploadRef->createUploadJob(id, vInfo);
						}
						catch (CORBA::TRANSIENT&) {
							DbgPrintf(1, _T("AgentSide_i::[createUploadJobByMapping] : Caught system exception TRANSIENT -- unable to contact the server"));
						}
						catch (CORBA::SystemException& ex) {
							DbgPrintf(1, _T("AgentSide_i::[createUploadJobByMapping] : Caught a CORBA:: %s"), ex._name());
						}
						catch (CORBA::Exception& ex)
						{
							DbgPrintf(1, _T("AgentSide_i::[createUploadJobByMapping] : Caught a CORBA:: %s"), ex._name());
						}
					}
				} else {
				}
			}
			DBFreeResult(hResult);
		}
	}
	DBConnectionPoolReleaseConnection(hdb);
}

TCHAR* AgentSide_i::getMappingTableNameByType(INT32 mappingType)
{
	DbgPrintf(6, _T("AgentSide_i::[getMappingTableNameByType] : mappingType = %d"), mappingType);
	TCHAR* result = nullptr;
	switch (mappingType)
	{
	case TYPE_MAPPING_CHANNEL:
		result = _T("channel_mapping");
		break;
	case TYPE_MAPPING_PLAYLIST:
		break;
	case TYPE_MAPPING_KEYWORD:
		break;
	case TYPE_MAPPING_CUSTOM_VIDEO_LINK:
		break;
	case TYPE_MAPPING_CUSTOM_VIDEO_LOCATION:
		break;
	default:
		break;
	}
	return result;
}

TCHAR* AgentSide_i::getClusterTableNameByType(INT32 clusterType)
{
	DbgPrintf(6, _T("AgentSide_i::[getClusterTableNameByType] : clusterType = %d"), clusterType);
	TCHAR* result = nullptr;
	switch (clusterType)
	{
	case TYPE_DOWNLOADED:
		result = _T("download_cluster");
		break;
	case TYPE_RENDERED:
		result = _T("render_cluster");
		break;
	case TYPE_UPLOADED:
		result = _T("upload_cluster");
		break;
	default:
		break;
	}
	return result;
}

::CORBA::LongLong AgentSide_i::getLastSyncTime(::CORBA::Long mappingId, ::CORBA::Long mappingType)
{
	DbgPrintf(6, _T("AgentSide_i::getLastSyncTime : mappingId = %ld"), mappingId);
	INT32 result = 0;
	DB_RESULT hResult;
	TCHAR query [MAX_DB_STRING];
	UINT32 dwNumRecords;

	DB_HANDLE hdb = DBConnectionPoolAcquireConnection();
	TCHAR* tbName = getMappingTableNameByType((INT32)mappingType);
	_sntprintf(query, sizeof query, _T("SELECT LastSyncTime FROM %s WHERE Id = %d"), tbName, (INT32)mappingId);
	DbgPrintf(6, _T("AgentSide_i::getLastSyncTime : SQL query = %s"), query);
	DB_STATEMENT hStmt = DBPrepare(hdb, query);
	if (hStmt != NULL)
	{
		hResult = DBSelectPrepared(hStmt);
		if (hResult != NULL)
		{
			dwNumRecords = DBGetNumRows(hResult);
			if (dwNumRecords > 0)
			{
				result = DBGetFieldInt64(hResult, 0, 0);
				DbgPrintf(1, _T("AgentSide_i::getLastSyncTime : last sync time = %d"), result);
			}
			DBFreeResult(hResult);
		}
	}
	DBConnectionPoolReleaseConnection(hdb);
	return result;
}

void AgentSide_i::updateLastSyntime(::CORBA::Long mappingId, ::CORBA::Long mappingType, ::CORBA::LongLong lastSyncTime)
{
	DbgPrintf(6, _T("AgentSide_i::[updateLastSyntime]"));
	TCHAR query [MAX_DB_STRING];
	DB_HANDLE hdb = DBConnectionPoolAcquireConnection();
	DB_STATEMENT hStmt;
	if (hdb != NULL)
	{
		TCHAR* tbName = getMappingTableNameByType((INT32)mappingType);
		_sntprintf(query, sizeof query, _T("UPDATE %s SET LastSyncTime = %d WHERE Id = %d"), tbName, (INT32)lastSyncTime , (INT32)mappingId);
		DbgPrintf(6, _T("AgentSide_i::[updateLastSyntime] SQL query = %s"), query);
		hStmt = DBPrepare(hdb, query);
		DBExecute(hStmt);
	}
	DBConnectionPoolReleaseConnection(hdb);
}

void AgentSide_i::updateDownloadedVideo(const ::SpiderCorba::SpiderDefine::VideoInfo& vInfo)
{
	DbgPrintf(6, _T("AgentSide_i::[updateDownloadedVideo]"));
	DB_HANDLE hdb = DBConnectionPoolAcquireConnection();
	DB_STATEMENT hStmt;

	if (hdb != NULL)
	{
		hStmt = DBPrepare(hdb, _T("INSERT INTO video_container (VideoId, Title, Tag, ")
		                  _T(" Description, Thumbnail, VDownloadedPath,")
		                  _T("MappingId, MappingType, ProcessStatus, License) VALUES (?,?,?,?,?,?,?,?,?,?)"));

		DBBind(hStmt, 1, DB_SQLTYPE_VARCHAR, (const TCHAR *)CORBA::wstring_dup(vInfo.videoId), DB_BIND_TRANSIENT);
		DBBind(hStmt, 2, DB_SQLTYPE_VARCHAR, (const TCHAR *)CORBA::wstring_dup(vInfo.title), DB_BIND_TRANSIENT);
		DBBind(hStmt, 3, DB_SQLTYPE_VARCHAR, (const TCHAR *)CORBA::wstring_dup(vInfo.tags), DB_BIND_TRANSIENT);
		DBBind(hStmt, 4, DB_SQLTYPE_VARCHAR, (const TCHAR *)CORBA::wstring_dup(vInfo.description), DB_BIND_TRANSIENT);
		DBBind(hStmt, 5, DB_SQLTYPE_VARCHAR, (const TCHAR *)CORBA::wstring_dup(vInfo.thumbnail), DB_BIND_TRANSIENT);
		DBBind(hStmt, 6, DB_SQLTYPE_VARCHAR, (const TCHAR *)CORBA::wstring_dup(vInfo.vDownloadPath), DB_BIND_TRANSIENT);
		DBBind(hStmt, 7, DB_SQLTYPE_INTEGER, (INT32)vInfo.mappingId);
		DBBind(hStmt, 8, DB_SQLTYPE_INTEGER, (INT32)vInfo.mappingType);
		DBBind(hStmt, 9, DB_SQLTYPE_INTEGER, (INT32)vInfo.processStatus);
		DBBind(hStmt, 10, DB_SQLTYPE_INTEGER, (INT32)vInfo.license);

		bool success = DBExecute(hStmt);
		if (success == true)
		{
			//notify to render app
			TCHAR* renderClusterId = getClusterId((INT32)vInfo.mappingId, (INT32)vInfo.mappingType, TYPE_RENDERED);
			SpiderRenderClient* renderClient = new SpiderRenderClient(renderClusterId);
			if (renderClient->initSuccess)
			{
				if (renderClient->mRenderRef != NULL)
				{
					try
					{
						INT32 jobId = getMaxId(_T("video_container"));
						//::SpiderCorba::SpiderDefine::RenderConfig renderCfg =  getRenderConfig(vInfo.mappingId);
						renderClient->mRenderRef->createRenderJob(jobId, vInfo);
					}
					catch (CORBA::TRANSIENT&) {
						DbgPrintf(1, _T("AgentSide_i::[] : Caught system exception TRANSIENT -- unable to contact the server"));
					}
					catch (CORBA::SystemException& ex) {
						DbgPrintf(1, _T("AgentSide_i::[] : Caught a CORBA:: %s"), ex._name());
					}
					catch (CORBA::Exception& ex)
					{
						DbgPrintf(1, _T("AgentSide_i::[] : Caught a CORBA:: %s"), ex._name());
					}
				}
			} else
			{
			}
		} else
		{
			DbgPrintf(1, _T("AgentSide_i::updateDownloadedVideo : insert new video info FALSE"));
		}
	}

	DBConnectionPoolReleaseConnection(hdb);
}

TCHAR* AgentSide_i::getClusterId(INT32 mappingId, INT32 mappingType, INT32 clusterType)
{
	DbgPrintf(6, _T("AgentSide_i::[getClusterId] : mappingId = %d - mapping type = %d"), mappingId, mappingType);
	TCHAR* clusterName = nullptr;
	TCHAR query [MAX_DB_STRING];
	DB_RESULT hResult;
	UINT32 dwNumRecords;
	DB_HANDLE hdb = DBConnectionPoolAcquireConnection();
	TCHAR* tbName = getMappingTableNameByType(mappingType);
	TCHAR* tbField;
	switch (clusterType)
	{
	case TYPE_DOWNLOADED:
		tbField = _T("DownloadClusterId");
		break;
	case TYPE_RENDERED:
		tbField = _T("RenderClusterId");
		break;
	case TYPE_UPLOADED:
		tbField = _T("UploadClusterId");
		break;
	default:
		break;
	}
	_sntprintf(query, sizeof query, _T("SELECT %s FROM %s WHERE Id = %d"), tbField, tbName, (INT32)mappingId);
	DbgPrintf(6, _T("AgentSide_i::[getClusterId] : SQL query = %s"), query);
	DB_STATEMENT hStmt = DBPrepare(hdb, query);
	if (hStmt != NULL)
	{
		hResult = DBSelectPrepared(hStmt);
		if (hResult != NULL)
		{
			dwNumRecords = DBGetNumRows(hResult);
			if (dwNumRecords > 0)
			{
				clusterName = DBGetField(hResult, 0, 0, NULL, 0);
				DbgPrintf(1, _T("AgentSide_i::getClusterId : cluster id = %s"), clusterName);
			}
			DBFreeResult(hResult);
		}
	}
	DBConnectionPoolReleaseConnection(hdb);
	return clusterName;
}

void AgentSide_i::updateRenderedVideo(::CORBA::Long jobId, const ::SpiderCorba::SpiderDefine::VideoInfo& vInfo)
{
	DbgPrintf(6, _T("AgentSide_i::[updateRenderedVideo] jobId = %d"), (INT32)jobId);
	DB_HANDLE hdb = DBConnectionPoolAcquireConnection();
	DB_STATEMENT hStmt;

	if (hdb != NULL)
	{
		hStmt = DBPrepare(hdb, _T("UPDATE video_container SET ProcessStatus = ?, VRenderedPath = ? WHERE Id = ?"));
		DBBind(hStmt, 1, DB_SQLTYPE_INTEGER, (INT32)vInfo.processStatus);
		DBBind(hStmt, 2, DB_SQLTYPE_VARCHAR, (const TCHAR *)vInfo.vRenderPath, DB_BIND_TRANSIENT);
		DBBind(hStmt, 3, DB_SQLTYPE_INTEGER, (INT32)jobId);
		bool success = DBExecute(hStmt);
		if (success == true)
		{
			//notify to upload app
			TCHAR* uploadClusterId = getClusterId((INT32)vInfo.mappingId, (INT32)vInfo.mappingType, TYPE_UPLOADED);
			SpiderUploadClient* uploadClient = new SpiderUploadClient((const TCHAR*)uploadClusterId);
			if (uploadClient->initSuccess)
			{
				if (uploadClient->mUploadRef != NULL)
				{
					try
					{
						uploadClient->mUploadRef->createUploadJob(jobId, vInfo);
					}
					catch (CORBA::TRANSIENT&) {
						DbgPrintf(1, _T("AgentSide_i::[] : Caught system exception TRANSIENT -- unable to contact the server"));
					}
					catch (CORBA::SystemException& ex) {
						DbgPrintf(1, _T("AgentSide_i::[] : Caught a CORBA:: %s"), ex._name());
					}
					catch (CORBA::Exception& ex)
					{
						DbgPrintf(1, _T("AgentSide_i::[] : Caught a CORBA:: %s"), ex._name());
					}
				}
			} else {
			}
		}
	}
	DBConnectionPoolReleaseConnection(hdb);
}

AgentCorbaServer::AgentCorbaServer(): initSuccess(false)
{
	//Default constructor
}

void AgentCorbaServer::initCorba()
{
	try {
		int param1 = 1;
		char* param2[] = { ""};
		CORBA::ORB_var          orb = CORBA::ORB_init(param1, param2);
		CORBA::Object_var       obj = orb->resolve_initial_references("RootPOA");
		PortableServer::POA_var poa = PortableServer::POA::_narrow(obj);

		PortableServer::Servant_var<AgentSide_i> myecho = new AgentSide_i();

		PortableServer::ObjectId_var myechoid = poa->activate_object(myecho);

		// Obtain a reference to the object, and register it in
		// the naming service.
		obj = myecho->_this();

		CORBA::String_var sior(orb->object_to_string(obj));
		cout << sior << endl;

		if (!bindObjectToName(orb, obj))
		{
			DbgPrintf(1, _T("AgentCorbaServer::initCorba: ERROR ! scan not bind object to name..."));
			return;
		}

		PortableServer::POAManager_var pman = poa->the_POAManager();
		pman->activate();
		DbgPrintf(1, _T("server is running and waiting connection from client..."));

		orb->run();
	}
	catch (CORBA::SystemException& ex) {
		DbgPrintf(1, _T("AgentCorbaServer::initCorba: Caught CORBA:: %s"), ex._name());
	}
	catch (CORBA::Exception& ex) {
		DbgPrintf(1, _T("AgentCorbaServer::initCorba: Caught CORBA::Exception: %s"), ex._name());
	}
}


CORBA::Boolean
AgentCorbaServer::bindObjectToName(CORBA::ORB_ptr orb, CORBA::Object_ptr objref)
{
	CosNaming::NamingContext_var rootContext;

	try {
		// Obtain a reference to the root context of the Name service:
		CORBA::Object_var obj = orb->resolve_initial_references("NameService");

		// Narrow the reference returned.
		rootContext = CosNaming::NamingContext::_narrow(obj);
		if (CORBA::is_nil(rootContext)) {
			DbgPrintf(1, _T("Failed to narrow the root naming context."));
			return false;
		}
	}
	catch (CORBA::NO_RESOURCES&) {
		DbgPrintf(1, _T("Caught NO_RESOURCES exception. You must configure omniORB "));
		DbgPrintf(1, _T("with the location of the naming service."));
		return false;
	}
	catch (CORBA::ORB::InvalidName&) {
		// This should not happen!
		DbgPrintf(1, _T("Service required is invalid [does not exist]."));
		return false;
	}

	try {
		// Bind objref with name Echo to the testContext:
		CosNaming::Name objectName;
		objectName.length(1);
		objectName[0].id   = (const char*) "AgentSide";   // string copied
		objectName[0].kind = (const char*) ""; // string copied

		try {
			rootContext->bind(objectName, objref);
		}
		catch (CosNaming::NamingContext::AlreadyBound& ex) {
			rootContext->rebind(objectName, objref);
		}
		// Note: Using rebind() will overwrite any Object previously bound
		//       to /test/Echo with obj.
		//       Alternatively, bind() can be used, which will raise a
		//       CosNaming::NamingContext::AlreadyBound exception if the name
		//       supplied is already bound to an object.
	}
	catch (CORBA::TRANSIENT& ex) {
		DbgPrintf(1, _T("Caught system exception TRANSIENT -- unable to contact the naming service."));
		DbgPrintf(1, _T(" Make sure the naming server is running and that omniORB is configured correctly."));
		return false;
	}
	catch (CORBA::SystemException& ex) {
		DbgPrintf(1, _T("Caught a CORBA::"), ex._name());
		return false;
	}
	return true;
}

INT32 AgentSide_i::getMaxId(TCHAR * tbName)
{
	INT32 result = -1;
	DB_RESULT hResult;
	DB_HANDLE hdb = DBConnectionPoolAcquireConnection();
	TCHAR query [MAX_DB_STRING];
	_sntprintf(query, sizeof query, _T("SELECT MAX(Id) FROM %s"), tbName);
	DB_STATEMENT hStmt = DBPrepare(hdb, query);
	if (hStmt != NULL)
	{
		hResult = DBSelectPrepared(hStmt);
		if (hResult != NULL)
		{
			result = DBGetFieldInt64(hResult, 0, 0);
			DBFreeResult(hResult);
		}
	}
	DBConnectionPoolReleaseConnection(hdb);
	return result;
}

void AgentSide_i::updateUploadedVideo(::CORBA::Long jobId)
{
	DbgPrintf(6, _T("AgentSide_i::[updateUploadedVideo] jobId = %ld"), jobId);
	DB_HANDLE hdb = DBConnectionPoolAcquireConnection();
	DB_STATEMENT hStmt;

	if (hdb != NULL)
	{
		hStmt = DBPrepare(hdb, _T("DELETE FROM video_container WHERE Id = ?"));
		DBBind(hStmt, 1, DB_SQLTYPE_INTEGER, (INT32)jobId);
		bool success = DBExecute(hStmt);
		if (success == true)
		{
			DbgPrintf(6, _T("AgentSide_i::[updateUploadedVideo] delete video record successful"));
			//TODO: delete video on disk
		}
		else {
			DbgPrintf(1, _T("AgentSide_i::[updateUploadedVideo] ERROR! Can not delete vide record for jobId: %ld"), jobId);
		}
	}
	DBConnectionPoolReleaseConnection(hdb);
}

::SpiderCorba::SpiderDefine::RenderConfig* AgentSide_i::getRenderConfig(::CORBA::Long mappingId, ::CORBA::Long mappingType)
{
	DbgPrintf(6, _T(" AgentSide_i::[getRenderConfig] mappingId = %d"), mappingId);
	::SpiderCorba::SpiderDefine::RenderConfig* renderCfg = new ::SpiderCorba::SpiderDefine::RenderConfig();
	DB_RESULT hResult;
	INT32 dwNumRecords;
	DB_HANDLE hdb = DBConnectionPoolAcquireConnection();
	TCHAR query [MAX_DB_STRING];
	_sntprintf(query, sizeof query, _T("SELECT VideoIntro, VideoOutro , Logo, EnableIntro, EnableOutro, EnableLogo ")
	           _T(" FROM spider_mapping_config WHERE MappingId = %d AND MappingType = %d"), (INT32)mappingId, (INT32)mappingType);
	DbgPrintf(6, _T(" AgentSide_i::[getRenderConfig] SQL query = %s"), query);
	DB_STATEMENT hStmt = DBPrepare(hdb, query);
	if (hStmt != NULL)
	{
		hResult = DBSelectPrepared(hStmt);
		if (hResult != NULL)
		{
			dwNumRecords = DBGetNumRows(hResult);
			DbgPrintf(6, _T(" AgentSide_i::[getRenderConfig] Num ber record = %d"), dwNumRecords);
			if (dwNumRecords > 0)
			{
				renderCfg->vIntroTemp = CORBA::wstring_dup(DBGetField(hResult, 0, 0, NULL, 0));
				renderCfg->vOutroTemp = CORBA::wstring_dup(DBGetField(hResult, 0, 1, NULL, 0));
				renderCfg->vLogoTemp = CORBA::wstring_dup(DBGetField(hResult, 0, 2, NULL, 0));
				renderCfg->enableIntro = DBGetFieldInt64(hResult, 0, 3) == 1;
				renderCfg->enableOutro = DBGetFieldInt64(hResult, 0, 4) == 1;
				renderCfg->enableLogo = DBGetFieldInt64(hResult, 0, 5) == 1;
			} else {
				DbgPrintf(1, _T(" AgentSide_i::[getRenderConfig] Not found render config data in database with mappingId = %d and mapping type = %d"),
				          mappingId, mappingType);
			}

			DBFreeResult(hResult);
		}
		else {
			DbgPrintf(1, _T(" AgentSide_i::[getRenderConfig] result is NULL"));
		}
	}
	else {
		DbgPrintf(1, _T(" AgentSide_i::[getRenderConfig] mappingId = Can not prepare query command"));
	}
	DBConnectionPoolReleaseConnection(hdb);
	return renderCfg;
}

::SpiderCorba::SpiderDefine::UploadConfig* AgentSide_i::getUploadConfig(::CORBA::Long mappingId, ::CORBA::Long mappingType)
{
	DbgPrintf(6, _T(" AgentSide_i::[getUploadConfig] mappingId = %d"), mappingId);
	::SpiderCorba::SpiderDefine::UploadConfig* uploadCfg = new ::SpiderCorba::SpiderDefine::UploadConfig();
	DB_RESULT hResult;
	INT32 dwNumRecords;
	DB_HANDLE hdb = DBConnectionPoolAcquireConnection();
	TCHAR query [MAX_DB_STRING];
	_sntprintf(query, sizeof query, _T("SELECT TitleTemplate, DescTemplate , TagTemplate, EnableTitle, ")
	           _T(" EnableDesc, EnableTag FROM spider_mapping_config WHERE MappingId = %d AND MappingType = %d"), mappingId, mappingType);
	DbgPrintf(6, _T(" AgentSide_i::[getUploadConfig] SQL query = %s"), query);
	DB_STATEMENT hStmt = DBPrepare(hdb, query);
	if (hStmt != NULL)
	{
		hResult = DBSelectPrepared(hStmt);
		if (hResult != NULL)
		{
			dwNumRecords = DBGetNumRows(hResult);
			DbgPrintf(6, _T(" AgentSide_i::[getUploadConfig] number record = %d"), dwNumRecords);
			if (dwNumRecords > 0)
			{
				uploadCfg->vTitleTemp = CORBA::wstring_dup(DBGetField(hResult, 0, 0, NULL, 0));
				uploadCfg->vDescTemp = CORBA::wstring_dup(DBGetField(hResult, 0, 1, NULL, 0));
				uploadCfg->vTagsTemp = CORBA::wstring_dup(DBGetField(hResult, 0, 2, NULL, 0));
				uploadCfg->enableTitle = DBGetFieldInt64(hResult, 0, 3) == 1;
				uploadCfg->enableDes = DBGetFieldInt64(hResult, 0, 4) == 1;
				uploadCfg->enableTags = DBGetFieldInt64(hResult, 0, 5) == 1;
			}
			DBFreeResult(hResult);
		}
		else {
			DbgPrintf(1, _T(" AgentSide_i::[getUploadConfig] result is NULL"));
		}
	}
	else {
		DbgPrintf(1, _T(" AgentSide_i::[getUploadConfig] mappingId = Can not prepare query command"));
	}
	DBConnectionPoolReleaseConnection(hdb);
	return uploadCfg;
}

::SpiderCorba::SpiderDefine::ClusterInfo* AgentSide_i::getClusterInfo(::CORBA::Long mappingId, ::CORBA::Long mappingType, ::CORBA::Long clusterType)
{
	DbgPrintf(6, _T(" Function [getClusterInfo] mappingId = %ld - mapping type  = %ld"), mappingId, mappingType);
	::SpiderCorba::SpiderDefine::ClusterInfo* clusterInfo = new ::SpiderCorba::SpiderDefine::ClusterInfo();
	DB_RESULT hResult;
	UINT32 dwNumRecords;
	TCHAR query [MAX_DB_STRING];

	DB_HANDLE hdb = DBConnectionPoolAcquireConnection();
	TCHAR* tbClusterName = getClusterTableNameByType((INT32)clusterType);
	TCHAR* tbMappingName = getMappingTableNameByType((INT32)mappingType);
	_sntprintf(query, sizeof query, _T("SELECT RenderClusterId FROM %s WHERE Id = %d"), tbMappingName, mappingId);
	DB_STATEMENT hStmt = DBPrepare(hdb, query);
	if (hStmt != NULL)
	{
		DBBind(hStmt, 1, DB_SQLTYPE_INTEGER, (INT32)mappingId);
		hResult = DBSelectPrepared(hStmt);
		if (hResult != NULL)
		{
			dwNumRecords = DBGetNumRows(hResult);
			if (dwNumRecords > 0)
			{
				TCHAR* clusterId  = DBGetField(hResult, 0, 0, NULL, 0);
				_sntprintf(query, sizeof query, _T("SELECT IpAddress FROM %s WHERE ClusterId = '%s'"), tbClusterName, clusterId);
				hStmt = DBPrepare(hdb, query);
				if (hStmt != NULL)
				{
					hResult = DBSelectPrepared(hStmt);
					if (hResult != NULL) {
						dwNumRecords = DBGetNumRows(hResult);
						if (dwNumRecords > 0)
						{
							clusterInfo->clusterIp = DBGetField(hResult, 0, 0, NULL, 0);
							clusterInfo->userName = _T("phongtran0715");
							clusterInfo->password = _T("123456aA@");
							DbgPrintf(6, _T(" Function [getClusterInfo] cluster IP = %d"), clusterInfo->clusterIp);
						}
					}
				}
			}
			DBFreeResult(hResult);
		}
	}
	DBConnectionPoolReleaseConnection(hdb);
	return clusterInfo;
}


::SpiderCorba::SpiderDefine::AuthenInfo* AgentSide_i::getAuthenInfo(::CORBA::Long mappingId, ::CORBA::Long mappingType)
{
	DbgPrintf(6, _T(" Function [getAuthenInfo] mappingId = %ld"), mappingId);
	::SpiderCorba::SpiderDefine::AuthenInfo* authenInfo = new ::SpiderCorba::SpiderDefine::AuthenInfo();
	DB_RESULT hResult;
	UINT32 dwNumRecords;
	TCHAR query [MAX_DB_STRING];

	TCHAR* tbName = getMappingTableNameByType((INT32)mappingType);
	DB_HANDLE hdb = DBConnectionPoolAcquireConnection();
	_sntprintf(query, sizeof query, _T("SELECT HomeChannelId FROM %s WHERE Id = ?"), tbName, mappingId);
	DbgPrintf(6, _T(" Function [getAuthenInfo] mappingId = SQL query = %s"), query);
	DB_STATEMENT hStmt = DBPrepare(hdb, query);
	if (hStmt != NULL)
	{
		DBBind(hStmt, 1, DB_SQLTYPE_INTEGER, (INT32)mappingId);
		hResult = DBSelectPrepared(hStmt);
		if (hResult != NULL)
		{
			dwNumRecords = DBGetNumRows(hResult);
			if (dwNumRecords > 0)
			{
				TCHAR* cHomeId  = DBGetField(hResult, 0, 0, NULL, 0);
				hStmt = DBPrepare(hdb, _T("SELECT UserName, Api, ClientSecret, ClientId FROM google_account WHERE Id IN ")
				                  _T(" (SELECT AccountId FROM home_channel_list WHERE ChannelId = ?)"));
				if (hStmt != NULL)
				{
					DBBind(hStmt, 1, DB_SQLTYPE_VARCHAR, cHomeId, DB_BIND_TRANSIENT);
					hResult = DBSelectPrepared(hStmt);
					if (hResult != NULL) {
						dwNumRecords = DBGetNumRows(hResult);
						if (dwNumRecords > 0)
						{
							authenInfo->userName = CORBA::wstring_dup(DBGetField(hResult, 0, 0, NULL, 0));
							authenInfo->apiKey = CORBA::wstring_dup(DBGetField(hResult, 0, 1, NULL, 0));
							authenInfo->clientSecret = CORBA::wstring_dup(DBGetField(hResult, 0, 2, NULL, 0));
							authenInfo->clientId = CORBA::wstring_dup(DBGetField(hResult, 0, 3, NULL, 0));
						}
					}
				}
			}
			DBFreeResult(hResult);
		}
	}
	DBConnectionPoolReleaseConnection(hdb);
	return authenInfo;
}

/*
::SpiderCorba::SpiderCorba::SpiderDefine::VideoInfo AgentSide_i::getVideoInfo(TCHAR* videoId, INT32 mappingId)
{
	DbgPrintf(6, _T(" Function [getVideoInfo] videoId = %s and mapping id = %d"), videoId, mappingId);
	::SpiderCorba::SpiderDefine::VideoInfo vInfo;
	DB_RESULT hResult;
	UINT32 dwNumRecords;

	DB_HANDLE hdb = DBConnectionPoolAcquireConnection();
	TCHAR query [MAX_DB_STRING];
	_sntprintf(query, sizeof query, _T("SELECT Title, Tag, Description, Thumbnail, VDownloadedPath, VRenderedPath, ")
	           _T(" ProcessStatus, License FROM video_container WHERE VideoId = '%s' AND MappingId = %d"), videoId, mappingId);
	DB_STATEMENT hStmt = DBPrepare(hdb, query);
	DbgPrintf(6, _T(" Function [getVideoInfo] SQL query= %s"), query);

	if (hStmt != NULL)
	{
		DBBind(hStmt, 1, DB_SQLTYPE_VARCHAR, videoId, NULL, 0);
		DBBind(hStmt, 2, DB_SQLTYPE_INTEGER, mappingId);
		hResult = DBSelectPrepared(hStmt);
		if (hResult != NULL)
		{
			dwNumRecords = DBGetNumRows(hResult);
			DbgPrintf(6, _T(" Function [getVideoInfo] number record = %d"), dwNumRecords);
			if (dwNumRecords > 0)
			{
				vInfo.videoId = CORBA::wstring_dup(videoId);
				vInfo.title = CORBA::wstring_dup(DBGetField(hResult, 0, 0, NULL, 0));
				vInfo.tags = CORBA::wstring_dup(DBGetField(hResult, 0, 1, NULL, 0));
				vInfo.description = CORBA::wstring_dup(DBGetField(hResult, 0, 2, NULL, 0));
				vInfo.thumbnail = CORBA::wstring_dup(DBGetField(hResult, 0, 3, NULL, 0));
				vInfo.vDownloadPath = CORBA::wstring_dup(DBGetField(hResult, 0, 4, NULL, 0));
				vInfo.vRenderPath = CORBA::wstring_dup(DBGetField(hResult, 0, 5, NULL, 0));
				vInfo.processStatus = DBGetFieldInt64(hResult, 0, 6);
				vInfo.license = DBGetFieldInt64(hResult, 0, 7);
				vInfo.mappingId = mappingId;
			} else {
				DbgPrintf(1, _T(" Function [getVideoInfo] Not found data in database"));
			}
			DBFreeResult(hResult);
		}
	}
	DBConnectionPoolReleaseConnection(hdb);
	return vInfo;
}
*/
AgentCorbaServer::~AgentCorbaServer()
{
}

