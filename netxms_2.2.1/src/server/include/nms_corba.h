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
   CORBA::Object_ptr getObjectReference(CORBA::ORB_ptr orb, const TCHAR* contextName);
public:
   SpiderCorba::DownloadSide_var mDownloadRef;
   CORBA::ORB_var mOrb;
   bool initSuccess;
   SpiderDownloadClient(const TCHAR* contextName);
   ~SpiderDownloadClient();
};

class LIBCORBA_EXPORTABLE SpiderRenderClient
{
private:
   CORBA::Object_ptr getObjectReference(CORBA::ORB_ptr orb, const TCHAR* contextName);
public:
   SpiderCorba::RenderSide_var mRenderRef;
   CORBA::ORB_var mOrb;
   bool initSuccess;
   SpiderRenderClient(const TCHAR* contextName);
   ~SpiderRenderClient();
};

class LIBCORBA_EXPORTABLE SpiderUploadClient
{
private:
   CORBA::Object_ptr getObjectReference(CORBA::ORB_ptr orb, const TCHAR* contextName);
public:
   SpiderCorba::UploadSide_var mUploadRef;
   CORBA::ORB_var mOrb;
   bool initSuccess;
   SpiderUploadClient(const TCHAR* contextName);
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
   INT32 getMaxId(TCHAR * tbName);
   TCHAR* getClusterId(INT32 mappingId, INT32 mappingType, INT32 clusterType);
   TCHAR* getMappingTableNameByType(INT32 mappingType);
   TCHAR* getClusterTableNameByType(INT32 clusterType);
   void createDownloadTimerByMapping(const ::CORBA::WChar* downloadClusterId, INT32 mappingType);
   void createRenderJobByMapping(const ::CORBA::WChar* renderClusterId, INT32 mappingType);
   void createUploadTimerByMapping(const ::CORBA::WChar* uploadClusterId, INT32 mappingType);
   void createUploadJobByMapping(const ::CORBA::WChar* uploadClusterId, INT32 mappingType);

public:
   void onDownloadStartup(const ::CORBA::WChar* downloadClusterId);
   ::CORBA::LongLong getLastSyncTime(::CORBA::Long mappingId, ::CORBA::Long mappingType);
   void updateLastSyntime(::CORBA::Long mappingId, ::CORBA::Long mappingType, ::CORBA::LongLong lastSyncTime);
   void updateDownloadedVideo(const ::SpiderCorba::SpiderDefine::VideoInfo& vInfo);
   void onRenderStartup(const ::CORBA::WChar* renderClusterId);
   ::SpiderCorba::SpiderDefine::RenderConfig* getRenderConfig(::CORBA::Long mappingId, ::CORBA::Long mappingType);
   void updateRenderedVideo(::CORBA::Long jobId, const ::SpiderCorba::SpiderDefine::VideoInfo& vInfo);
   void onUploadStartup(const ::CORBA::WChar* uploadClusterId);
   ::SpiderCorba::SpiderDefine::UploadConfig* getUploadConfig(::CORBA::Long mappingId, ::CORBA::Long mappingType);
   void updateUploadedVideo(::CORBA::Long jobId);
   ::SpiderCorba::SpiderDefine::AuthenInfo* getAuthenInfo(::CORBA::Long mappingId, ::CORBA::Long mappingType);
   ::SpiderCorba::SpiderDefine::ClusterInfo* getClusterInfo(::CORBA::Long mappingId, ::CORBA::Long mappingType, ::CORBA::Long clusterType);
};

#endif /* _nms_corba_h_ */