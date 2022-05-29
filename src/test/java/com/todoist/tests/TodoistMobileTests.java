package com.todoist.tests;

import com.codeborne.selenide.Condition;
import io.appium.java_client.AppiumBy;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.$;
import static io.qameta.allure.Allure.step;

public class TodoistMobileTests extends TestBase {

    @Test
    @DisplayName("Авторизация зарегистрированного пользователя")
    void loginTest() {
        step("Выбираем войти через email", () ->
            $(AppiumBy.id("com.todoist:id/btn_welcome_email")).click());
        step("Вводим email", () -> {
            $(AppiumBy.id("com.todoist:id/email_exists_input")).setValue("tak25047@yuoia.com");
            $(AppiumBy.id("com.todoist:id/btn_continue_with_email")).click();
        });
        step("Вводим пароль", () -> {
            $(AppiumBy.id("com.todoist:id/log_in_password")).setValue("tak250471");
            $(AppiumBy.id("com.todoist:id/btn_log_in")).click();
        });
        step("Проверяем авторизацию", () ->
            $(AppiumBy.id("com.todoist:id/toolbar")).$(AppiumBy.className("android.widget.TextView"))
                    .shouldHave(Condition.text("Today")));
    }

    @Test
    @DisplayName("Авторизация с невалидным email")
    void loginInvalidEmailTest() {
        step("Выбираем войти через email", () ->
            $(AppiumBy.id("com.todoist:id/btn_welcome_email")).click());
        step("Вводим невалидный email", () -> {
            $(AppiumBy.id("com.todoist:id/email_exists_input")).setValue("tak25047");
            $(AppiumBy.id("com.todoist:id/btn_continue_with_email")).click();
        });
        step("Проверяем наличие ошибки", () ->
            $(AppiumBy.id("com.todoist:id/textinput_error")).shouldHave(Condition.text("Email is invalid")));
    }

    @Test
    @DisplayName("Авторизация с незарегистрированным email")
    void loginUnEmailTest() {
        step("Выбираем войти через email", () ->
            $(AppiumBy.id("com.todoist:id/btn_welcome_email")).click());
        step("Вводим незарегистрированный ранее email", () -> {
            $(AppiumBy.id("com.todoist:id/email_exists_input")).setValue("tak25048@yuoia.com");
            $(AppiumBy.id("com.todoist:id/btn_continue_with_email")).click();
        });
        step("Проверяем открытие страницы регистрации", () ->
            $(AppiumBy.id("com.todoist:id/sign_up_container")).$(AppiumBy.className("android.widget.TextView"))
                    .shouldHave(Condition.text("Sign up")));
    }

    @Test
    @DisplayName("Создание новой задачи")
    void createTaskTest() {
        step("Авторизуемся", () -> {
            $(AppiumBy.id("com.todoist:id/btn_welcome_email")).click();
            $(AppiumBy.id("com.todoist:id/email_exists_input")).setValue("tak25047@yuoia.com");
            $(AppiumBy.id("com.todoist:id/btn_continue_with_email")).click();
            $(AppiumBy.id("com.todoist:id/log_in_password")).setValue("tak250471");
            $(AppiumBy.id("com.todoist:id/btn_log_in")).click();
        });
        step("Нажимаем создать задачу", () ->
            $(AppiumBy.id("com.todoist:id/fab")).click());
        step("Вводим имя и описание задачи", () -> {
            $(AppiumBy.id("android:id/message")).setValue("New Task");
            $(AppiumBy.id("com.todoist:id/description")).setValue("Description for task");
        });
        step("Нажимаем Создать задачу", () ->
            $(AppiumBy.id("android:id/button1")).click());
        step("Проверяем создание задачи", () -> {
            $(AppiumBy.id("com.todoist:id/text")).shouldHave(Condition.text("New Task"));
            $(AppiumBy.id("com.todoist:id/description")).shouldHave(Condition.text("Description for task"));
        });
    }

    @Test
    @DisplayName("Создание нового проекта")
    void createProjectTest() {
        step("Авторизуемся", () -> {
            $(AppiumBy.id("com.todoist:id/btn_welcome_email")).click();
            $(AppiumBy.id("com.todoist:id/email_exists_input")).setValue("tak25047@yuoia.com");
            $(AppiumBy.id("com.todoist:id/btn_continue_with_email")).click();
            $(AppiumBy.id("com.todoist:id/log_in_password")).setValue("tak250471");
            $(AppiumBy.id("com.todoist:id/btn_log_in")).click();
        });
        step("Открываем меню", () ->
            $(AppiumBy.className("android.widget.ImageButton")).click());
        step("Нажимаем Создать проект", () ->
            $(AppiumBy.id("com.todoist:id/add")).click());
        step("Вводим имя проекта", () ->
            $(AppiumBy.id("com.todoist:id/name")).setValue("New Project"));
        step("Нажимаем Добавить в избранное", () ->
            $(AppiumBy.id("com.todoist:id/favorite")).click());
        step("Нажимаем Создать проект", () ->
            $(AppiumBy.id("com.todoist:id/menu_form_submit")).click());
        step("Проверяем создание проекта", () ->
            $(AppiumBy.id("com.todoist:id/toolbar")).$(AppiumBy.className("android.widget.TextView"))
                    .shouldHave(Condition.text("New Project")));
    }

    @Test
    @DisplayName("Поиск проекта")
    void searchProjectTest() {
        step("Авторизуемся", () -> {
            $(AppiumBy.id("com.todoist:id/btn_welcome_email")).click();
            $(AppiumBy.id("com.todoist:id/email_exists_input")).setValue("tak25047@yuoia.com");
            $(AppiumBy.id("com.todoist:id/btn_continue_with_email")).click();
            $(AppiumBy.id("com.todoist:id/log_in_password")).setValue("tak250471");
            $(AppiumBy.id("com.todoist:id/btn_log_in")).click();
        });
        step("Нажимаем кнопку поиска", () ->
            $(AppiumBy.id("com.todoist:id/menu_content_search")).click());
        step("Вводим поисковый запрос", () ->
            $(AppiumBy.id("com.todoist:id/search_edit_text")).setValue("Test Project"));
        step("Проверяем наличие запроса в поисковой выдаче", () -> {
            $(AppiumBy.id("com.todoist:id/search_results")).$(AppiumBy.id("android:id/title"))
                    .shouldHave(Condition.text("Projects"));
            $(AppiumBy.id("com.todoist:id/search_results")).$(AppiumBy.id("com.todoist:id/content"))
                    .shouldHave(Condition.text("Test Project"));
        });
    }
}
