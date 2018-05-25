#include "nxcore.h"
#include <nms_corba.h>
#include <stdio.h>
THREAD_RESULT THREAD_CALL CorbaServerThread(void *)
{
	AgentCorbaServer *agentCorbaServer = new AgentCorbaServer();
	agentCorbaServer->initCorba();
	if (agentCorbaServer->initSuccess)
	{
		printf("Corba server init SUCCESSFUL! \n");
	} else {
		printf("Corba server init FALSE! \n");
	}
	return THREAD_OK;
}