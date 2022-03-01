package ru.netology.page;

import com.codeborne.selenide.ElementsCollection;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.$$;

public class MainPage {
    private ElementsCollection buttons = $$("button");

    public FormPaymentByCard openFormPaymentByCard() {
        buttons.find(exactText("Купить")).click();
        return new FormPaymentByCard();
    }

    public FormPaymentOnCredit openFormPaymentOnCredit() {
        buttons.find(exactText("Купить в кредит")).click();
        return new FormPaymentOnCredit();
    }
}
