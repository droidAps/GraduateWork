package ru.netology.data;

import com.github.javafaker.Faker;
import io.qameta.allure.Step;
import lombok.Value;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DataHelper {
    private DataHelper() {
    }

    @Value
    public static class Card {
        private String number;
        private String status;
    }

    @Step("Получение номера карты, операции по которой проходят со статусом 'APPROVED'")
    public static Card getFirstCard() {
        return new Card("4444 4444 4444 4441", "APPROVED");
    }

    @Step("Получение номера карты, операции по которой проходят со статусом 'DECLINED'")
    public static Card getSecondCard() {
        return new Card("4444 4444 4444 4442", "DECLINED");
    }

    @Step("Генерация владельца карты (English)")
    public static String getValidHolder() {
        var faker = new Faker();
        String holder = faker.name().firstName() + " " + faker.name().lastName();
        return holder;
    }

    @Step("Получение номера месяца из даты")
    public static String getMonth(String Date) {
        String[] words = Date.split("\\.");
        String month = words[1];
        return month;
    }

    @Step("Получение года из даты")
    public static String getYear(String Date) {
        String[] words = Date.split("\\.");
        String year = words[2];
        return year;
    }

    @Step("Генерация кода 'CVC/CVV'")
    public static String getValidCode() {
        var faker = new Faker();
        return faker.number().digits(3);
    }

    @Step("Генерация валидного срока действия карты")
    public static String validDateOfManufacture() {
        var faker = new Faker();
        int monthsAfter = faker.number().numberBetween(1, 12);
        int yearsAfter = faker.number().numberBetween(0, 2);
        return LocalDate.now().plusMonths(monthsAfter).plusYears(yearsAfter).format(DateTimeFormatter.ofPattern("dd.MM.yy"));
    }

    @Step("Генерация даты раньше текущей")
    public static String getDateEarlierThanCurrent(int monthsBefore) {
        var faker = new Faker();
        return LocalDate.now().minusMonths(monthsBefore).format(DateTimeFormatter.ofPattern("dd.MM.yy"));
    }

    @Step("Генерация даты позже текущей")
    public static String getDateLaterThanCurrent(int monthsAfter) {
        var faker = new Faker();
        return LocalDate.now().plusMonths(monthsAfter).format(DateTimeFormatter.ofPattern("dd.MM.yy"));
    }

    @Step("Генерация случайного номера карты")
    public static String getRandomNumberCard() {
        var faker = new Faker();
        var numberCard = "5" + faker.number().digits(15);
        return numberCard;
    }

}

