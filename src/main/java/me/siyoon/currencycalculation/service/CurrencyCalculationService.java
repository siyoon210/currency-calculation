package me.siyoon.currencycalculation.service;

public interface CurrencyCalculationService {
    Double getExchangeRate(String sendingCountry, String receivingCountry);
}
