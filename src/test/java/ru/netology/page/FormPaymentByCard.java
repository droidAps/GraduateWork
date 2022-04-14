package ru.netology.page;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
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

    @Step("Заполнение поля 'Номер карты'")
    public void fillNumberField(String number) {
        numberField.setValue(number);
    }

    @Step("Заполнение поля 'Месяц'")
    public void fillMonthField(String month) {
        monthField.setValue(month);
    }

    @Step("Заполнение поля 'Год'")
    public void fillYearField(String year) {
        yearField.setValue(year);
    }

    @Step("Заполнение поля 'CVC/CVV'")
    public void fillCodeField(String code) { codeField.setValue(code); }

    @Step("Заполнение поля 'Владелец'")
    public void fillHolderField(String holder) {
        fields.get(3).setValue(holder);
    }

    @Step("Получение значения из поля 'Номер карты'")
    public String getValueFromNumberField() {
        return numberField.getValue();
    }

    @Step("Получение значения из поля 'Месяц'")
    public String getValueFromMonthField() {
        return monthField.getValue();
    }

    @Step("Получение значения из поля 'Год'")
    public String getValueFromYearField() {
        return yearField.getValue();
    }

    @Step("Получение значения из поля 'CVC/CVV'")
    public String getValueFromCodeField() {
        return numberField.getValue();
    }

    @Step("Получение значения из поля 'Владелец'")
    public String getValueFromHolderField() {
        return fields.get(3).getValue();
    }

    @Step("Отправка заполненной формы покупки по карте")
    public void sendFormPaymentByCard(CardInfo card) {
        fillNumberField(card.getNumber());
        fillMonthField(card.getMonth());
        fillYearField(card.getYear());
        fillHolderField(card.getHolder());
        fillCodeField(card.getCode());
        buttons.find(exactText("Продолжить")).click();
    }

    @Step("Нажатие кнопки 'Продолжить'")
    public void clickButtonContinue() {
        buttons.find(exactText("Продолжить")).click();
    }

    @Step("Проверка появления сообщения об успехе операции")
    public void successMessageCheck() {
        notifications.first().shouldBe(visible, Duration.ofSeconds(20)).shouldHave(exactText("Операция одобрена Банком."));
    }

    @Step("Проверка появления сообщения об отказе в операции")
    public void failureMessageCheck() {
        notifications.last().shouldBe(visible, Duration.ofSeconds(20)).shouldHave(exactText("Ошибка! Банк отказал в проведении операции."));
    }

    @Step("Проверка появления сообщения о статусе операции")
    public void notificationCheck() {
        $(".notification__content").shouldBe(visible, Duration.ofSeconds(40));
    }

    @Step("Проверка появления уведомления о неверном формате данных под полем 'Номер карты'")
    public void notificationUnderNumberFieldCheck() {
        underFields.first().shouldBe(visible, Duration.ofSeconds(10)).shouldHave(exactText("Неверный формат"));
    }

    @Step("Проверка появления уведомления о неверном сроке действия карты под полем 'Месяц'")
    public void notificationUnderMonthFieldCheck() {
        $(".input__sub").shouldBe(visible, Duration.ofSeconds(10)).shouldHave(exactText("Неверно указан срок действия карты"));
    }
}