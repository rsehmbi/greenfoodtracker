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

   // int current;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_facts);
        final fact f1= new fact();
        textView =(TextView) findViewById(R.id.factsID);

        streamcountlines = this.getResources().openRawResource(R.raw.factstextfile);
        readcounlines = new BufferedReader(new InputStreamReader(streamcountlines));

        try{

            while(readcounlines.readLine() != null){
                f1.setIntcount(f1.getIntcount()+1);//intcount++;

            }
        }catch (Exception e){
            e.printStackTrace();
        }

        inputStream = this.getResources().openRawResource(R.raw.factstextfile);
        bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

        //textdata = new String[intcount];
        f1.init(f1.getIntcount());
        try {
            for(int i = 0 ; i < f1.getIntcount();i++){
                f1.setTextdata(bufferedReader.readLine(),i);//textdata[i]=bufferedReader.readLine();
            }
        }catch (Exception f ){
                f.printStackTrace();
        }

        textView.setText(f1.getTextdata(0));//textdata[0]);
       // current=0;
        button = (Button)findViewById(R.id.RefreshID);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                f1.pickRandomFacts();
                textView.setText(f1.getTextdata(0));//textdata[0]);
            }
        });


    }

   // private void pickRandomFacts() {
     //   Collections.shuffle(Arrays.asList(textdata));
   // }

}
