package mobile.tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.$;
import static io.appium.java_client.AppiumBy.id;
import static io.qameta.allure.Allure.step;
import static org.assertj.core.api.Assertions.assertThat;

public class IosTests extends TestBase{

    @Test
    @Tag("ios")
    @DisplayName("Проверка ввода и отображения текста в UI Elements")
    void enterTextTest() {

        step("Нажимаем на кнопку Text", () -> {
            $(id("Text Button")).click();
        });

        step("Нажимаем Enter", () -> {
            $(id("Text Input")).click();
        });

        step("Вводим в поле Enter a text текст Apium и нажимаем Enter", () -> {
            $(id("Text Input")).sendKeys("Apium");
            $(id("Text Input")).pressEnter();
        });

        step("Проверяем наличие заданного текста", () -> {
            assertThat($(id("Text Output")).getText())
                    .isEqualTo("Apium");
        });
    }
}
