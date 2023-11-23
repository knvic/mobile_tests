package mobile.config;

import org.aeonbits.owner.Config;

@Config.Sources({
        "classpath:${deviceHost}.properties",
        "classpath:emulator.properties"

})

public interface MobileConfig extends Config {

    @Key("appUrl")
    String getApp();

    @Key("device")
    @DefaultValue("Samsung Galaxy S22 Ultra")
    String getDevice();

    @Key("os_version")
    @DefaultValue("12.0")
    String getVersion();


}
