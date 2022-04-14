package ru.netology.database;

import io.qameta.allure.Step;
import lombok.SneakyThrows;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import java.sql.DriverManager;

public class DBHelper {
    private static String url = System.getProperty("databaseURL");
    private static String user = System.getProperty("databaseUser");
    private static String password = System.getProperty("databasePassword");

    private DBHelper() {
    }


    @SneakyThrows
    @Step("Очистка всех таблиц БД")
    public static void cleanAllTables() {
        var runner = new QueryRunner();
        try (
                var conn = DriverManager.getConnection(url, user, password)
        ) {
            var cleanCreditRequest = "DELETE FROM credit_request_entity;";
            var cleanOrder = "DELETE FROM order_entity;";
            var cleanPayment = "DELETE FROM payment_entity;";

            runner.update(conn, cleanCreditRequest);
            runner.update(conn, cleanOrder);
            runner.update(conn, cleanPayment);
        }
    }

    @SneakyThrows
    @Step("Получение последней по времени создания строки из таблицы 'order_entity'")
    public static OrderTable getFirstLineInOrderTable() {
        var runner = new QueryRunner();
        var querySQL = "SELECT * FROM order_entity ORDER BY created DESC LIMIT 1;";
        try (
                var conn = DriverManager.getConnection(url, user, password)
        ) {
            var firstLine = runner.query(conn, querySQL, new BeanHandler<>(OrderTable.class));
            return firstLine;
        }
    }

    @SneakyThrows
    @Step("Получение последней по времени создания строки из таблицы 'payment_entity'")
    public static PaymentTable getFirstLineInPaymentTable() {
        var runner = new QueryRunner();
        var querySQL = "SELECT * FROM payment_entity ORDER BY created DESC LIMIT 1;";
        try (
                var conn = DriverManager.getConnection(url, user, password)
        ) {
            var firstLine = runner.query(conn, querySQL, new BeanHandler<>(PaymentTable.class));
            return firstLine;
        }
    }

    @SneakyThrows
    @Step("Получение последней по времени создания строки из таблицы 'credit_request_entity'")
    public static CreditRequestTable getFirstLineInCreditRequestTable() {
        var runner = new QueryRunner();
        var querySQL = "SELECT * FROM credit_request_entity ORDER BY created DESC LIMIT 1;";
        try (
                var conn = DriverManager.getConnection(url, user, password)
        ) {
            var firstLine = runner.query(conn, querySQL, new BeanHandler<>(CreditRequestTable.class));
            return firstLine;
        }
    }

    @Step("Получение значения id операции из таблицы 'order_entity'")
    public static String getLastIdOrderTable() {
        var orderInfo = getFirstLineInOrderTable();
        if(orderInfo != null) {
            return orderInfo.getId();
        }
        return "no data in order table";
    }

    @Step("Получение значения id платежа по карте из таблицы 'order_entity'")
    public static String getLastPaymentId() {
        var orderInfo = getFirstLineInOrderTable();
        if(orderInfo != null) {
            return orderInfo.getPayment_id();
        }
        return "no data in order table";
    }

    @Step("Получение значения id запроса на кредит из таблицы 'order_entity'")
    public static String getLastCreditId() {
        var orderInfo = getFirstLineInOrderTable();
        if(orderInfo != null) {
            return orderInfo.getCredit_id();
        }
        return "no data in order table";
    }

    @Step("Получение значения id успешной транзакции из таблицы 'payment_entity'")
    public static String getLastTransactionIdWithApprovedStatus() {
        var paymentInfo = getFirstLineInPaymentTable();
        if(paymentInfo == null) {
            return "no data in payment table";
        }
        var actual = paymentInfo.getStatus();
        if(actual.equals("APPROVED")) {
            return paymentInfo.getTransaction_id();
        }
       return "no suitable data in payment table";
    }

    @Step("Получение значения id неуспешной транзакции из таблицы 'payment_entity'")
    public static String getLastTransactionIdWithDeclinedStatus() {
        var paymentInfo = getFirstLineInPaymentTable();
        if(paymentInfo == null) {
            return "no data in payment table";
        }
        var actual = paymentInfo.getStatus();
        if(actual.equals("DECLINED")) {
            return paymentInfo.getTransaction_id();
        }
        return "no suitable data in payment table";
    }

    @Step("Получение значения id одобренного запроса на кредит из таблицы 'credit_request_entity'")
    public static String getLastBankIdWithApprovedStatus() {
        var creditRequestInfo = getFirstLineInCreditRequestTable();
        if(creditRequestInfo == null) {
            return "no data in credit request table";
        }
        var actual = creditRequestInfo.getStatus();
        if(actual.equals("APPROVED")) {
            return creditRequestInfo.getBank_id();
        }
        return "no suitable data in credit request table";
    }

    @Step("Получение значения id неодобренного запроса на кредит из таблицы 'credit_request_entity'")
    public static String getLastBankIdWithDeclinedStatus() {
        var creditRequestInfo = getFirstLineInCreditRequestTable();
        if(creditRequestInfo == null) {
            return "no data in credit request table";
        }
        var actual = creditRequestInfo.getStatus();
        if(actual.equals("DECLINED")) {
            return creditRequestInfo.getBank_id();
        }
        return "no suitable data in credit request table";
    }

}