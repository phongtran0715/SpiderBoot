package SpiderDownloadApp;


/**
* SpiderDownloadApp/SpiderFootSideHelper.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from ../../../idl/SpiderAgentAPI.idl
* Thursday, May 24, 2018 4:13:01 PM ICT
*/

abstract public class SpiderFootSideHelper
{
  private static String  _id = "IDL:SpiderDownloadApp/SpiderFootSide:1.0";

  public static void insert (org.omg.CORBA.Any a, SpiderDownloadApp.SpiderFootSide that)
  {
    org.omg.CORBA.portable.OutputStream out = a.create_output_stream ();
    a.type (type ());
    write (out, that);
    a.read_value (out.create_input_stream (), type ());
  }

  public static SpiderDownloadApp.SpiderFootSide extract (org.omg.CORBA.Any a)
  {
    return read (a.create_input_stream ());
  }

  private static org.omg.CORBA.TypeCode __typeCode = null;
  synchronized public static org.omg.CORBA.TypeCode type ()
  {
    if (__typeCode == null)
    {
      __typeCode = org.omg.CORBA.ORB.init ().create_interface_tc (SpiderDownloadApp.SpiderFootSideHelper.id (), "SpiderFootSide");
    }
    return __typeCode;
  }

  public static String id ()
  {
    return _id;
  }

  public static SpiderDownloadApp.SpiderFootSide read (org.omg.CORBA.portable.InputStream istream)
  {
    return narrow (istream.read_Object (_SpiderFootSideStub.class));
  }

  public static void write (org.omg.CORBA.portable.OutputStream ostream, SpiderDownloadApp.SpiderFootSide value)
  {
    ostream.write_Object ((org.omg.CORBA.Object) value);
  }

  public static SpiderDownloadApp.SpiderFootSide narrow (org.omg.CORBA.Object obj)
  {
    if (obj == null)
      return null;
    else if (obj instanceof SpiderDownloadApp.SpiderFootSide)
      return (SpiderDownloadApp.SpiderFootSide)obj;
    else if (!obj._is_a (id ()))
      throw new org.omg.CORBA.BAD_PARAM ();
    else
    {
      org.omg.CORBA.portable.Delegate delegate = ((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate ();
      SpiderDownloadApp._SpiderFootSideStub stub = new SpiderDownloadApp._SpiderFootSideStub ();
      stub._set_delegate(delegate);
      return stub;
    }
  }

  public static SpiderDownloadApp.SpiderFootSide unchecked_narrow (org.omg.CORBA.Object obj)
  {
    if (obj == null)
      return null;
    else if (obj instanceof SpiderDownloadApp.SpiderFootSide)
      return (SpiderDownloadApp.SpiderFootSide)obj;
    else
    {
      org.omg.CORBA.portable.Delegate delegate = ((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate ();
      SpiderDownloadApp._SpiderFootSideStub stub = new SpiderDownloadApp._SpiderFootSideStub ();
      stub._set_delegate(delegate);
      return stub;
    }
  }

}
