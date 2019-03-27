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
        } else { //송금국가가 source 에 명시된 국가가 아닐 경우, 변환 연산하여 반환
            return currencyInfo.getQuotes().get(sourceCountry + receivingCountry)
                    / currencyInfo.getQuotes().get(sourceCountry + sendingCountry);
        }
    }
}
