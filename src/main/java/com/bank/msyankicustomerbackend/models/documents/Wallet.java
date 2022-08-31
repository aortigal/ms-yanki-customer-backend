package com.bank.msyankicustomerbackend.models.documents;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Wallet{

    private String id;

    private String phoneNumber;

    private BigDecimal amount;

}
