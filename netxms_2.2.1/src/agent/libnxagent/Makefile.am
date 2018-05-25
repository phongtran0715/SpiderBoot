SOURCES = bridge.cpp cmdexec.cpp dfile_info.cpp main.cpp registry.cpp tools.cpp

lib_LTLIBRARIES = libnxagent.la

libnxagent_la_SOURCES = $(SOURCES)
libnxagent_la_CPPFLAGS=-I@top_srcdir@/include -fexceptions
libnxagent_la_LDFLAGS = -version-info $(NETXMS_LIBRARY_VERSION)
libnxagent_la_LIBADD = ../../db/libnxdb/libnxdb.la ../../libnetxms/libnetxms.la /usr/local/lib/libomniORB4.a /usr/local/lib/libomnithread.a

EXTRA_DIST = Makefile.w32 libnxagent.h libnxagent.vcxproj libnxagent.vcxproj.filters
