package SpiderDownloadApp;

/**
* SpiderDownloadApp/SpiderFootSideHolder.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from ../../idl/SpiderAgentAPI.idl
* Saturday, May 19, 2018 10:02:58 AM ICT
*/

public final class SpiderFootSideHolder implements org.omg.CORBA.portable.Streamable
{
  public SpiderDownloadApp.SpiderFootSide value = null;

  public SpiderFootSideHolder ()
  {
  }

  public SpiderFootSideHolder (SpiderDownloadApp.SpiderFootSide initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = SpiderDownloadApp.SpiderFootSideHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    SpiderDownloadApp.SpiderFootSideHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return SpiderDownloadApp.SpiderFootSideHelper.type ();
  }

}
