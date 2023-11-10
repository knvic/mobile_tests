package mobile.config;


import org.aeonbits.owner.ConfigFactory;


public enum ConfigReader {
    Instance;
    public static final MobileConfig webConfig =
            ConfigFactory.create(
                    MobileConfig.class,
                    System.getProperties()
            );

    public static final AuthConfig authConfig =
            ConfigFactory.create(
                    AuthConfig.class,
                    System.getProperties()
            );

    public MobileConfig readMobileConfig() {
        return webConfig;
    }
    public AuthConfig readAuthConfig() {
        return  authConfig;
    }

}