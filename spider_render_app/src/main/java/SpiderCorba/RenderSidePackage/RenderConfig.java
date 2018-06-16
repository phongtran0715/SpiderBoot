package SpiderCorba.RenderSidePackage;


/**
* SpiderCorba/RenderSidePackage/RenderConfig.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from ../../../idl/SpiderAgentAPI.idl
* Thursday, June 7, 2018 2:19:30 PM ICT
*/

public final class RenderConfig implements org.omg.CORBA.portable.IDLEntity
{
  public String vIntroTemp = null;
  public String vOutroTemp = null;
  public String vLogoTemp = null;
  public boolean enableIntro = false;
  public boolean enableOutro = false;
  public boolean enableLogo = false;

  public RenderConfig ()
  {
  } // ctor

  public RenderConfig (String _vIntroTemp, String _vOutroTemp, String _vLogoTemp, boolean _enableIntro, boolean _enableOutro, boolean _enableLogo)
  {
    vIntroTemp = _vIntroTemp;
    vOutroTemp = _vOutroTemp;
    vLogoTemp = _vLogoTemp;
    enableIntro = _enableIntro;
    enableOutro = _enableOutro;
    enableLogo = _enableLogo;
  } // ctor

} // class RenderConfig