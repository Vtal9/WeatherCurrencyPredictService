package edu.phystech.currencyservice.database.entities;


import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Entity
@Table(name = "valute")
@JacksonXmlRootElement(localName = "Valute")
public class Valute implements Serializable {
    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "num_code", nullable = false)
    @JacksonXmlProperty(localName = "NumCode")
    private String numCode;

    @Column(name = "char_code", nullable = false)
    @JacksonXmlProperty(localName = "CharCode")
    private String charCode;

    @Column(name = "nominal", nullable = false)
    @JacksonXmlProperty(localName = "Nominal")
    private int nominal;

    @Column(name = "name", nullable = false)
    @JacksonXmlProperty(localName = "Name")
    private String name;

    @Column(name = "value", nullable = false)
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Valute valute = (Valute) o;
        return nominal == valute.nominal &&
                Objects.equals(id, valute.id) &&
                Objects.equals(numCode, valute.numCode) &&
                Objects.equals(charCode, valute.charCode) &&
                Objects.equals(name, valute.name) &&
                Objects.equals(value, valute.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, numCode, charCode, nominal, name, value);
    }
}
