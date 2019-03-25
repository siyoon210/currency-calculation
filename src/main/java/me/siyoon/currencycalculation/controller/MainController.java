package me.siyoon.currencycalculation.controller;

import lombok.RequiredArgsConstructor;
import me.siyoon.currencycalculation.service.CurrencyCalculationService;
import me.siyoon.currencycalculation.util.NumberFormatUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class MainController {
    private final CurrencyCalculationService currencyCalculationService;

    @GetMapping("/")
    private String main(Model model) {
        String defaultSendingCountry = "USD";
        String defaultReceivingCountry = "KRW";
        Double exchangeRate = currencyCalculationService.getExchangeRate(defaultSendingCountry, defaultReceivingCountry);
        model.addAttribute("defaultExchangeRate",
                NumberFormatUtil.convert(exchangeRate) + " " + defaultReceivingCountry + "/" + defaultSendingCountry);
        return "index";
    }
}
