package com.yp.challenge.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.yp.challenge.enums.Currency;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class TransferRequest {
    private BigDecimal amount;
    private Currency currency;
    @JsonProperty("origin_account")
    private Integer originAccount;
    @JsonProperty("destination_account")
    private Integer destinationAccount;
    private String description;
}
