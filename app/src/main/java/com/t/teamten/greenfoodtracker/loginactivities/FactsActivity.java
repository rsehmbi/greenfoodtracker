package com.t.teamten.greenfoodtracker.loginactivities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.t.teamten.greenfoodtracker.R;
import com.t.teamten.greenfoodtracker.loginactivities.fact;
import com.t.teamten.greenfoodtracker.settingsforuser;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class FactsActivity extends AppCompatActivity {
    // Facts Activity so that the user can see random facts instead of using the Calculator all the time.
    TextView randomFacts;
    Button refreshButton;
    InputStream streamcountlines;
    BufferedReader readcounlines;

    InputStream inputStream;
    BufferedReader bufferedReader;
    float x1,x2,y1,y2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_facts);
        final fact factsObject= new fact();
        randomFacts =(TextView) findViewById(R.id.factsID);
        streamcountlines = this.getResources().openRawResource(R.raw.factstextfile);
        readcounlines = new BufferedReader(new InputStreamReader(streamcountlines));
        try{

            while(readcounlines.readLine() != null){
                factsObject.setIntcount(factsObject.getIntcount()+1);

            }
        }catch (Exception exceptiontoCatch){
            exceptiontoCatch.printStackTrace();
        }

        inputStream = this.getResources().openRawResource(R.raw.factstextfile);
        bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

        factsObject.inititializingarray(factsObject.getIntcount());
        try {
            for(int i = 0 ; i < factsObject.getIntcount();i++){
                factsObject.setTextdata(bufferedReader.readLine(),i);
            }
        }catch (Exception exceptiontoCatchforBufferReader ){
                exceptiontoCatchforBufferReader.printStackTrace();
        }

        randomFacts.setText(factsObject.getTextdata(0));
        refreshButton = (Button)findViewById(R.id.RefreshID);
        refreshButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                factsObject.pickRandomFacts();
                randomFacts.setText(factsObject.getTextdata(0));
            }
        });

    }
    public boolean onTouchEvent(MotionEvent touchEvent){
        switch(touchEvent.getAction()){
            case MotionEvent.ACTION_DOWN:
                x1 = touchEvent.getX();
                y1 = touchEvent.getY();
                break;
            case MotionEvent.ACTION_UP:
                x2 = touchEvent.getX();
                y2 = touchEvent.getY();
                if(x1 < x2){
                    Intent i = new Intent(FactsActivity.this,HomeScreen.class);
                    startActivity(i);
                }else if(x1 > x2){
                    Intent i = new Intent(FactsActivity.this,settingsforuser.class);
                    startActivity(i);
                }
                break;
        }
        return false;
    }


}
