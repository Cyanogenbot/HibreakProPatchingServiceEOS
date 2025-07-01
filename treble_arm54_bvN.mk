TARGET_GAPPS_ARCH := arm64
include build/make/target/product/aosp_arm64.mk
$(call inherit-product, device/phh/treble/base.mk)

# Include a9_services
$(call inherit-product, vendor/a9_services/a9_services.mk)

$(call inherit-product, device/phh/treble/lineage.mk)

PRODUCT_NAME := treble_arm64_bvN
PRODUCT_DEVICE := tdgsi_arm64_ab
PRODUCT_BRAND := bigme
PRODUCT_SYSTEM_BRAND := bigme
PRODUCT_MODEL := Hibreak Pro

# Overwrite the inherited "emulator" characteristics
PRODUCT_CHARACTERISTICS := device

PRODUCT_PACKAGES += \
    a9_eink_server \
    A9AccessibilityService \
    phh-su me.phh.superuser su

LINEAGE_BUILDTYPE := VANILLA
LINEAGE_EXTRAVERSION := -EXT4
LINEAGE_BUILD := GSI

# Set vendor properties
PRODUCT_VENDOR_PROPERTIES += \
    ro.vendor.a9_eink_daemon.enabled=true

# Include a9_services modules
PRODUCT_SOONG_NAMESPACES += \
    vendor/a9_services/a9_eink_daemon
