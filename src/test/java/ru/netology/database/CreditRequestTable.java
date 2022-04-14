package ru.netology.database;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreditRequestTable {
    private String id;
    private String bank_id;
    private String created;
    private String status;
}
