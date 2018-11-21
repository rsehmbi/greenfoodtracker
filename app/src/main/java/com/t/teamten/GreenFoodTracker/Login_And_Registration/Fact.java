package com.t.teamten.GreenFoodTracker.Login_And_Registration;

import java.util.Arrays;
import java.util.Collections;
//Helper Fact class for facts activity.
public class Fact {

    private String[] textdata;
    private int intcount;

    Fact()
    {
        textdata = new String[intcount];
        intcount = 0;
    }

    public void inititializingarray(int index)
    {
        this.textdata = new String[index];
    }
    public String getTextdata(int index) {
        return textdata[index];
    }

    public int getIntcount() {
        return intcount;
    }

    public void setIntcount(int intcount) {
        this.intcount = intcount;
    }

    public void setTextdata(String line, int index) {
        this.textdata[index]=line;
    }

    public String[] Textdata() {
        return textdata;
    }

     public void pickRandomFacts() {
       Collections.shuffle(Arrays.asList(Textdata()));
     }
}
