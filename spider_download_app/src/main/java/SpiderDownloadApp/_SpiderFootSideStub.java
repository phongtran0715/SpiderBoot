package SpiderDownloadApp;


/**
* SpiderDownloadApp/_SpiderFootSideStub.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from ../../../idl/SpiderAgentAPI.idl
* Monday, June 4, 2018 5:10:40 PM ICT
*/

public class _SpiderFootSideStub extends org.omg.CORBA.portable.ObjectImpl implements SpiderDownloadApp.SpiderFootSide
{

  public boolean createMappingChannel (int timerId, String cHomeId, String cMonitorId, String downloadClusterId, int timerInterval)
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("createMappingChannel", true);
                $out.write_long (timerId);
                $out.write_wstring (cHomeId);
                $out.write_wstring (cMonitorId);
                $out.write_wstring (downloadClusterId);
                $out.write_long (timerInterval);
                $in = _invoke ($out);
                boolean $result = $in.read_boolean ();
                return $result;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                return createMappingChannel (timerId, cHomeId, cMonitorId, downloadClusterId, timerInterval        );
            } finally {
                _releaseReply ($in);
            }
  } // createMappingChannel

  public boolean modifyMappingChannel (int timerId, String cHomeId, String cMonitorId, String downloadClusterId, int timerInterval, int synStatus)
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("modifyMappingChannel", true);
                $out.write_long (timerId);
                $out.write_wstring (cHomeId);
                $out.write_wstring (cMonitorId);
                $out.write_wstring (downloadClusterId);
                $out.write_long (timerInterval);
                $out.write_long (synStatus);
                $in = _invoke ($out);
                boolean $result = $in.read_boolean ();
                return $result;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                return modifyMappingChannel (timerId, cHomeId, cMonitorId, downloadClusterId, timerInterval, synStatus        );
            } finally {
                _releaseReply ($in);
            }
  } // modifyMappingChannel

  public boolean deleteMappingChannel (int timerId, String downloadClusterId)
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("deleteMappingChannel", true);
                $out.write_long (timerId);
                $out.write_wstring (downloadClusterId);
                $in = _invoke ($out);
                boolean $result = $in.read_boolean ();
                return $result;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                return deleteMappingChannel (timerId, downloadClusterId        );
            } finally {
                _releaseReply ($in);
            }
  } // deleteMappingChannel

  // Type-specific CORBA::Object operations
  private static String[] __ids = {
    "IDL:SpiderDownloadApp/SpiderFootSide:1.0"};

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
} // class _SpiderFootSideStub
