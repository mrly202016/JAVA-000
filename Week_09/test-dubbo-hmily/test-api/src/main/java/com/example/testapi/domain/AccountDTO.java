package com.example.testapi.domain;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class AccountDTO implements Serializable {

    private static final long serialVersionUID = 7223470850578998427L;
    
    private String userId;
    private BigDecimal cnyAmount;
    private BigDecimal usdAmount;

}
