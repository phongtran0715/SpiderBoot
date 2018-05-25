#include <echo.hh>

#ifdef HAVE_STD
#  include <iostream>
   using namespace std;
#else
#  include <iostream.h>
#endif
#include <nms_corba.h>


CORBA::Object_ptr
CorbaClient::getObjectReference(CORBA::ORB_ptr orb)
{
  CosNaming::NamingContext_var rootContext;
  
  try {
    // Obtain a reference to the root context of the Name service:
    CORBA::Object_var obj;
    obj = orb->resolve_initial_references("NameService");

    // Narrow the reference returned.
    rootContext = CosNaming::NamingContext::_narrow(obj);

    if (CORBA::is_nil(rootContext)) {
      cerr << "Failed to narrow the root naming context." << endl;
      return CORBA::Object::_nil();
    }
  }
  catch (CORBA::NO_RESOURCES&) {
    cerr << "Caught NO_RESOURCES exception. You must configure omniORB "
   << "with the location" << endl
   << "of the naming service." << endl;
    return CORBA::Object::_nil();
  }
  catch (CORBA::ORB::InvalidName& ex) {
    // This should not happen!
    cerr << "Service required is invalid [does not exist]." << endl;
    return CORBA::Object::_nil();
  }

  // Create a name object, containing the name test/context:
  CosNaming::Name name;
  name.length(2);

  name[0].id   = (const char*) "test";       // string copied
  name[0].kind = (const char*) "my_context"; // string copied
  name[1].id   = (const char*) "Echo";
  name[1].kind = (const char*) "Object";
  // Note on kind: The kind field is used to indicate the type
  // of the object. This is to avoid conventions such as that used
  // by files (name.type -- e.g. test.ps = postscript etc.)

  try {
    // Resolve the name to an object reference.
    return rootContext->resolve(name);
  }
  catch (CosNaming::NamingContext::NotFound& ex) {
    // This exception is thrown if any of the components of the
    // path [contexts or the object] aren't found:
    cerr << "Context not found." << endl;
  }
  catch (CORBA::TRANSIENT& ex) {
    cerr << "Caught system exception TRANSIENT -- unable to contact the "
         << "naming service." << endl
   << "Make sure the naming server is running and that omniORB is "
   << "configured correctly." << endl;
  }
  catch (CORBA::SystemException& ex) {
    cerr << "Caught a CORBA::" << ex._name()
   << " while using the naming service." << endl;
  }
  return CORBA::Object::_nil();
}

void CorbaClient::hello(Echo_ptr e)
{
  if (CORBA::is_nil(e)) {
    cerr << "hello: The object reference is nil!\n" << endl;
    return;
  }

  CORBA::String_var src = (const char*) "Hello!";

  CORBA::String_var dest = e->echoString(src);

  cerr << "I said, \"" << (char*)src << "\"." << endl
       << "The Echo object replied, \"" << (char*)dest <<"\"." << endl;
}

//////////////////////////////////////////////////////////////////////

bool CorbaClient::initCorbaClient () 
{
  bool result = false;
  try {
    int argc = 2;
    char* argv[] = { "-ORBInitRef", "NameService=corbaloc::localhost:2809/NameService" };
    CORBA::ORB_var orb = CORBA::ORB_init(argc, argv);

    CORBA::Object_var obj = getObjectReference(orb);

    Echo_var echoref = Echo::_narrow(obj);

    for (CORBA::ULong count=0; count < 10; count++)
      hello(echoref);

    orb->destroy();
    result = true;
  }
  catch (CORBA::TRANSIENT&) {
    cerr << "Caught system exception TRANSIENT -- unable to contact the "
         << "server." << endl;
  }
  catch (CORBA::SystemException& ex) {
    cerr << "Caught a CORBA::" << ex._name() << endl;
  }
  catch (CORBA::Exception& ex) {
    cerr << "Caught CORBA::Exception: " << ex._name() << endl;
  }
  return result;
}

//////////////////////////////////////////////////////////////////////

