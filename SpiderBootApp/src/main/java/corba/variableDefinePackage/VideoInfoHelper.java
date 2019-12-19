package corba.variableDefinePackage;


/**
* corba/variableDefinePackage/VideoInfoHelper.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from SpiderAgentAPI.idl
* Thursday, December 19, 2019 2:43:12 PM ICT
*/

abstract public class VideoInfoHelper
{
  private static String  _id = "IDL:corba/variableDefine/VideoInfo:1.0";

  public static void insert (org.omg.CORBA.Any a, corba.variableDefinePackage.VideoInfo that)
  {
    org.omg.CORBA.portable.OutputStream out = a.create_output_stream ();
    a.type (type ());
    write (out, that);
    a.read_value (out.create_input_stream (), type ());
  }

  public static corba.variableDefinePackage.VideoInfo extract (org.omg.CORBA.Any a)
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
          org.omg.CORBA.StructMember[] _members0 = new org.omg.CORBA.StructMember [10];
          org.omg.CORBA.TypeCode _tcOf_members0 = null;
          _tcOf_members0 = org.omg.CORBA.ORB.init ().create_wstring_tc (0);
          _members0[0] = new org.omg.CORBA.StructMember (
            "videoId",
            _tcOf_members0,
            null);
          _tcOf_members0 = org.omg.CORBA.ORB.init ().create_wstring_tc (0);
          _members0[1] = new org.omg.CORBA.StructMember (
            "title",
            _tcOf_members0,
            null);
          _tcOf_members0 = org.omg.CORBA.ORB.init ().create_wstring_tc (0);
          _members0[2] = new org.omg.CORBA.StructMember (
            "tags",
            _tcOf_members0,
            null);
          _tcOf_members0 = org.omg.CORBA.ORB.init ().create_wstring_tc (0);
          _members0[3] = new org.omg.CORBA.StructMember (
            "description",
            _tcOf_members0,
            null);
          _tcOf_members0 = org.omg.CORBA.ORB.init ().create_wstring_tc (0);
          _members0[4] = new org.omg.CORBA.StructMember (
            "thumbnail",
            _tcOf_members0,
            null);
          _tcOf_members0 = org.omg.CORBA.ORB.init ().create_wstring_tc (0);
          _members0[5] = new org.omg.CORBA.StructMember (
            "vDownloadPath",
            _tcOf_members0,
            null);
          _tcOf_members0 = org.omg.CORBA.ORB.init ().create_wstring_tc (0);
          _members0[6] = new org.omg.CORBA.StructMember (
            "vRenderPath",
            _tcOf_members0,
            null);
          _tcOf_members0 = org.omg.CORBA.ORB.init ().get_primitive_tc (org.omg.CORBA.TCKind.tk_long);
          _members0[7] = new org.omg.CORBA.StructMember (
            "mappingId",
            _tcOf_members0,
            null);
          _tcOf_members0 = org.omg.CORBA.ORB.init ().get_primitive_tc (org.omg.CORBA.TCKind.tk_long);
          _members0[8] = new org.omg.CORBA.StructMember (
            "processStatus",
            _tcOf_members0,
            null);
          _tcOf_members0 = org.omg.CORBA.ORB.init ().get_primitive_tc (org.omg.CORBA.TCKind.tk_long);
          _members0[9] = new org.omg.CORBA.StructMember (
            "license",
            _tcOf_members0,
            null);
          __typeCode = org.omg.CORBA.ORB.init ().create_struct_tc (corba.variableDefinePackage.VideoInfoHelper.id (), "VideoInfo", _members0);
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

  public static corba.variableDefinePackage.VideoInfo read (org.omg.CORBA.portable.InputStream istream)
  {
    corba.variableDefinePackage.VideoInfo value = new corba.variableDefinePackage.VideoInfo ();
    value.videoId = istream.read_wstring ();
    value.title = istream.read_wstring ();
    value.tags = istream.read_wstring ();
    value.description = istream.read_wstring ();
    value.thumbnail = istream.read_wstring ();
    value.vDownloadPath = istream.read_wstring ();
    value.vRenderPath = istream.read_wstring ();
    value.mappingId = istream.read_long ();
    value.processStatus = istream.read_long ();
    value.license = istream.read_long ();
    return value;
  }

  public static void write (org.omg.CORBA.portable.OutputStream ostream, corba.variableDefinePackage.VideoInfo value)
  {
    ostream.write_wstring (value.videoId);
    ostream.write_wstring (value.title);
    ostream.write_wstring (value.tags);
    ostream.write_wstring (value.description);
    ostream.write_wstring (value.thumbnail);
    ostream.write_wstring (value.vDownloadPath);
    ostream.write_wstring (value.vRenderPath);
    ostream.write_long (value.mappingId);
    ostream.write_long (value.processStatus);
    ostream.write_long (value.license);
  }

}
