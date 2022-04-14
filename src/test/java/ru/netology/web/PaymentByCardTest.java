package ru.netology.web;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.Feature;
import io.qameta.allure.Step;
import io.qameta.allure.Story;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import ru.netology.data.CardInfo;
import ru.netology.data.DataHelper;
import ru.netology.database.DBHelper;
import ru.netology.page.MainPage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.*;


public class PaymentByCardTest {

    @BeforeAll
    static void setupAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @BeforeEach
    @Step("Очистка таблиц в БД")
    void setUp() { DBHelper.cleanAllTables(); }

    @BeforeEach
    @Step("Открытие порта")
    void openURL() {
        open("http://localhost:8080");
    }

    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
    }

    @Test
    @Feature("Покупка тура")
    @Story("Функциональные тесты веб-формы покупки")
    @DisplayName("BT-1. Успешная оплата по карте")
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
    @Feature("Покупка тура")
    @Story("Тесты базы данных")
    @DisplayName("BT-2. Проверка данных в БД при совершении успешного платежа по карте")
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
    @Feature("Покупка тура")
    @Story("Функциональные тесты веб-формы покупки")
    @DisplayName("BT-5. Отказ в проведении операции по карте")
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
    @Feature("Покупка тура")
    @Story("Тесты базы данных")
    @DisplayName("BT-6. Проверка данных в БД при отказе в проведении операции по карте")
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
    @Feature("Покупка тура")
    @Story("Функциональные тесты веб-формы покупки")
    @DisplayName("BT-9. Отказ в проведении операции по незарегистрированной карте")
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
    @Feature("Покупка тура")
    @Story("Тесты базы данных")
    @DisplayName("BT-10. Проверка данных в БД при отказе в проведении операции по незарегистрированной карте")
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
        assertEquals("no data in order table", id);
    }
}
