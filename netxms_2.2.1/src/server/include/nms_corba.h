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

class LIBCORBA_EXPORTABLE SpiderDownloadClient
{
private:
   CORBA::Object_ptr getObjectReference(CORBA::ORB_ptr orb);
public:
   SpiderCorba::DownloadSide_var mDownloadRef;
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
   SpiderCorba::RenderSide_var mRenderRef;
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
   SpiderCorba::UploadSide_var mUploadRef;
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


class AgentSide_i : public POA_SpiderCorba::AgentSide
{
private:
   ::SpiderCorba::RenderSide::RenderConfig getRenderConfig(INT32 mappingId);
   ::SpiderCorba::UploadSide::UploadConfig getUploadConfig(INT32 mappingId);
   ::SpiderCorba::SpiderDefine::VideoInfo getVideoInfo(TCHAR* videoId, INT32 mappingId);
   TCHAR* getVideoContainerField(INT32 jobId, TCHAR* fieldName);
   INT32 getMaxId(TCHAR * tbName);
public:
   void onDownloadStartup(const ::CORBA::WChar* appId);
   void onRenderStartup(const ::CORBA::WChar* appId);
   void onUploadStartup(const ::CORBA::WChar* appId);
   ::CORBA::LongLong getLastSyncTime(::CORBA::Long mappingId);
   void updateLastSyntime(::CORBA::Long mappingId, ::CORBA::LongLong lastSyncTime);
   void updateDownloadedVideo(const ::SpiderCorba::SpiderDefine::VideoInfo& vInfo);
   void updateRenderedVideo(::CORBA::Long jobId, ::CORBA::Long processStatus, const ::CORBA::WChar* vRenderPath);
   void updateUploadedVideo(::CORBA::Long jobId);
   ::CORBA::WChar* getMonitorChannelId(::CORBA::Long mappingId);
   ::SpiderCorba::AgentSide::ClusterInfo* getClusterInfo(::CORBA::Long clusterType, ::CORBA::Long mappingId);
   ::SpiderCorba::AgentSide::AuthenInfo* getAuthenInfo(::CORBA::Long mappingId);
};

#endif /* _nms_corba_h_ */