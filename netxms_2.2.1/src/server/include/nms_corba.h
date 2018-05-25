#ifndef _nms_corba_h_
#define _nms_corba_h_

#ifndef __CORBA_H_EXTERNAL_GUARD__
#include <omniORB4/CORBA.h>
#endif

#include <echo.hh>

class CorbaServer
{
public:
	CorbaServer();
	~CorbaServer();
	CORBA::Boolean bindObjectToName(CORBA::ORB_ptr orb, CORBA::Object_ptr objref);
	bool initCorbaServer();
};

class CorbaClient
{
public:
	CorbaClient();
	~CorbaClient();
	CORBA::Object_ptr getObjectReference(CORBA::ORB_ptr orb);
	void hello(Echo_ptr e);
	bool initCorbaClient ();
};

#endif   /* _nms_corba_h_ */