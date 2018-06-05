package SpiderUploadApp.SpiderFootSidePackage;


/**
* SpiderUploadApp/SpiderFootSidePackage/UploadInfoHelper.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from ../../../idl/SpiderAgentAPI.idl
* Tuesday, June 5, 2018 5:14:34 PM ICT
*/

abstract public class UploadInfoHelper
{
  private static String  _id = "IDL:SpiderUploadApp/SpiderFootSide/UploadInfo:1.0";

  public static void insert (org.omg.CORBA.Any a, SpiderUploadApp.SpiderFootSidePackage.UploadInfo that)
  {
    org.omg.CORBA.portable.OutputStream out = a.create_output_stream ();
    a.type (type ());
    write (out, that);
    a.read_value (out.create_input_stream (), type ());
  }

  public static SpiderUploadApp.SpiderFootSidePackage.UploadInfo extract (org.omg.CORBA.Any a)
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
          org.omg.CORBA.StructMember[] _members0 = new org.omg.CORBA.StructMember [11];
          org.omg.CORBA.TypeCode _tcOf_members0 = null;
          _tcOf_members0 = org.omg.CORBA.ORB.init ().get_primitive_tc (org.omg.CORBA.TCKind.tk_long);
          _members0[0] = new org.omg.CORBA.StructMember (
            "jobId",
            _tcOf_members0,
            null);
          _tcOf_members0 = org.omg.CORBA.ORB.init ().create_wstring_tc (0);
          _members0[1] = new org.omg.CORBA.StructMember (
            "videoId",
            _tcOf_members0,
            null);
          _tcOf_members0 = org.omg.CORBA.ORB.init ().create_wstring_tc (0);
          _members0[2] = new org.omg.CORBA.StructMember (
            "vTitle",
            _tcOf_members0,
            null);
          _tcOf_members0 = org.omg.CORBA.ORB.init ().create_wstring_tc (0);
          _members0[3] = new org.omg.CORBA.StructMember (
            "vDesc",
            _tcOf_members0,
            null);
          _tcOf_members0 = org.omg.CORBA.ORB.init ().create_wstring_tc (0);
          _members0[4] = new org.omg.CORBA.StructMember (
            "vTags",
            _tcOf_members0,
            null);
          _tcOf_members0 = org.omg.CORBA.ORB.init ().create_wstring_tc (0);
          _members0[5] = new org.omg.CORBA.StructMember (
            "vThumbnail",
            _tcOf_members0,
            null);
          _tcOf_members0 = org.omg.CORBA.ORB.init ().create_wstring_tc (0);
          _members0[6] = new org.omg.CORBA.StructMember (
            "vLocation",
            _tcOf_members0,
            null);
          _tcOf_members0 = org.omg.CORBA.ORB.init ().get_primitive_tc (org.omg.CORBA.TCKind.tk_boolean);
          _members0[7] = new org.omg.CORBA.StructMember (
            "enableTitle",
            _tcOf_members0,
            null);
          _tcOf_members0 = org.omg.CORBA.ORB.init ().get_primitive_tc (org.omg.CORBA.TCKind.tk_boolean);
          _members0[8] = new org.omg.CORBA.StructMember (
            "enableDes",
            _tcOf_members0,
            null);
          _tcOf_members0 = org.omg.CORBA.ORB.init ().get_primitive_tc (org.omg.CORBA.TCKind.tk_boolean);
          _members0[9] = new org.omg.CORBA.StructMember (
            "enableTags",
            _tcOf_members0,
            null);
          _tcOf_members0 = org.omg.CORBA.ORB.init ().get_primitive_tc (org.omg.CORBA.TCKind.tk_long);
          _members0[10] = new org.omg.CORBA.StructMember (
            "mappingId",
            _tcOf_members0,
            null);
          __typeCode = org.omg.CORBA.ORB.init ().create_struct_tc (SpiderUploadApp.SpiderFootSidePackage.UploadInfoHelper.id (), "UploadInfo", _members0);
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

  public static SpiderUploadApp.SpiderFootSidePackage.UploadInfo read (org.omg.CORBA.portable.InputStream istream)
  {
    SpiderUploadApp.SpiderFootSidePackage.UploadInfo value = new SpiderUploadApp.SpiderFootSidePackage.UploadInfo ();
    value.jobId = istream.read_long ();
    value.videoId = istream.read_wstring ();
    value.vTitle = istream.read_wstring ();
    value.vDesc = istream.read_wstring ();
    value.vTags = istream.read_wstring ();
    value.vThumbnail = istream.read_wstring ();
    value.vLocation = istream.read_wstring ();
    value.enableTitle = istream.read_boolean ();
    value.enableDes = istream.read_boolean ();
    value.enableTags = istream.read_boolean ();
    value.mappingId = istream.read_long ();
    return value;
  }

  public static void write (org.omg.CORBA.portable.OutputStream ostream, SpiderUploadApp.SpiderFootSidePackage.UploadInfo value)
  {
    ostream.write_long (value.jobId);
    ostream.write_wstring (value.videoId);
    ostream.write_wstring (value.vTitle);
    ostream.write_wstring (value.vDesc);
    ostream.write_wstring (value.vTags);
    ostream.write_wstring (value.vThumbnail);
    ostream.write_wstring (value.vLocation);
    ostream.write_boolean (value.enableTitle);
    ostream.write_boolean (value.enableDes);
    ostream.write_boolean (value.enableTags);
    ostream.write_long (value.mappingId);
  }

}
