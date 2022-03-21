package ru.netology.database;

import java.sql.Date;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderTable {
    private String id;
    private String created;
    private String creditId;
    private String paymentId;
}
