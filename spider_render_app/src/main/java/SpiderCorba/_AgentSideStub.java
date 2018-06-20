package SpiderCorba;


/**
* SpiderCorba/_AgentSideStub.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from ../../../idl/SpiderAgentAPI.idl
* Wednesday, June 20, 2018 6:12:05 PM ICT
*/

public class _AgentSideStub extends org.omg.CORBA.portable.ObjectImpl implements SpiderCorba.AgentSide
{


  //Render app function
  public void onRenderStartup (String renderClusterId)
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("onRenderStartup", true);
                $out.write_wstring (renderClusterId);
                $in = _invoke ($out);
                return;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                onRenderStartup (renderClusterId        );
            } finally {
                _releaseReply ($in);
            }
  } // onRenderStartup

  public SpiderCorba.SpiderDefinePackage.RenderConfig getRenderConfig (int mappingId)
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("getRenderConfig", true);
                $out.write_long (mappingId);
                $in = _invoke ($out);
                SpiderCorba.SpiderDefinePackage.RenderConfig $result = SpiderCorba.SpiderDefinePackage.RenderConfigHelper.read ($in);
                return $result;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                return getRenderConfig (mappingId        );
            } finally {
                _releaseReply ($in);
            }
  } // getRenderConfig

  public void updateRenderedVideo (int jobId, SpiderCorba.SpiderDefinePackage.VideoInfo vInfo)
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("updateRenderedVideo", true);
                $out.write_long (jobId);
                SpiderCorba.SpiderDefinePackage.VideoInfoHelper.write ($out, vInfo);
                $in = _invoke ($out);
                return;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                updateRenderedVideo (jobId, vInfo        );
            } finally {
                _releaseReply ($in);
            }
  } // updateRenderedVideo

  // Type-specific CORBA::Object operations
  private static String[] __ids = {
    "IDL:SpiderCorba/AgentSide:1.0", 
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
} // class _AgentSideStub
