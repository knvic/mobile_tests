package mobile.tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;

import io.qameta.allure.selenide.AllureSelenide;
import mobile.config.AuthConfig;
import mobile.config.ConfigReader;
import mobile.config.MobileConfig;
import mobile.config.MobileConfigForProject;
import mobile.drivers.BrowserstackDriver;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import static com.codeborne.selenide.Selenide.open;


public class TestBase {
    protected static final MobileConfig mobileConfig = ConfigReader.Instance.readMobileConfig();
    protected static final AuthConfig authConfig = ConfigReader.Instance.readAuthConfig();
    @BeforeAll
    static void beforeAll() {
        MobileConfigForProject mobileConfigForProject=new MobileConfigForProject(mobileConfig,authConfig );
        mobileConfigForProject.webConfig();
    }

    @BeforeEach
    void beforeEach() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
        open();
    }

    @AfterEach
    void afterEach() {
//        Attach.screenshotAs("Last screenshot");
       /* Attach.pageSource();

        String sessionId = Selenide.sessionId().toString();
        closeWebDriver();

        Attach.addVideo(sessionId);*/
    }
}
