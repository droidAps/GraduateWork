package ru.netology.database;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentTable {
    private String id;
    private String amount;
    private String created;
    private String status;
    private String transaction_id;
}
