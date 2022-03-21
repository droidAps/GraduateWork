package ru.netology.web;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.data.CardInfo;
import ru.netology.data.DataHelper;
import ru.netology.database.DBHelper;
import ru.netology.page.MainPage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.*;

public class InputValueInFormTest {

    @BeforeEach
    void setUp() { DBHelper.cleanAllTables(); }

    @BeforeEach
    void openURL() {
        open("http://localhost:8080");
    }

    @Test
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
    void shouldSendEmptyForm() {
        var mainPage = new MainPage();
        var formPaymentByCard = mainPage.openFormPaymentByCard();
        formPaymentByCard.clickButtonContinue();

        formPaymentByCard.notificationUnderNumberFieldCheck();
    }

    @Test
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
