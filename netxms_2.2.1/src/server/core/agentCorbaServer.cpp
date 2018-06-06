#include "nxcore.h"

#ifdef HAVE_STD
#  include <iostream>
using namespace std;
#else
#  include <iostream.h>
#endif

void AgentSide_i::onDownloadStartup(const ::CORBA::WChar* appId)
{
	DbgPrintf(1, _T("AgentSide_i::[onDownloadStartup] Download cluster"));
	DB_RESULT hResult;
	UINT32 i, dwNumRecords;
	SpiderDownloadClient* downloadClient = new SpiderDownloadClient();
	DB_HANDLE hdb = DBConnectionPoolAcquireConnection();
	DB_STATEMENT hStmt = DBPrepare(hdb, _T("SELECT * FROM channel_mapping WHERE StatusSync = ? AND DownloadClusterId = ?"));
	if (hStmt != NULL)
	{
		DBBind(hStmt, 1, DB_SQLTYPE_INTEGER, (INT32)1);
		DBBind(hStmt, 2, DB_SQLTYPE_VARCHAR, (const TCHAR *)appId, DB_BIND_TRANSIENT);
		hResult = DBSelectPrepared(hStmt);
		if (hResult != NULL)
		{
			dwNumRecords = DBGetNumRows(hResult);
			DbgPrintf(1, _T("AgentSide_i::[DownloadStartup] numRecord = %d"), dwNumRecords);
			for (i = 0; i < dwNumRecords; i++)
			{
				INT32 id = DBGetFieldInt64(hResult, i, 0);
				TCHAR* cHomeId = DBGetField(hResult, i, 1, NULL, 0);
				TCHAR* cMonitorId = DBGetField(hResult, i, 2, NULL, 0);
				INT64 timeSync = DBGetFieldInt64(hResult, i, 3);
				if (downloadClient->initSuccess)
				{
					if (downloadClient->mDownloadRef != NULL)
					{
						try
						{
							downloadClient->mDownloadRef->createMappingChannel(id, CORBA::wstring_dup(cHomeId),
							        CORBA::wstring_dup(cMonitorId), CORBA::wstring_dup((TCHAR*)appId), timeSync);
							DbgPrintf(1, _T("AgentSide_i::[onDownloadStartup] : home id = %s"), CORBA::wstring_dup(cHomeId));
							DbgPrintf(1, _T("AgentSide_i::[onDownloadStartup] : monitor id = %s"), CORBA::wstring_dup(cMonitorId));
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
			DBFreeResult(hResult);
		}
	}
	DBConnectionPoolReleaseConnection(hdb);
}

void AgentSide_i::onRenderStartup(const ::CORBA::WChar* appId)
{
	DbgPrintf(6, _T("AgentSide_i::[onRenderStartup]"));
	DB_RESULT hResult;
	UINT32 i, dwNumRecords;
	SpiderRenderClient* renderClient = new SpiderRenderClient();
	DB_HANDLE hdb = DBConnectionPoolAcquireConnection();
	DB_STATEMENT hStmt = DBPrepare(hdb, _T("SELECT Id, VideoId, MappingId, VDownloadedPath FROM video_container WHERE MappingId IN ")
	                               _T(" (SELECT Id FROM channel_mapping WHERE ProcessClusterId = ?) AND ProcessStatus = ?"));
	if (hStmt != NULL)
	{
		DBBind(hStmt, 1, DB_SQLTYPE_VARCHAR, (const TCHAR*)appId, NULL, 0);
		DBBind(hStmt, 2, DB_SQLTYPE_INTEGER, (INT32)1);
		hResult = DBSelectPrepared(hStmt);
		if (hResult != NULL)
		{
			dwNumRecords = DBGetNumRows(hResult);
			DbgPrintf(6, _T("AgentSide_i::[onRenderStartup] numRecord = %d"), dwNumRecords);
			for (i = 0; i < dwNumRecords; i++)
			{
				INT32 id = DBGetFieldInt64(hResult, i, 0);
				TCHAR* videoId = DBGetField(hResult, i, 1, NULL, 0);
				INT32 mappingId = DBGetFieldInt64(hResult, i, 2);
				TCHAR* downloadPath = DBGetField(hResult, i, 3, NULL, 0);
				if (renderClient->initSuccess)
				{
					if (renderClient->mRenderRef != NULL)
					{
						try
						{
							RenderConifgParam* renderConfig =  getRenderConfig(mappingId);
							if (renderConfig == nullptr)
							{
								DbgPrintf(1, _T("AgentSide_i::[onRenderStartup] : Can not get render config for mappingId = %d"), mappingId);
								return;
							}
							::SpiderRenderApp::SpiderFootSide::RenderInfo renderInfo;
							renderInfo.jobId = id;
							renderInfo.videoId = CORBA::wstring_dup(videoId);
							renderInfo.vIntro = CORBA::wstring_dup(renderConfig->vIntro);
							renderInfo.vOutro = CORBA::wstring_dup(renderConfig->vOutro);
							renderInfo.vLogo = CORBA::wstring_dup(renderConfig->vLogo);
							renderInfo.enableIntro = renderConfig->enableIntro;
							renderInfo.enableOutro = renderConfig->enableOutro;
							renderInfo.enableLogo = renderConfig->enableLogo;
							renderInfo.vLocation = CORBA::wstring_dup(downloadPath);

							renderClient->mRenderRef->createRenderJob(renderInfo);
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
			DBFreeResult(hResult);
		}
	}
	DBConnectionPoolReleaseConnection(hdb);
}

void AgentSide_i::onUploadStartup(const ::CORBA::WChar* appId)
{
	DbgPrintf(6, _T("AgentSide_i::onUploadStartup : uploadId = %s"), appId);
	DB_RESULT hResult;
	UINT32 i, dwNumRecords;
	SpiderUploadClient* uploadClient = new SpiderUploadClient();

	DB_HANDLE hdb = DBConnectionPoolAcquireConnection();
	DB_STATEMENT hStmt = DBPrepare(hdb, _T("SELECT Id, VideoId, Title, Tag, Description, Thumbnail, ")
	                               _T(" VRenderedPath, MappingId FROM video_container WHERE MappingId IN ")
	                               _T(" (SELECT MappingId FROM channel_mapping WHERE UploadClusterId = ?) AND ProcessStatus = ?"));
	if (hStmt != NULL)
	{
		DBBind(hStmt, 1, DB_SQLTYPE_VARCHAR, (const TCHAR*) appId, DB_BIND_TRANSIENT);
		DBBind(hStmt, 2, DB_SQLTYPE_INTEGER, (INT32)2);
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
				TCHAR* vLocation = DBGetField(hResult, i, 6, NULL, 0);
				INT32 mappingId = DBGetFieldInt64(hResult, i, 7);

				if (uploadClient->initSuccess)
				{
					if (uploadClient->mUploadRef != NULL)
					{
						try
						{
							::SpiderUploadApp::SpiderFootSide::UploadInfo vInfo;
							vInfo.jobId = id;
							vInfo.videoId = CORBA::wstring_dup(videoId);
							vInfo.vTitle = CORBA::wstring_dup(vTitle);
							vInfo.vDesc = CORBA::wstring_dup(vDesc);
							vInfo.vTags = CORBA::wstring_dup(vTags);
							vInfo.vThumbnail = CORBA::wstring_dup(vThumb);
							vInfo.vLocation = CORBA::wstring_dup(vLocation);
							vInfo.mappingId = mappingId;

							uploadClient->mUploadRef->createUploadJob(vInfo);
						}
						catch (CORBA::TRANSIENT&) {
							DbgPrintf(1, _T("AgentSide_i::[onUploadStartup] : Caught system exception TRANSIENT -- unable to contact the server"));
						}
						catch (CORBA::SystemException& ex) {
							DbgPrintf(1, _T("AgentSide_i::[onUploadStartup] : Caught a CORBA:: %s"), ex._name());
						}
						catch (CORBA::Exception& ex)
						{
							DbgPrintf(1, _T("AgentSide_i::[onUploadStartup] : Caught a CORBA:: %s"), ex._name());
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

::CORBA::Boolean AgentSide_i::createUploadJob(const ::SpiderUploadApp::SpiderFootSide::UploadInfo& vInfo)
{
	bool result = false;
	return result;
}

::CORBA::LongLong AgentSide_i::getLastSyncTime(::CORBA::Long mappingId)
{
	DbgPrintf(6, _T("AgentSide_i::getLastSyncTime : mappingId = %ld"), mappingId);
	INT32 result = 0;
	DB_RESULT hResult;
	UINT32 i, dwNumRecords;
	DB_HANDLE hdb = DBConnectionPoolAcquireConnection();
	DB_STATEMENT hStmt = DBPrepare(hdb, _T("SELECT LastSyncTime FROM channel_mapping WHERE Id = ?"));
	if (hStmt != NULL)
	{
		DBBind(hStmt, 1, DB_SQLTYPE_INTEGER, (INT32)mappingId);
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

void AgentSide_i::updateLastSyntime(::CORBA::Long mappingId, ::CORBA::LongLong lastSyncTime)
{
	DbgPrintf(6, _T("AgentSide_i::[updateLastSyntime]"));
	DB_HANDLE hdb = DBConnectionPoolAcquireConnection();
	DB_STATEMENT hStmt;
	if (hdb != NULL)
	{
		hStmt = DBPrepare(hdb, _T("UPDATE channel_mapping SET LastSyncTime = ? WHERE Id = ?"));
		DBBind(hStmt, 1, DB_SQLTYPE_INTEGER, (INT32)lastSyncTime);
		DBBind(hStmt, 2, DB_SQLTYPE_INTEGER, (INT32)mappingId);
		DBExecute(hStmt);
	}
	DBConnectionPoolReleaseConnection(hdb);
}

void AgentSide_i::updateDownloadedVideo(const ::SpiderAgentApp::AgentSide::VideoInfo& vInfo)
{
	DbgPrintf(6, _T("AgentSide_i::[updateDownloadedVideo]"));
	DB_HANDLE hdb = DBConnectionPoolAcquireConnection();
	DB_STATEMENT hStmt;

	if (hdb != NULL)
	{
		hStmt = DBPrepare(hdb, _T("INSERT INTO video_container (VideoId, Title, Tag, ")
		                  _T(" Description, Thumbnail, VDownloadedPath,")
		                  _T("MappingId, ProcessStatus, License) VALUES (?,?,?,?,?,?,?,?,?)"));

		DBBind(hStmt, 1, DB_SQLTYPE_VARCHAR, (const TCHAR *)CORBA::wstring_dup(vInfo.videoId), DB_BIND_TRANSIENT);
		DBBind(hStmt, 2, DB_SQLTYPE_VARCHAR, (const TCHAR *)CORBA::wstring_dup(vInfo.title), DB_BIND_TRANSIENT);
		DBBind(hStmt, 3, DB_SQLTYPE_VARCHAR, (const TCHAR *)CORBA::wstring_dup(vInfo.tags), DB_BIND_TRANSIENT);
		DBBind(hStmt, 4, DB_SQLTYPE_VARCHAR, (const TCHAR *)CORBA::wstring_dup(vInfo.description), DB_BIND_TRANSIENT);
		DBBind(hStmt, 5, DB_SQLTYPE_VARCHAR, (const TCHAR *)CORBA::wstring_dup(vInfo.thumbnail), DB_BIND_TRANSIENT);
		DBBind(hStmt, 6, DB_SQLTYPE_VARCHAR, (const TCHAR *)CORBA::wstring_dup(vInfo.vDownloadPath), DB_BIND_TRANSIENT);
		DBBind(hStmt, 7, DB_SQLTYPE_INTEGER, (INT32)vInfo.mappingId);
		DBBind(hStmt, 8, DB_SQLTYPE_INTEGER, (INT32)vInfo.processStatus);
		DBBind(hStmt, 9, DB_SQLTYPE_INTEGER, (INT32)vInfo.license);


		bool success = DBExecute(hStmt);
		if (success == true)
		{
			//notify to render app
			SpiderRenderClient* renderClient = new SpiderRenderClient();
			if (renderClient->initSuccess)
			{
				if (renderClient->mRenderRef != NULL)
				{
					try
					{

						RenderConifgParam* renderConfig =  getRenderConfig(vInfo.mappingId);
						if (renderConfig == nullptr)
						{
							DbgPrintf(1, _T("AgentSide_i::[] : Can not get render config for mapping id = %d"), vInfo.mappingId);
							return;
						} else {
							DbgPrintf(1, _T("AgentSide_i::[] : Get render config ok"));
						}
						::SpiderRenderApp::SpiderFootSide::RenderInfo renderInfo;
						renderInfo.jobId = getMaxId(_T("video_container"));
						renderInfo.videoId = CORBA::wstring_dup(vInfo.videoId);
						renderInfo.vIntro = CORBA::wstring_dup(renderConfig->vIntro);
						renderInfo.vOutro = CORBA::wstring_dup(renderConfig->vOutro);
						renderInfo.vLogo = CORBA::wstring_dup(renderConfig->vLogo);
						renderInfo.enableIntro = renderConfig->enableIntro;
						renderInfo.enableOutro = renderConfig->enableOutro;
						renderInfo.enableLogo = renderConfig->enableLogo;
						renderInfo.vLocation = CORBA::wstring_dup(vInfo.vDownloadPath);

						renderClient->mRenderRef->createRenderJob(renderInfo);

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

void AgentSide_i::updateRenderedVideo(::CORBA::Long jobId, ::CORBA::Long processStatus, const ::CORBA::WChar* vRenderPath)
{
	DbgPrintf(6, _T("AgentSide_i::[updateRenderedVideo] jobId = %d"), (INT32)jobId);
	DB_HANDLE hdb = DBConnectionPoolAcquireConnection();
	DB_STATEMENT hStmt;

	if (hdb != NULL)
	{
		hStmt = DBPrepare(hdb, _T("UPDATE video_container SET ProcessStatus = ?, VRenderedPath = ? WHERE Id = ?"));
		DBBind(hStmt, 1, DB_SQLTYPE_INTEGER, (INT32)processStatus);
		DBBind(hStmt, 2, DB_SQLTYPE_VARCHAR, (const TCHAR *)vRenderPath, DB_BIND_TRANSIENT);
		DBBind(hStmt, 3, DB_SQLTYPE_INTEGER, (INT32)jobId);
		bool success = DBExecute(hStmt);
		if (success == true)
		{
			//notify to render app
			SpiderUploadClient* uploadClient = new SpiderUploadClient();
			if (uploadClient->initSuccess)
			{
				if (uploadClient->mUploadRef != NULL)
				{
					try
					{
						INT32 mappingId = getMappingId(jobId);
						UploadConfigParam* uploadConfig =  getUploadConfig(mappingId);
						if (uploadConfig == nullptr)
						{
							DbgPrintf(1, _T("AgentSide_i::[] : Can not get render config for mapping id = %d"), mappingId);
							return;
						} else {
							DbgPrintf(1, _T("AgentSide_i::[] : Get upload config ok"));
						}
						::SpiderUploadApp::SpiderFootSide::UploadInfo uploadInfo;
						uploadInfo.jobId = jobId;
						uploadInfo.videoId = CORBA::wstring_dup(_T(""));
						uploadInfo.vTitle = CORBA::wstring_dup(uploadConfig->vTitle);
						uploadInfo.vDesc = CORBA::wstring_dup(uploadConfig->vDesc);
						uploadInfo.vTags = CORBA::wstring_dup(uploadConfig->vTags);
						uploadInfo.vThumbnail = CORBA::wstring_dup(uploadConfig->vThumb);
						uploadInfo.enableTitle = uploadConfig->enableTitle;
						uploadInfo.enableDes = uploadConfig->enableDesc;
						uploadInfo.enableTags = uploadConfig->enableTags;
						uploadInfo.vLocation = CORBA::wstring_dup(vRenderPath);

						uploadClient->mUploadRef->createUploadJob(uploadInfo);

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
	DbgPrintf(6, _T("AgentSide_i::[updateUploadedVideo]jobId = %ld"), jobId);
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

RenderConifgParam* AgentSide_i::getRenderConfig(INT32 mappingId)
{
	DbgPrintf(1, _T(" AgentSide_i::[getRenderConfig] mappingId = %d"), mappingId);
	RenderConifgParam* renderConfig = new RenderConifgParam();
	DB_RESULT hResult;
	DB_HANDLE hdb = DBConnectionPoolAcquireConnection();
	TCHAR query [MAX_DB_STRING];
	_sntprintf(query, sizeof query, _T("SELECT VideoIntro, VideoOutro , Logo, EnableIntro, EnableOutro, EnableLogo ")
	           _T(" FROM spider_mapping_config WHERE MappingId = '%d'"), mappingId);
	DB_STATEMENT hStmt = DBPrepare(hdb, query);
	if (hStmt != NULL)
	{
		hResult = DBSelectPrepared(hStmt);
		if (hResult != NULL)
		{
			renderConfig->vIntro = DBGetField(hResult, 0, 0, NULL, 0);
			renderConfig->vOutro = DBGetField(hResult, 0, 1, NULL, 0);
			renderConfig->vLogo = DBGetField(hResult, 0, 2, NULL, 0);
			renderConfig->enableIntro = DBGetFieldInt64(hResult, 0, 3) == 1;
			renderConfig->enableOutro = DBGetFieldInt64(hResult, 0, 4) == 1;
			renderConfig->enableLogo = DBGetFieldInt64(hResult, 0, 5) == 1;
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
	return renderConfig;
}

UploadConfigParam* AgentSide_i::getUploadConfig(INT32 mappingId)
{
	DbgPrintf(1, _T(" AgentSide_i::[getUploadConfig] mappingId = %d"), mappingId);
	UploadConfigParam* uploadConfig = new UploadConfigParam();
	DB_RESULT hResult;
	DB_HANDLE hdb = DBConnectionPoolAcquireConnection();
	TCHAR query [MAX_DB_STRING];
	_sntprintf(query, sizeof query, _T("SELECT TitleTemplate, DescTemplate , TagTemplate, EnableTitle, EnableDesc, EnableTag ")
	           _T(" FROM spider_mapping_config WHERE MappingId = '%d'"), mappingId);
	DB_STATEMENT hStmt = DBPrepare(hdb, query);
	if (hStmt != NULL)
	{
		hResult = DBSelectPrepared(hStmt);
		if (hResult != NULL)
		{
			uploadConfig->vTitle = DBGetField(hResult, 0, 0, NULL, 0);
			uploadConfig->vDesc = DBGetField(hResult, 0, 1, NULL, 0);
			uploadConfig->vTags = DBGetField(hResult, 0, 2, NULL, 0);
			uploadConfig->vThumb = _T("");
			uploadConfig->enableTitle = DBGetFieldInt64(hResult, 0, 3) == 1;
			uploadConfig->enableDesc = DBGetFieldInt64(hResult, 0, 4) == 1;
			uploadConfig->enableTags = DBGetFieldInt64(hResult, 0, 5) == 1;

			DBFreeResult(hResult);
			DbgPrintf(1, _T(" AgentSide_i::[getUploadConfig] uploadConfig->vTitle = %s"), uploadConfig->vTitle);
			DbgPrintf(1, _T(" AgentSide_i::[getUploadConfig] uploadConfig->vDesc = %s"), uploadConfig->vDesc);
			DbgPrintf(1, _T(" AgentSide_i::[getUploadConfig] uploadConfig->vTags = %s"), uploadConfig->vTags);
		}
		else {
			DbgPrintf(1, _T(" AgentSide_i::[getUploadConfig] result is NULL"));
		}
	}
	else {
		DbgPrintf(1, _T(" AgentSide_i::[getUploadConfig] mappingId = Can not prepare query command"));
	}
	DBConnectionPoolReleaseConnection(hdb);
	return uploadConfig;
}

::CORBA::WChar* AgentSide_i::getMonitorChannelId(::CORBA::Long mappingId)
{
	DbgPrintf(6, _T(" Function [getMonitorChannelId] mappingId = %d"), mappingId);
	TCHAR* result = _T("");
	DB_RESULT hResult;
	UINT32 i, dwId, dwNumRecords;

	DB_HANDLE hdb = DBConnectionPoolAcquireConnection();
	DB_STATEMENT hStmt = DBPrepare(hdb, _T("SELECT MonitorChannelId FROM channel_mapping WHERE Id = ?"));
	if (hStmt != NULL)
	{
		DBBind(hStmt, 1, DB_SQLTYPE_INTEGER, (INT32)mappingId);
		hResult = DBSelectPrepared(hStmt);
		if (hResult != NULL)
		{
			dwNumRecords = DBGetNumRows(hResult);
			if (dwNumRecords > 0)
			{
				result = DBGetField(hResult, 0, 0, NULL, 0);

				DbgPrintf(1, _T(" Function [getMonitorChannelId] result = %s"), result);
			}
			DBFreeResult(hResult);
		}
	}
	DBConnectionPoolReleaseConnection(hdb);
	return CORBA::wstring_dup(result);
}

INT32 AgentSide_i::getMappingId(INT32 jobId)
{
	DbgPrintf(6, _T(" Function [getMappingId] jobId = %d"), jobId);
	INT32 result = -1;
	DB_RESULT hResult;
	UINT32 i, dwId, dwNumRecords;

	DB_HANDLE hdb = DBConnectionPoolAcquireConnection();
	DB_STATEMENT hStmt = DBPrepare(hdb, _T("SELECT MappingId FROM video_container WHERE Id = ?"));
	if (hStmt != NULL)
	{
		DBBind(hStmt, 1, DB_SQLTYPE_INTEGER, jobId);
		hResult = DBSelectPrepared(hStmt);
		if (hResult != NULL)
		{
			dwNumRecords = DBGetNumRows(hResult);
			if (dwNumRecords > 0)
			{
				result = DBGetFieldInt64(hResult, 0, 0);

				DbgPrintf(1, _T(" Function [getMappingId] result = %d"), result);
			}
			DBFreeResult(hResult);
		}
	}
	DBConnectionPoolReleaseConnection(hdb);
	return result;
}

::SpiderAgentApp::AgentSide::ClusterInfo* AgentSide_i::getClusterInfo(::CORBA::Long clusterType, ::CORBA::Long mappingId)
{
	DbgPrintf(6, _T(" Function [getClusterInfo] mappingId = %ld"), mappingId);
	::SpiderAgentApp::AgentSide::ClusterInfo* clusterInfo = new ::SpiderAgentApp::AgentSide::ClusterInfo();
	DB_RESULT hResult;
	UINT32 i, dwNumRecords;

	DB_HANDLE hdb = DBConnectionPoolAcquireConnection();
	DB_STATEMENT hStmt = DBPrepare(hdb, _T("SELECT ProcessClusterId FROM channel_mapping WHERE Id = ?"));
	if (hStmt != NULL)
	{
		DBBind(hStmt, 1, DB_SQLTYPE_INTEGER, mappingId);
		hResult = DBSelectPrepared(hStmt);
		if (hResult != NULL)
		{
			dwNumRecords = DBGetNumRows(hResult);
			if (dwNumRecords > 0)
			{
				TCHAR* renderClusterId  = DBGetField(hResult, 0, 0, NULL, 0);
				hStmt = DBPrepare(hdb, _T("SELECT IpAddress FROM render_cluster WHERE ClusterId = ?"));
				if (hStmt != NULL)
				{
					DBBind(hStmt, 1, DB_SQLTYPE_VARCHAR, renderClusterId, DB_BIND_TRANSIENT);
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

::SpiderAgentApp::AgentSide::AuthenInfo* AgentSide_i::getAuthenInfo(::CORBA::Long mappingId)
{
	DbgPrintf(6, _T(" Function [getClusterInfo] mappingId = %ld"), mappingId);
	::SpiderAgentApp::AgentSide::AuthenInfo* authenInfo = new ::SpiderAgentApp::AgentSide::AuthenInfo();
	DB_RESULT hResult;
	UINT32 i, dwNumRecords;

	DB_HANDLE hdb = DBConnectionPoolAcquireConnection();
	DB_STATEMENT hStmt = DBPrepare(hdb, _T("SELECT HomeChannelId FROM channel_mapping WHERE Id = ?"));
	if (hStmt != NULL)
	{
		DBBind(hStmt, 1, DB_SQLTYPE_INTEGER, mappingId);
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

AgentCorbaServer::~AgentCorbaServer()
{
}

