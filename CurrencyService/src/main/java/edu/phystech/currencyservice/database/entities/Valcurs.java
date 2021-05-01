package edu.phystech.currencyservice.database.entities;

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
@Table(name = "Valcurs")
@XmlRootElement(name = "ValCurs")
public class Valcurs implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "date", nullable = false)
    @JacksonXmlProperty(localName = "Date", isAttribute = true)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy")
    private Date date;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy="id")
    @JacksonXmlElementWrapper(useWrapping = false)
    @JacksonXmlProperty(localName = "Valute")
    private final List<Valute> valuteList;

    public List<Valute> getValuteList() {
        return valuteList;
    }

    public Valcurs(List<Valute> valuteList) {
        this.valuteList = valuteList;
    }

    public Valcurs() {
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
