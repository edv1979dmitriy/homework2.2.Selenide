package ru.netology;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.$;
import static java.time.LocalDateTime.now;

public class FormTaskTwoTest {

    @Test
    public void shouldSendFormWithValidField() {
        String choosenDay = now().plusDays(7).format(DateTimeFormatter.ofPattern("d"));
        String carrentyMonth = now().format(DateTimeFormatter.ofPattern("MM"));
        String chosenMonth = now().plusDays(7).format(DateTimeFormatter.ofPattern("MM"));
        open("http://localhost:9999");
        $("[data-test-id='city'] input").setValue("Аб");
        $$(".menu-item__control").find(Condition.exactText("Абакан")).click();
        $("[data-test-id='date'] input").doubleClick().sendKeys("DELETE");
        $("[data-test-id='date'] button").click();
        if (!carrentyMonth.equals(chosenMonth)) {
            $("[data-step='1']").click();
        }
        $$(".calendar__day").findBy(Condition.exactText(choosenDay)).click();
        $("[data-test-id='name'] input").setValue("Алексей Петров");
        $("[data-test-id='phone'] input").setValue("+79113334455");
        $("[data-test-id='agreement'] .checkbox__text").click();
        $$("button").find(Condition.exactText("Забронировать")).click();
        $("[data-test-id='notification']").shouldBe(Condition.visible, Duration.ofSeconds(15));
    }
}
