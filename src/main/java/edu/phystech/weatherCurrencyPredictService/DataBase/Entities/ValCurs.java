package edu.phystech.weatherCurrencyPredictService.DataBase.Entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "ValCurs")
@XmlRootElement(name = "ValCurs")
public class ValCurs implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JacksonXmlProperty(localName = "Date", isAttribute = true)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy")
    private Date date;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JacksonXmlElementWrapper(useWrapping = false)
    @JacksonXmlProperty(localName = "Valute")
    private List<Valute> ValuteList;

    public List<Valute> getValuteList() {
        return ValuteList;
    }

    public ValCurs(List<Valute> valuteList) {
        this.ValuteList = valuteList;
    }

    public ValCurs() {
        ValuteList = new ArrayList<>();
    }

    public Valute getValute(String valuteName) {
        for (Valute valute : ValuteList) {
            if (valute.getName().equals(valuteName)) {
                return valute;
            }
        }
        return null;
    }
}
