package me.siyoon.currencycalculation.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/")
    private String main() {
        return "index";
    }
}
