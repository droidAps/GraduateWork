package ru.netology.database;

import lombok.SneakyThrows;
import org.apache.commons.dbutils.QueryRunner;

import java.sql.DriverManager;

public class DBHelper {
    private static String url = System.getProperty("databaseURL");
    private static String user = System.getProperty("databaseUser");
    private static String password = System.getProperty("databasePassword");

    private DBHelper() {
    }

    @SneakyThrows
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
    public static OrderTable getFirstLineInOrderTable() {
        String query = "SELECT * FROM order_entity";
        try (
                var conn = DriverManager.getConnection(url, user, password)
        ) {
            var stmt = conn.createStatement();
            var result = stmt.executeQuery(query);

            while (result.next()) {
                var id = result.getString("id");
                var created = result.getString("created");
                var creditId = result.getString("credit_id");
                var paymentId = result.getString("payment_id");
                return new OrderTable(id, created, creditId, paymentId);
            }
        }
        return null;
    }

    @SneakyThrows
    public static PaymentTable getFirstLineInPaymentTable() {
        String query = "SELECT * FROM payment_entity";
        try (
                var conn = DriverManager.getConnection(url, user, password)

        ) {
            var stmt = conn.createStatement();
            var result = stmt.executeQuery(query);

            while (result.next()) {
                var id = result.getString("id");
                var amount = result.getInt("amount");
                var created = result.getDate("created");
                var status = result.getString("status");
                var transactionId = result.getString("transaction_id");
                return new PaymentTable(id, amount, created, status, transactionId);
            }
        }
        return null;
    }

    @SneakyThrows
    public static CreditRequestTable getFirstLineInCreditRequestTable() {
        String query = "SELECT * FROM credit_request_entity";
        try (
                var conn = DriverManager.getConnection(url, user, password)

        ) { var stmt = conn.createStatement();
            var result = stmt.executeQuery(query);

            while (result.next()) {
                var id = result.getString("id");
                var bankId = result.getString("bank_id");
                var created = result.getDate("created");
                var status = result.getString("status");
                return new CreditRequestTable(id, bankId, created, status);
            }
        }
        return null;
    }

    public static String getLastIdOrderTable() {
        var orderInfo = getFirstLineInOrderTable();
        if(orderInfo != null) {
            return orderInfo.getId();
        }
        return null;
    }

    public static String getLastPaymentId() {
        var orderInfo = getFirstLineInOrderTable();
        if(orderInfo != null) {
            return orderInfo.getPaymentId();
        }
        return null;
    }

    public static String getLastCreditId() {
        var orderInfo = getFirstLineInOrderTable();
        if(orderInfo != null) {
            return orderInfo.getCreditId();
        }
        return null;
    }

    public static String getLastTransactionIdWithApprovedStatus() {
        var paymentInfo = getFirstLineInPaymentTable();
        if(paymentInfo == null) {
            return null;
        }
        var actual = paymentInfo.getStatus();
        if(actual.equals("APPROVED")) {
            return paymentInfo.getTransactionId();
        }
       return null;
    }

    public static String getLastTransactionIdWithDeclinedStatus() {
        var paymentInfo = getFirstLineInPaymentTable();
        if(paymentInfo == null) {
            return null;
        }
        var actual = paymentInfo.getStatus();
        if(actual.equals("DECLINED")) {
            return paymentInfo.getTransactionId();
        }
        return null;
    }

    public static String getLastTransactionId() {
        var paymentInfo = getFirstLineInPaymentTable();
        if(paymentInfo != null) {
            return paymentInfo.getTransactionId();
        }
        return null;
    }

    public static String getLastBankIdWithApprovedStatus() {
        var creditRequestInfo = getFirstLineInCreditRequestTable();
        if(creditRequestInfo == null) {
            return null;
        }
        var actual = creditRequestInfo.getStatus();
        if(actual.equals("APPROVED")) {
            return creditRequestInfo.getBankId();
        }
        return null;
    }

    public static String getLastBankIdWithDeclinedStatus() {
        var creditRequestInfo = getFirstLineInCreditRequestTable();
        if(creditRequestInfo == null) {
            return null;
        }
        var actual = creditRequestInfo.getStatus();
        if(actual.equals("DECLINED")) {
            return creditRequestInfo.getBankId();
        }
        return null;
    }

    public static String getLastBankId() {
        var creditRequestInfo = getFirstLineInCreditRequestTable();
        if(creditRequestInfo != null) {
            return creditRequestInfo.getBankId();
        }
        return null;
    }

}