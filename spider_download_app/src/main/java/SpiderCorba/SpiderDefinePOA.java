package SpiderCorba;


/**
* SpiderCorba/SpiderDefinePOA.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from ../../../idl/SpiderAgentAPI.idl
* Wednesday, June 20, 2018 4:30:11 PM ICT
*/

public abstract class SpiderDefinePOA extends org.omg.PortableServer.Servant
 implements SpiderCorba.SpiderDefineOperations, org.omg.CORBA.portable.InvokeHandler
{

  // Constructors

  private static java.util.Hashtable _methods = new java.util.Hashtable ();
  static
  {
  }

  public org.omg.CORBA.portable.OutputStream _invoke (String $method,
                                org.omg.CORBA.portable.InputStream in,
                                org.omg.CORBA.portable.ResponseHandler $rh)
  {
    org.omg.CORBA.portable.OutputStream out = null;
    java.lang.Integer __method = (java.lang.Integer)_methods.get ($method);
    if (__method == null)
      throw new org.omg.CORBA.BAD_OPERATION (0, org.omg.CORBA.CompletionStatus.COMPLETED_MAYBE);

    return out;
  } // _invoke

  // Type-specific CORBA::Object operations
  private static String[] __ids = {
    "IDL:SpiderCorba/SpiderDefine:1.0"};

  public String[] _all_interfaces (org.omg.PortableServer.POA poa, byte[] objectId)
  {
    return (String[])__ids.clone ();
  }

  public SpiderDefine _this() 
  {
    return SpiderDefineHelper.narrow(
    super._this_object());
  }

  public SpiderDefine _this(org.omg.CORBA.ORB orb) 
  {
    return SpiderDefineHelper.narrow(
    super._this_object(orb));
  }


} // class SpiderDefinePOA
