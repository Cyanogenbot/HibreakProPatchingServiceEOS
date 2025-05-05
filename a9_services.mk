PRODUCT_PACKAGES += \
    a9_eink_server \
    A9AccessibilityService

PRODUCT_PROPERTY_OVERRIDES += \
    persist.accessibility.enabled_service=com.lmqr.ha9_comp_service/.A9AccessibilityService

PRODUCT_COPY_FILES += \
    $(LOCAL_PATH)/a9_eink_daemon/etc/init/a9_eink_daemon.rc:$(TARGET_COPY_OUT_SYSTEM)/etc/init/a9_eink_daemon.rc