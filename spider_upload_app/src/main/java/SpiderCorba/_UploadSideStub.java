package SpiderCorba;


/**
* SpiderCorba/_UploadSideStub.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from ../../../idl/SpiderAgentAPI.idl
* Friday, June 15, 2018 7:08:29 PM ICT
*/

public class _UploadSideStub extends org.omg.CORBA.portable.ObjectImpl implements SpiderCorba.UploadSide
{

  public boolean createUploadTimer (String timerId, String uploadClusterId)
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("createUploadTimer", true);
                $out.write_wstring (timerId);
                $out.write_wstring (uploadClusterId);
                $in = _invoke ($out);
                boolean $result = $in.read_boolean ();
                return $result;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                return createUploadTimer (timerId, uploadClusterId        );
            } finally {
                _releaseReply ($in);
            }
  } // createUploadTimer

  public boolean modifyUploadTimer (String timerId, String uploadClusterId, int synStatus, SpiderCorba.UploadSidePackage.UploadConfig uploadCfg)
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("modifyUploadTimer", true);
                $out.write_wstring (timerId);
                $out.write_wstring (uploadClusterId);
                $out.write_long (synStatus);
                SpiderCorba.UploadSidePackage.UploadConfigHelper.write ($out, uploadCfg);
                $in = _invoke ($out);
                boolean $result = $in.read_boolean ();
                return $result;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                return modifyUploadTimer (timerId, uploadClusterId, synStatus, uploadCfg        );
            } finally {
                _releaseReply ($in);
            }
  } // modifyUploadTimer

  public boolean deleteUploadTimer (String timerId, String uploadClusterId)
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("deleteUploadTimer", true);
                $out.write_wstring (timerId);
                $out.write_wstring (uploadClusterId);
                $in = _invoke ($out);
                boolean $result = $in.read_boolean ();
                return $result;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                return deleteUploadTimer (timerId, uploadClusterId        );
            } finally {
                _releaseReply ($in);
            }
  } // deleteUploadTimer

  public boolean createUploadJob (int jobId, SpiderCorba.SpiderDefinePackage.VideoInfo vInfo, SpiderCorba.UploadSidePackage.UploadConfig uploadCfg)
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("createUploadJob", true);
                $out.write_long (jobId);
                SpiderCorba.SpiderDefinePackage.VideoInfoHelper.write ($out, vInfo);
                SpiderCorba.UploadSidePackage.UploadConfigHelper.write ($out, uploadCfg);
                $in = _invoke ($out);
                boolean $result = $in.read_boolean ();
                return $result;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                return createUploadJob (jobId, vInfo, uploadCfg        );
            } finally {
                _releaseReply ($in);
            }
  } // createUploadJob

  public boolean deleteUploadJob (int jobId, String uploadClusterId)
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("deleteUploadJob", true);
                $out.write_long (jobId);
                $out.write_wstring (uploadClusterId);
                $in = _invoke ($out);
                boolean $result = $in.read_boolean ();
                return $result;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                return deleteUploadJob (jobId, uploadClusterId        );
            } finally {
                _releaseReply ($in);
            }
  } // deleteUploadJob

  // Type-specific CORBA::Object operations
  private static String[] __ids = {
    "IDL:SpiderCorba/UploadSide:1.0", 
    "IDL:SpiderCorba/SpiderDefine:1.0"};

  public String[] _ids ()
  {
    return (String[])__ids.clone ();
  }

  private void readObject (java.io.ObjectInputStream s) throws java.io.IOException
  {
     String str = s.readUTF ();
     String[] args = null;
     java.util.Properties props = null;
     org.omg.CORBA.ORB orb = org.omg.CORBA.ORB.init (args, props);
   try {
     org.omg.CORBA.Object obj = orb.string_to_object (str);
     org.omg.CORBA.portable.Delegate delegate = ((org.omg.CORBA.portable.ObjectImpl) obj)._get_delegate ();
     _set_delegate (delegate);
   } finally {
     orb.destroy() ;
   }
  }

  private void writeObject (java.io.ObjectOutputStream s) throws java.io.IOException
  {
     String[] args = null;
     java.util.Properties props = null;
     org.omg.CORBA.ORB orb = org.omg.CORBA.ORB.init (args, props);
   try {
     String str = orb.object_to_string (this);
     s.writeUTF (str);
   } finally {
     orb.destroy() ;
   }
  }
} // class _UploadSideStub