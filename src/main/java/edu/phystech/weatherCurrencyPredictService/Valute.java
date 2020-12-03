package edu.phystech.weatherCurrencyPredictService;


import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import java.io.Serializable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@JacksonXmlRootElement(localName = "Valute")
public class Valute implements Serializable {
    @JacksonXmlProperty(localName = "NumCode")
    private String NumCode;
    @JacksonXmlProperty(localName = "CharCode")
    private String CharCode;
    @JacksonXmlProperty(localName = "Nominal")
    private int Nominal;
    @JacksonXmlProperty(localName = "Name")
    private String Name;
    @JacksonXmlProperty(localName = "Value")
    private String Value;

    public Valute(String numCode, String charCode, int nominal, String name, String value) {
        this.NumCode = numCode;
        this.CharCode = charCode;
        this.Nominal = nominal;
        this.Name = name;
        this.Value = value;
    }

    public String getNumCode() {
        return NumCode;
    }

    public String getCharCode() {
        return CharCode;
    }

    public int getNominal() {
        return Nominal;
    }

    public String getName() {
        return Name;
    }

    public double getValue() {
        Pattern formatToDoublePattern = Pattern.compile(",");
        Matcher formatToDoubleMatcher = formatToDoublePattern.matcher(Value);
        return Double.parseDouble(formatToDoubleMatcher.replaceFirst("."));
    }

    public Valute(){
    }

}
