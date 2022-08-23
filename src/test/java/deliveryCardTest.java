import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class deliveryCardTest {
    public String date (int day) {
        return LocalDate.now().plusDays(day).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }

    String plannedDate = date(3);

    @Test
    void cardDeliveryTest() {
        Configuration.holdBrowserOpen = true;


        open("http://localhost:9999");
        $("[data-test-id = city] input").setValue("Казань");
        $("[data-test-id = date] input").doubleClick();
        $("[data-test-id = date] input").sendKeys(Keys.BACK_SPACE);
        $("[data-test-id = date] input").setValue((plannedDate));
        $("[data-test-id = name] input").setValue("Горин Геннадий");
        $("[data-test-id = phone] input").setValue("+79879779555");
        $("[data-test-id = agreement]").click();
        $$(By.className("button__content")).first().click();
        $("[data-test-id = notification]").shouldBe(Condition.visible, Duration.ofSeconds(20));
        $x(".//div[@class='notification__title']"). shouldBe(text("Успешно!"));
        $x(".//div[@class='notification__content']").shouldBe(text("Встреча успешно забронирована на " + plannedDate));










    }
}
