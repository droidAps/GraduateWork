package ru.netology.page;

import com.codeborne.selenide.ElementsCollection;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.$$;

public class MainPage {
    private ElementsCollection buttons = $$("button");

    @Step("Нажатие кнопки 'Купить'")
    public FormPaymentByCard openFormPaymentByCard() {
        buttons.find(exactText("Купить")).click();
        return new FormPaymentByCard();
    }

    @Step("Нажатие кнопки 'Купить в кредит'")
    public FormPaymentOnCredit openFormPaymentOnCredit() {
        buttons.find(exactText("Купить в кредит")).click();
        return new FormPaymentOnCredit();
    }
}
