#include "nxcore.h"
#include <nms_corba.h>

THREAD_RESULT THREAD_CALL CorbaServerThread(void *arg)
{
	CorbaServer* corbaServer = new CorbaServer();
	bool result = corbaServer->initCorbaServer();
	if(result)
	{
		printf ("Init corba server successful!\n");
	}else{
		printf ("Init corba server false!\n");
	}
	return THREAD_OK;
}