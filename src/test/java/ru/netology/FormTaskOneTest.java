package ru.netology;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Selenide.*;
import static java.time.LocalDateTime.now;

public class FormTaskOneTest {

    public String date() {
        return now().plusDays(4).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }

    @Test
    public void shouldSendFormWithValidField() {
        open("http://localhost:9999");
        $("[data-test-id='city'] input").setValue("Абакан");
        $("[data-test-id='date'] input").doubleClick().sendKeys("DELETE");
        $("[data-test-id='date'] input").setValue(date());
        $("[data-test-id='name'] input").setValue("Алексей Петров");
        $("[data-test-id='phone'] input").setValue("+79113334455");
        $("[data-test-id='agreement'] .checkbox__text").click();
        $$("button").find(Condition.exactText("Забронировать")).click();
        $("[data-test-id='notification']").shouldBe(Condition.visible, Duration.ofSeconds(15));
        $("[data-test-id='notification'] .notification__title").shouldBe(Condition.exactText("Успешно!"), Duration.ofSeconds(15));
        $("[data-test-id='notification'] .notification__content").shouldBe(Condition.exactText("Встреча успешно забронирована на " + date()), Duration.ofSeconds(15));
    }
}
