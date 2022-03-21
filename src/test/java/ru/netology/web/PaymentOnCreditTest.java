package ru.netology.web;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.data.CardInfo;
import ru.netology.data.DataHelper;
import ru.netology.database.DBHelper;
import ru.netology.page.MainPage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class PaymentOnCreditTest {

    @BeforeEach
    void setUp() { DBHelper.cleanAllTables(); }

    @BeforeEach
    void openURL() {
        open("http://localhost:8080");
    }

    @Test
    void successfulPaymentOnCreditTest() {
        var validDate = DataHelper.validDateOfManufacture();
        var cardInfo = new CardInfo(DataHelper.getFirstCard().getNumber(),
                DataHelper.getMonth(validDate),
                DataHelper.getYear(validDate),
                DataHelper.getValidHolder(),
                DataHelper.getValidCode());

        var mainPage = new MainPage();
        var formPaymentOnCredit = mainPage.openFormPaymentOnCredit();
        formPaymentOnCredit.sendFormPaymentOnCredit(cardInfo);

        formPaymentOnCredit.successMessageCheck();
    }

    @Test
    void shouldCheckDataOfSuccessfulPaymentOnCredit() {
        var validDate = DataHelper.validDateOfManufacture();
        var cardInfo = new CardInfo(DataHelper.getFirstCard().getNumber(),
                DataHelper.getMonth(validDate),
                DataHelper.getYear(validDate),
                DataHelper.getValidHolder(),
                DataHelper.getValidCode());

        var mainPage = new MainPage();
        var formPaymentOnCredit = mainPage.openFormPaymentOnCredit();
        formPaymentOnCredit.sendFormPaymentOnCredit(cardInfo);
        formPaymentOnCredit.notificationCheck();

        var creditId = DBHelper.getLastCreditId();
        var bankId = DBHelper.getLastBankIdWithApprovedStatus();
        assertEquals(bankId, creditId);
    }

    @Test
    void unsuccessfulPaymentOnCreditTest() {
        var validDate = DataHelper.validDateOfManufacture();
        var cardInfo = new CardInfo(DataHelper.getSecondCard().getNumber(),
                DataHelper.getMonth(validDate),
                DataHelper.getYear(validDate),
                DataHelper.getValidHolder(),
                DataHelper.getValidCode());

        var mainPage = new MainPage();
        var formPaymentOnCredit = mainPage.openFormPaymentOnCredit();
        formPaymentOnCredit.sendFormPaymentOnCredit(cardInfo);

        formPaymentOnCredit.failureMessageCheck();
    }

    @Test
    void shouldCheckDataOfUnsuccessfulPaymentOnCredit() {
        var validDate = DataHelper.validDateOfManufacture();
        var cardInfo = new CardInfo(DataHelper.getSecondCard().getNumber(),
                DataHelper.getMonth(validDate),
                DataHelper.getYear(validDate),
                DataHelper.getValidHolder(),
                DataHelper.getValidCode());

        var mainPage = new MainPage();
        var formPaymentOnCredit = mainPage.openFormPaymentOnCredit();
        formPaymentOnCredit.sendFormPaymentOnCredit(cardInfo);
        formPaymentOnCredit.notificationCheck();

        var creditId = DBHelper.getLastCreditId();
        var bankId = DBHelper.getLastBankIdWithDeclinedStatus();
        assertEquals(bankId, creditId);
    }

    @Test
    void unsuccessfulPaymentOnCreditByUnregisteredCardTest() {
        var validDate = DataHelper.validDateOfManufacture();
        var cardInfo = new CardInfo(DataHelper.getRandomNumberCard(),
                DataHelper.getMonth(validDate),
                DataHelper.getYear(validDate),
                DataHelper.getValidHolder(),
                DataHelper.getValidCode());

        var mainPage = new MainPage();
        var formPaymentOnCredit = mainPage.openFormPaymentOnCredit();
        formPaymentOnCredit.sendFormPaymentOnCredit(cardInfo);

        formPaymentOnCredit.failureMessageCheck();
    }

    @Test
    void shouldCheckDataOfUnsuccessfulPaymentOnCreditByUnregisteredCard() {
        var validDate = DataHelper.validDateOfManufacture();
        var cardInfo = new CardInfo(DataHelper.getRandomNumberCard(),
                DataHelper.getMonth(validDate),
                DataHelper.getYear(validDate),
                DataHelper.getValidHolder(),
                DataHelper.getValidCode());

        var mainPage = new MainPage();
        var formPaymentOnCredit = mainPage.openFormPaymentOnCredit();
        formPaymentOnCredit.sendFormPaymentOnCredit(cardInfo);
        formPaymentOnCredit.notificationCheck();

        var id = DBHelper.getLastIdOrderTable();
        assertNull(id);
    }
}