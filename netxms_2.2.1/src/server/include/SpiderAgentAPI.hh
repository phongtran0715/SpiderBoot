// This file is generated by omniidl (C++ backend)- omniORB_4_2. Do not edit.
#ifndef __SpiderAgentAPI_hh__
#define __SpiderAgentAPI_hh__

#ifndef __CORBA_H_EXTERNAL_GUARD__
#include <omniORB4/CORBA.h>
#endif

#ifndef  USE_stub_in_nt_dll
# define USE_stub_in_nt_dll_NOT_DEFINED_SpiderAgentAPI
#endif
#ifndef  USE_core_stub_in_nt_dll
# define USE_core_stub_in_nt_dll_NOT_DEFINED_SpiderAgentAPI
#endif
#ifndef  USE_dyn_stub_in_nt_dll
# define USE_dyn_stub_in_nt_dll_NOT_DEFINED_SpiderAgentAPI
#endif






#ifdef USE_stub_in_nt_dll
# ifndef USE_core_stub_in_nt_dll
#  define USE_core_stub_in_nt_dll
# endif
# ifndef USE_dyn_stub_in_nt_dll
#  define USE_dyn_stub_in_nt_dll
# endif
#endif

#ifdef _core_attr
# error "A local CPP macro _core_attr has already been defined."
#else
# ifdef  USE_core_stub_in_nt_dll
#  define _core_attr _OMNIORB_NTDLL_IMPORT
# else
#  define _core_attr
# endif
#endif

#ifdef _dyn_attr
# error "A local CPP macro _dyn_attr has already been defined."
#else
# ifdef  USE_dyn_stub_in_nt_dll
#  define _dyn_attr _OMNIORB_NTDLL_IMPORT
# else
#  define _dyn_attr
# endif
#endif



_CORBA_MODULE SpiderDownloadApp

_CORBA_MODULE_BEG

#ifndef __SpiderDownloadApp_mSpiderFootSide__
#define __SpiderDownloadApp_mSpiderFootSide__
  class SpiderFootSide;
  class _objref_SpiderFootSide;
  class _impl_SpiderFootSide;
  
  typedef _objref_SpiderFootSide* SpiderFootSide_ptr;
  typedef SpiderFootSide_ptr SpiderFootSideRef;

  class SpiderFootSide_Helper {
  public:
    typedef SpiderFootSide_ptr _ptr_type;

    static _ptr_type _nil();
    static _CORBA_Boolean is_nil(_ptr_type);
    static void release(_ptr_type);
    static void duplicate(_ptr_type);
    static void marshalObjRef(_ptr_type, cdrStream&);
    static _ptr_type unmarshalObjRef(cdrStream&);
  };

  typedef _CORBA_ObjRef_Var<_objref_SpiderFootSide, SpiderFootSide_Helper> SpiderFootSide_var;
  typedef _CORBA_ObjRef_OUT_arg<_objref_SpiderFootSide,SpiderFootSide_Helper > SpiderFootSide_out;

#endif

  // interface SpiderFootSide
  class SpiderFootSide {
  public:
    // Declarations for this interface type.
    typedef SpiderFootSide_ptr _ptr_type;
    typedef SpiderFootSide_var _var_type;

    static _ptr_type _duplicate(_ptr_type);
    static _ptr_type _narrow(::CORBA::Object_ptr);
    static _ptr_type _unchecked_narrow(::CORBA::Object_ptr);
    
    static _ptr_type _nil();

    static inline void _marshalObjRef(_ptr_type, cdrStream&);

    static inline _ptr_type _unmarshalObjRef(cdrStream& s) {
      omniObjRef* o = omniObjRef::_unMarshal(_PD_repoId,s);
      if (o)
        return (_ptr_type) o->_ptrToObjRef(_PD_repoId);
      else
        return _nil();
    }

    static inline _ptr_type _fromObjRef(omniObjRef* o) {
      if (o)
        return (_ptr_type) o->_ptrToObjRef(_PD_repoId);
      else
        return _nil();
    }

    static _core_attr const char* _PD_repoId;

    // Other IDL defined within this scope.
    
  };

  class _objref_SpiderFootSide :
    public virtual ::CORBA::Object,
    public virtual omniObjRef
  {
  public:
    // IDL operations
    ::CORBA::Boolean createMappingChannel(::CORBA::Long timerId, const ::CORBA::WChar* cHomeId, const ::CORBA::WChar* cMonitorId, const ::CORBA::WChar* downloadClusterId, ::CORBA::Long timerInterval);
    ::CORBA::Boolean modifyMappingChannel(::CORBA::Long timerId, const ::CORBA::WChar* cHomeId, const ::CORBA::WChar* cMonitorId, const ::CORBA::WChar* downloadClusterId, ::CORBA::Long timerInterval, ::CORBA::Long synStatus);
    ::CORBA::Boolean deleteMappingChannel(::CORBA::Long timerId, const ::CORBA::WChar* downloadClusterId);

    // Constructors
    inline _objref_SpiderFootSide()  { _PR_setobj(0); }  // nil
    _objref_SpiderFootSide(omniIOR*, omniIdentity*);

  protected:
    virtual ~_objref_SpiderFootSide();

    
  private:
    virtual void* _ptrToObjRef(const char*);

    _objref_SpiderFootSide(const _objref_SpiderFootSide&);
    _objref_SpiderFootSide& operator = (const _objref_SpiderFootSide&);
    // not implemented

    friend class SpiderFootSide;
  };

  class _pof_SpiderFootSide : public _OMNI_NS(proxyObjectFactory) {
  public:
    inline _pof_SpiderFootSide() : _OMNI_NS(proxyObjectFactory)(SpiderFootSide::_PD_repoId) {}
    virtual ~_pof_SpiderFootSide();

    virtual omniObjRef* newObjRef(omniIOR*,omniIdentity*);
    virtual _CORBA_Boolean is_a(const char*) const;
  };

  class _impl_SpiderFootSide :
    public virtual omniServant
  {
  public:
    virtual ~_impl_SpiderFootSide();

    virtual ::CORBA::Boolean createMappingChannel(::CORBA::Long timerId, const ::CORBA::WChar* cHomeId, const ::CORBA::WChar* cMonitorId, const ::CORBA::WChar* downloadClusterId, ::CORBA::Long timerInterval) = 0;
    virtual ::CORBA::Boolean modifyMappingChannel(::CORBA::Long timerId, const ::CORBA::WChar* cHomeId, const ::CORBA::WChar* cMonitorId, const ::CORBA::WChar* downloadClusterId, ::CORBA::Long timerInterval, ::CORBA::Long synStatus) = 0;
    virtual ::CORBA::Boolean deleteMappingChannel(::CORBA::Long timerId, const ::CORBA::WChar* downloadClusterId) = 0;
    
  public:  // Really protected, workaround for xlC
    virtual _CORBA_Boolean _dispatch(omniCallHandle&);

  private:
    virtual void* _ptrToInterface(const char*);
    virtual const char* _mostDerivedRepoId();
    
  };


_CORBA_MODULE_END

_CORBA_MODULE SpiderRenderApp

_CORBA_MODULE_BEG

#ifndef __SpiderRenderApp_mSpiderFootSide__
#define __SpiderRenderApp_mSpiderFootSide__
  class SpiderFootSide;
  class _objref_SpiderFootSide;
  class _impl_SpiderFootSide;
  
  typedef _objref_SpiderFootSide* SpiderFootSide_ptr;
  typedef SpiderFootSide_ptr SpiderFootSideRef;

  class SpiderFootSide_Helper {
  public:
    typedef SpiderFootSide_ptr _ptr_type;

    static _ptr_type _nil();
    static _CORBA_Boolean is_nil(_ptr_type);
    static void release(_ptr_type);
    static void duplicate(_ptr_type);
    static void marshalObjRef(_ptr_type, cdrStream&);
    static _ptr_type unmarshalObjRef(cdrStream&);
  };

  typedef _CORBA_ObjRef_Var<_objref_SpiderFootSide, SpiderFootSide_Helper> SpiderFootSide_var;
  typedef _CORBA_ObjRef_OUT_arg<_objref_SpiderFootSide,SpiderFootSide_Helper > SpiderFootSide_out;

#endif

  // interface SpiderFootSide
  class SpiderFootSide {
  public:
    // Declarations for this interface type.
    typedef SpiderFootSide_ptr _ptr_type;
    typedef SpiderFootSide_var _var_type;

    static _ptr_type _duplicate(_ptr_type);
    static _ptr_type _narrow(::CORBA::Object_ptr);
    static _ptr_type _unchecked_narrow(::CORBA::Object_ptr);
    
    static _ptr_type _nil();

    static inline void _marshalObjRef(_ptr_type, cdrStream&);

    static inline _ptr_type _unmarshalObjRef(cdrStream& s) {
      omniObjRef* o = omniObjRef::_unMarshal(_PD_repoId,s);
      if (o)
        return (_ptr_type) o->_ptrToObjRef(_PD_repoId);
      else
        return _nil();
    }

    static inline _ptr_type _fromObjRef(omniObjRef* o) {
      if (o)
        return (_ptr_type) o->_ptrToObjRef(_PD_repoId);
      else
        return _nil();
    }

    static _core_attr const char* _PD_repoId;

    // Other IDL defined within this scope.
    struct RenderInfo {
      typedef _CORBA_ConstrType_Variable_Var<RenderInfo> _var_type;

      
      ::CORBA::Long jobId;

      ::CORBA::WString_member videoId;

      ::CORBA::WString_member vIntro;

      ::CORBA::WString_member vOutro;

      ::CORBA::WString_member vLogo;

      ::CORBA::Boolean enableIntro;

      ::CORBA::Boolean enableOutro;

      ::CORBA::Boolean enableLogo;

      ::CORBA::WString_member vLocation;

    

      void operator>>= (cdrStream &) const;
      void operator<<= (cdrStream &);
    };

    typedef RenderInfo::_var_type RenderInfo_var;

    typedef _CORBA_ConstrType_Variable_OUT_arg< RenderInfo,RenderInfo_var > RenderInfo_out;

  
  };

  class _objref_SpiderFootSide :
    public virtual ::CORBA::Object,
    public virtual omniObjRef
  {
  public:
    // IDL operations
    ::CORBA::Boolean createRenderJob(const ::SpiderRenderApp::SpiderFootSide::RenderInfo& vInfo);
    ::CORBA::Boolean deleteRenderJob(const ::CORBA::WChar* renderClusterId, ::CORBA::Long jobId);
    void deleteRenderdVideo(const ::CORBA::WChar* renderClusterId, const ::CORBA::WChar* vLocation);

    // Constructors
    inline _objref_SpiderFootSide()  { _PR_setobj(0); }  // nil
    _objref_SpiderFootSide(omniIOR*, omniIdentity*);

  protected:
    virtual ~_objref_SpiderFootSide();

    
  private:
    virtual void* _ptrToObjRef(const char*);

    _objref_SpiderFootSide(const _objref_SpiderFootSide&);
    _objref_SpiderFootSide& operator = (const _objref_SpiderFootSide&);
    // not implemented

    friend class SpiderFootSide;
  };

  class _pof_SpiderFootSide : public _OMNI_NS(proxyObjectFactory) {
  public:
    inline _pof_SpiderFootSide() : _OMNI_NS(proxyObjectFactory)(SpiderFootSide::_PD_repoId) {}
    virtual ~_pof_SpiderFootSide();

    virtual omniObjRef* newObjRef(omniIOR*,omniIdentity*);
    virtual _CORBA_Boolean is_a(const char*) const;
  };

  class _impl_SpiderFootSide :
    public virtual omniServant
  {
  public:
    virtual ~_impl_SpiderFootSide();

    virtual ::CORBA::Boolean createRenderJob(const ::SpiderRenderApp::SpiderFootSide::RenderInfo& vInfo) = 0;
    virtual ::CORBA::Boolean deleteRenderJob(const ::CORBA::WChar* renderClusterId, ::CORBA::Long jobId) = 0;
    virtual void deleteRenderdVideo(const ::CORBA::WChar* renderClusterId, const ::CORBA::WChar* vLocation) = 0;
    
  public:  // Really protected, workaround for xlC
    virtual _CORBA_Boolean _dispatch(omniCallHandle&);

  private:
    virtual void* _ptrToInterface(const char*);
    virtual const char* _mostDerivedRepoId();
    
  };


_CORBA_MODULE_END

_CORBA_MODULE SpiderUploadApp

_CORBA_MODULE_BEG

#ifndef __SpiderUploadApp_mSpiderFootSide__
#define __SpiderUploadApp_mSpiderFootSide__
  class SpiderFootSide;
  class _objref_SpiderFootSide;
  class _impl_SpiderFootSide;
  
  typedef _objref_SpiderFootSide* SpiderFootSide_ptr;
  typedef SpiderFootSide_ptr SpiderFootSideRef;

  class SpiderFootSide_Helper {
  public:
    typedef SpiderFootSide_ptr _ptr_type;

    static _ptr_type _nil();
    static _CORBA_Boolean is_nil(_ptr_type);
    static void release(_ptr_type);
    static void duplicate(_ptr_type);
    static void marshalObjRef(_ptr_type, cdrStream&);
    static _ptr_type unmarshalObjRef(cdrStream&);
  };

  typedef _CORBA_ObjRef_Var<_objref_SpiderFootSide, SpiderFootSide_Helper> SpiderFootSide_var;
  typedef _CORBA_ObjRef_OUT_arg<_objref_SpiderFootSide,SpiderFootSide_Helper > SpiderFootSide_out;

#endif

  // interface SpiderFootSide
  class SpiderFootSide {
  public:
    // Declarations for this interface type.
    typedef SpiderFootSide_ptr _ptr_type;
    typedef SpiderFootSide_var _var_type;

    static _ptr_type _duplicate(_ptr_type);
    static _ptr_type _narrow(::CORBA::Object_ptr);
    static _ptr_type _unchecked_narrow(::CORBA::Object_ptr);
    
    static _ptr_type _nil();

    static inline void _marshalObjRef(_ptr_type, cdrStream&);

    static inline _ptr_type _unmarshalObjRef(cdrStream& s) {
      omniObjRef* o = omniObjRef::_unMarshal(_PD_repoId,s);
      if (o)
        return (_ptr_type) o->_ptrToObjRef(_PD_repoId);
      else
        return _nil();
    }

    static inline _ptr_type _fromObjRef(omniObjRef* o) {
      if (o)
        return (_ptr_type) o->_ptrToObjRef(_PD_repoId);
      else
        return _nil();
    }

    static _core_attr const char* _PD_repoId;

    // Other IDL defined within this scope.
    struct UploadInfo {
      typedef _CORBA_ConstrType_Variable_Var<UploadInfo> _var_type;

      
      ::CORBA::Long jobId;

      ::CORBA::WString_member videoId;

      ::CORBA::WString_member vTitle;

      ::CORBA::WString_member vDesc;

      ::CORBA::WString_member vTags;

      ::CORBA::WString_member vThumbnail;

      ::CORBA::WString_member vLocation;

      ::CORBA::Boolean enableTitle;

      ::CORBA::Boolean enableDes;

      ::CORBA::Boolean enableTags;

      ::CORBA::Long mappingId;

    

      void operator>>= (cdrStream &) const;
      void operator<<= (cdrStream &);
    };

    typedef UploadInfo::_var_type UploadInfo_var;

    typedef _CORBA_ConstrType_Variable_OUT_arg< UploadInfo,UploadInfo_var > UploadInfo_out;

  
  };

  class _objref_SpiderFootSide :
    public virtual ::CORBA::Object,
    public virtual omniObjRef
  {
  public:
    // IDL operations
    ::CORBA::Boolean createUploadJob(const ::SpiderUploadApp::SpiderFootSide::UploadInfo& vInfo);
    ::CORBA::Boolean deleteUploadJob(const ::CORBA::WChar* uploadClusterId, ::CORBA::Long jobId);

    // Constructors
    inline _objref_SpiderFootSide()  { _PR_setobj(0); }  // nil
    _objref_SpiderFootSide(omniIOR*, omniIdentity*);

  protected:
    virtual ~_objref_SpiderFootSide();

    
  private:
    virtual void* _ptrToObjRef(const char*);

    _objref_SpiderFootSide(const _objref_SpiderFootSide&);
    _objref_SpiderFootSide& operator = (const _objref_SpiderFootSide&);
    // not implemented

    friend class SpiderFootSide;
  };

  class _pof_SpiderFootSide : public _OMNI_NS(proxyObjectFactory) {
  public:
    inline _pof_SpiderFootSide() : _OMNI_NS(proxyObjectFactory)(SpiderFootSide::_PD_repoId) {}
    virtual ~_pof_SpiderFootSide();

    virtual omniObjRef* newObjRef(omniIOR*,omniIdentity*);
    virtual _CORBA_Boolean is_a(const char*) const;
  };

  class _impl_SpiderFootSide :
    public virtual omniServant
  {
  public:
    virtual ~_impl_SpiderFootSide();

    virtual ::CORBA::Boolean createUploadJob(const ::SpiderUploadApp::SpiderFootSide::UploadInfo& vInfo) = 0;
    virtual ::CORBA::Boolean deleteUploadJob(const ::CORBA::WChar* uploadClusterId, ::CORBA::Long jobId) = 0;
    
  public:  // Really protected, workaround for xlC
    virtual _CORBA_Boolean _dispatch(omniCallHandle&);

  private:
    virtual void* _ptrToInterface(const char*);
    virtual const char* _mostDerivedRepoId();
    
  };


_CORBA_MODULE_END

_CORBA_MODULE SpiderAgentApp

_CORBA_MODULE_BEG

#ifndef __SpiderAgentApp_mAgentSide__
#define __SpiderAgentApp_mAgentSide__
  class AgentSide;
  class _objref_AgentSide;
  class _impl_AgentSide;
  
  typedef _objref_AgentSide* AgentSide_ptr;
  typedef AgentSide_ptr AgentSideRef;

  class AgentSide_Helper {
  public:
    typedef AgentSide_ptr _ptr_type;

    static _ptr_type _nil();
    static _CORBA_Boolean is_nil(_ptr_type);
    static void release(_ptr_type);
    static void duplicate(_ptr_type);
    static void marshalObjRef(_ptr_type, cdrStream&);
    static _ptr_type unmarshalObjRef(cdrStream&);
  };

  typedef _CORBA_ObjRef_Var<_objref_AgentSide, AgentSide_Helper> AgentSide_var;
  typedef _CORBA_ObjRef_OUT_arg<_objref_AgentSide,AgentSide_Helper > AgentSide_out;

#endif

  // interface AgentSide
  class AgentSide {
  public:
    // Declarations for this interface type.
    typedef AgentSide_ptr _ptr_type;
    typedef AgentSide_var _var_type;

    static _ptr_type _duplicate(_ptr_type);
    static _ptr_type _narrow(::CORBA::Object_ptr);
    static _ptr_type _unchecked_narrow(::CORBA::Object_ptr);
    
    static _ptr_type _nil();

    static inline void _marshalObjRef(_ptr_type, cdrStream&);

    static inline _ptr_type _unmarshalObjRef(cdrStream& s) {
      omniObjRef* o = omniObjRef::_unMarshal(_PD_repoId,s);
      if (o)
        return (_ptr_type) o->_ptrToObjRef(_PD_repoId);
      else
        return _nil();
    }

    static inline _ptr_type _fromObjRef(omniObjRef* o) {
      if (o)
        return (_ptr_type) o->_ptrToObjRef(_PD_repoId);
      else
        return _nil();
    }

    static _core_attr const char* _PD_repoId;

    // Other IDL defined within this scope.
    struct AuthenInfo {
      typedef _CORBA_ConstrType_Variable_Var<AuthenInfo> _var_type;

      
      ::CORBA::WString_member userName;

      ::CORBA::WString_member apiKey;

      ::CORBA::WString_member clientSecret;

      ::CORBA::WString_member clientId;

    

      void operator>>= (cdrStream &) const;
      void operator<<= (cdrStream &);
    };

    typedef AuthenInfo::_var_type AuthenInfo_var;

    typedef _CORBA_ConstrType_Variable_OUT_arg< AuthenInfo,AuthenInfo_var > AuthenInfo_out;

    struct ClusterInfo {
      typedef _CORBA_ConstrType_Variable_Var<ClusterInfo> _var_type;

      
      ::CORBA::WString_member clusterIp;

      ::CORBA::WString_member userName;

      ::CORBA::WString_member password;

    

      void operator>>= (cdrStream &) const;
      void operator<<= (cdrStream &);
    };

    typedef ClusterInfo::_var_type ClusterInfo_var;

    typedef _CORBA_ConstrType_Variable_OUT_arg< ClusterInfo,ClusterInfo_var > ClusterInfo_out;

    struct VideoInfo {
      typedef _CORBA_ConstrType_Variable_Var<VideoInfo> _var_type;

      
      ::CORBA::WString_member videoId;

      ::CORBA::WString_member title;

      ::CORBA::WString_member tags;

      ::CORBA::WString_member description;

      ::CORBA::WString_member thumbnail;

      ::CORBA::WString_member vDownloadPath;

      ::CORBA::WString_member vRenderPath;

      ::CORBA::Long mappingId;

      ::CORBA::Long processStatus;

      ::CORBA::Long license;

    

      void operator>>= (cdrStream &) const;
      void operator<<= (cdrStream &);
    };

    typedef VideoInfo::_var_type VideoInfo_var;

    typedef _CORBA_ConstrType_Variable_OUT_arg< VideoInfo,VideoInfo_var > VideoInfo_out;

  
  };

  class _objref_AgentSide :
    public virtual ::CORBA::Object,
    public virtual omniObjRef
  {
  public:
    // IDL operations
    void onDownloadStartup(const ::CORBA::WChar* appId);
    void onRenderStartup(const ::CORBA::WChar* appId);
    void onUploadStartup(const ::CORBA::WChar* appId);
    ::CORBA::LongLong getLastSyncTime(::CORBA::Long mappingId);
    void updateLastSyntime(::CORBA::Long mappingId, ::CORBA::LongLong lastSyncTime);
    void updateDownloadedVideo(const ::SpiderAgentApp::AgentSide::VideoInfo& vInfo);
    void updateRenderedVideo(::CORBA::Long jobId, ::CORBA::Long processStatus, const ::CORBA::WChar* vRenderPath);
    void updateUploadedVideo(::CORBA::Long jobId);
    ::CORBA::WChar* getMonitorChannelId(::CORBA::Long mappingId);
    AgentSide::ClusterInfo* getClusterInfo(::CORBA::Long clusterType, ::CORBA::Long mappingId);
    AgentSide::AuthenInfo* getAuthenInfo(::CORBA::Long mappingId);

    // Constructors
    inline _objref_AgentSide()  { _PR_setobj(0); }  // nil
    _objref_AgentSide(omniIOR*, omniIdentity*);

  protected:
    virtual ~_objref_AgentSide();

    
  private:
    virtual void* _ptrToObjRef(const char*);

    _objref_AgentSide(const _objref_AgentSide&);
    _objref_AgentSide& operator = (const _objref_AgentSide&);
    // not implemented

    friend class AgentSide;
  };

  class _pof_AgentSide : public _OMNI_NS(proxyObjectFactory) {
  public:
    inline _pof_AgentSide() : _OMNI_NS(proxyObjectFactory)(AgentSide::_PD_repoId) {}
    virtual ~_pof_AgentSide();

    virtual omniObjRef* newObjRef(omniIOR*,omniIdentity*);
    virtual _CORBA_Boolean is_a(const char*) const;
  };

  class _impl_AgentSide :
    public virtual omniServant
  {
  public:
    virtual ~_impl_AgentSide();

    virtual void onDownloadStartup(const ::CORBA::WChar* appId) = 0;
    virtual void onRenderStartup(const ::CORBA::WChar* appId) = 0;
    virtual void onUploadStartup(const ::CORBA::WChar* appId) = 0;
    virtual ::CORBA::LongLong getLastSyncTime(::CORBA::Long mappingId) = 0;
    virtual void updateLastSyntime(::CORBA::Long mappingId, ::CORBA::LongLong lastSyncTime) = 0;
    virtual void updateDownloadedVideo(const ::SpiderAgentApp::AgentSide::VideoInfo& vInfo) = 0;
    virtual void updateRenderedVideo(::CORBA::Long jobId, ::CORBA::Long processStatus, const ::CORBA::WChar* vRenderPath) = 0;
    virtual void updateUploadedVideo(::CORBA::Long jobId) = 0;
    virtual ::CORBA::WChar* getMonitorChannelId(::CORBA::Long mappingId) = 0;
    virtual AgentSide::ClusterInfo* getClusterInfo(::CORBA::Long clusterType, ::CORBA::Long mappingId) = 0;
    virtual AgentSide::AuthenInfo* getAuthenInfo(::CORBA::Long mappingId) = 0;
    
  public:  // Really protected, workaround for xlC
    virtual _CORBA_Boolean _dispatch(omniCallHandle&);

  private:
    virtual void* _ptrToInterface(const char*);
    virtual const char* _mostDerivedRepoId();
    
  };


_CORBA_MODULE_END



_CORBA_MODULE POA_SpiderDownloadApp
_CORBA_MODULE_BEG

  class SpiderFootSide :
    public virtual SpiderDownloadApp::_impl_SpiderFootSide,
    public virtual ::PortableServer::ServantBase
  {
  public:
    virtual ~SpiderFootSide();

    inline ::SpiderDownloadApp::SpiderFootSide_ptr _this() {
      return (::SpiderDownloadApp::SpiderFootSide_ptr) _do_this(::SpiderDownloadApp::SpiderFootSide::_PD_repoId);
    }
  };

_CORBA_MODULE_END

_CORBA_MODULE POA_SpiderRenderApp
_CORBA_MODULE_BEG

  class SpiderFootSide :
    public virtual SpiderRenderApp::_impl_SpiderFootSide,
    public virtual ::PortableServer::ServantBase
  {
  public:
    virtual ~SpiderFootSide();

    inline ::SpiderRenderApp::SpiderFootSide_ptr _this() {
      return (::SpiderRenderApp::SpiderFootSide_ptr) _do_this(::SpiderRenderApp::SpiderFootSide::_PD_repoId);
    }
  };

_CORBA_MODULE_END

_CORBA_MODULE POA_SpiderUploadApp
_CORBA_MODULE_BEG

  class SpiderFootSide :
    public virtual SpiderUploadApp::_impl_SpiderFootSide,
    public virtual ::PortableServer::ServantBase
  {
  public:
    virtual ~SpiderFootSide();

    inline ::SpiderUploadApp::SpiderFootSide_ptr _this() {
      return (::SpiderUploadApp::SpiderFootSide_ptr) _do_this(::SpiderUploadApp::SpiderFootSide::_PD_repoId);
    }
  };

_CORBA_MODULE_END

_CORBA_MODULE POA_SpiderAgentApp
_CORBA_MODULE_BEG

  class AgentSide :
    public virtual SpiderAgentApp::_impl_AgentSide,
    public virtual ::PortableServer::ServantBase
  {
  public:
    virtual ~AgentSide();

    inline ::SpiderAgentApp::AgentSide_ptr _this() {
      return (::SpiderAgentApp::AgentSide_ptr) _do_this(::SpiderAgentApp::AgentSide::_PD_repoId);
    }
  };

_CORBA_MODULE_END



_CORBA_MODULE OBV_SpiderDownloadApp
_CORBA_MODULE_BEG

_CORBA_MODULE_END

_CORBA_MODULE OBV_SpiderRenderApp
_CORBA_MODULE_BEG

_CORBA_MODULE_END

_CORBA_MODULE OBV_SpiderUploadApp
_CORBA_MODULE_BEG

_CORBA_MODULE_END

_CORBA_MODULE OBV_SpiderAgentApp
_CORBA_MODULE_BEG

_CORBA_MODULE_END





#undef _core_attr
#undef _dyn_attr



inline void
SpiderDownloadApp::SpiderFootSide::_marshalObjRef(::SpiderDownloadApp::SpiderFootSide_ptr obj, cdrStream& s) {
  omniObjRef::_marshal(obj->_PR_getobj(),s);
}

inline void
SpiderRenderApp::SpiderFootSide::_marshalObjRef(::SpiderRenderApp::SpiderFootSide_ptr obj, cdrStream& s) {
  omniObjRef::_marshal(obj->_PR_getobj(),s);
}

inline void
SpiderUploadApp::SpiderFootSide::_marshalObjRef(::SpiderUploadApp::SpiderFootSide_ptr obj, cdrStream& s) {
  omniObjRef::_marshal(obj->_PR_getobj(),s);
}

inline void
SpiderAgentApp::AgentSide::_marshalObjRef(::SpiderAgentApp::AgentSide_ptr obj, cdrStream& s) {
  omniObjRef::_marshal(obj->_PR_getobj(),s);
}



#ifdef   USE_stub_in_nt_dll_NOT_DEFINED_SpiderAgentAPI
# undef  USE_stub_in_nt_dll
# undef  USE_stub_in_nt_dll_NOT_DEFINED_SpiderAgentAPI
#endif
#ifdef   USE_core_stub_in_nt_dll_NOT_DEFINED_SpiderAgentAPI
# undef  USE_core_stub_in_nt_dll
# undef  USE_core_stub_in_nt_dll_NOT_DEFINED_SpiderAgentAPI
#endif
#ifdef   USE_dyn_stub_in_nt_dll_NOT_DEFINED_SpiderAgentAPI
# undef  USE_dyn_stub_in_nt_dll
# undef  USE_dyn_stub_in_nt_dll_NOT_DEFINED_SpiderAgentAPI
#endif

#endif  // __SpiderAgentAPI_hh__

