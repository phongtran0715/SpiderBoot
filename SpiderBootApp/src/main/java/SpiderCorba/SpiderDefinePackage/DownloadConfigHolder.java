package SpiderCorba.SpiderDefinePackage;

/**
* SpiderCorba/SpiderDefinePackage/DownloadConfigHolder.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from SpiderAgentAPI.idl
* Friday, December 20, 2019 2:42:57 PM ICT
*/

public final class DownloadConfigHolder implements org.omg.CORBA.portable.Streamable
{
  public SpiderCorba.SpiderDefinePackage.DownloadConfig value = null;

  public DownloadConfigHolder ()
  {
  }

  public DownloadConfigHolder (SpiderCorba.SpiderDefinePackage.DownloadConfig initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = SpiderCorba.SpiderDefinePackage.DownloadConfigHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    SpiderCorba.SpiderDefinePackage.DownloadConfigHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return SpiderCorba.SpiderDefinePackage.DownloadConfigHelper.type ();
  }

}
