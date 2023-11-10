package mobile.config;

import org.aeonbits.owner.Config;

import java.net.URL;

@Config.Sources({
        "classpath:${env}.properties",
        "classpath:local.properties"
})

public interface AuthConfig extends Config {

    @Key("user")
    String getUser();

    @Key("key")
    String getKey();

    @Key("remoteUrl")
    String getRemoteUrl();
}
