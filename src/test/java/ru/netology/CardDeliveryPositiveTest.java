package ru.netology;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class CardDeliveryPositiveTest {

    public String generateDate(int addDays, String pattern) {
        return LocalDate.now().plusDays(addDays).format(DateTimeFormatter.ofPattern(pattern));
    }
    @BeforeEach
    void setUp() {
        open("http://localhost:9999");
    }

    // 1. Отправка заявки с верно заполненными полями;
    @Test
    public void shouldReturnSuccessIfFieldsAreFilledInCorrectly() {
        $("[data-test-id=city] [placeholder='Город']").setValue("Москва");
        String planningDate = generateDate(4, "dd.MM.yyyy");
        $("[data-test-id=date] [class='input__box'] [placeholder='Дата встречи']").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id=date] [class='input__box'] [placeholder='Дата встречи']").setValue(planningDate);
        $("[data-test-id=name] [type=text]").setValue("Наталья Козина");
        $("[data-test-id=phone] [type=tel]").setValue("+79883339918");
        $("[data-test-id=agreement]").click();
        $("[role=button] .button__content").click();
        $(withText("Успешно!")).shouldBe(Condition.visible, Duration.ofSeconds(15));
        $("[data-test-id='notification'] .notification__content")
                .shouldHave(Condition.exactText("Встреча успешно забронирована на " + planningDate));
    }

    // 2. Отправка заявки с указанием фамилии через дефис;
    @Test
    public void shouldReturnSuccessfullyIfSurnameWithHyphen() {
        $("[data-test-id=city] [placeholder='Город']").setValue("Москва");
        String planningDate = generateDate(4, "dd.MM.yyyy");
        $("[data-test-id=date] [class='input__box'] [placeholder='Дата встречи']").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id=date] [class='input__box'] [placeholder='Дата встречи']").setValue(planningDate);
        $("[data-test-id=name] [type=text]").setValue("Наталья Козина-Быкова");
        $("[data-test-id=phone] [type=tel]").setValue("+79883339918");
        $("[data-test-id=agreement]").click();
        $("[role=button] .button__content").click();
        $(withText("Успешно!")).shouldBe(Condition.visible, Duration.ofSeconds(15));
        $("[data-test-id='notification'] .notification__content")
                .shouldHave(Condition.exactText("Встреча успешно забронирована на " + planningDate));
    }

    // 3. Отправка заявки с указанием города через дефис;
    @Test
    public void shouldReturnSuccessfullyIfCityWithHyphen() {
        $("[data-test-id=city] [placeholder='Город']").setValue("Ростов-на-Дону");
        String planningDate = generateDate(4, "dd.MM.yyyy");
        $("[data-test-id=date] [class='input__box'] [placeholder='Дата встречи']").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id=date] [class='input__box'] [placeholder='Дата встречи']").setValue(planningDate);
        $("[data-test-id=name] [type=text]").setValue("Наталья Козина");
        $("[data-test-id=phone] [type=tel]").setValue("+79883339918");
        $("[data-test-id=agreement]").click();
        $("[role=button] .button__content").click();
        $(withText("Успешно!")).shouldBe(Condition.visible, Duration.ofSeconds(15));
        $("[data-test-id='notification'] .notification__content")
                .shouldHave(Condition.exactText("Встреча успешно забронирована на " + planningDate));
    }
}