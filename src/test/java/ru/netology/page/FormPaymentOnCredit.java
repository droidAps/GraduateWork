package ru.netology.page;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
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
    public void fillCodeField(String code) {
        codeField.setValue(code);
    }

    @Step("Заполнение поля 'Владелец'")
    public void fillHolderField(String holder) {
        fields.get(3).setValue(holder);
    }

    @Step("Отправка заполненной формы покупки в кредит")
    public void sendFormPaymentOnCredit(CardInfo card) {
        fillNumberField(card.getNumber());
        fillMonthField(card.getMonth());
        fillYearField(card.getYear());
        fillHolderField(card.getHolder());
        fillCodeField(card.getCode());
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
}
