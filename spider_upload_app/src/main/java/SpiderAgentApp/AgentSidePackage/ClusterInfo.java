package SpiderAgentApp.AgentSidePackage;


/**
* SpiderAgentApp/AgentSidePackage/ClusterInfo.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from ../../../idl/SpiderAgentAPI.idl
* Tuesday, June 5, 2018 5:14:34 PM ICT
*/

public final class ClusterInfo implements org.omg.CORBA.portable.IDLEntity
{
  public String clusterIp = null;
  public String userName = null;
  public String password = null;

  public ClusterInfo ()
  {
  } // ctor

  public ClusterInfo (String _clusterIp, String _userName, String _password)
  {
    clusterIp = _clusterIp;
    userName = _userName;
    password = _password;
  } // ctor

} // class ClusterInfo
