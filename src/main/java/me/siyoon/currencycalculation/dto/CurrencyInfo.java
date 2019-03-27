package me.siyoon.currencycalculation.dto;

import lombok.*;

import java.util.Map;

@Getter @Setter
public class CurrencyInfo {
    private boolean success;
    private int timestamp;
    private String source;
    private Map<String, Double> quotes;
    private Map<String, String> error;
}
