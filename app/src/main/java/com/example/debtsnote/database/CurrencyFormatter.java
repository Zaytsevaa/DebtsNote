package com.example.debtsnote.database;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

public class CurrencyFormatter {

    public static String toStringCurrency(int debtTotal) {
        String value;
        if (debtTotal >= 0) {
            value = String.valueOf(debtTotal / 100) + "." + String.valueOf(debtTotal % 100);
        } else {
            value = String.valueOf(debtTotal / 100) + "." + String.valueOf(Math.abs(debtTotal % 100));
        }
        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(new Locale("ru", "RU"));
        try {
            // Depends on Local
            return currencyFormat.format(NumberFormat.getNumberInstance(new Locale("en", "US")).parse(value));
        } catch (ParseException e) {
            e.printStackTrace();
            return (value + " р.");
        }
    }

    public static int toIntCurrency(String debtInput) {
        int debtValue;
        if (debtInput.contains(".")) {
            debtValue = 100 * Integer.valueOf(debtInput.substring(0, debtInput.indexOf(".")));
            String substring = debtInput.substring(debtInput.indexOf(".") + 1);
            if (!debtInput.contains("-")) {
                debtValue += Integer.valueOf(substring);
            } else {
                debtValue -= Integer.valueOf(substring);
            }
        } else {
            debtValue = 100 * Integer.valueOf(debtInput);
        }
        return debtValue;
    }
}
