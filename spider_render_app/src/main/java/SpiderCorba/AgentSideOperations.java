package SpiderCorba;


/**
* SpiderCorba/AgentSideOperations.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from ../../../idl/SpiderAgentAPI.idl
* Wednesday, June 20, 2018 6:12:05 PM ICT
*/

public interface AgentSideOperations  extends SpiderCorba.SpiderDefineOperations
{

  //Render app function
  void onRenderStartup (String renderClusterId);
  SpiderCorba.SpiderDefinePackage.RenderConfig getRenderConfig (int mappingId);
  void updateRenderedVideo (int jobId, SpiderCorba.SpiderDefinePackage.VideoInfo vInfo);
} // interface AgentSideOperations
