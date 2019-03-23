package me.siyoon.currencycalculation.service;

import me.siyoon.currencycalculation.dto.CurrencyInfo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CurrencyLayerAPIService {
    @Value("${currencyLayer.accessKey}")
    private String accessKey;
    @Value("${currencyLayer.endPointUrl}")
    private String endPointUrl;
    @Value("${currencyLayer.source}")
    private String source;
    @Value("${currencyLayer.currencies}")
    private String currencies;
    @Value("${currencyLayer.currencyConversion}")
    private boolean currencyConversion;
    @Value("${currencyLayer.updateFrequencySec}")
    private int updateFrequencySec;

    private RestTemplate restTemplate;
    private CurrencyInfo currencyInfo;

    public CurrencyLayerAPIService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public CurrencyInfo getCurrencyInfo(){
        CurrencyInfo currencyInfo = restTemplate.getForObject(
                endPointUrl + "/live?access_key=" + accessKey + "&source=" + source + "&currencies=" + currencies,
                CurrencyInfo.class);
        return currencyInfo;
    }
}
