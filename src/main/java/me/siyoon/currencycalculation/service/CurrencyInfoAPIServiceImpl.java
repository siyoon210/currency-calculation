package me.siyoon.currencycalculation.service;

import me.siyoon.currencycalculation.dto.CurrencyInfo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.TimeUnit;

@Service
public class CurrencyInfoAPIServiceImpl implements CurrencyInfoAPIService {
    @Value("${currencyLayer.accessKey}")
    private String accessKey;
    @Value("${currencyLayer.endPointUrl}")
    private String endPointUrl;
    @Value("${currencyLayer.source}")
    private String source;
    @Value("${currencyLayer.currencies}")
    private String currencies;
    @Value("${currencyLayer.updateFrequencySec}")
    private int updateFrequencySec;

    private RestTemplate restTemplate;
    private CurrencyInfo currencyInfo;

    public CurrencyInfoAPIServiceImpl(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public CurrencyInfo getCurrencyInfo() {
        if (isOutdatedCurrencyInfo()) {
            currencyInfo = restTemplate.getForObject(
                    endPointUrl + "/live?access_key=" + accessKey
                            + "&source=" + source
                            + "&currencies=" + currencies,
                    CurrencyInfo.class);
        }

        checkCurrencyInfoValidation();

        return currencyInfo;
    }

    private boolean isOutdatedCurrencyInfo() {
        if (currencyInfo == null) {
            return true;
        }

        long currentTimeSec = TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis());
        return currentTimeSec - currencyInfo.getTimestamp() > updateFrequencySec;
    }

    private void checkCurrencyInfoValidation() {
        if (currencyInfo == null || !currencyInfo.isSuccess()) {
            if (currencyInfo == null) {
                throw new RestClientException("currencyInfo null");
            }
            throw new RestClientException("api 호출 실패 - "
                    + currencyInfo.getError().get("code") + " : "
                    + currencyInfo.getError().get("type"));
        }
    }
}
