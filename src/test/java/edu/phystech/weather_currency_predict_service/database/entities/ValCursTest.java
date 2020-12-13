package edu.phystech.weather_currency_predict_service.database.entities;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ValCursTest {
    private static ValCurs valCurs;
    @BeforeAll
    static void setUp(){
        valCurs = new ValCurs();
    }

    @Test
    void getValuteList() {
        assertNotEquals(valCurs.getValuteList(), null);
    }

    @Test
    void getValute() {
        Valute valute = new Valute("numCode", "charCode", 5, "name", "value");
        valCurs.getValuteList().add(valute);
        assertEquals(valCurs.getValute("name"), valute);
    }

    @Test
    void testGetNullValute() {
        List<Valute> valList = new ArrayList<>();
        valList.add(new Valute("numCode", "charCode", 5, "name", "value"));
        ValCurs newValCurs = new ValCurs(valList);
        assertNull(newValCurs.getValute("nullValute"));
    }
}