package ru.netology.web;

import org.junit.jupiter.api.*;
import ru.netology.data.CardInfo;
import ru.netology.data.DataHelper;
import ru.netology.database.DBHelper;
import ru.netology.page.MainPage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.*;


public class PaymentByCardTest {

    @BeforeEach
    void setUp() { DBHelper.cleanAllTables(); }

    @BeforeEach
    void openURL() {
        open("http://localhost:8080");
    }

    @Test
    void successfulPaymentByCardTest() {
        var validDate = DataHelper.validDateOfManufacture();
        var cardInfo = new CardInfo(DataHelper.getFirstCard().getNumber(),
                DataHelper.getMonth(validDate),
                DataHelper.getYear(validDate),
                DataHelper.getValidHolder(),
                DataHelper.getValidCode());

        var mainPage = new MainPage();
        var formPaymentByCard = mainPage.openFormPaymentByCard();
        formPaymentByCard.sendFormPaymentByCard(cardInfo);

        formPaymentByCard.successMessageCheck();
    }

    @Test
    void shouldCheckDataOfSuccessfulCardPayment() {
        var validDate = DataHelper.validDateOfManufacture();
        var cardInfo = new CardInfo(DataHelper.getFirstCard().getNumber(),
                DataHelper.getMonth(validDate),
                DataHelper.getYear(validDate),
                DataHelper.getValidHolder(),
                DataHelper.getValidCode());

        var mainPage = new MainPage();
        var formPaymentByCard = mainPage.openFormPaymentByCard();
        formPaymentByCard.sendFormPaymentByCard(cardInfo);
        formPaymentByCard.notificationCheck();

        var paymentId = DBHelper.getLastPaymentId();
        var transactionId = DBHelper.getLastTransactionIdWithApprovedStatus();
        assertEquals(transactionId, paymentId);
    }

    @Test
    void unsuccessfulPaymentByCardTest() {
        var validDate = DataHelper.validDateOfManufacture();
        var cardInfo = new CardInfo(DataHelper.getSecondCard().getNumber(),
                DataHelper.getMonth(validDate),
                DataHelper.getYear(validDate),
                DataHelper.getValidHolder(),
                DataHelper.getValidCode());

        var mainPage = new MainPage();
        var formPaymentByCard = mainPage.openFormPaymentByCard();
        formPaymentByCard.sendFormPaymentByCard(cardInfo);

        formPaymentByCard.failureMessageCheck();
    }

    @Test
    void shouldCheckDataOfUnsuccessfulCardPayment() {
        var validDate = DataHelper.validDateOfManufacture();
        var cardInfo = new CardInfo(DataHelper.getSecondCard().getNumber(),
                DataHelper.getMonth(validDate),
                DataHelper.getYear(validDate),
                DataHelper.getValidHolder(),
                DataHelper.getValidCode());

        var mainPage = new MainPage();
        var formPaymentByCard = mainPage.openFormPaymentByCard();
        formPaymentByCard.sendFormPaymentByCard(cardInfo);
        formPaymentByCard.notificationCheck();

        var paymentId = DBHelper.getLastPaymentId();
        var transactionId = DBHelper.getLastTransactionIdWithDeclinedStatus();
        assertEquals(transactionId, paymentId);
    }

    @Test
    void unsuccessfulPaymentByUnregisteredCardTest() {
        var validDate = DataHelper.validDateOfManufacture();
        var cardInfo = new CardInfo(DataHelper.getRandomNumberCard(),
                DataHelper.getMonth(validDate),
                DataHelper.getYear(validDate),
                DataHelper.getValidHolder(),
                DataHelper.getValidCode());

        var mainPage = new MainPage();
        var formPaymentByCard = mainPage.openFormPaymentByCard();
        formPaymentByCard.sendFormPaymentByCard(cardInfo);

        formPaymentByCard.failureMessageCheck();
    }

    @Test
    void shouldCheckDataOfUnsuccessfulUnregisteredCardPayment() {
        var validDate = DataHelper.validDateOfManufacture();
        var cardInfo = new CardInfo(DataHelper.getRandomNumberCard(),
                DataHelper.getMonth(validDate),
                DataHelper.getYear(validDate),
                DataHelper.getValidHolder(),
                DataHelper.getValidCode());

        var mainPage = new MainPage();
        var formPaymentByCard = mainPage.openFormPaymentByCard();
        formPaymentByCard.sendFormPaymentByCard(cardInfo);
        formPaymentByCard.notificationCheck();

        var id = DBHelper.getLastIdOrderTable();
        assertNull(id);
    }
}
