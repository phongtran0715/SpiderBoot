package corba.variableDefinePackage;


/**
* corba/variableDefinePackage/AuthenInfo.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from SpiderAgentAPI.idl
* Thursday, December 19, 2019 2:43:12 PM ICT
*/

public final class AuthenInfo implements org.omg.CORBA.portable.IDLEntity
{
  public String userName = null;
  public String apiKey = null;
  public String clientSecret = null;
  public String clientId = null;

  public AuthenInfo ()
  {
  } // ctor

  public AuthenInfo (String _userName, String _apiKey, String _clientSecret, String _clientId)
  {
    userName = _userName;
    apiKey = _apiKey;
    clientSecret = _clientSecret;
    clientId = _clientId;
  } // ctor

} // class AuthenInfo
