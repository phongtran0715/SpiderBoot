package SpiderCorba;

/**
* SpiderCorba/YoutubeAgentHolder.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from ../../../idl/SpiderAgentAPI.idl
* Friday, July 13, 2018 9:28:06 AM ICT
*/

public final class YoutubeAgentHolder implements org.omg.CORBA.portable.Streamable
{
  public SpiderCorba.YoutubeAgent value = null;

  public YoutubeAgentHolder ()
  {
  }

  public YoutubeAgentHolder (SpiderCorba.YoutubeAgent initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = SpiderCorba.YoutubeAgentHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    SpiderCorba.YoutubeAgentHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return SpiderCorba.YoutubeAgentHelper.type ();
  }

}
