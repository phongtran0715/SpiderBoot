package SpiderCorba;


/**
* SpiderCorba/SpiderDefineHelper.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from ../../../idl/SpiderAgentAPI.idl
* Friday, July 13, 2018 9:28:06 AM ICT
*/

abstract public class SpiderDefineHelper
{
  private static String  _id = "IDL:SpiderCorba/SpiderDefine:1.0";

  public static void insert (org.omg.CORBA.Any a, SpiderCorba.SpiderDefine that)
  {
    org.omg.CORBA.portable.OutputStream out = a.create_output_stream ();
    a.type (type ());
    write (out, that);
    a.read_value (out.create_input_stream (), type ());
  }

  public static SpiderCorba.SpiderDefine extract (org.omg.CORBA.Any a)
  {
    return read (a.create_input_stream ());
  }

  private static org.omg.CORBA.TypeCode __typeCode = null;
  synchronized public static org.omg.CORBA.TypeCode type ()
  {
    if (__typeCode == null)
    {
      __typeCode = org.omg.CORBA.ORB.init ().create_interface_tc (SpiderCorba.SpiderDefineHelper.id (), "SpiderDefine");
    }
    return __typeCode;
  }

  public static String id ()
  {
    return _id;
  }

  public static SpiderCorba.SpiderDefine read (org.omg.CORBA.portable.InputStream istream)
  {
    return narrow (istream.read_Object (_SpiderDefineStub.class));
  }

  public static void write (org.omg.CORBA.portable.OutputStream ostream, SpiderCorba.SpiderDefine value)
  {
    ostream.write_Object ((org.omg.CORBA.Object) value);
  }

  public static SpiderCorba.SpiderDefine narrow (org.omg.CORBA.Object obj)
  {
    if (obj == null)
      return null;
    else if (obj instanceof SpiderCorba.SpiderDefine)
      return (SpiderCorba.SpiderDefine)obj;
    else if (!obj._is_a (id ()))
      throw new org.omg.CORBA.BAD_PARAM ();
    else
    {
      org.omg.CORBA.portable.Delegate delegate = ((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate ();
      SpiderCorba._SpiderDefineStub stub = new SpiderCorba._SpiderDefineStub ();
      stub._set_delegate(delegate);
      return stub;
    }
  }

  public static SpiderCorba.SpiderDefine unchecked_narrow (org.omg.CORBA.Object obj)
  {
    if (obj == null)
      return null;
    else if (obj instanceof SpiderCorba.SpiderDefine)
      return (SpiderCorba.SpiderDefine)obj;
    else
    {
      org.omg.CORBA.portable.Delegate delegate = ((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate ();
      SpiderCorba._SpiderDefineStub stub = new SpiderCorba._SpiderDefineStub ();
      stub._set_delegate(delegate);
      return stub;
    }
  }

}
