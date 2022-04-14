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

public class InputValueInFormTest {

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
    @Story("Тесты полей заполнения веб-формы покупки")
    @DisplayName("BT-13. Проверка на ввод букв на русском языке в поле 'Номер карты'")
    void shouldInputRussianSymbolsInCardNumberField() {
        var testWord = "номер";
        var mainPage = new MainPage();
        var formPaymentByCard = mainPage.openFormPaymentByCard();
        formPaymentByCard.fillNumberField(testWord);

        var expected = "";
        var actual = formPaymentByCard.getValueFromNumberField();
        assertEquals(expected, actual);
    }

    @Test
    @Feature("Покупка тура")
    @Story("Тесты полей заполнения веб-формы покупки")
    @DisplayName("BT-14. Проверка на ввод букв на английском языке в поле 'Номер карты'")
    void shouldInputEnglishSymbolsInCardNumberField() {
        var testWord = "number";
        var mainPage = new MainPage();
        var formPaymentByCard = mainPage.openFormPaymentByCard();
        formPaymentByCard.fillNumberField(testWord);

        var expected = "";
        var actual = formPaymentByCard.getValueFromNumberField();
        assertEquals(expected, actual);
    }

    @Test
    @Feature("Покупка тура")
    @Story("Тесты полей заполнения веб-формы покупки")
    @DisplayName("BT-15. Проверка на ввод спецсимволов в поле 'Номер карты'")
    void shouldInputSpecialSymbolsInCardNumberField() {
        var testWord = "/.,";
        var mainPage = new MainPage();
        var formPaymentByCard = mainPage.openFormPaymentByCard();
        formPaymentByCard.fillNumberField(testWord);

        var expected = "";
        var actual = formPaymentByCard.getValueFromNumberField();
        assertEquals(expected, actual);
    }

    @Test
    @Feature("Покупка тура")
    @Story("Тесты полей заполнения веб-формы покупки")
    @DisplayName("BT-16. Проверка на ввод букв на русском языке в поле 'Месяц'")
    void shouldInputRussianSymbolsInMonthField() {
        var testWord = "мм";
        var mainPage = new MainPage();
        var formPaymentByCard = mainPage.openFormPaymentByCard();
        formPaymentByCard.fillMonthField(testWord);

        var expected = "";
        var actual = formPaymentByCard.getValueFromMonthField();
        assertEquals(expected, actual);
    }

    @Test
    @Feature("Покупка тура")
    @Story("Тесты полей заполнения веб-формы покупки")
    @DisplayName("BT-17. Проверка на ввод букв на английском языке в поле 'Месяц'")
    void shouldInputEnglishSymbolsInMonthField() {
        var testWord = "ff";
        var mainPage = new MainPage();
        var formPaymentByCard = mainPage.openFormPaymentByCard();
        formPaymentByCard.fillMonthField(testWord);

        var expected = "";
        var actual = formPaymentByCard.getValueFromMonthField();
        assertEquals(expected, actual);
    }

    @Test
    @Feature("Покупка тура")
    @Story("Тесты полей заполнения веб-формы покупки")
    @DisplayName("BT-18. Проверка на ввод спецсимволов в поле 'Месяц'")
    void shouldInputSpecialSymbolsInMonthField() {
        var testWord = "/.";
        var mainPage = new MainPage();
        var formPaymentByCard = mainPage.openFormPaymentByCard();
        formPaymentByCard.fillMonthField(testWord);

        var expected = "";
        var actual = formPaymentByCard.getValueFromMonthField();
        assertEquals(expected, actual);
    }

    @Test
    @Feature("Покупка тура")
    @Story("Тесты полей заполнения веб-формы покупки")
    @DisplayName("BT-19. Проверка на ввод букв на русском языке в поле 'Год'")
    void shouldInputRussianSymbolsInYearField() {
        var testWord = "гг";
        var mainPage = new MainPage();
        var formPaymentByCard = mainPage.openFormPaymentByCard();
        formPaymentByCard.fillYearField(testWord);

        var expected = "";
        var actual = formPaymentByCard.getValueFromYearField();
        assertEquals(expected, actual);
    }

    @Test
    @Feature("Покупка тура")
    @Story("Тесты полей заполнения веб-формы покупки")
    @DisplayName("BT-20. Проверка на ввод букв на английском языке в поле 'Год'")
    void shouldInputEnglishSymbolsInYearField() {
        var testWord = "yy";
        var mainPage = new MainPage();
        var formPaymentByCard = mainPage.openFormPaymentByCard();
        formPaymentByCard.fillYearField(testWord);

        var expected = "";
        var actual = formPaymentByCard.getValueFromYearField();
        assertEquals(expected, actual);
    }

    @Test
    @Feature("Покупка тура")
    @Story("Тесты полей заполнения веб-формы покупки")
    @DisplayName("BT-21. Проверка на ввод спецсимволов в поле 'Год'")
    void shouldInputSpecialSymbolsInYearField() {
        var testWord = "/.";
        var mainPage = new MainPage();
        var formPaymentByCard = mainPage.openFormPaymentByCard();
        formPaymentByCard.fillYearField(testWord);

        var expected = "";
        var actual = formPaymentByCard.getValueFromYearField();
        assertEquals(expected, actual);
    }

    @Test
    @Feature("Покупка тура")
    @Story("Тесты полей заполнения веб-формы покупки")
    @DisplayName("BT-22. Проверка на ввод букв на русском языке в поле 'CVC/CVV'")
    void shouldInputRussianSymbolsInCodeField() {
        var testWord = "код";
        var mainPage = new MainPage();
        var formPaymentByCard = mainPage.openFormPaymentByCard();
        formPaymentByCard.fillCodeField(testWord);

        var expected = "";
        var actual = formPaymentByCard.getValueFromCodeField();
        assertEquals(expected, actual);
    }

    @Test
    @Feature("Покупка тура")
    @Story("Тесты полей заполнения веб-формы покупки")
    @DisplayName("BT-23. Проверка на ввод букв на английском языке в поле 'CVC/CVV'")
    void shouldInputEnglishSymbolsInCodeField() {
        var testWord = "kod";
        var mainPage = new MainPage();
        var formPaymentByCard = mainPage.openFormPaymentByCard();
        formPaymentByCard.fillCodeField(testWord);

        var expected = "";
        var actual = formPaymentByCard.getValueFromCodeField();
        assertEquals(expected, actual);
    }

    @Test
    @Feature("Покупка тура")
    @Story("Тесты полей заполнения веб-формы покупки")
    @DisplayName("BT-24. Проверка на ввод спецсимволов в поле 'CVC/CVV'")
    void shouldInputSpecialSymbolsInCodeField() {
        var testWord = "/.,";
        var mainPage = new MainPage();
        var formPaymentByCard = mainPage.openFormPaymentByCard();
        formPaymentByCard.fillCodeField(testWord);

        var expected = "";
        var actual = formPaymentByCard.getValueFromCodeField();
        assertEquals(expected, actual);
    }

    @Test
    @Feature("Покупка тура")
    @Story("Тесты полей заполнения веб-формы покупки")
    @DisplayName("BT-25. Проверка на ввод букв на русском языке в поле 'Владелец'")
    void shouldInputRussianSymbolsInHolderField() {
        var testWord = "Иван Иванов";
        var mainPage = new MainPage();
        var formPaymentByCard = mainPage.openFormPaymentByCard();
        formPaymentByCard.fillHolderField(testWord);

        var expected = "";
        var actual = formPaymentByCard.getValueFromHolderField();
        assertEquals(expected, actual);
    }

    @Test
    @Feature("Покупка тура")
    @Story("Тесты полей заполнения веб-формы покупки")
    @DisplayName("BT-26. Проверка на ввод цифр в поле 'Владелец'")
    void shouldInputDigitsInHolderField() {
        var testWord = "12345";
        var mainPage = new MainPage();
        var formPaymentByCard = mainPage.openFormPaymentByCard();
        formPaymentByCard.fillHolderField(testWord);

        var expected = "";
        var actual = formPaymentByCard.getValueFromHolderField();
        assertEquals(expected, actual);
    }

    @Test
    @Feature("Покупка тура")
    @Story("Тесты полей заполнения веб-формы покупки")
    @DisplayName("BT-27. Проверка на ввод спецсимволов в поле 'Владелец'")
    void shouldInputSpecialSymbolsInHolderField() {
        var testWord = "/.,";
        var mainPage = new MainPage();
        var formPaymentByCard = mainPage.openFormPaymentByCard();
        formPaymentByCard.fillHolderField(testWord);

        var expected = "";
        var actual = formPaymentByCard.getValueFromHolderField();
        assertEquals(expected, actual);
    }

    @Test
    @Feature("Покупка тура")
    @Story("Тесты полей заполнения веб-формы покупки")
    @DisplayName("BT-28. Отправка пустой формы")
    void shouldSendEmptyForm() {
        var mainPage = new MainPage();
        var formPaymentByCard = mainPage.openFormPaymentByCard();
        formPaymentByCard.clickButtonContinue();

        formPaymentByCard.notificationUnderNumberFieldCheck();
    }

    @Test
    @Feature("Покупка тура")
    @Story("Тесты полей заполнения веб-формы покупки")
    @DisplayName("BT-29. Отправка формы с истекшим сроком действия карты")
    void shouldSendFormWithExpiredCard() {
        var testDate = DataHelper.getDateEarlierThanCurrent(1);
        var cardInfo = new CardInfo(DataHelper.getFirstCard().getNumber(),
                DataHelper.getMonth(testDate),
                DataHelper.getYear(testDate),
                DataHelper.getValidHolder(),
                DataHelper.getValidCode());

        var mainPage = new MainPage();
        var formPaymentByCard = mainPage.openFormPaymentByCard();
        formPaymentByCard.sendFormPaymentByCard(cardInfo);

        formPaymentByCard.notificationUnderMonthFieldCheck();
    }

    @Test
    @Feature("Покупка тура")
    @Story("Тесты полей заполнения веб-формы покупки")
    @DisplayName("BT-30. Отправка формы со сроком действия карты позже текущей даты на 5 лет")
    void shouldSendFormWithDateCardFiveYearsLaterThanCurrent() {
        var testDate = DataHelper.getDateLaterThanCurrent(61);
        var cardInfo = new CardInfo(DataHelper.getFirstCard().getNumber(),
                DataHelper.getMonth(testDate),
                DataHelper.getYear(testDate),
                DataHelper.getValidHolder(),
                DataHelper.getValidCode());

        var mainPage = new MainPage();
        var formPaymentByCard = mainPage.openFormPaymentByCard();
        formPaymentByCard.sendFormPaymentByCard(cardInfo);

        formPaymentByCard.notificationUnderMonthFieldCheck();
    }
}
