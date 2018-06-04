#ifndef _nms_corba_h_
#define _nms_corba_h_

#include "SpiderAgentAPI.hh"

#ifdef _WIN32
#ifdef LIBCORBA_EXPORTS
#define LIBCORBA_EXPORTABLE __declspec(dllexport)
#else
#define LIBCORBA_EXPORTABLE __declspec(dllimport)
#endif
#else    /* _WIN32 */
#define LIBCORBA_EXPORTABLE
#endif

struct RenderConifgParam
{
   TCHAR* vIntro;
   TCHAR* vOutro;
   TCHAR* vLogo;
   bool enableIntro;
   bool enableOutro;
   bool enableLogo;
};

class LIBCORBA_EXPORTABLE SpiderDownloadClient
{
private:
   CORBA::Object_ptr getObjectReference(CORBA::ORB_ptr orb);
public:
   SpiderDownloadApp::SpiderFootSide_var mDownloadRef;
   CORBA::ORB_var mOrb;
   bool initSuccess;
   SpiderDownloadClient();
   ~SpiderDownloadClient();
};

class LIBCORBA_EXPORTABLE SpiderRenderClient
{
private:
   CORBA::Object_ptr getObjectReference(CORBA::ORB_ptr orb);
public:
   SpiderRenderApp::SpiderFootSide_var mRenderRef;
   CORBA::ORB_var mOrb;
   bool initSuccess;
   SpiderRenderClient();
   ~SpiderRenderClient();
};

class LIBCORBA_EXPORTABLE SpiderUploadClient
{
private:
   CORBA::Object_ptr getObjectReference(CORBA::ORB_ptr orb);
public:
   SpiderUploadApp::SpiderFootSide_var mUploadRef;
   CORBA::ORB_var mOrb;
   bool initSuccess;
   SpiderUploadClient();
   ~SpiderUploadClient();
};

class AgentCorbaServer
{
private:
   CORBA::Boolean bindObjectToName(CORBA::ORB_ptr, CORBA::Object_ptr);
   INT32 getMaxId(TCHAR * tbName);
public:
   void initCorba();
   bool initSuccess;
   AgentCorbaServer();
   ~AgentCorbaServer();
};


class AgentSide_i : public POA_SpiderAgentApp::AgentSide
{
private:
   RenderConifgParam* getRenderConfig(INT32 mappingId);
   INT32 getMaxId(TCHAR * tbName);
public:
   void onDownloadStartup(const ::CORBA::WChar* appId);
   void onRenderStartup(const ::CORBA::WChar* appId);
   void onUploadStartup(const ::CORBA::WChar* appId);
   ::CORBA::LongLong getLastSyncTime(::CORBA::Long mappingId);
   void updateLastSyntime(::CORBA::Long mappingId, ::CORBA::LongLong lastSyncTime);
   void updateDownloadedVideo(const ::SpiderAgentApp::AgentSide::VideoInfo& vInfo);
   void updateRenderedVideo(::CORBA::Long jobId, ::CORBA::Long processStatus, const ::CORBA::WChar* vRenderPath);
   void updateUploadedVideo(::CORBA::Long jobId);
   ::CORBA::WChar* getMonitorChannelId(::CORBA::Long mappingId);
};

#endif /* _nms_corba_h_ */