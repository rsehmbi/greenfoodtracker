package com.t.teamten.greenfoodtracker.loginactivities;

import org.junit.Test;

import static org.junit.Assert.*;

public class FactTest {

    @Test
    public void initializeArray() {
        Fact fact = new Fact();
        fact.initializeArray(10);
        assertEquals(10, fact.getTextData().length);
    }

    @Test
    public void getTextData() {
        Fact fact = new Fact();
        fact.initializeArray(10);
        fact.setTextData("test", 0);
        assertEquals("test", fact.getTextData(0));
    }


    @Test
    public void setIntCount() {
        Fact fact = new Fact();
        fact.initializeArray(10);
        fact.setIntCount(20);
        assertEquals(20, fact.getIntCount());
    }


}