package ru.netology.data;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CardInfo {
    private String number;
    private String month;
    private String year;
    private String holder;
    private String code;
}
