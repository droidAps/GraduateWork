package ru.netology.page;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import ru.netology.data.CardInfo;

import java.time.Duration;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class FormPaymentOnCredit {
    private ElementsCollection heading = $$("h3");
    private SelenideElement numberField = $("[placeholder='0000 0000 0000 0000']");
    private SelenideElement monthField = $("[placeholder='08']");
    private SelenideElement yearField = $("[placeholder='22']");
    private SelenideElement codeField = $("[placeholder='999']");
    private ElementsCollection fields = $$(".input__control");
    private ElementsCollection buttons = $$("button");
    private ElementsCollection notifications = $$(".notification__content");

    public FormPaymentOnCredit() {
        heading.last().shouldBe(visible).shouldHave(exactText("Кредит по данным карты"));
    }

    public void fillNumberField(String number) {
        numberField.setValue(number);
    }

    public void fillMonthField(String month) {
        monthField.setValue(month);
    }

    public void fillYearField(String year) {
        yearField.setValue(year);
    }

    public void fillCodeField(String code) {
        codeField.setValue(code);
    }

    public void fillHolderField(String holder) {
        fields.get(3).setValue(holder);
    }

    public void sendFormPaymentOnCredit(CardInfo card) {
        fillNumberField(card.getNumber());
        fillMonthField(card.getMonth());
        fillYearField(card.getYear());
        fillHolderField(card.getHolder());
        fillCodeField(card.getCode());
        buttons.find(exactText("Продолжить")).click();
    }

    public void successMessageCheck() {
        notifications.first().shouldBe(visible, Duration.ofSeconds(20)).shouldHave(exactText("Операция одобрена Банком."));
    }

    public void failureMessageCheck() {
        notifications.last().shouldBe(visible, Duration.ofSeconds(20)).shouldHave(exactText("Ошибка! Банк отказал в проведении операции."));
    }

    public void notificationCheck() {
        $(".notification__content").shouldBe(visible, Duration.ofSeconds(40));
    }
}
