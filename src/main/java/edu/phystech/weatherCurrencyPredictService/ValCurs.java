package edu.phystech.weatherCurrencyPredictService;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@XmlRootElement(name = "ValCurs")
public class ValCurs implements Serializable {
    @JacksonXmlElementWrapper(useWrapping = false)
    @JacksonXmlProperty(localName = "Valute")
    private List<Valute> ValuteList;

    public List<Valute> getValuteList() {
        return ValuteList;
    }

    public ValCurs(List<Valute> valuteList) {
        this.ValuteList = valuteList;
    }
    public ValCurs(){
        ValuteList = new ArrayList<>();
    }

    public Valute getValute(String valuteName){
        for(Valute valute : ValuteList){
            if(valute.getName().equals(valuteName)){
                return valute;
            }
        }
        return null;
    }
}
