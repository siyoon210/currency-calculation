package me.siyoon.currencycalculation.service;

import lombok.RequiredArgsConstructor;
import me.siyoon.currencycalculation.dto.CurrencyInfo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CurrencyCalculationServiceImpl implements CurrencyCalculationService {
    @Value("${currencyLayer.source}")
    private String sourceCountry;

    private final CurrencyInfoAPIService currencyInfoAPIService;

    @Override
    public Double getExchangeRate(String sendingCountry, String receivingCountry) {
        CurrencyInfo currencyInfo = currencyInfoAPIService.getCurrencyInfo();

        if (sendingCountry.equals(sourceCountry)) {
            return currencyInfo.getQuotes().get(sendingCountry + receivingCountry);
        } else {
            return currencyInfo.getQuotes().get(sourceCountry + receivingCountry)
                    / currencyInfo.getQuotes().get(sourceCountry + sendingCountry);
        }
    }
}
