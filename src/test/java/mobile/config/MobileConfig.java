package mobile.config;

import org.aeonbits.owner.Config;

import java.net.URL;

@Config.Sources({
        "classpath:${env}.properties",
        "classpath:android.properties"
})

public interface MobileConfig extends Config {

    @Key("appUrl")
    String getApp();

    @Key("device")
    @DefaultValue("Samsung Galaxy S22 Ultra")
    String getDevice();

    @Key("osversion")
    @DefaultValue("12.0")
    String getVersion();
    @Key("browser")
    String getBrowser();
    @Key("timeout")
    Long getTimeOut();
    @Key(" browserSize")
    @DefaultValue("null")
    String getBrowserSize();



}
