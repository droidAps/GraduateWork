package ru.netology.page;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import ru.netology.data.CardInfo;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class FormPaymentByCard {
    private ElementsCollection heading = $$("h3");
    private SelenideElement numberField = $("[placeholder='0000 0000 0000 0000']");
    private SelenideElement monthField = $("[placeholder='08']");
    private SelenideElement yearField = $("[placeholder='22']");
    private SelenideElement codeField = $("[placeholder='999']");
    private ElementsCollection fields = $$(".input__control");
    private ElementsCollection buttons = $$("button");
    private ElementsCollection notifications = $$(".notification__content");
    private ElementsCollection underFields = $$(".input__sub");

    public FormPaymentByCard() {
        heading.last().shouldBe(visible).shouldHave(exactText("Оплата по карте"));
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

    public String getValueFromNumberField() {
        return numberField.getValue();
    }

    public String getValueFromMonthField() {
        return monthField.getValue();
    }

    public String getValueFromYearField() {
        return yearField.getValue();
    }

    public String getValueFromCodeField() {
        return numberField.getValue();
    }

    public String getValueFromHolderField() {
        return fields.get(3).getValue();
    }

    public void sendFormPaymentByCard(CardInfo card) {
        fillNumberField(card.getNumber());
        fillMonthField(card.getMonth());
        fillYearField(card.getYear());
        fillHolderField(card.getHolder());
        fillCodeField(card.getCode());
        buttons.find(exactText("Продолжить")).click();
    }

    public void clickButtonContinue() {
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

    public void notificationUnderNumberFieldCheck() {
        underFields.first().shouldBe(visible, Duration.ofSeconds(10)).shouldHave(exactText("Неверный формат"));
    }

    public void notificationUnderMonthFieldCheck() {
        $(".input__sub").shouldBe(visible, Duration.ofSeconds(10)).shouldHave(exactText("Неверно указан срок действия карты"));
    }
}