package corba;


/**
* corba/SpiderBootSideOperations.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from SpiderAgentAPI.idl
* Thursday, December 19, 2019 2:43:12 PM ICT
*/

public interface SpiderBootSideOperations  extends corba.variableDefineOperations
{

  //Download function
  boolean createDownloadTimer (int timerId, int timerInterval);
  boolean modifyDownloadTimer (int timerId, int timerInterval, int syncStatus);
  boolean deleteDowloadTimer (int timerId);
  boolean deleteDownloadedVideo (int jobId);

  //Render function
  boolean createRenderJob (int jobId, corba.variableDefinePackage.VideoInfo vInfo);
  boolean deleteRenderJob (int jobId);
  void deleteRenderdVideo (String vLocation);

  //Upload function
  boolean createUploadTimer (String cHomeId);
  boolean deleteUploadTimer (String cHomeId);
  boolean createUploadJob (int jobId, corba.variableDefinePackage.VideoInfo vInfo, String cHomeId);
  boolean deleteUploadJob (int jobId, String cHomeId);
} // interface SpiderBootSideOperations
