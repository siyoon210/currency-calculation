package me.siyoon.currencycalculation.service;

import lombok.RequiredArgsConstructor;
import me.siyoon.currencycalculation.dto.CurrencyInfo;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CurrencyCalculationServiceImpl implements CurrencyCalculationService {
    private final CurrencyInfoAPIService currencyInfoAPIService;

    // TODO : 일단 USD 만 고려한다.
    @Override
    public Double getExchangeRate(String sendingCountry, String receivingCountry) {
        CurrencyInfo currencyInfo = currencyInfoAPIService.getCurrencyInfo();
        return currencyInfo.getQuotes().get(sendingCountry + receivingCountry);
    }

    @Override
    public Double getReceivingAmount(String sendingCountry, String receivingCountry, double amount) {
        Double exchangeRate = this.getExchangeRate(sendingCountry, receivingCountry);
        return exchangeRate * amount;
    }
}
