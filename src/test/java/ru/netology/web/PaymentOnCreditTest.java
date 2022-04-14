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
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PaymentOnCreditTest {

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
    @DisplayName("BT-3. Успешная оплата в кредит")
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
    @Feature("Покупка тура")
    @Story("Тесты базы данных")
    @DisplayName("BT-4. Проверка данных в БД при совершении успешного платежа в кредит")
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
    @Feature("Покупка тура")
    @Story("Функциональные тесты веб-формы покупки")
    @DisplayName("BT-7. Отказ в проведении операции в кредит")
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
    @Feature("Покупка тура")
    @Story("Тесты базы данных")
    @DisplayName("BT-8. Проверка данных в БД при отказе в проведении операции в кредит")
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
    @Feature("Покупка тура")
    @Story("Функциональные тесты веб-формы покупки")
    @DisplayName("BT-11. Отказ в проведении операции в кредит по незарегистрированной карте")
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
    @Feature("Покупка тура")
    @Story("Тесты базы данных")
    @DisplayName("BT-12. Проверка данных в БД при отказе в проведении операции в кредит по незарегистрированной карте")
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
        assertEquals("no data in order table", id);
    }
}