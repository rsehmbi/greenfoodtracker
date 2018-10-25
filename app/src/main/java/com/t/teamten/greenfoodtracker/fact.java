package com.t.teamten.greenfoodtracker;

import java.util.Arrays;
import java.util.Collections;

public class fact {

    private String[] textdata;
    private int intcount;

    fact()
    {
        textdata = new String[intcount];
        intcount=0;
    }

    public void init(int count)
    {
        this.textdata = new String[count];
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
