package SpiderAgentApp;


/**
* SpiderAgentApp/AgentSideOperations.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from ../../../idl/SpiderAgentAPI.idl
* Thursday, May 24, 2018 4:13:01 PM ICT
*/

public interface AgentSideOperations 
{
  void onDownloadStartup ();
  void onRenderStartup ();
  void onUploadStartup ();
  long getLastSyncTime (int mappingId);
  void updateLastSyntime (int mappingId, long lastSyncTime);
  void updateDownloadedVideo (SpiderAgentApp.AgentSidePackage.VideoInfo vInfo);
  void updateRenderedVideo (int videoId, int processStatus, String videoLocation);
  void updateUploadedVideo (int videoId, int processStatus, String videoLocation);
} // interface AgentSideOperations