package ru.netology.base;

import lombok.SneakyThrows;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import ru.netology.data.DataHelper;

import java.sql.DriverManager;

public class DBHelper {
    private static String url = "jdbc:mysql://localhost:3306/";
    private static String nameDB = "mydb";
    private static String user = "app";
    private static String password = "pass";

    private DBHelper() {
    }

    @SneakyThrows
    public static void cleanAllTables() {
        var runner = new QueryRunner();
        try (
                var conn = DriverManager.getConnection(url + nameDB, user, password);
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
    public static String getLastIdOrderTable() {
        var runner = new QueryRunner();
        try (
                var conn = DriverManager.getConnection(url + nameDB, user, password);
        ) {
            var idSQL = "SELECT id FROM order_entity WHERE id LIKE '%' ORDER BY created DESC LIMIT 1;";
            String id = runner.query(conn, idSQL, new ScalarHandler<>());
            return id;
        }
    }

    @SneakyThrows
    public static String getLastPaymentId() {
        var runner = new QueryRunner();
        try (
                var conn = DriverManager.getConnection(url + nameDB, user, password);
        ) {
            var paymentIdSQL = "SELECT payment_id FROM order_entity WHERE id LIKE '%' ORDER BY created DESC LIMIT 1;";
            String paymentId = runner.query(conn, paymentIdSQL, new ScalarHandler<>());
            return paymentId;
        }
    }

    @SneakyThrows
    public static String getLastCreditId() {
        var runner = new QueryRunner();
        try (
                var conn = DriverManager.getConnection(url + nameDB, user, password);
        ) {
            var creditIdSQL = "SELECT credit_id FROM order_entity WHERE id LIKE '%' ORDER BY created DESC LIMIT 1;";
            String creditId = runner.query(conn, creditIdSQL, new ScalarHandler<>());
            return creditId;
        }
    }

    @SneakyThrows
    public static String getLastTransactionIdWithApprovedStatus() {
        var runner = new QueryRunner();
        try (
                var conn = DriverManager.getConnection(url + nameDB, user, password);
        ) {
            var transactionIdSQL = "SELECT transaction_id FROM payment_entity WHERE status LIKE 'APPROVED' ORDER BY created DESC LIMIT 1;";
            String transactionId = runner.query(conn, transactionIdSQL, new ScalarHandler<>());
            return transactionId;
        }
    }

    @SneakyThrows
    public static String getLastTransactionIdWithDeclinedStatus() {
        var runner = new QueryRunner();
        try (
                var conn = DriverManager.getConnection(url + nameDB, user, password);
        ) {
            var transactionIdSQL = "SELECT transaction_id FROM payment_entity WHERE status LIKE 'DECLINED' ORDER BY created DESC LIMIT 1;";
            String transactionId = runner.query(conn, transactionIdSQL, new ScalarHandler<>());
            return transactionId;
        }
    }

    @SneakyThrows
    public static String getLastTransactionId() {
        var runner = new QueryRunner();
        try (
                var conn = DriverManager.getConnection(url + nameDB, user, password);
        ) {
            var transactionIdSQL = "SELECT transaction_id FROM payment_entity WHERE status LIKE '%' ORDER BY created DESC LIMIT 1;";
            String transactionId = runner.query(conn, transactionIdSQL, new ScalarHandler<>());
            return transactionId;
        }
    }

    @SneakyThrows
    public static String getLastBankIdWithApprovedStatus() {
        var runner = new QueryRunner();
        try (
                var conn = DriverManager.getConnection(url + nameDB, user, password);
        ) {
            var bankIdSQL = "SELECT bank_id FROM credit_request_entity WHERE status LIKE 'APPROVED' ORDER BY created DESC LIMIT 1;";
            String bankId = runner.query(conn, bankIdSQL, new ScalarHandler<>());
            return bankId;
        }
    }

    @SneakyThrows
    public static String getLastBankIdWithDeclinedStatus() {
        var runner = new QueryRunner();
        try (
                var conn = DriverManager.getConnection(url + nameDB, user, password);
        ) {
            var bankIdSQL = "SELECT bank_id FROM credit_request_entity WHERE status LIKE 'DECLINED' ORDER BY created DESC LIMIT 1;";
            String bankId = runner.query(conn, bankIdSQL, new ScalarHandler<>());
            return bankId;
        }
    }

    @SneakyThrows
    public static String getLastBankId() {
        var runner = new QueryRunner();
        try (
                var conn = DriverManager.getConnection(url + nameDB, user, password);
        ) {
            var bankIdSQL = "SELECT bank_id FROM credit_request_entity WHERE status LIKE '%' ORDER BY created DESC LIMIT 1;";
            String bankId = runner.query(conn, bankIdSQL, new ScalarHandler<>());
            return bankId;
        }
    }
}