package ru.netology.database;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderTable {
    private String id;
    private String created;
    private String credit_id;
    private String payment_id;
}
