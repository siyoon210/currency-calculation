package me.siyoon.currencycalculation.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter @Setter
public class ExchangeRateInputDto {
    @NotBlank
    private String sendingCountry;
    @NotBlank
    private String receivingCountry;
//    @Value("${currencyLayer.source}")
//    private String source;
//    @Value("#{'${currencyLayer.currencies}'.split(',')}")
//    private Set<String> currencies;

//    public ExchangeRateInputDto() {
//    }

//    public ExchangeRateInputDto(@NotBlank String sendingCountry, @NotBlank String receivingCountry) {
//        this.sendingCountry = sendingCountry;
//        this.receivingCountry = receivingCountry;
//        checkInputValidation();
//    }

//    protected void checkInputValidation() {
//        if (!sendingCountry.equals(source) && !currencies.contains(sendingCountry)) {
//            throw new IllegalArgumentException("송금국가의 입력값이 올바르지 않습니다.");
//        }
//
//        if (!currencies.contains(receivingCountry)) {
//            throw new IllegalArgumentException("수취국가의 입력값이 올바르지 않습니다.");
//        }
//    }
}
