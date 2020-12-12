package edu.phystech.weatherCurrencyPredictService.DataBase.Entities;


import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import javax.persistence.*;
import java.io.Serializable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Entity
@Table(name = "Valute")
@JacksonXmlRootElement(localName = "Valute")
public class Valute implements Serializable {
    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JacksonXmlProperty(localName = "NumCode")
    private String numCode;
    @JacksonXmlProperty(localName = "CharCode")
    private String charCode;
    @JacksonXmlProperty(localName = "Nominal")
    private int nominal;
    @JacksonXmlProperty(localName = "Name")
    private String name;
    @JacksonXmlProperty(localName = "Value")
    private String value;

    public Valute(String numCode, String charCode, int nominal, String name, String value) {
        this.numCode = numCode;
        this.charCode = charCode;
        this.nominal = nominal;
        this.name = name;
        this.value = value;
    }

    public String getNumCode() {
        return numCode;
    }

    public String getCharCode() {
        return charCode;
    }

    public int getNominal() {
        return nominal;
    }

    public String getName() {
        return name;
    }

    public double getValue() {
        Pattern formatToDoublePattern = Pattern.compile(",");
        Matcher formatToDoubleMatcher = formatToDoublePattern.matcher(value);
        return Double.parseDouble(formatToDoubleMatcher.replaceFirst("."));
    }

    public Valute() {
    }

}
