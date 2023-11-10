package mobile.config;

import com.codeborne.selenide.Configuration;
import mobile.drivers.BrowserstackDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.Map;

public class MobileConfigForProject {
    private final MobileConfig mobileConfig;
    private final AuthConfig authConfig;

    public MobileConfigForProject(MobileConfig webConfig, AuthConfig authConfig) {
        this.mobileConfig = webConfig;
        this.authConfig = authConfig;
    }

    public void webConfig() {

        Configuration.browser = mobileConfig.getBrowser();
        Configuration.browserSize =  mobileConfig.getBrowserSize();
        Configuration.timeout = mobileConfig.getTimeOut();

    }





}


