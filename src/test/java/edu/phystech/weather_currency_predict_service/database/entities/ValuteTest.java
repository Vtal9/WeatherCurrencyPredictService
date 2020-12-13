package edu.phystech.weather_currency_predict_service.database.entities;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ValuteTest {
    private static Valute valute;
    
    @BeforeAll
    static void setUp(){
        valute = new Valute("numCode", "charCode", 5, "name", "25.3");
    }

    @Test
    void getNumCode() {
        assertEquals("numCode", valute.getNumCode());
    }

    @Test
    void getCharCode() {
        assertEquals("charCode", valute.getCharCode());
    }

    @Test
    void getNominal() {
        assertEquals(5, valute.getNominal());
    }

    @Test
    void getName() {
        assertEquals("name", valute.getName());
    }

    @Test
    void getValue() {
        assertEquals(25.3, valute.getValue());
    }

    @Test
    void testEquals() {
        Valute newValute = new Valute("numCode", "charCode", 5, "name", "25.3");
        assertTrue(newValute.equals(valute));
    }

    @Test
    void testHashCode() {
        Valute newValute = new Valute("numCode", "charCode", 5, "name", "25.3");
        assertEquals(valute.hashCode(), newValute.hashCode());
    }
}