package me.siyoon.currencycalculation.controller;

import lombok.RequiredArgsConstructor;
import me.siyoon.currencycalculation.dto.InputDto;
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
    public ResponseEntity getExchangeRate(@Valid @ModelAttribute InputDto inputDto) {
        Double exchangeRate = currencyCalculationService
                .getExchangeRate(inputDto.getSendingCountry(), inputDto.getReceivingCountry());
        return ResponseEntity.ok(NumberFormatUtil.convert(exchangeRate));
    }

    @GetMapping("/receiving-amount")
    public ResponseEntity getReceivingAmount(@Valid @ModelAttribute InputDto inputDto) {
        Map<String, String> responseMap = new HashMap<>();

        Double exchangeRate = currencyCalculationService
                .getExchangeRate(inputDto.getSendingCountry(), inputDto.getReceivingCountry());
        Double receivingAmount = exchangeRate * inputDto.getAmount();

        responseMap.put("exchangeRate", NumberFormatUtil.convert(exchangeRate));
        responseMap.put("receivingAmount", NumberFormatUtil.convert(receivingAmount));
        return ResponseEntity.ok(responseMap);
    }

}
