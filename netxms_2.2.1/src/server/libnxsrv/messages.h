/****************************************************************************
 Messages for NetXMS Server
****************************************************************************/

#ifndef _messages_h_
#define _messages_h_

//
// MessageId: MSG_SERVER_STARTED
//
// MessageText:
//
// NetXMS Server started
//
#define MSG_SERVER_STARTED             ((DWORD)0x00000001)

//
// MessageId: MSG_SERVER_STOPPED
//
// MessageText:
//
// NetXMS Server stopped
//
#define MSG_SERVER_STOPPED             ((DWORD)0x00000002)

//
// MessageId: MSG_DLSYM_FAILED
//
// MessageText:
//
// Unable to resolve symbol "%1": %2
//
#define MSG_DLSYM_FAILED               ((DWORD)0x00000003)

//
// MessageId: MSG_DLOPEN_FAILED
//
// MessageText:
//
// Unable to load module "%1": %2
//
#define MSG_DLOPEN_FAILED              ((DWORD)0x00000004)

//
// MessageId: MSG_DBDRV_NO_ENTRY_POINTS
//
// MessageText:
//
// Unable to find all required exportable functions in database driver "%1"
//
#define MSG_DBDRV_NO_ENTRY_POINTS      ((DWORD)0x00000005)

//
// MessageId: MSG_DBDRV_INIT_FAILED
//
// MessageText:
//
// Database driver "%1" initialization failed
//
#define MSG_DBDRV_INIT_FAILED          ((DWORD)0x00000006)

//
// MessageId: MSG_DBDRV_LOADED
//
// MessageText:
//
// Database driver "%1" loaded and initialized successfully
//
#define MSG_DBDRV_LOADED               ((DWORD)0x00000007)

//
// MessageId: MSG_DB_CONNFAIL
//
// MessageText:
//
// Unable to establish connection with database (%1)
//
#define MSG_DB_CONNFAIL                ((DWORD)0x00000008)

//
// MessageId: MSG_OBJECT_ID_EXIST
//
// MessageText:
//
// Attempt to add node with non-unique ID %1 to database
//
#define MSG_OBJECT_ID_EXIST            ((DWORD)0x00000009)

//
// MessageId: MSG_OBJECT_ADDR_EXIST
//
// MessageText:
//
// Attempt to add node with non-unique IP address %1 to database
//
#define MSG_OBJECT_ADDR_EXIST          ((DWORD)0x0000000a)

//
// MessageId: MSG_BAD_NETOBJ_TYPE
//
// MessageText:
//
// Internal error: invalid NetObj.Type() value %1
//
#define MSG_BAD_NETOBJ_TYPE            ((DWORD)0x0000000b)

//
// MessageId: MSG_RAW_SOCK_FAILED
//
// MessageText:
//
// Unable to create raw socket for ICMP protocol
//
#define MSG_RAW_SOCK_FAILED            ((DWORD)0x0000000c)

//
// MessageId: MSG_DUPLICATE_KEY
//
// MessageText:
//
// Attempt to add object with non-unique key %1 to index
//
#define MSG_DUPLICATE_KEY              ((DWORD)0x0000000d)

//
// MessageId: MSG_SUBNET_LOAD_FAILED
//
// MessageText:
//
// Failed to load subnet object with id %1 from database
//
#define MSG_SUBNET_LOAD_FAILED         ((DWORD)0x0000000e)

//
// MessageId: MSG_NODE_LOAD_FAILED
//
// MessageText:
//
// Failed to load node object with id %1 from database
//
#define MSG_NODE_LOAD_FAILED           ((DWORD)0x0000000f)

//
// MessageId: MSG_INTERFACE_LOAD_FAILED
//
// MessageText:
//
// Failed to load interface object with id %1 from database
//
#define MSG_INTERFACE_LOAD_FAILED      ((DWORD)0x00000010)

//
// MessageId: MSG_CONTAINER_LOAD_FAILED
//
// MessageText:
//
// Failed to load container object with id %1 from database
//
#define MSG_CONTAINER_LOAD_FAILED      ((DWORD)0x00000011)

//
// MessageId: MSG_TG_LOAD_FAILED
//
// MessageText:
//
// Failed to load template group object with id %1 from database
//
#define MSG_TG_LOAD_FAILED             ((DWORD)0x00000012)

//
// MessageId: MSG_TEMPLATE_LOAD_FAILED
//
// MessageText:
//
// Failed to load template object with id %1 from database
//
#define MSG_TEMPLATE_LOAD_FAILED       ((DWORD)0x00000013)

//
// MessageId: MSG_INVALID_SUBNET_ID
//
// MessageText:
//
// Inconsistent database: node %1 linked to non-existing subnet %2
//
#define MSG_INVALID_SUBNET_ID          ((DWORD)0x00000014)

//
// MessageId: MSG_SUBNET_NOT_SUBNET
//
// MessageText:
//
// Inconsistent database: node %1 linked to object %2 which is not a subnet
//
#define MSG_SUBNET_NOT_SUBNET          ((DWORD)0x00000015)

//
// MessageId: MSG_INVALID_NODE_ID
//
// MessageText:
//
// Inconsistent database: interface %1 linked to non-existing node %2
//
#define MSG_INVALID_NODE_ID            ((DWORD)0x00000016)

//
// MessageId: MSG_NODE_NOT_NODE
//
// MessageText:
//
// Inconsistent database: interface %1 linked to object %2 which is not a node
//
#define MSG_NODE_NOT_NODE              ((DWORD)0x00000017)

//
// MessageId: MSG_SNMP_UNKNOWN_TYPE
//
// MessageText:
//
// Unknown SNMP variable type %1 in GET response PDU
//
#define MSG_SNMP_UNKNOWN_TYPE          ((DWORD)0x00000018)

//
// MessageId: MSG_SNMP_GET_ERROR
//
// MessageText:
//
// Error %1 in processing SNMP GET request
//
#define MSG_SNMP_GET_ERROR             ((DWORD)0x00000019)

//
// MessageId: MSG_SNMP_BAD_PACKET
//
// MessageText:
//
// Error in SNMP response packet: %1
//
#define MSG_SNMP_BAD_PACKET            ((DWORD)0x0000001a)

//
// MessageId: MSG_OID_PARSE_ERROR
//
// MessageText:
//
// Error parsing SNMP OID "%1" in %2 (destination IP address %3)
//
#define MSG_OID_PARSE_ERROR            ((DWORD)0x0000001b)

//
// MessageId: MSG_SNMP_TRANSPORT_FAILED
//
// MessageText:
//
// Unable to create UDP transport for SNMP
//
#define MSG_SNMP_TRANSPORT_FAILED      ((DWORD)0x0000001c)

//
// MessageId: MSG_SOCKET_FAILED
//
// MessageText:
//
// Unable to create socket in function %1
//
#define MSG_SOCKET_FAILED              ((DWORD)0x0000001d)

//
// MessageId: MSG_BIND_ERROR
//
// MessageText:
//
// Unable to bind socket to port %1 in function %2: %3
//
#define MSG_BIND_ERROR                 ((DWORD)0x0000001e)

//
// MessageId: MSG_ACCEPT_ERROR
//
// MessageText:
//
// Error returned by accept() system call: %1
//
#define MSG_ACCEPT_ERROR               ((DWORD)0x0000001f)

//
// MessageId: MSG_TOO_MANY_ACCEPT_ERRORS
//
// MessageText:
//
// There are too many consecutive accept() errors
//
#define MSG_TOO_MANY_ACCEPT_ERRORS     ((DWORD)0x00000020)

//
// MessageId: MSG_AGENT_CONNECT_FAILED
//
// MessageText:
//
// Unable to establish connection with agent on host %1
//
#define MSG_AGENT_CONNECT_FAILED       ((DWORD)0x00000021)

//
// MessageId: MSG_AGENT_COMM_FAILED
//
// MessageText:
//
// Unable to communicate with agent on host %1 (possibly due to authentication failure)
//
#define MSG_AGENT_COMM_FAILED          ((DWORD)0x00000022)

//
// MessageId: MSG_AGENT_BAD_HELLO
//
// MessageText:
//
// Invalid HELLO message received from agent on host %1 (possibly due to incompatible protocol version)
//
#define MSG_AGENT_BAD_HELLO            ((DWORD)0x00000023)

//
// MessageId: MSG_GETIPADDRTABLE_FAILED
//
// MessageText:
//
// Call to GetIpAddrTable() failed: %1
//
#define MSG_GETIPADDRTABLE_FAILED      ((DWORD)0x00000024)

//
// MessageId: MSG_GETIPNETTABLE_FAILED
//
// MessageText:
//
// Call to GetIpNetTable() failed: %1
//
#define MSG_GETIPNETTABLE_FAILED       ((DWORD)0x00000025)

//
// MessageId: MSG_EVENT_LOAD_ERROR
//
// MessageText:
//
// Unable to load events from database
//
#define MSG_EVENT_LOAD_ERROR           ((DWORD)0x00000026)

//
// MessageId: MSG_THREAD_NOT_RESPONDING
//
// MessageText:
//
// Thread "%1" does not respond to watchdog thread
//
#define MSG_THREAD_NOT_RESPONDING      ((DWORD)0x00000027)

//
// MessageId: MSG_SESSION_CLOSED
//
// MessageText:
//
// Client session closed due to communication error (%1)
//
#define MSG_SESSION_CLOSED             ((DWORD)0x00000028)

//
// MessageId: MSG_TOO_MANY_SESSIONS
//
// MessageText:
//
// Too many client sessions open - unable to accept new client connection
//
#define MSG_TOO_MANY_SESSIONS          ((DWORD)0x00000029)

//
// MessageId: MSG_ERROR_LOADING_USERS
//
// MessageText:
//
// Unable to load users and user groups from database (probably database is corrupted)
//
#define MSG_ERROR_LOADING_USERS        ((DWORD)0x0000002a)

//
// MessageId: MSG_SQL_ERROR
//
// MessageText:
//
// SQL query failed (Query = "%1"): %2
//
#define MSG_SQL_ERROR                  ((DWORD)0x0000002b)

//
// MessageId: MSG_INVALID_PASSWORD_HASH
//
// MessageText:
//
// Invalid password hash for user %1: password reset to default
//
#define MSG_INVALID_PASSWORD_HASH      ((DWORD)0x0000002c)

//
// MessageId: MSG_EPP_LOAD_FAILED
//
// MessageText:
//
// Error loading event processing policy from database
//
#define MSG_EPP_LOAD_FAILED            ((DWORD)0x0000002d)

//
// MessageId: MSG_INIT_LOCKS_FAILED
//
// MessageText:
//
// Error initializing component locks table
//
#define MSG_INIT_LOCKS_FAILED          ((DWORD)0x0000002e)

//
// MessageId: MSG_DB_LOCKED
//
// MessageText:
//
// Database is already locked by another NetXMS server instance (IP address: %1, machine info: %2)
//
#define MSG_DB_LOCKED                  ((DWORD)0x0000002f)

//
// MessageId: MSG_ACTIONS_LOAD_FAILED
//
// MessageText:
//
// Error loading event actions configuration from database
//
#define MSG_ACTIONS_LOAD_FAILED        ((DWORD)0x00000030)

//
// MessageId: MSG_CREATE_PROCESS_FAILED
//
// MessageText:
//
// Unable to create process "%1": %2
//
#define MSG_CREATE_PROCESS_FAILED      ((DWORD)0x00000031)

//
// MessageId: MSG_NO_UNIQUE_ID
//
// MessageText:
//
// Unable to assign unique ID to object in group "%1". You should perform database optimization to fix that.
//
#define MSG_NO_UNIQUE_ID               ((DWORD)0x00000032)

//
// MessageId: MSG_INVALID_DTYPE
//
// MessageText:
//
// Internal error: invalid DTYPE %1 in %2
//
#define MSG_INVALID_DTYPE              ((DWORD)0x00000033)

//
// MessageId: MSG_IOCTL_FAILED
//
// MessageText:
//
// Call to ioctl() failed (Operation: %1 Error: %2)
//
#define MSG_IOCTL_FAILED               ((DWORD)0x00000034)

//
// MessageId: MSG_IFNAMEINDEX_FAILED
//
// MessageText:
//
// Call to if_nameindex() failed (%1)
//
#define MSG_IFNAMEINDEX_FAILED         ((DWORD)0x00000035)

//
// MessageId: MSG_SUPERUSER_CREATED
//
// MessageText:
//
// System account created because it was not presented in database
//
#define MSG_SUPERUSER_CREATED          ((DWORD)0x00000036)

//
// MessageId: MSG_EVERYONE_GROUP_CREATED
//
// MessageText:
//
// User group "Everyone" created because it was not presented in database
//
#define MSG_EVERYONE_GROUP_CREATED     ((DWORD)0x00000037)

//
// MessageId: MSG_INVALID_DATA_DIR
//
// MessageText:
//
// Data directory "%1" does not exist or is inaccessible
//
#define MSG_INVALID_DATA_DIR           ((DWORD)0x00000038)

//
// MessageId: MSG_ERROR_CREATING_DATA_DIR
//
// MessageText:
//
// Error creating data directory "%1"
//
#define MSG_ERROR_CREATING_DATA_DIR    ((DWORD)0x00000039)

//
// MessageId: MSG_INVALID_EPP_OBJECT
//
// MessageText:
//
// Invalid object identifier %1 in event processing policy
//
#define MSG_INVALID_EPP_OBJECT         ((DWORD)0x0000003a)

//
// MessageId: MSG_INVALID_CONTAINER_MEMBER
//
// MessageText:
//
// Inconsistent database: container object %1 has reference to non-existing child object %2
//
#define MSG_INVALID_CONTAINER_MEMBER   ((DWORD)0x0000003b)

//
// MessageId: MSG_ROOT_INVALID_CHILD_ID
//
// MessageText:
//
// Inconsistent database: %2 object has reference to non-existing child object %1
//
#define MSG_ROOT_INVALID_CHILD_ID      ((DWORD)0x0000003c)

//
// MessageId: MSG_ERROR_READ_IMAGE_CATALOG
//
// MessageText:
//
// Error reading image catalog from database
//
#define MSG_ERROR_READ_IMAGE_CATALOG   ((DWORD)0x0000003d)

//
// MessageId: MSG_IMAGE_FILE_IO_ERROR
//
// MessageText:
//
// I/O error reading image file %2 (image ID %1)
//
#define MSG_IMAGE_FILE_IO_ERROR        ((DWORD)0x0000003e)

//
// MessageId: MSG_WRONG_DB_VERSION
//
// MessageText:
//
// Your database has format version %1, but server is compiled for version %2
//
#define MSG_WRONG_DB_VERSION           ((DWORD)0x0000003f)

//
// MessageId: MSG_ACTION_INIT_ERROR
//
// MessageText:
//
// Unable to initialize actions
//
#define MSG_ACTION_INIT_ERROR          ((DWORD)0x00000040)

//
// MessageId: MSG_DEBUG
//
// MessageText:
//
// %1
//
#define MSG_DEBUG                      ((DWORD)0x00000041)

//
// MessageId: MSG_SIGNAL_RECEIVED
//
// MessageText:
//
// Signal %1 received
//
#define MSG_SIGNAL_RECEIVED            ((DWORD)0x00000042)

//
// MessageId: MSG_INVALID_DCT_MAP
//
// MessageText:
//
// Inconsistent database: template object %1 has reference to non-existing node object %2
//
#define MSG_INVALID_DCT_MAP            ((DWORD)0x00000043)

//
// MessageId: MSG_DCT_MAP_NOT_NODE
//
// MessageText:
//
// Inconsistent database: template object %1 has reference to child object %2 which is not a node object
//
#define MSG_DCT_MAP_NOT_NODE           ((DWORD)0x00000044)

//
// MessageId: MSG_INVALID_TRAP_OID
//
// MessageText:
//
// Invalid trap enterprise ID %1 in trap configuration table
//
#define MSG_INVALID_TRAP_OID           ((DWORD)0x00000045)

//
// MessageId: MSG_INVALID_TRAP_ARG_OID
//
// MessageText:
//
// Invalid trap parameter OID %1 for trap %2 in trap configuration table
//
#define MSG_INVALID_TRAP_ARG_OID       ((DWORD)0x00000046)

//
// MessageId: MSG_MODULE_BAD_MAGIC
//
// MessageText:
//
// Module "%1" has invalid magic number - probably it was compiled for different NetXMS server version
//
#define MSG_MODULE_BAD_MAGIC           ((DWORD)0x00000047)

//
// MessageId: MSG_MODULE_LOADED
//
// MessageText:
//
// Server module "%1" loaded successfully
//
#define MSG_MODULE_LOADED              ((DWORD)0x00000048)

//
// MessageId: MSG_NO_MODULE_ENTRY_POINT
//
// MessageText:
//
// Unable to find entry point in server module "%1"
//
#define MSG_NO_MODULE_ENTRY_POINT      ((DWORD)0x00000049)

//
// MessageId: MSG_MODULE_INIT_FAILED
//
// MessageText:
//
// Initialization of server module "%1" failed
//
#define MSG_MODULE_INIT_FAILED         ((DWORD)0x0000004a)

//
// MessageId: MSG_NETSRV_LOAD_FAILED
//
// MessageText:
//
// Failed to load network service object with id %1 from database
//
#define MSG_NETSRV_LOAD_FAILED         ((DWORD)0x0000004b)

//
// MessageId: MSG_SMSDRV_NO_ENTRY_POINTS
//
// MessageText:
//
// Unable to find all required exportable functions in SMS driver "%1"
//
#define MSG_SMSDRV_NO_ENTRY_POINTS     ((DWORD)0x0000004c)

//
// MessageId: MSG_SMSDRV_INIT_FAILED
//
// MessageText:
//
// SMS driver "%1" initialization failed
//
#define MSG_SMSDRV_INIT_FAILED         ((DWORD)0x0000004d)

//
// MessageId: MSG_ZONE_LOAD_FAILED
//
// MessageText:
//
// Failed to load zone object with id %1 from database
//
#define MSG_ZONE_LOAD_FAILED           ((DWORD)0x0000004e)

//
// MessageId: MSG_GSM_MODEM_INFO
//
// MessageText:
//
// GSM modem on %1 initialized successfully. Hardware ID: "%2".
//
#define MSG_GSM_MODEM_INFO             ((DWORD)0x0000004f)

//
// MessageId: MSG_INIT_CRYPTO_FAILED
//
// MessageText:
//
// Failed to initialize cryptografy module
//
#define MSG_INIT_CRYPTO_FAILED         ((DWORD)0x00000050)

//
// MessageId: MSG_VPNC_LOAD_FAILED
//
// MessageText:
//
// Failed to load VPN connector object with id %1 from database
//
#define MSG_VPNC_LOAD_FAILED           ((DWORD)0x00000051)

//
// MessageId: MSG_INVALID_NODE_ID_EX
//
// MessageText:
//
// Inconsistent database: %3 %1 linked to non-existing node %2
//
#define MSG_INVALID_NODE_ID_EX         ((DWORD)0x00000052)

//
// MessageId: MSG_SCRIPT_COMPILATION_ERROR
//
// MessageText:
//
// Error compiling library script %2 (ID: %1): %3
//
#define MSG_SCRIPT_COMPILATION_ERROR   ((DWORD)0x00000053)

//
// MessageId: MSG_RADIUS_UNKNOWN_ENCR_METHOD
//
// MessageText:
//
// RADIUS client error: encryption style %1 is not implemented (attribute %2)
//
#define MSG_RADIUS_UNKNOWN_ENCR_METHOD ((DWORD)0x00000054)

//
// MessageId: MSG_RADIUS_DECR_FAILED
//
// MessageText:
//
// RADIUS client error: decryption (style %1) failed for attribute %2
//
#define MSG_RADIUS_DECR_FAILED         ((DWORD)0x00000055)

//
// MessageId: MSG_RADIUS_AUTH_SUCCESS
//
// MessageText:
//
// User %1 was successfully authenticated by RADIUS server %2
//
#define MSG_RADIUS_AUTH_SUCCESS        ((DWORD)0x00000056)

//
// MessageId: MSG_RADIUS_AUTH_FAILED
//
// MessageText:
//
// Authentication request for user %1 was rejected by RADIUS server %2
//
#define MSG_RADIUS_AUTH_FAILED         ((DWORD)0x00000057)

//
// MessageId: MSG_UNKNOWN_AUTH_METHOD
//
// MessageText:
//
// Unsupported authentication method %1 requested for user %2
//
#define MSG_UNKNOWN_AUTH_METHOD        ((DWORD)0x00000058)

//
// MessageId: MSG_CONDITION_LOAD_FAILED
//
// MessageText:
//
// Failed to load condition object with id %1 from database
//
#define MSG_CONDITION_LOAD_FAILED      ((DWORD)0x00000059)

//
// MessageId: MSG_COND_SCRIPT_COMPILATION_ERROR
//
// MessageText:
//
// Failed to compile evaluation script for condition object %1 "%2": %3
//
#define MSG_COND_SCRIPT_COMPILATION_ERROR ((DWORD)0x0000005a)

//
// MessageId: MSG_COND_SCRIPT_EXECUTION_ERROR
//
// MessageText:
//
// Failed to execute evaluation script for condition object %1 "%2": %3
//
#define MSG_COND_SCRIPT_EXECUTION_ERROR ((DWORD)0x0000005b)

//
// MessageId: MSG_DB_LOCK_REMOVED
//
// MessageText:
//
// Stalled database lock removed
//
#define MSG_DB_LOCK_REMOVED            ((DWORD)0x0000005c)

//
// MessageId: MSG_DBDRV_API_VERSION_MISMATCH
//
// MessageText:
//
// Database driver "%1" cannot be loaded because of API version mismatch (driver: %3; server: %2)
//
#define MSG_DBDRV_API_VERSION_MISMATCH ((DWORD)0x0000005d)

//
// MessageId: MSG_PLATFORM_SUBAGENT_LOADED
//
// MessageText:
//
// Platform subagent "%1" successfully loaded
//
#define MSG_PLATFORM_SUBAGENT_LOADED   ((DWORD)0x0000005e)

//
// MessageId: MSG_PLATFORM_SUBAGENT_NOT_LOADED
//
// MessageText:
//
// Cannot load platform subagent "%1": %2
//
#define MSG_PLATFORM_SUBAGENT_NOT_LOADED ((DWORD)0x0000005f)

//
// MessageId: MSG_EPRULE_SCRIPT_COMPILATION_ERROR
//
// MessageText:
//
// Failed to compile evaluation script for event processing policy rule #%1: %2
//
#define MSG_EPRULE_SCRIPT_COMPILATION_ERROR ((DWORD)0x00000060)

//
// MessageId: MSG_EPRULE_SCRIPT_EXECUTION_ERROR
//
// MessageText:
//
// Failed to execute evaluation script for event processing policy rule #%1: %2
//
#define MSG_EPRULE_SCRIPT_EXECUTION_ERROR ((DWORD)0x00000061)

//
// MessageId: MSG_CANNOT_FIND_SELF
//
// MessageText:
//
// NetXMS server cannot create node object for itself - probably because platform subagent cannot be loaded (check above error messages, if any)
//
#define MSG_CANNOT_FIND_SELF           ((DWORD)0x00000062)

//
// MessageId: MSG_INVALID_CLUSTER_MEMBER
//
// MessageText:
//
// Inconsistent database: cluster object %1 has reference to non-existing node object %2
//
#define MSG_INVALID_CLUSTER_MEMBER     ((DWORD)0x00000063)

//
// MessageId: MSG_CLUSTER_MEMBER_NOT_NODE
//
// MessageText:
//
// Inconsistent database: cluster object %1 has reference to child object %2 which is not a node object
//
#define MSG_CLUSTER_MEMBER_NOT_NODE    ((DWORD)0x00000064)

//
// MessageId: MSG_CLUSTER_LOAD_FAILED
//
// MessageText:
//
// Failed to load cluster object with id %1 from database
//
#define MSG_CLUSTER_LOAD_FAILED        ((DWORD)0x00000065)

//
// MessageId: MSG_EXCEPTION
//
// MessageText:
//
// EXCEPTION %1 (%2) at %3 (crash dump was generated); please send files %4 and %5 to dump@netxms.org
//
#define MSG_EXCEPTION                  ((DWORD)0x00000066)

//
// MessageId: MSG_CANNOT_INIT_CERT_STORE
//
// MessageText:
//
// Cannot initialize certificate store: %1
//
#define MSG_CANNOT_INIT_CERT_STORE     ((DWORD)0x00000067)

//
// MessageId: MSG_CANNOT_LOAD_CERT
//
// MessageText:
//
// Cannot load certificate %1 from database: %2
//
#define MSG_CANNOT_LOAD_CERT           ((DWORD)0x00000068)

//
// MessageId: MSG_CANNOT_ADD_CERT
//
// MessageText:
//
// Cannot add certificate %1 to store: %2
//
#define MSG_CANNOT_ADD_CERT            ((DWORD)0x00000069)

//
// MessageId: MSG_CA_CERTIFICATES_LOADED
//
// MessageText:
//
// Successfully loaded %1 trusted CA certificates
//
#define MSG_CA_CERTIFICATES_LOADED     ((DWORD)0x0000006a)

//
// MessageId: MSG_INTERNAL_ERROR
//
// MessageText:
//
// Internal error: %1
//
#define MSG_INTERNAL_ERROR             ((DWORD)0x0000006b)

//
// MessageId: MSG_CODEPAGE_ERROR
//
// MessageText:
//
// Unable to set codepage to %1
//
#define MSG_CODEPAGE_ERROR             ((DWORD)0x0000006c)

//
// MessageId: MSG_INVALID_BEACON
//
// MessageText:
//
// Invalid beacon host name or address %1 - host will be excluded from beacon list
//
#define MSG_INVALID_BEACON             ((DWORD)0x0000006d)

//
// MessageId: MSG_LISTENING_FOR_CLIENTS
//
// MessageText:
//
// Listening for client connections on TCP socket %1:%2
//
#define MSG_LISTENING_FOR_CLIENTS      ((DWORD)0x0000006e)

//
// MessageId: MSG_LISTENING_FOR_SNMP
//
// MessageText:
//
// Listening for SNMP traps on UDP socket %1:%2
//
#define MSG_LISTENING_FOR_SNMP         ((DWORD)0x0000006f)

//
// MessageId: MSG_LISTENING_FOR_SYSLOG
//
// MessageText:
//
// Listening for syslog messages on UDP socket %1:%2
//
#define MSG_LISTENING_FOR_SYSLOG       ((DWORD)0x00000070)

//
// MessageId: MSG_OTHER
//
// MessageText:
//
// %1
//
#define MSG_OTHER                      ((DWORD)0x00000071)

//
// MessageId: MSG_SYSLOG_PARSER_INIT_FAILED
//
// MessageText:
//
// Cannot initialize syslog parser: %1
//
#define MSG_SYSLOG_PARSER_INIT_FAILED  ((DWORD)0x00000072)

//
// MessageId: MSG_ISC_SESSION_ESTABLISHED
//
// MessageText:
//
// Established ISC session from %1 to service %2
//
#define MSG_ISC_SESSION_ESTABLISHED    ((DWORD)0x00000073)

//
// MessageId: MSG_ISC_SESSION_SETUP_FAILED
//
// MessageText:
//
// Failed to establish ISC session from %1 to service %2: %3
//
#define MSG_ISC_SESSION_SETUP_FAILED   ((DWORD)0x00000074)

//
// MessageId: MSG_EVENT_FORWARD_FAILED
//
// MessageText:
//
// Failed to forward event to server %1: %2
//
#define MSG_EVENT_FORWARD_FAILED       ((DWORD)0x00000075)

//
// MessageId: MSG_TEMPLATE_SCRIPT_COMPILATION_ERROR
//
// MessageText:
//
// Failed to compile filter script for template object %1 "%2": %3
//
#define MSG_TEMPLATE_SCRIPT_COMPILATION_ERROR ((DWORD)0x00000076)

//
// MessageId: MSG_TEMPLATE_SCRIPT_EXECUTION_ERROR
//
// MessageText:
//
// Failed to execute filter script for template object %1 "%2": %3
//
#define MSG_TEMPLATE_SCRIPT_EXECUTION_ERROR ((DWORD)0x00000077)

//
// MessageId: MSG_CONTAINER_SCRIPT_COMPILATION_ERROR
//
// MessageText:
//
// Failed to compile filter script for container object %1 "%2": %3
//
#define MSG_CONTAINER_SCRIPT_COMPILATION_ERROR ((DWORD)0x00000078)

//
// MessageId: MSG_CONTAINER_SCRIPT_EXECUTION_ERROR
//
// MessageText:
//
// Failed to execute filter script for container object %1 "%2": %3
//
#define MSG_CONTAINER_SCRIPT_EXECUTION_ERROR ((DWORD)0x00000079)

//
// MessageId: MSG_PG_LOAD_FAILED
//
// MessageText:
//
// Failed to load policy group object with id %1 from database
//
#define MSG_PG_LOAD_FAILED             ((DWORD)0x0000007a)

//
// MessageId: MSG_AGENTPOLICY_LOAD_FAILED
//
// MessageText:
//
// Failed to load agent policy object with id %1 from database
//
#define MSG_AGENTPOLICY_LOAD_FAILED    ((DWORD)0x0000007b)

//
// MessageId: MSG_INVALID_AP_BINDING
//
// MessageText:
//
// Inconsistent database: agent policy object %1 has reference to non-existing node object %2
//
#define MSG_INVALID_AP_BINDING         ((DWORD)0x0000007c)

//
// MessageId: MSG_AP_BINDING_NOT_NODE
//
// MessageText:
//
// Inconsistent database: agent policy object %1 has reference to child object %2 which is not a node object
//
#define MSG_AP_BINDING_NOT_NODE        ((DWORD)0x0000007d)

//
// MessageId: MSG_MG_LOAD_FAILED
//
// MessageText:
//
// Failed to load network map group object with id %1 from database
//
#define MSG_MG_LOAD_FAILED             ((DWORD)0x0000007e)

//
// MessageId: MSG_NETMAP_LOAD_FAILED
//
// MessageText:
//
// Failed to load network map object with id %1 from database
//
#define MSG_NETMAP_LOAD_FAILED         ((DWORD)0x0000007f)

//
// MessageId: MSG_SET_PROCESS_AFFINITY_NOT_SUPPORTED
//
// MessageText:
//
// Setting process CPU affinity is not supported on this operating system
//
#define MSG_SET_PROCESS_AFFINITY_NOT_SUPPORTED ((DWORD)0x00000080)

//
// MessageId: MSG_WSASTARTUP_FAILED
//
// MessageText:
//
// Unable to initialize Windows Sockets: %1
//
#define MSG_WSASTARTUP_FAILED          ((DWORD)0x00000081)

//
// MessageId: MSG_NDD_LOADED
//
// MessageText:
//
// Network device driver "%1" loaded successfully
//
#define MSG_NDD_LOADED                 ((DWORD)0x00000082)

//
// MessageId: MSG_NO_NDD_ENTRY_POINT
//
// MessageText:
//
// Unable to find entry point in network device driver "%1"
//
#define MSG_NO_NDD_ENTRY_POINT         ((DWORD)0x00000083)

//
// MessageId: MSG_NDD_INIT_FAILED
//
// MessageText:
//
// Initialization of network device driver "%1" failed
//
#define MSG_NDD_INIT_FAILED            ((DWORD)0x00000084)

//
// MessageId: MSG_NDD_API_VERSION_MISMATCH
//
// MessageText:
//
// Network device driver "%1" cannot be loaded because of API version mismatch (driver: %3; server: %2)
//
#define MSG_NDD_API_VERSION_MISMATCH   ((DWORD)0x00000085)

//
// MessageId: MSG_DASHBOARD_LOAD_FAILED
//
// MessageText:
//
// Failed to load dashboard object with id %1 from database
//
#define MSG_DASHBOARD_LOAD_FAILED      ((DWORD)0x00000086)

//
// MessageId: MSG_SMSDRV_INVALID_OPTION
//
// MessageText:
//
// Invalid option %1 passed to SMS driver
//
#define MSG_SMSDRV_INVALID_OPTION      ((DWORD)0x00000087)

//
// MessageId: MSG_RG_LOAD_FAILED
//
// MessageText:
//
// Failed to load report group object with id %1 from database
//
#define MSG_RG_LOAD_FAILED             ((DWORD)0x00000088)

//
// MessageId: MSG_REPORT_LOAD_FAILED
//
// MessageText:
//
// Failed to load report object with id %1 from database
//
#define MSG_REPORT_LOAD_FAILED         ((DWORD)0x00000089)

//
// MessageId: MSG_SLMCHECK_SCRIPT_COMPILATION_ERROR
//
// MessageText:
//
// Failed to compile script for service check object %1 "%2": %3
//
#define MSG_SLMCHECK_SCRIPT_COMPILATION_ERROR ((DWORD)0x0000008a)

//
// MessageId: MSG_SLMCHECK_SCRIPT_EXECUTION_ERROR
//
// MessageText:
//
// Failed to execute script for service check object %1 "%2": %3
//
#define MSG_SLMCHECK_SCRIPT_EXECUTION_ERROR ((DWORD)0x0000008b)

//
// MessageId: MSG_BUSINESS_SERVICE_LOAD_FAILED
//
// MessageText:
//
// Failed to load business service object with id %1 from database
//
#define MSG_BUSINESS_SERVICE_LOAD_FAILED ((DWORD)0x0000008c)

//
// MessageId: MSG_NODE_LINK_LOAD_FAILED
//
// MessageText:
//
// Failed to load node link object with id %1 from database
//
#define MSG_NODE_LINK_LOAD_FAILED      ((DWORD)0x0000008d)

//
// MessageId: MSG_SERVICE_CHECK_LOAD_FAILED
//
// MessageText:
//
// Failed to load service check object with id %1 from database
//
#define MSG_SERVICE_CHECK_LOAD_FAILED  ((DWORD)0x0000008e)

//
// MessageId: MSG_MOBILEDEVICE_LOAD_FAILED
//
// MessageText:
//
// Failed to load mobile device object with id %1 from database
//
#define MSG_MOBILEDEVICE_LOAD_FAILED   ((DWORD)0x0000008f)

//
// MessageId: MSG_MD_SESSION_CLOSED
//
// MessageText:
//
// Mobile device session closed due to communication error (%1)
//
#define MSG_MD_SESSION_CLOSED          ((DWORD)0x00000090)

//
// MessageId: MSG_TOO_MANY_MD_SESSIONS
//
// MessageText:
//
// Too many mobile device sessions open - unable to accept new client connection
//
#define MSG_TOO_MANY_MD_SESSIONS       ((DWORD)0x00000091)

//
// MessageId: MSG_LISTENING_FOR_MOBILE_DEVICES
//
// MessageText:
//
// Listening for mobile device connections on TCP socket %1:%2
//
#define MSG_LISTENING_FOR_MOBILE_DEVICES ((DWORD)0x00000092)

//
// MessageId: MSG_AP_LOAD_FAILED
//
// MessageText:
//
// Failed to load access point object with id %1 from database
//
#define MSG_AP_LOAD_FAILED             ((DWORD)0x00000093)

//
// MessageId: MSG_RACK_LOAD_FAILED
//
// MessageText:
//
// Failed to load rack object with id %1 from database
//
#define MSG_RACK_LOAD_FAILED           ((DWORD)0x00000094)

//
// MessageId: MSG_NETMAP_SCRIPT_COMPILATION_ERROR
//
// MessageText:
//
// Failed to compile filter script for network map object %1 "%2": %3
//
#define MSG_NETMAP_SCRIPT_COMPILATION_ERROR ((DWORD)0x00000095)

//
// MessageId: MSG_NETMAP_SCRIPT_EXECUTION_ERROR
//
// MessageText:
//
// Failed to execute filter script for network map object %1 "%2": %3
//
#define MSG_NETMAP_SCRIPT_EXECUTION_ERROR ((DWORD)0x00000096)

//
// MessageId: MSG_XMPP_ERROR
//
// MessageText:
//
// XMPP: %1 %2
//
#define MSG_XMPP_ERROR                 ((DWORD)0x00000097)

//
// MessageId: MSG_XMPP_WARNING
//
// MessageText:
//
// XMPP: %1 %2
//
#define MSG_XMPP_WARNING               ((DWORD)0x00000098)

//
// MessageId: MSG_XMPP_INFO
//
// MessageText:
//
// XMPP: %1 %2
//
#define MSG_XMPP_INFO                  ((DWORD)0x00000099)

//
// MessageId: MSG_SERIAL_PORT_OPEN_FAILED
//
// MessageText:
//
// Unable to open serial port %1
//
#define MSG_SERIAL_PORT_OPEN_FAILED    ((DWORD)0x0000009a)

//
// MessageId: MSG_SERIAL_PORT_SET_FAILED
//
// MessageText:
//
// Unable to configure serial port %1
//
#define MSG_SERIAL_PORT_SET_FAILED     ((DWORD)0x0000009b)

//
// MessageId: MSG_HDLINK_LOADED
//
// MessageText:
//
// Helpdesk link module %1 (version %2) loaded successfully
//
#define MSG_HDLINK_LOADED              ((DWORD)0x0000009c)

//
// MessageId: MSG_NO_HDLINK_ENTRY_POINT
//
// MessageText:
//
// Unable to find entry point in helpdesk link module "%1"
//
#define MSG_NO_HDLINK_ENTRY_POINT      ((DWORD)0x0000009d)

//
// MessageId: MSG_HDLINK_INIT_FAILED
//
// MessageText:
//
// Initialization of helpdesk link module "%1" failed
//
#define MSG_HDLINK_INIT_FAILED         ((DWORD)0x0000009e)

//
// MessageId: MSG_HDLINK_API_VERSION_MISMATCH
//
// MessageText:
//
// Helpdesk link module "%1" cannot be loaded because of API version mismatch (module: %3; server: %2)
//
#define MSG_HDLINK_API_VERSION_MISMATCH ((DWORD)0x0000009f)

//
// MessageId: MSG_PDSDRV_LOADED
//
// MessageText:
//
// Performance data storage driver "%1" loaded successfully
//
#define MSG_PDSDRV_LOADED              ((DWORD)0x000000a0)

//
// MessageId: MSG_NO_PDSDRV_ENTRY_POINT
//
// MessageText:
//
// Unable to find entry point in performance data storage driver "%1"
//
#define MSG_NO_PDSDRV_ENTRY_POINT      ((DWORD)0x000000a1)

//
// MessageId: MSG_PDSDRV_INIT_FAILED
//
// MessageText:
//
// Initialization of performance data storage driver "%1" failed
//
#define MSG_PDSDRV_INIT_FAILED         ((DWORD)0x000000a2)

//
// MessageId: MSG_PDSDRV_API_VERSION_MISMATCH
//
// MessageText:
//
// Performance data storage driver "%1" cannot be loaded because of API version mismatch (driver: %3; server: %2)
//
#define MSG_PDSDRV_API_VERSION_MISMATCH ((DWORD)0x000000a3)

//
// MessageId: MSG_DBCONNPOOL_INIT_FAILED
//
// MessageText:
//
// Failed to initialize database connection pool
//
#define MSG_DBCONNPOOL_INIT_FAILED     ((DWORD)0x000000a4)

//
// MessageId: MSG_INSTANCE_FILTER_SCRIPT_COMPILATION_ERROR
//
// MessageText:
//
// Failed to compile instance filter script for DCI %3 "%4" on %1 "%2": %5
//
#define MSG_INSTANCE_FILTER_SCRIPT_COMPILATION_ERROR ((DWORD)0x000000a5)

//
// MessageId: MSG_TRANSFORMATION_SCRIPT_COMPILATION_ERROR
//
// MessageText:
//
// Failed to compile transformation script for object %1 "%2" DCI %3 "%4": %5
//
#define MSG_TRANSFORMATION_SCRIPT_COMPILATION_ERROR ((DWORD)0x000000a6)

//
// MessageId: MSG_TRANSFORMATION_SCRIPT_EXECUTION_ERROR
//
// MessageText:
//
// Failed to execute transformation script for object %1 "%2" DCI %3 "%4": %5
//
#define MSG_TRANSFORMATION_SCRIPT_EXECUTION_ERROR ((DWORD)0x000000a7)

//
// MessageId: MSG_THRESHOLD_SCRIPT_COMPILATION_ERROR
//
// MessageText:
//
// Failed to compile threshold script for node "%1" DCI %2 threshold %3: %4
//
#define MSG_THRESHOLD_SCRIPT_COMPILATION_ERROR ((DWORD)0x000000a8)

//
// MessageId: MSG_THRESHOLD_SCRIPT_EXECUTION_ERROR
//
// MessageText:
//
// Failed to execute threshold script for node "%1" DCI %2 threshold %3: %4
//
#define MSG_THRESHOLD_SCRIPT_EXECUTION_ERROR ((DWORD)0x000000a9)

//
// MessageId: MSG_CHASSIS_LOAD_FAILED
//
// MessageText:
//
// Failed to load chassis object with id %1 from database
//
#define MSG_CHASSIS_LOAD_FAILED        ((DWORD)0x000000aa)

//
// MessageId: MSG_NPE_REGISTERED
//
// MessageText:
//
// Prediction engine %1 version %2 registered
//
#define MSG_NPE_REGISTERED             ((DWORD)0x000000ab)

//
// MessageId: MSG_NPE_INIT_FAILED
//
// MessageText:
//
// Initialization of prediction engine %1 version %2 failed
//
#define MSG_NPE_INIT_FAILED            ((DWORD)0x000000ac)

//
// MessageId: MSG_THREAD_RUNNING
//
// MessageText:
//
// Thread "%1" returned to running state
//
#define MSG_THREAD_RUNNING             ((DWORD)0x000000ad)

//
// MessageId: MSG_LISTENING_FOR_AGENTS
//
// MessageText:
//
// Listening for agent connections on TCP socket %1:%2
//
#define MSG_LISTENING_FOR_AGENTS       ((DWORD)0x000000ae)

//
// MessageId: MSG_SERVER_CERT_NOT_SET
//
// MessageText:
//
// Server certificate not set
//
#define MSG_SERVER_CERT_NOT_SET        ((DWORD)0x000000af)

//
// MessageId: MSG_CANNOT_LOAD_SERVER_CERT
//
// MessageText:
//
// Cannot load server certificate from %1 (%2)
//
#define MSG_CANNOT_LOAD_SERVER_CERT    ((DWORD)0x000000b0)

//
// MessageId: MSG_WAITING_FOR_DB_PASSWORD
//
// MessageText:
//
// Server is waiting for database password to be supplied
//
#define MSG_WAITING_FOR_DB_PASSWORD    ((DWORD)0x000000b1)

//
// MessageId: MSG_SENSOR_LOAD_FAILED
//
// MessageText:
//
// Failed to load sensor object with id %1 from database
//
#define MSG_SENSOR_LOAD_FAILED         ((DWORD)0x000000b2)

//
// MessageId: MSG_DEBUG_TAG
//
// MessageText:
//
// [%1] %2
//
#define MSG_DEBUG_TAG                  ((DWORD)0x000000b3)

//
// MessageId: MSG_UNABLE_TO_GET_DB_SCHEMA_VERSION
//
// MessageText:
//
// Unable to get database schema version
//
#define MSG_UNABLE_TO_GET_DB_SCHEMA_VERSION ((DWORD)0x000000b4)

//
// MessageId: MSG_WRONG_DB_SCHEMA_VERSION
//
// MessageText:
//
// Your database has format version %1.%2, but server is compiled for version %3.%4
//
#define MSG_WRONG_DB_SCHEMA_VERSION    ((DWORD)0x000000b5)

#endif
