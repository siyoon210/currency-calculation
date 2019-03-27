package me.siyoon.currencycalculation.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Getter @Setter
public class InputDto {
    @NotBlank
    private String sendingCountry;
    @NotBlank
    private String receivingCountry;
    @Min(0) @Max(10000)
    private double amount;
}
