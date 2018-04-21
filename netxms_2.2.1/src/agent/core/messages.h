/****************************************************************************
 Messages for NetXMS Core Agent
****************************************************************************/

#ifndef _messages_h_
#define _messages_h_

//
// MessageId: MSG_AGENT_STARTED
//
// MessageText:
//
// NetXMS Agent started
//
#define MSG_AGENT_STARTED              ((DWORD)0x00000001)

//
// MessageId: MSG_AGENT_STOPPED
//
// MessageText:
//
// NetXMS Agent stopped
//
#define MSG_AGENT_STOPPED              ((DWORD)0x00000002)

//
// MessageId: MSG_SUBAGENT_LOADED
//
// MessageText:
//
// Subagent "%1" (%2) loaded successfully (version %3)
//
#define MSG_SUBAGENT_LOADED            ((DWORD)0x00000003)

//
// MessageId: MSG_SUBAGENT_LOAD_FAILED
//
// MessageText:
//
// Error loading subagent module "%1": %2
//
#define MSG_SUBAGENT_LOAD_FAILED       ((DWORD)0x00000004)

//
// MessageId: MSG_NO_SUBAGENT_ENTRY_POINT
//
// MessageText:
//
// Unable to find entry point in subagent module "%1"
//
#define MSG_NO_SUBAGENT_ENTRY_POINT    ((DWORD)0x00000005)

//
// MessageId: MSG_SUBAGENT_INIT_FAILED
//
// MessageText:
//
// Initialization of subagent "%1" (%2) failed
//
#define MSG_SUBAGENT_INIT_FAILED       ((DWORD)0x00000006)

//
// MessageId: MSG_UNEXPECTED_IRC
//
// MessageText:
//
// Internal error: unexpected iRC=%1 in ProcessCommand("%2")
//
#define MSG_UNEXPECTED_IRC             ((DWORD)0x00000007)

//
// MessageId: MSG_SOCKET_ERROR
//
// MessageText:
//
// Unable to open socket: %1
//
#define MSG_SOCKET_ERROR               ((DWORD)0x00000008)

//
// MessageId: MSG_BIND_ERROR
//
// MessageText:
//
// Unable to bind socket: %1
//
#define MSG_BIND_ERROR                 ((DWORD)0x00000009)

//
// MessageId: MSG_ACCEPT_ERROR
//
// MessageText:
//
// Unable to accept incoming connection: %1
//
#define MSG_ACCEPT_ERROR               ((DWORD)0x0000000a)

//
// MessageId: MSG_TOO_MANY_ERRORS
//
// MessageText:
//
// Too many consecutive errors on accept() call
//
#define MSG_TOO_MANY_ERRORS            ((DWORD)0x0000000b)

//
// MessageId: MSG_WSASTARTUP_FAILED
//
// MessageText:
//
// WSAStartup failed: %1
//
#define MSG_WSASTARTUP_FAILED          ((DWORD)0x0000000c)

//
// MessageId: MSG_TOO_MANY_SESSIONS
//
// MessageText:
//
// Too many communication sessions open - unable to accept new connection
//
#define MSG_TOO_MANY_SESSIONS          ((DWORD)0x0000000d)

//
// MessageId: MSG_SESSION_BROKEN
//
// MessageText:
//
// Communication session broken: %1
//
#define MSG_SESSION_BROKEN             ((DWORD)0x0000000e)

//
// MessageId: MSG_DEBUG
//
// MessageText:
//
// %1
//
#define MSG_DEBUG                      ((DWORD)0x0000000f)

//
// MessageId: MSG_AUTH_FAILED
//
// MessageText:
//
// Authentication failed for peer %1, method: %2
//
#define MSG_AUTH_FAILED                ((DWORD)0x00000010)

//
// MessageId: MSG_UNEXPECTED_ATTRIBUTE
//
// MessageText:
//
// Internal error: unexpected process attribute code %1 in GetProcessAttribute()
//
#define MSG_UNEXPECTED_ATTRIBUTE       ((DWORD)0x00000011)

//
// MessageId: MSG_UNEXPECTED_TYPE
//
// MessageText:
//
// Internal error: unexpected type code %1 in GetProcessAttribute()
//
#define MSG_UNEXPECTED_TYPE            ((DWORD)0x00000012)

//
// MessageId: MSG_NO_FUNCTION
//
// MessageText:
//
// Unable to resolve symbol "%1"
//
#define MSG_NO_FUNCTION                ((DWORD)0x00000013)

//
// MessageId: MSG_NO_DLL
//
// MessageText:
//
// Unable to get handle to "%1"
//
#define MSG_NO_DLL                     ((DWORD)0x00000014)

//
// MessageId: MSG_ADD_ACTION_FAILED
//
// MessageText:
//
// Unable to add action "%1"
//
#define MSG_ADD_ACTION_FAILED          ((DWORD)0x00000015)

//
// MessageId: MSG_CREATE_PROCESS_FAILED
//
// MessageText:
//
// Unable to create process "%1": %2
//
#define MSG_CREATE_PROCESS_FAILED      ((DWORD)0x00000016)

//
// MessageId: MSG_SUBAGENT_MSG
//
// MessageText:
//
// %1
//
#define MSG_SUBAGENT_MSG               ((DWORD)0x00000017)

//
// MessageId: MSG_SUBAGENT_BAD_MAGIC
//
// MessageText:
//
// Subagent "%1" has invalid magic number - probably it was compiled for different agent version
//
#define MSG_SUBAGENT_BAD_MAGIC         ((DWORD)0x00000018)

//
// MessageId: MSG_ADD_EXT_PARAM_FAILED
//
// MessageText:
//
// Unable to add external parameter "%1"
//
#define MSG_ADD_EXT_PARAM_FAILED       ((DWORD)0x00000019)

//
// MessageId: MSG_CREATE_TMP_FILE_FAILED
//
// MessageText:
//
// Unable to create temporary file to hold process output: %1
//
#define MSG_CREATE_TMP_FILE_FAILED     ((DWORD)0x0000001a)

//
// MessageId: MSG_SIGNAL_RECEIVED
//
// MessageText:
//
// Signal %1 received
//
#define MSG_SIGNAL_RECEIVED            ((DWORD)0x0000001b)

//
// MessageId: MSG_INIT_CRYPTO_FAILED
//
// MessageText:
//
// Failed to initialize cryptografy module
//
#define MSG_INIT_CRYPTO_FAILED         ((DWORD)0x0000001c)

//
// MessageId: MSG_DECRYPTION_FAILURE
//
// MessageText:
//
// Failed to decrypt received message
//
#define MSG_DECRYPTION_FAILURE         ((DWORD)0x0000001d)

//
// MessageId: MSG_GETVERSION_FAILED
//
// MessageText:
//
// Unable to deterime OS version (GetVersionEx() failed: %1)
//
#define MSG_GETVERSION_FAILED          ((DWORD)0x0000001e)

//
// MessageId: MSG_SELECT_ERROR
//
// MessageText:
//
// Call to select() failed: %1
//
#define MSG_SELECT_ERROR               ((DWORD)0x0000001f)

//
// MessageId: MSG_SUBAGENT_ALREADY_LOADED
//
// MessageText:
//
// Subagent "%1" already loaded from module "%2"
//
#define MSG_SUBAGENT_ALREADY_LOADED    ((DWORD)0x00000020)

//
// MessageId: MSG_PROCESS_KILLED
//
// MessageText:
//
// Process "%1" killed because of execution timeout
//
#define MSG_PROCESS_KILLED             ((DWORD)0x00000021)

//
// MessageId: MSG_DEBUG_SESSION
//
// MessageText:
//
// [CS-%1(%2)] %3
//
#define MSG_DEBUG_SESSION              ((DWORD)0x00000022)

//
// MessageId: MSG_SUBAGENT_REGISTRATION_FAILED
//
// MessageText:
//
// Registration of subagent "%1" failed
//
#define MSG_SUBAGENT_REGISTRATION_FAILED ((DWORD)0x00000023)

//
// MessageId: MSG_LISTENING
//
// MessageText:
//
// Listening on socket %1:%2
//
#define MSG_LISTENING                  ((DWORD)0x00000024)

//
// MessageId: MSG_WAITFORPROCESS_FAILED
//
// MessageText:
//
// Call to WaitForProcess failed for process %1
//
#define MSG_WAITFORPROCESS_FAILED      ((DWORD)0x00000025)

//
// MessageId: MSG_REGISTRATION_FAILED
//
// MessageText:
//
// Registration on management server failed: %1
//
#define MSG_REGISTRATION_FAILED        ((DWORD)0x00000026)

//
// MessageId: MSG_REGISTRATION_SUCCESSFULL
//
// MessageText:
//
// Successfully registered on management server %1
//
#define MSG_REGISTRATION_SUCCESSFULL   ((DWORD)0x00000027)

//
// MessageId: MSG_WATCHDOG_STARTED
//
// MessageText:
//
// Watchdog process started
//
#define MSG_WATCHDOG_STARTED           ((DWORD)0x00000028)

//
// MessageId: MSG_WATCHDOG_STOPPED
//
// MessageText:
//
// Watchdog process stopped
//
#define MSG_WATCHDOG_STOPPED           ((DWORD)0x00000029)

//
// MessageId: MSG_EXCEPTION
//
// MessageText:
//
// EXCEPTION %1 (%2) at %3 (crash dump was generated); please send files %4 and %5 to dump@netxms.org
//
#define MSG_EXCEPTION                  ((DWORD)0x0000002a)

//
// MessageId: MSG_USE_CONFIG_D
//
// MessageText:
//
// Additional configs was loaded from %1
//
#define MSG_USE_CONFIG_D               ((DWORD)0x0000002b)

//
// MessageId: MSG_SQL_ERROR
//
// MessageText:
//
// SQL query failed (Query = "%1"): %2
//
#define MSG_SQL_ERROR                  ((DWORD)0x0000002c)

//
// MessageId: MSG_DB_LIBRARY
//
// MessageText:
//
// DB Library: %1
//
#define MSG_DB_LIBRARY                 ((DWORD)0x0000002d)

//
// MessageId: MSG_DEBUG_LEVEL
//
// MessageText:
//
// Debug level set to %1
//
#define MSG_DEBUG_LEVEL                ((DWORD)0x0000002e)

//
// MessageId: MSG_REGISTRY_LOAD_FAILED
//
// MessageText:
//
// Failed to load agent's registry from file %1
//
#define MSG_REGISTRY_LOAD_FAILED       ((DWORD)0x0000002f)

//
// MessageId: MSG_REGISTRY_SAVE_FAILED
//
// MessageText:
//
// Failed to save agent's registry to file %1: %2
//
#define MSG_REGISTRY_SAVE_FAILED       ((DWORD)0x00000030)

//
// MessageId: MSG_ADD_PARAM_PROVIDER_FAILED
//
// MessageText:
//
// Unable to add external parameters provider "%1"
//
#define MSG_ADD_PARAM_PROVIDER_FAILED  ((DWORD)0x00000031)

//
// MessageId: MSG_ADD_EXTERNAL_SUBAGENT_FAILED
//
// MessageText:
//
// Unable to add external subagent "%1"
//
#define MSG_ADD_EXTERNAL_SUBAGENT_FAILED ((DWORD)0x00000032)

//
// MessageId: MSG_LOCAL_DB_OPEN_FAILED
//
// MessageText:
//
// Unable to open local database
//
#define MSG_LOCAL_DB_OPEN_FAILED       ((DWORD)0x00000033)

//
// MessageId: MSG_LOCAL_DB_CORRUPTED
//
// MessageText:
//
// Local database is corrupted and cannot be used
//
#define MSG_LOCAL_DB_CORRUPTED         ((DWORD)0x00000034)

//
// MessageId: MSG_DC_DBSCHEMA_UPGRADE_FAILED
//
// MessageText:
//
// Data collection database schema upgrade failed
//
#define MSG_DC_DBSCHEMA_UPGRADE_FAILED ((DWORD)0x00000035)

//
// MessageId: MSG_INVALID_TUNNEL_CONFIG
//
// MessageText:
//
// Invalid server connection configuration record "%1"
//
#define MSG_INVALID_TUNNEL_CONFIG      ((DWORD)0x00000036)

//
// MessageId: MSG_DEBUG_VSESSION
//
// MessageText:
//
// [VCS-%1] %2
//
#define MSG_DEBUG_VSESSION             ((DWORD)0x00000037)

//
// MessageId: MSG_ADD_EXT_LIST_FAILED
//
// MessageText:
//
// Unable to add external list "%1"
//
#define MSG_ADD_EXT_LIST_FAILED        ((DWORD)0x00000038)

//
// MessageId: MSG_ADD_EXT_TABLE_FAILED
//
// MessageText:
//
// Unable to add external table "%1"
//
#define MSG_ADD_EXT_TABLE_FAILED       ((DWORD)0x00000039)

//
// MessageId: MSG_AGENT_VERSION
//
// MessageText:
//
// Core agent version %1
//
#define MSG_AGENT_VERSION              ((DWORD)0x0000003a)

//
// MessageId: MSG_DEBUG_TAG
//
// MessageText:
//
// [%1] %2
//
#define MSG_DEBUG_TAG                  ((DWORD)0x0000003b)

#endif
