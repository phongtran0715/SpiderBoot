package SpiderCorba.SpiderDefinePackage;


/**
* SpiderCorba/SpiderDefinePackage/RenderConfigHelper.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from SpiderAgentAPI.idl
* Friday, December 20, 2019 2:42:57 PM ICT
*/

abstract public class RenderConfigHelper
{
  private static String  _id = "IDL:SpiderCorba/SpiderDefine/RenderConfig:1.0";

  public static void insert (org.omg.CORBA.Any a, SpiderCorba.SpiderDefinePackage.RenderConfig that)
  {
    org.omg.CORBA.portable.OutputStream out = a.create_output_stream ();
    a.type (type ());
    write (out, that);
    a.read_value (out.create_input_stream (), type ());
  }

  public static SpiderCorba.SpiderDefinePackage.RenderConfig extract (org.omg.CORBA.Any a)
  {
    return read (a.create_input_stream ());
  }

  private static org.omg.CORBA.TypeCode __typeCode = null;
  private static boolean __active = false;
  synchronized public static org.omg.CORBA.TypeCode type ()
  {
    if (__typeCode == null)
    {
      synchronized (org.omg.CORBA.TypeCode.class)
      {
        if (__typeCode == null)
        {
          if (__active)
          {
            return org.omg.CORBA.ORB.init().create_recursive_tc ( _id );
          }
          __active = true;
          org.omg.CORBA.StructMember[] _members0 = new org.omg.CORBA.StructMember [1];
          org.omg.CORBA.TypeCode _tcOf_members0 = null;
          _tcOf_members0 = org.omg.CORBA.ORB.init ().create_wstring_tc (0);
          _members0[0] = new org.omg.CORBA.StructMember (
            "renderCommand",
            _tcOf_members0,
            null);
          __typeCode = org.omg.CORBA.ORB.init ().create_struct_tc (SpiderCorba.SpiderDefinePackage.RenderConfigHelper.id (), "RenderConfig", _members0);
          __active = false;
        }
      }
    }
    return __typeCode;
  }

  public static String id ()
  {
    return _id;
  }

  public static SpiderCorba.SpiderDefinePackage.RenderConfig read (org.omg.CORBA.portable.InputStream istream)
  {
    SpiderCorba.SpiderDefinePackage.RenderConfig value = new SpiderCorba.SpiderDefinePackage.RenderConfig ();
    value.renderCommand = istream.read_wstring ();
    return value;
  }

  public static void write (org.omg.CORBA.portable.OutputStream ostream, SpiderCorba.SpiderDefinePackage.RenderConfig value)
  {
    ostream.write_wstring (value.renderCommand);
  }

}