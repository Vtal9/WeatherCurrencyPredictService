package edu.phystech.weatherCurrencyPredictService;

import org.springframework.http.ResponseEntity;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CurrencyData {
    private final String currency;

    private static final String responseRegex = "Доллар США</Name><Value>([0-9]*.[0-9]*)";

    public CurrencyData(ResponseEntity<String> response) {
        Pattern pattern = Pattern.compile(responseRegex);
        Matcher matcher = pattern.matcher(response.getBody());
        matcher.find();
        currency = matcher.group(1);
    }

    public double asDouble() {
        Pattern formatToDoublePattern = Pattern.compile(",");
        Matcher formatToDoubleMatcher = formatToDoublePattern.matcher(currency);
        return Double.parseDouble(formatToDoubleMatcher.replaceFirst("."));
    }

}
