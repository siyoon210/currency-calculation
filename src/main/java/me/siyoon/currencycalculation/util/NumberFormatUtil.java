package me.siyoon.currencycalculation.util;

import java.text.DecimalFormat;

public class NumberFormatUtil {
    private static final DecimalFormat decimalFormat = new DecimalFormat("#,##0.00");

    public static String convert(Number number) {
        return decimalFormat.format(number);
    }
}
