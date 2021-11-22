package com.yp.challenge.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class TransferResponse {
    private String status;
    private List<String> errors;
    @JsonProperty("tax_collected")
    private BigDecimal taxCollected;
    private BigDecimal CAD;
}
