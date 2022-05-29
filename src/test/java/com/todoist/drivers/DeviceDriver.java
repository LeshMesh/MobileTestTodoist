package com.todoist.drivers;

public class DeviceDriver {
    public static String getDeviceDriver(String deviceHost) {

        if ("browserstack".equals(deviceHost)) {
            return BrowserstackMobileDriver.class.getName();
        }
        throw new RuntimeException("Select device: browserstack / emulation / real");
    }
}
