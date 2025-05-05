public class BootReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        // Get the intended service from system property
        String propertyValue = SystemProperties.get(
            "persist.accessibility.enabled_service", "");
            
        // Check if service is already enabled
        String serviceName = context.getPackageName() + "/" + 
            A9AccessibilityService.class.getCanonicalName();
        String enabledServices = Settings.Secure.getString(
            context.getContentResolver(),
            Settings.Secure.ENABLED_ACCESSIBILITY_SERVICES);
            
        if (enabledServices == null) enabledServices = "";
        
        // Enable if needed and matching our property
        if (!enabledServices.contains(serviceName) && 
            (propertyValue.equals(serviceName) || propertyValue.isEmpty())) {
            
            if (!enabledServices.isEmpty()) {
                enabledServices += ":";
            }
            enabledServices += serviceName;
            
            Settings.Secure.putString(
                context.getContentResolver(),
                Settings.Secure.ENABLED_ACCESSIBILITY_SERVICES,
                enabledServices);
                
            Settings.Secure.putInt(
                context.getContentResolver(),
                Settings.Secure.ACCESSIBILITY_ENABLED, 1);
        }
    }
}