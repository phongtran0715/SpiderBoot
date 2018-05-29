#include "nxcore.h"

#ifdef HAVE_STD
#  include <iostream>
using namespace std;
#else
#  include <iostream.h>
#endif

void AgentSide_i::onDownloadStartup(const char* appId)
{
	DbgPrintf(1, _T("AgentSide_i::[onDownloadStartup]"));
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
							downloadClient->mDownloadRef->createMappingChannel(id, CORBA::string_dup((const char*)cHomeId),
							        CORBA::string_dup((const char*)cMonitorId), CORBA::string_dup(appId), timeSync);
							DbgPrintf(1, _T("AgentSide_i::[onDownloadStartup] : home id = %s"), CORBA::string_dup((const char*)cHomeId));
							DbgPrintf(1, _T("AgentSide_i::[onDownloadStartup] : monitor id = %s"), CORBA::string_dup((const char*)cMonitorId));
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

void AgentSide_i::onRenderStartup(const char* appId)
{
	DbgPrintf(1, _T("AgentSide_i::[onDownloadStartup]"));
	DB_RESULT hResult;
	UINT32 i, dwNumRecords;
	SpiderRenderClient* renderClient = new SpiderRenderClient();
	DB_HANDLE hdb = DBConnectionPoolAcquireConnection();
	DB_STATEMENT hStmt = DBPrepare(hdb, _T("SELECT Id, VideoId, HomeChannelId, VDownloadedPath FROM video_container WHERE ProcessStatus = ? "));
	if (hStmt != NULL)
	{
		DBBind(hStmt, 1, DB_SQLTYPE_INTEGER, (INT32)1);
		hResult = DBSelectPrepared(hStmt);
		if (hResult != NULL)
		{
			dwNumRecords = DBGetNumRows(hResult);
			DbgPrintf(1, _T("AgentSide_i::[DownloadStartup] numRecord = %d"), dwNumRecords);
			for (i = 0; i < dwNumRecords; i++)
			{
				INT32 id = DBGetFieldInt64(hResult, i, 0);
				TCHAR* videoId = DBGetField(hResult, i, 1, NULL, 0);
				TCHAR* cHomeId = DBGetField(hResult, i, 2, NULL, 0);
				TCHAR* downloadPath = DBGetField(hResult, i, 3, NULL, 0);
				if (renderClient->initSuccess)
				{
					if (renderClient->mRenderRef != NULL)
					{
						try
						{
							//TODO: create render info struct
							::SpiderRenderApp::SpiderFootSide::RenderInfo vInfo;
							vInfo.jobId = id;
							vInfo.videoId = CORBA::string_dup((const char*)videoId);
							vInfo.vIntro = CORBA::string_dup((const char*)"/home/phongtran0715/Downloads/Video/test/intro.mp4");
							vInfo.vOutro = CORBA::string_dup((const char*)"/home/phongtran0715/Downloads/Video/test/outro.mp4");
							vInfo.vLogo = CORBA::string_dup((const char*)"/home/phongtran0715/Downloads/Video/test/logo.png");
							vInfo.vdownloadPath = CORBA::string_dup((const char*)"/home/phongtran0715/Downloads/Video/test/input.mp4");
							renderClient->mRenderRef->createRenderJob(id, vInfo);
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

void AgentSide_i::onUploadStartup(const char* appId)
{
	DB_RESULT hResult;
	UINT32 i, dwNumRecords;
	SpiderUploadClient* uploadClient = new SpiderUploadClient();
	DB_HANDLE hdb = DBConnectionPoolAcquireConnection();
	DB_STATEMENT hStmt = DBPrepare(hdb, _T("SELECT * FROM video_container WHERE ProcessStatus = ? AND UploadClusterId = ?"));
	if (hStmt != NULL)
	{
		//DBBind(hStmt, 1, DB_SQLTYPE_INTEGER, (INT32)VIDEO_RENDERED);
		DBBind(hStmt, 1, DB_SQLTYPE_INTEGER, (INT32)2);
		DBBind(hStmt, 2, DB_SQLTYPE_VARCHAR, (const TCHAR*) appId, DB_BIND_DYNAMIC);
		hResult = DBSelectPrepared(hStmt);
		if (hResult != NULL)
		{
			dwNumRecords = DBGetNumRows(hResult);
			for (i = 0; i < dwNumRecords; i++)
			{
				//TODO: send information to upload app
			}
			DBFreeResult(hResult);
		}
	}
	DBConnectionPoolReleaseConnection(hdb);
}

::CORBA::LongLong AgentSide_i::getLastSyncTime(::CORBA::Long mappingId)
{
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
	DbgPrintf(1, _T("AgentSide_i::updateLastSyntime : mappingId = %ld"), mappingId);
	DbgPrintf(1, _T("AgentSide_i::updateLastSyntime : lastSyncTime = %ld"), lastSyncTime);

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
	DB_HANDLE hdb = DBConnectionPoolAcquireConnection();
	DB_STATEMENT hStmt;

	if (hdb != NULL)
	{
		DbgPrintf(1, _T("AgentSide_i::updateDownloadedVideo : videoId = %s"), vInfo.videoId);
		DbgPrintf(1, _T("AgentSide_i::updateDownloadedVideo : title = %s"), vInfo.title);

		hStmt = DBPrepare(hdb, _T("INSERT INTO video_container (VideoId, Title, Tag, ")
		                  _T(" Description, Thumbnail, VDownloadedPath, HomeChannelId,")
		                  _T(" MonitorChannelId, ProcessStatus, License) VALUES (?,?,?,?,?,?,?,?,?,?)"));
		DBBind(hStmt, 1, DB_SQLTYPE_VARCHAR, (const TCHAR *)vInfo.videoId, DB_BIND_TRANSIENT);
		DBBind(hStmt, 2, DB_SQLTYPE_VARCHAR, (const TCHAR *)vInfo.title, DB_BIND_TRANSIENT);
		DBBind(hStmt, 3, DB_SQLTYPE_VARCHAR, (const TCHAR *)vInfo.tags, DB_BIND_TRANSIENT);
		DBBind(hStmt, 4, DB_SQLTYPE_VARCHAR, (const TCHAR *)vInfo.description, DB_BIND_TRANSIENT);
		DBBind(hStmt, 5, DB_SQLTYPE_VARCHAR, (const TCHAR *)vInfo.thumbnail, DB_BIND_TRANSIENT);
		DBBind(hStmt, 6, DB_SQLTYPE_VARCHAR, (const TCHAR *)vInfo.vDownloadPath, DB_BIND_TRANSIENT);
		DBBind(hStmt, 7, DB_SQLTYPE_VARCHAR, (const TCHAR *)vInfo.homeChannelId, DB_BIND_TRANSIENT);
		DBBind(hStmt, 8, DB_SQLTYPE_VARCHAR, (const TCHAR *)vInfo.monitorChannelId, DB_BIND_TRANSIENT);
		DBBind(hStmt, 9, DB_SQLTYPE_INTEGER, (INT32)vInfo.processStatus);
		DBBind(hStmt, 10, DB_SQLTYPE_INTEGER, (INT32)vInfo.license);

		bool success = DBExecute(hStmt);

		if (success == false)
		{
			DbgPrintf(1, _T("AgentSide_i::updateDownloadedVideo : insert new video info FALSE"));
		}
	}
	DBConnectionPoolReleaseConnection(hdb);
}

void AgentSide_i::updateRenderedVideo(::CORBA::Long videoId, ::CORBA::Long processStatus, const char* videoLocation)
{

}

void AgentSide_i::updateUploadedVideo(::CORBA::Long videoId, ::CORBA::Long processStatus, const char* videoLocation)
{

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
			cout << "ERROR ! scan not bind object to name... " << endl;
			return;
		}

		PortableServer::POAManager_var pman = poa->the_POAManager();
		pman->activate();
		cout << "server is running and waiting connection from client... " << endl;

		orb->run();
	}
	catch (CORBA::SystemException& ex) {
		cerr << "Caught CORBA::" << ex._name() << endl;
	}
	catch (CORBA::Exception& ex) {
		cerr << "Caught CORBA::Exception: " << ex._name() << endl;
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
			cerr << "Failed to narrow the root naming context." << endl;
			return false;
		}
	}
	catch (CORBA::NO_RESOURCES&) {
		cerr << "Caught NO_RESOURCES exception. You must configure omniORB "
		     << "with the location" << endl
		     << "of the naming service." << endl;
		return false;
	}
	catch (CORBA::ORB::InvalidName&) {
		// This should not happen!
		cerr << "Service required is invalid [does not exist]." << endl;
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
		cerr << "Caught system exception TRANSIENT -- unable to contact the "
		     << "naming service." << endl
		     << "Make sure the naming server is running and that omniORB is "
		     << "configured correctly." << endl;

		return false;
	}
	catch (CORBA::SystemException& ex) {
		cerr << "Caught a CORBA::" << ex._name()
		     << " while using the naming service." << endl;
		return false;
	}
	return true;
}

AgentCorbaServer::~AgentCorbaServer()
{
}

