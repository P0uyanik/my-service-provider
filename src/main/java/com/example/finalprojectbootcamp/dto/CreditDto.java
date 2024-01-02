package com.example.finalprojectbootcamp.dto;

import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CreditDto implements Serializable {
    private  String str ;
    private BigDecimal creditAmount ;
}
