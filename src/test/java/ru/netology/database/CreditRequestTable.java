package ru.netology.database;

import java.sql.Date;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreditRequestTable {
    private String id;
    private String bankId;
    private Date created;
    private String status;
}
