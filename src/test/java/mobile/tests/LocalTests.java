package mobile.tests;

import com.codeborne.selenide.CollectionCondition;
import io.qameta.allure.Description;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.*;
import static io.appium.java_client.AppiumBy.accessibilityId;
import static io.appium.java_client.AppiumBy.id;
import static io.qameta.allure.Allure.step;
@Tag("local")
public class LocalTests  extends TestBase {
    @Test
    @Description("Проверка непустой поисковой выдачи")
    void successfulSearchTest() {
        back();
        step("Поиск через строку поиска", () -> {
            $(accessibilityId("Search Wikipedia")).click();
            $(id("org.wikipedia.alpha:id/search_src_text")).sendKeys("Appium");
        });
        step("Проверка непустой выдачи", () ->
                $$(id("org.wikipedia.alpha:id/page_list_item_title")).shouldHave(CollectionCondition.sizeGreaterThan(0)));
    }
    @Test
    @Description("Проверка открытия статьи")
    void openArticleTest() {
        back();
        step("Поиск через строку поиска", () -> {
            $(accessibilityId("Search Wikipedia")).click();
            $(id("org.wikipedia.alpha:id/search_src_text")).sendKeys("salsa");
        });
        step("Проверка непустой выдачи", () ->
                $$(id("org.wikipedia.alpha:id/page_list_item_title")).shouldHave(CollectionCondition.sizeGreaterThan(0)));
        step("Открытие первого элемента из списка", () ->
                $$(id("org.wikipedia.alpha:id/page_list_item_title")).first().click());
        step("Проверка заголовка открытой страницы", () -> {
             $(byXpath("//android.webkit.WebView[@text=\"Salsa\"]")).shouldBe(visible);
             $(byXpath("//android.webkit.WebView[@text=\"Salsa\"]")).shouldBe(text("Salsa"));
        });
    }



    @Test
    @Description("Проверка onboarding screen")
    void  onboardingScteenTest() {
        step("Проверка первой страницы", () ->
                $(id("org.wikipedia.alpha:id/secondaryTextView"))
                        .shouldHave(text("We’ve found the following on your device:")));

        step("Переход на вторую страницу", () ->
                $(id("org.wikipedia.alpha:id/fragment_onboarding_forward_button")).click());

        step("Проверка текста", () ->
                $(id("org.wikipedia.alpha:id/primaryTextView"))
                        .shouldHave(text("New ways to explore")));

        step("Переход на третью страницу", () ->
                $(id("org.wikipedia.alpha:id/fragment_onboarding_forward_button")).click());

        step("Проверка текста", () ->
                $(id("org.wikipedia.alpha:id/primaryTextView"))
                        .shouldHave(text("Reading lists with sync")));

        step("Переход на четвертую страницу", () ->
                $(id("org.wikipedia.alpha:id/fragment_onboarding_forward_button")).click());

        step("Проверка текста", () ->
                $(id("org.wikipedia.alpha:id/primaryTextView"))
                        .shouldHave(text("Send anonymous data")));

    }
    }
