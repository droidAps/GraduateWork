package ru.netology.data;

import com.github.javafaker.Faker;
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

    public static Card getFirstCard() {
        return new Card("4444 4444 4444 4441", "APPROVED");
    }

    public static Card getSecondCard() {
        return new Card("4444 4444 4444 4442", "DECLINED");
    }

    public static String getValidHolder() {
        var faker = new Faker();
        String holder = faker.name().firstName() + " " + faker.name().lastName();
        return holder;
    }

    public static String getValidMonth(String validDate) {
        String[] words = validDate.split("\\.");
        String month = words[1];
        return month;
    }

    public static String getValidYear(String validDate) {
        String[] words = validDate.split("\\.");
        String year = words[2];
        return year;
    }

    public static String getValidCode() {
        var faker = new Faker();
        return faker.number().digits(3);
    }

    public static String validDateOfManufacture() {
        var faker = new Faker();
        int monthsAfter = faker.number().numberBetween(0, 12);
        int yearsAfter = faker.number().numberBetween(0, 2);
        return LocalDate.now().plusMonths(monthsAfter).plusYears(yearsAfter).format(DateTimeFormatter.ofPattern("dd.MM.yy"));
    }

    public static String getRandomNumberCard() {
        var faker = new Faker();
        var numberCard = "5" + faker.number().digits(15);
        return numberCard;
    }

}

