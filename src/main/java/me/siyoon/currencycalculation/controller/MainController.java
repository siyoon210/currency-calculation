package me.siyoon.currencycalculation.controller;

import lombok.RequiredArgsConstructor;
import me.siyoon.currencycalculation.service.CurrencyCalculationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class MainController {
    private final CurrencyCalculationService currencyCalculationService;

    @GetMapping("/")
    private String hello(Model model) {
        model.addAttribute("defaultExchangeRate",
                currencyCalculationService.getExchangeRate("USD","KRW"));
        return "index";
    }
}
