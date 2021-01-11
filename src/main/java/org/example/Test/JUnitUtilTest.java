package org.example.Test;

import org.example.Buisness.Calculate;
import org.junit.Test;
import junit.framework.Assert;

import java.time.LocalDate;

public class JUnitUtilTest extends Assert {

    @Test
    public void getCoefObjectTypeTest() {
        double result;
        result = new Calculate().getCoefObjectType("Квартира");
        assertEquals(1.7, result);

        result = new Calculate().getCoefObjectType("Дом");
        assertEquals(1.5, result);

        result = new Calculate().getCoefObjectType("Комната");
        assertEquals(1.3, result);

        result = new Calculate().getCoefObjectType(null);
        assertEquals(0.0, result);

        result = new Calculate().getCoefObjectType("somthstrange");
        assertEquals(0.0, result);

    }

    @Test
    public void getConstructionYearTest() {
        double result;
        result = new Calculate().getCoefConstructionYear (1950);
        assertEquals(1.3, result);

        result = new Calculate().getCoefConstructionYear (2000);
        assertEquals(1.6, result);

        result = new Calculate().getCoefConstructionYear (2010);
        assertEquals(1.6, result);

        result = new Calculate().getCoefConstructionYear (2014);
        assertEquals(1.6, result);

        result = new Calculate().getCoefConstructionYear (2015);
        assertEquals(2.0, result);

        result = new Calculate().getCoefConstructionYear (2099);
        assertEquals(2.0, result);

        result = new Calculate().getCoefConstructionYear (null);
        assertEquals(0.0, result);

        result = new Calculate().getCoefConstructionYear (-20);
        assertEquals(0.0, result);

    }

    @Test
    public void getCoefSizeTest() {
        double result;
        result = new Calculate().getCoefSize(45.00);
        assertEquals(1.2, result);

        result = new Calculate().getCoefSize(45.02);
        assertEquals(1.2, result);

        result = new Calculate().getCoefSize(45.2);
        assertEquals(1.2, result);

        result = new Calculate().getCoefSize(49.99);
        assertEquals(1.2, result);

        result = new Calculate().getCoefSize(50.00);
        assertEquals(1.5, result);

        result = new Calculate().getCoefSize(50.00000001);
        assertEquals(1.5, result);

        result = new Calculate().getCoefSize(100.00);
        assertEquals(1.5, result);

        result = new Calculate().getCoefSize(100.01);
        assertEquals(2.0, result);

        result = new Calculate().getCoefSize(150.00);
        assertEquals(2.0, result);

        result = new Calculate().getCoefSize(null);
        assertEquals(0.0, result);
    }

    @Test
    public void calculateTest() {
        double result;
        result = new Calculate(1000000, LocalDate.of(2021, 1, 1),
                LocalDate.of(2021, 1, 2), 1.7, 1.3, 1.5 ).getInsuranceFee() ;
        assertEquals(1657500.0, result);

        result = new Calculate(0, LocalDate.of(2021, 1, 1),
                LocalDate.of(2021, 1, 2), 1.7, 1.3, 1.5 ).getInsuranceFee() ;
        assertEquals(0.0, result);

    }
}
