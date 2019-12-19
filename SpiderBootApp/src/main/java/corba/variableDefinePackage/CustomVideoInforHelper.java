package corba.variableDefinePackage;


/**
* corba/variableDefinePackage/CustomVideoInforHelper.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from SpiderAgentAPI.idl
* Thursday, December 19, 2019 2:43:12 PM ICT
*/

abstract public class CustomVideoInforHelper
{
  private static String  _id = "IDL:corba/variableDefine/CustomVideoInfor:1.0";

  public static void insert (org.omg.CORBA.Any a, corba.variableDefinePackage.CustomVideoInfor that)
  {
    org.omg.CORBA.portable.OutputStream out = a.create_output_stream ();
    a.type (type ());
    write (out, that);
    a.read_value (out.create_input_stream (), type ());
  }

  public static corba.variableDefinePackage.CustomVideoInfor extract (org.omg.CORBA.Any a)
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
          org.omg.CORBA.StructMember[] _members0 = new org.omg.CORBA.StructMember [2];
          org.omg.CORBA.TypeCode _tcOf_members0 = null;
          _tcOf_members0 = org.omg.CORBA.ORB.init ().get_primitive_tc (org.omg.CORBA.TCKind.tk_long);
          _members0[0] = new org.omg.CORBA.StructMember (
            "id",
            _tcOf_members0,
            null);
          _tcOf_members0 = org.omg.CORBA.ORB.init ().create_wstring_tc (0);
          _members0[1] = new org.omg.CORBA.StructMember (
            "videoId",
            _tcOf_members0,
            null);
          __typeCode = org.omg.CORBA.ORB.init ().create_struct_tc (corba.variableDefinePackage.CustomVideoInforHelper.id (), "CustomVideoInfor", _members0);
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

  public static corba.variableDefinePackage.CustomVideoInfor read (org.omg.CORBA.portable.InputStream istream)
  {
    corba.variableDefinePackage.CustomVideoInfor value = new corba.variableDefinePackage.CustomVideoInfor ();
    value.id = istream.read_long ();
    value.videoId = istream.read_wstring ();
    return value;
  }

  public static void write (org.omg.CORBA.portable.OutputStream ostream, corba.variableDefinePackage.CustomVideoInfor value)
  {
    ostream.write_long (value.id);
    ostream.write_wstring (value.videoId);
  }

}
