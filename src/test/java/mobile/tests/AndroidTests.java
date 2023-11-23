package mobile.tests;


import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static io.appium.java_client.AppiumBy.*;
import static io.qameta.allure.Allure.step;
import static org.assertj.core.api.Assertions.assertThat;

@Epic("Тесты приложения Android in BrowserStack")
@Feature("Базовые мобильные тесты")
@Owner("krivorotovnv")
@Link(value = "Testing", url = "https://app-automate.browserstack.com/")
@Severity(SeverityLevel.BLOCKER)
@Tag("browserstack")
public class AndroidTests extends TestBase {

    @Story(value = "Тесты мобильного Android приложения")
    @DisplayName("Тестирование части функционала страницы Settings")
    @Description("Проверяем вход на страницу Settings, предполагаем , что все переключатели в положении ON.")
    @ValueSource(
            ints = {1, 2, 3, 4}
    )
    @ParameterizedTest(name = "Проверка работоспособности переключателя  {0} на странице Settings")
    void checkLoginButtonTest(int element) {

        step("Нажать на кнопку навигации", () -> {
            $(id("org.wikipedia.alpha:id/menu_overflow_button")).click();
        });

       step("Нажать на элемент меню Settings", () -> {
            $(id("org.wikipedia.alpha:id/explore_overflow_settings")).click();
        });

        step("Проверяем что выбираемый элемент переключатель активен.", () ->
                $$(id("org.wikipedia.alpha:id/switchWidget")).get(element - 1).isEnabled()
        );


        step("Проверяем атрибут Text. Он должен быть ON.", () ->
                {
                    assertThat($$(id("org.wikipedia.alpha:id/switchWidget")).get(element - 1).getAttribute("Text")).isEqualTo("ON");
                }
        );

        step("Проверяем, что значение арибута Checked он должен быть true ", () -> {
                    assertThat($$(id("org.wikipedia.alpha:id/switchWidget")).get(element - 1).getAttribute("Checked")).isEqualTo("true");
                }
        );
        step("Нажимакм на переключатель, тем самым меняем состояние на выключено.", () -> {
                    $$(id("org.wikipedia.alpha:id/switchWidget")).get(element - 1).click();
                    assertThat($$(id("org.wikipedia.alpha:id/switchWidget")).get(element - 1).getAttribute("Text")).isEqualTo("OFF");
                }
        );
        step("Проверяем изменение значения атрибута Text. Он должен быть ЩАА.", () ->
                {
                    assertThat($$(id("org.wikipedia.alpha:id/switchWidget")).get(element - 1).getAttribute("Text")).isEqualTo("OFF");
                }
        );

        step("Проверяем, что значение арибута Checked изменолось на false ", () -> {
                    assertThat($$(id("org.wikipedia.alpha:id/switchWidget")).get(element - 1).getAttribute("Checked")).isEqualTo("false");
                }
        );


    }


    @Story(value = "Тесты мобильного Android приложения")
    @DisplayName("Элементы меню для перехода на страницу Settings")
    @Description("Проверяем наличие и доступность элементов меню для перехода на страницу Settings.")
    @Test
    void checkLoginButtonTest() {

        step("Нажать на кнопку навигации", () -> {
            $(id("org.wikipedia.alpha:id/menu_overflow_button")).click();
        });

        step("Проверить наличие элемента Settings", () -> {
            $(id("org.wikipedia.alpha:id/explore_overflow_settings")).shouldHave(text("Settings"));
        });
        step("Проверить доступность элемента Settings", () -> {
            $(id("org.wikipedia.alpha:id/explore_overflow_settings")).shouldHave(text("Settings")).isEnabled();
        });
    }

    @Test
    void successfulSearchTest() {
        step("Нажимаем на меню  поиска", () -> {
            $(accessibilityId("Search Wikipedia")).click();
        });
        step("Вводим в строке посикаAppium", () -> {
            $(id("org.wikipedia.alpha:id/search_src_text")).sendKeys("Appium");
        });
        step("Проверяем что в результат поиска присутствует и на пуcтой.", () ->
                $$(id("org.wikipedia.alpha:id/page_list_item_title"))
                        .shouldHave(sizeGreaterThan(0)));
    }


    @Test
    void openFirstSearchLinkTest() {
        step("Нажимаем на меню  поиска", () -> {
            $(accessibilityId("Search Wikipedia")).click();
        });
        step("Вводим в строке посика Pojo", () -> {
            $(id("org.wikipedia.alpha:id/search_src_text")).sendKeys("Pojo");
        });
        step("Проверяем что в результат поиска присутствует и на путой.", () ->
                $$(id("org.wikipedia.alpha:id/page_list_item_title"))
                        .shouldHave(sizeGreaterThan(0)));
        step("Открыть первый объект из результата поиска", () ->
                $$(id("org.wikipedia.alpha:id/page_list_item_container")).first().click());
        step("Проверка наличия текста с ошибкой", () -> {
            $(id("org.wikipedia.alpha:id/view_wiki_error_text")).shouldBe(visible);
        });
    }


}