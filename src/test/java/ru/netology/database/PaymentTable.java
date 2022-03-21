package ru.netology.database;

import java.sql.Date;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentTable {
    private String id;
    private int amount;
    private Date created;
    private String status;
    private String transactionId;
}
