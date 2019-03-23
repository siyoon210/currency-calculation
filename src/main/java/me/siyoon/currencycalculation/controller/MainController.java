package me.siyoon.currencycalculation.controller;

import lombok.RequiredArgsConstructor;
import me.siyoon.currencycalculation.service.CurrencyLayerAPIService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class MainController {
    private final CurrencyLayerAPIService currencyLayerAPIService;

    @GetMapping("/")
    private String hello(Model model) {
        model.addAttribute("currencyInfo", currencyLayerAPIService.getCurrencyInfo());
        return "index";
    }
}
