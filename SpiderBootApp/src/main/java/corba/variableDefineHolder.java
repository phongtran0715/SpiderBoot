package corba;

/**
* corba/variableDefineHolder.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from SpiderAgentAPI.idl
* Thursday, December 19, 2019 2:43:12 PM ICT
*/

public final class variableDefineHolder implements org.omg.CORBA.portable.Streamable
{
  public corba.variableDefine value = null;

  public variableDefineHolder ()
  {
  }

  public variableDefineHolder (corba.variableDefine initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = corba.variableDefineHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    corba.variableDefineHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return corba.variableDefineHelper.type ();
  }

}
