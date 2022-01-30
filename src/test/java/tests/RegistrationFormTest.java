package tests;

import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Condition.*;
import static io.qameta.allure.Allure.step;

public class RegistrationFormTest extends TestBase {

    @Test
    void fillFormTest() {

        step("Открываем страницу", () -> {
            open("/automation-practice-form");
        });

        step("Вводим имя", () -> {
            $(byId("firstName")).setValue("TestFirstName");
        });

        step("Вводим фамилию", () -> {
            $(byId("lastName")).setValue("TestLastName");
        });

        step("Вводим e-mail", () -> {
            $(byId("userEmail")).setValue("test@mail.ru");
        });

        step("Выбираем пол", () -> $("#genterWrapper").$(byText("Male")).click());

        step("Вводим телефон", () -> {
            $("#userNumber").setValue("1234567890");
        });

        step("Вводим дату рождения", () -> {
            $("#dateOfBirthInput").scrollTo().click();
            $(".react-datepicker__month-select").click();
            $(".react-datepicker__month-select").selectOption("November");
            $(".react-datepicker__year-select").click();
            $(".react-datepicker__year-select").selectOption("1998");
            $("[class*='react-datepicker__day--030']").click();
        });

        step("Выбираем предмет", () -> {
            $("#subjectsInput").setValue("Economics").pressEnter();
        });

        step("Выбираем хобби", () -> $("[for=hobbies-checkbox-1]").click());

        step("Выбираем адрес", () -> {
            $("#currentAddress").setValue("TestAddress");
        });

        step("Выбрать область", () -> {
            $("#state").click();
            $("#stateCity-wrapper").$(byText("Haryana")).click();
        });

        step("Выбрать город", () -> {
            $("#react-select-4-input").setValue("Karnal").pressEnter();
        });

        step("Нажать Submit", () -> $("[id=submit]").click());

        step("Проверка корректности заполенения формы", () -> {
            $(".table-responsive").shouldHave(
                    text("TestFirstName"),
                    text("TestLastName"),
                    text("test@mail.ru"),
                    text("Male"),
                    text("1234567890"),
                    text("30 November,1998"),
                    text("Economics"),
                    text("Sports"),
                    text("TestAddress"),
                    text("Haryana"),
                    text("Karnal"));
        });
    }
}