package me.siyoon.currencycalculation.controller;

import lombok.RequiredArgsConstructor;
import me.siyoon.currencycalculation.dto.ExchangeRateInputDto;
import me.siyoon.currencycalculation.service.CurrencyCalculationService;
import me.siyoon.currencycalculation.util.NumberFormatUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class CurrencyCalculationApiController {
    private final CurrencyCalculationService currencyCalculationService;

    @GetMapping("/exchange-rate")
    public ResponseEntity getExchangeRate(@Valid @ModelAttribute ExchangeRateInputDto exchangeRateInputDto) {
        Double exchangeRate = currencyCalculationService
                .getExchangeRate(exchangeRateInputDto.getSendingCountry(), exchangeRateInputDto.getReceivingCountry());
        return ResponseEntity.ok(NumberFormatUtil.convert(exchangeRate));
    }

    @GetMapping("/receiving-amount")
    public ResponseEntity getReceivingAmount(@RequestParam String sendingCountry, @RequestParam String receivingCountry,
    @RequestParam double amount) {
        Map<String, String> responseMap = new HashMap<>();
        Double exchangeRate = currencyCalculationService.getExchangeRate(sendingCountry, receivingCountry);
        Double receivingAmount = exchangeRate * amount;

        responseMap.put("exchangeRate", NumberFormatUtil.convert(exchangeRate));
        responseMap.put("receivingAmount", NumberFormatUtil.convert(receivingAmount));
        return ResponseEntity.ok(responseMap);
    }

}
