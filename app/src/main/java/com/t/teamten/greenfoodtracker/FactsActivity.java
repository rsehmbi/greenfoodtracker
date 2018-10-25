package com.t.teamten.greenfoodtracker;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Scanner;

public class FactsActivity extends AppCompatActivity {

    TextView textView;
    Button button;
    InputStream streamcountlines;
    BufferedReader readcounlines;

    InputStream inputStream;
    BufferedReader bufferedReader;
    int intcount=0;
    int current;

    String[] textdata;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_facts);

        textView =(TextView) findViewById(R.id.factsID);

        streamcountlines = this.getResources().openRawResource(R.raw.factstextfile);
        readcounlines = new BufferedReader(new InputStreamReader(streamcountlines));

        try{

            while(readcounlines.readLine() != null){
                intcount++;

            }
        }catch (Exception e){
            e.printStackTrace();
        }

        inputStream = this.getResources().openRawResource(R.raw.factstextfile);
        bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

        textdata = new String[intcount];

        try {
            for(int i = 0 ; i < intcount;i++){
                textdata[i]=bufferedReader.readLine();
            }
        }catch (Exception f ){
                f.printStackTrace();
        }

        textView.setText(textdata[0]);
        current=0;
        button = (Button)findViewById(R.id.RefreshID);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pickRandomFacts();
                textView.setText(textdata[0]);
            }
        });


    }

    private void pickRandomFacts() {
        Collections.shuffle(Arrays.asList(textdata));
    }

}
