package SpiderRenderApp.SpiderFootSidePackage;

/**
* SpiderRenderApp/SpiderFootSidePackage/RenderInfoHolder.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from ../../../idl/SpiderAgentAPI.idl
* Monday, June 4, 2018 3:41:36 PM ICT
*/

public final class RenderInfoHolder implements org.omg.CORBA.portable.Streamable
{
  public SpiderRenderApp.SpiderFootSidePackage.RenderInfo value = null;

  public RenderInfoHolder ()
  {
  }

  public RenderInfoHolder (SpiderRenderApp.SpiderFootSidePackage.RenderInfo initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = SpiderRenderApp.SpiderFootSidePackage.RenderInfoHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    SpiderRenderApp.SpiderFootSidePackage.RenderInfoHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return SpiderRenderApp.SpiderFootSidePackage.RenderInfoHelper.type ();
  }

}
