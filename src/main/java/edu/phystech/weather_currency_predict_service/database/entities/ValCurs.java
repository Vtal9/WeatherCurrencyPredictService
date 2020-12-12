package edu.phystech.weather_currency_predict_service.database.entities;

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
    private List<Valute> valuteList;

    public List<Valute> getValuteList() {
        return valuteList;
    }

    public ValCurs(List<Valute> valuteList) {
        this.valuteList = valuteList;
    }

    public ValCurs() {
        valuteList = new ArrayList<>();
    }

    public Valute getValute(String valuteName) {
        for (Valute valute : valuteList) {
            if (valute.getName().equals(valuteName)) {
                return valute;
            }
        }
        return null;
    }
}
