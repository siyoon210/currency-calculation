package me.siyoon.currencycalculation.service;

public interface CurrencyCalculationService {
    Double getExchangeRate(String sendingCountry, String receivingCountry);

    Double getReceivingAmoint(String sendingCountry, String receivingCountry, double amount);
}
