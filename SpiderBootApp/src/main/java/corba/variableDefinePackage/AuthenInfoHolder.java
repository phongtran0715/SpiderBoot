package corba.variableDefinePackage;

/**
* corba/variableDefinePackage/AuthenInfoHolder.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from SpiderAgentAPI.idl
* Thursday, December 19, 2019 2:43:12 PM ICT
*/

public final class AuthenInfoHolder implements org.omg.CORBA.portable.Streamable
{
  public corba.variableDefinePackage.AuthenInfo value = null;

  public AuthenInfoHolder ()
  {
  }

  public AuthenInfoHolder (corba.variableDefinePackage.AuthenInfo initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = corba.variableDefinePackage.AuthenInfoHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    corba.variableDefinePackage.AuthenInfoHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return corba.variableDefinePackage.AuthenInfoHelper.type ();
  }

}
