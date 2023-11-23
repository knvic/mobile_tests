package mobile.tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;

import io.qameta.allure.selenide.AllureSelenide;
import mobile.drivers.BrowserstackDriver;
import mobile.drivers.LocalDriver;
import mobile.helpers.Attach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.sessionId;
import static com.codeborne.selenide.WebDriverRunner.closeWebDriver;


public class TestBase {

   //public static final String deviceHost =  System.getProperty("deviceHost");
    public static final String deviceHost =  "browserstack";

    @BeforeAll
    static void beforeAll() {

        if (deviceHost.equals("browserstack")) {
            Configuration.browser = BrowserstackDriver.class.getName();
        } else if (deviceHost.equals("emulator")) {
            Configuration.browser = LocalDriver.class.getName();
        }

        Configuration.browserSize = null;
        Configuration.timeout = 30000;
    }

    @BeforeEach
    void beforeEach() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
        open();
    }

    @AfterEach
    void afterEach() {
//        Attach.screenshotAs("Last screenshot");
        Attach.pageSource();


        if (deviceHost.equals("browserstack")) {
            String sessionId = sessionId().toString();
            closeWebDriver();
            Attach.addVideo(sessionId);
        } else if (deviceHost.equals("emulator")) {
            Attach.screenshotAs("Last screenshot");
            closeWebDriver();
        }


    }
}
