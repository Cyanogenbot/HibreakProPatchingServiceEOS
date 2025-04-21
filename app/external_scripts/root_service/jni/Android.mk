LOCAL_PATH := $(call my-dir)


include $(CLEAR_VARS)
LOCAL_MODULE := a9_eink_server
LOCAL_SRC_FILES := eink_daemon.c
LOCAL_CFLAGS := -Wall -Wextra -Werror -fPIE -D_FORTIFY_SOURCE=2
LOCAL_LDLIBS := -llog
include $(BUILD_EXECUTABLE)
