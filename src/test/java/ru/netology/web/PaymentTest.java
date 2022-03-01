package ru.netology.web;

import org.junit.jupiter.api.*;
import ru.netology.base.DBHelper;
import ru.netology.data.CardInfo;
import ru.netology.data.DataHelper;
import ru.netology.page.MainPage;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.sleep;
import static org.junit.jupiter.api.Assertions.*;

public class PaymentTest {

    @BeforeEach
    void setUp() {
        DBHelper.cleanAllTables();
    }

    @BeforeEach
    void openURL() {
        open("http://localhost:8080");
    }

    @Test
    void successfulPaymentByCardTest() {
        var validDate = DataHelper.validDateOfManufacture();
        var cardInfo = new CardInfo(DataHelper.getFirstCard().getNumber(),
                DataHelper.getValidMonth(validDate),
                DataHelper.getValidYear(validDate),
                DataHelper.getValidHolder(),
                DataHelper.getValidCode());

        var mainPage = new MainPage();
        var formPaymentByCard = mainPage.openFormPaymentByCard();
        formPaymentByCard.sendFormPaymentByCard(cardInfo);

        formPaymentByCard.successMessageCheck();
    }

    @Test
    void successfulPaymentOnCreditTest() {
        var validDate = DataHelper.validDateOfManufacture();
        var cardInfo = new CardInfo(DataHelper.getFirstCard().getNumber(),
                DataHelper.getValidMonth(validDate),
                DataHelper.getValidYear(validDate),
                DataHelper.getValidHolder(),
                DataHelper.getValidCode());

        var mainPage = new MainPage();
        var formPaymentOnCredit = mainPage.openFormPaymentOnCredit();
        formPaymentOnCredit.sendFormPaymentOnCredit(cardInfo);

        formPaymentOnCredit.successMessageCheck();
    }

    @Test
    void shouldCheckDataOfSuccessfulCardPayment() {
        var validDate = DataHelper.validDateOfManufacture();
        var cardInfo = new CardInfo(DataHelper.getFirstCard().getNumber(),
                DataHelper.getValidMonth(validDate),
                DataHelper.getValidYear(validDate),
                DataHelper.getValidHolder(),
                DataHelper.getValidCode());

        var mainPage = new MainPage();
        var formPaymentByCard = mainPage.openFormPaymentByCard();
        formPaymentByCard.sendFormPaymentByCard(cardInfo);
        sleep(10000);

        var paymentId = DBHelper.getLastPaymentId();
        var transactionId = DBHelper.getLastTransactionIdWithApprovedStatus();
        assertEquals(transactionId, paymentId);
    }

    @Test
    void shouldCheckDataOfSuccessfulPaymentOnCredit() {
        var validDate = DataHelper.validDateOfManufacture();
        var cardInfo = new CardInfo(DataHelper.getFirstCard().getNumber(),
                DataHelper.getValidMonth(validDate),
                DataHelper.getValidYear(validDate),
                DataHelper.getValidHolder(),
                DataHelper.getValidCode());

        var mainPage = new MainPage();
        var formPaymentOnCredit = mainPage.openFormPaymentOnCredit();
        formPaymentOnCredit.sendFormPaymentOnCredit(cardInfo);
        sleep(10000);

        var creditId = DBHelper.getLastCreditId();
        var bankId = DBHelper.getLastBankIdWithApprovedStatus();
        assertEquals(bankId, creditId);
    }

    @Test
    void unsuccessfulPaymentByCardTest() {
        var validDate = DataHelper.validDateOfManufacture();
        var cardInfo = new CardInfo(DataHelper.getSecondCard().getNumber(),
                DataHelper.getValidMonth(validDate),
                DataHelper.getValidYear(validDate),
                DataHelper.getValidHolder(),
                DataHelper.getValidCode());

        var mainPage = new MainPage();
        var formPaymentByCard = mainPage.openFormPaymentByCard();
        formPaymentByCard.sendFormPaymentByCard(cardInfo);

        formPaymentByCard.failureMessageCheck();
    }

    @Test
    void unsuccessfulPaymentOnCreditTest() {
        var validDate = DataHelper.validDateOfManufacture();
        var cardInfo = new CardInfo(DataHelper.getSecondCard().getNumber(),
                DataHelper.getValidMonth(validDate),
                DataHelper.getValidYear(validDate),
                DataHelper.getValidHolder(),
                DataHelper.getValidCode());

        var mainPage = new MainPage();
        var formPaymentOnCredit = mainPage.openFormPaymentOnCredit();
        formPaymentOnCredit.sendFormPaymentOnCredit(cardInfo);

        formPaymentOnCredit.failureMessageCheck();
    }

    @Test
    void shouldCheckDataOfUnsuccessfulCardPayment() {
        var validDate = DataHelper.validDateOfManufacture();
        var cardInfo = new CardInfo(DataHelper.getSecondCard().getNumber(),
                DataHelper.getValidMonth(validDate),
                DataHelper.getValidYear(validDate),
                DataHelper.getValidHolder(),
                DataHelper.getValidCode());

        var mainPage = new MainPage();
        var formPaymentByCard = mainPage.openFormPaymentByCard();
        formPaymentByCard.sendFormPaymentByCard(cardInfo);
        sleep(10000);

        var paymentId = DBHelper.getLastPaymentId();
        var transactionId = DBHelper.getLastTransactionIdWithDeclinedStatus();
        assertEquals(transactionId, paymentId);
    }

    @Test
    void shouldCheckDataOfUnsuccessfulPaymentOnCredit() {
        var validDate = DataHelper.validDateOfManufacture();
        var cardInfo = new CardInfo(DataHelper.getSecondCard().getNumber(),
                DataHelper.getValidMonth(validDate),
                DataHelper.getValidYear(validDate),
                DataHelper.getValidHolder(),
                DataHelper.getValidCode());

        var mainPage = new MainPage();
        var formPaymentOnCredit = mainPage.openFormPaymentOnCredit();
        formPaymentOnCredit.sendFormPaymentOnCredit(cardInfo);
        sleep(10000);

        var creditId = DBHelper.getLastCreditId();
        var bankId = DBHelper.getLastBankIdWithDeclinedStatus();
        assertEquals(bankId, creditId);
    }

    @Test
    void unsuccessfulPaymentByUnregisteredCardTest() {
        var validDate = DataHelper.validDateOfManufacture();
        var cardInfo = new CardInfo(DataHelper.getRandomNumberCard(),
                DataHelper.getValidMonth(validDate),
                DataHelper.getValidYear(validDate),
                DataHelper.getValidHolder(),
                DataHelper.getValidCode());

        var mainPage = new MainPage();
        var formPaymentByCard = mainPage.openFormPaymentByCard();
        formPaymentByCard.sendFormPaymentByCard(cardInfo);

        formPaymentByCard.failureMessageCheck();
    }

    @Test
    void unsuccessfulPaymentOnCreditByUnregisteredCardTest() {
        var validDate = DataHelper.validDateOfManufacture();
        var cardInfo = new CardInfo(DataHelper.getRandomNumberCard(),
                DataHelper.getValidMonth(validDate),
                DataHelper.getValidYear(validDate),
                DataHelper.getValidHolder(),
                DataHelper.getValidCode());

        var mainPage = new MainPage();
        var formPaymentOnCredit = mainPage.openFormPaymentOnCredit();
        formPaymentOnCredit.sendFormPaymentOnCredit(cardInfo);

        formPaymentOnCredit.failureMessageCheck();
    }

    @Test
    void shouldCheckDataOfUnsuccessfulUnregisteredCardPayment() {
        var validDate = DataHelper.validDateOfManufacture();
        var cardInfo = new CardInfo(DataHelper.getRandomNumberCard(),
                DataHelper.getValidMonth(validDate),
                DataHelper.getValidYear(validDate),
                DataHelper.getValidHolder(),
                DataHelper.getValidCode());

        var mainPage = new MainPage();
        var formPaymentByCard = mainPage.openFormPaymentByCard();
        formPaymentByCard.sendFormPaymentByCard(cardInfo);
        sleep(10000);

        var id = DBHelper.getLastIdOrderTable();
        assertNull(id);
    }

    @Test
    void shouldCheckDataOfUnsuccessfulPaymentOnCreditByUnregisteredCard() {
        var validDate = DataHelper.validDateOfManufacture();
        var cardInfo = new CardInfo(DataHelper.getRandomNumberCard(),
                DataHelper.getValidMonth(validDate),
                DataHelper.getValidYear(validDate),
                DataHelper.getValidHolder(),
                DataHelper.getValidCode());

        var mainPage = new MainPage();
        var formPaymentOnCredit = mainPage.openFormPaymentOnCredit();
        formPaymentOnCredit.sendFormPaymentOnCredit(cardInfo);
        sleep(10000);

        var id = DBHelper.getLastIdOrderTable();
        assertNull(id);
    }
}
