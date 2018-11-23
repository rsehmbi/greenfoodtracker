package com.t.teamten.greenfoodtracker.resultscreenactivities;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.t.teamten.greenfoodtracker.R;
import com.t.teamten.greenfoodtracker.calcactivities.CalcActivity;
import com.t.teamten.greenfoodtracker.calcactivities.CalculatorActivityData;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import firebaseuser.Realtime_Pledge_Data;
import foodandco2.FoodData;
import lecho.lib.hellocharts.model.PieChartData;
import lecho.lib.hellocharts.model.SliceValue;
import lecho.lib.hellocharts.view.PieChartView;

public class ResultScreenFirst extends AppCompatActivity {

    private ArrayList<String> seekBarName;
    private ArrayList<Integer> seekBarData;
    private FoodData foodData;
    private FirebaseUser mUser;
    private DatabaseReference mDatabase;

    Integer totalSaved;
    Button mPledgeSavings;
    SeekBar mBeefBar;
    SeekBar mLambBar;
    SeekBar mEggBar;
    SeekBar mBeanBar;
    SeekBar mPorkBar;
    SeekBar mChickenBar;
    SeekBar mFishBar;
    SeekBar mVeggieBar;
    TextView mCurrentCar;
    TextView mCurrentRadiator;
    TextView mAdjustedCar;
    TextView mAdjustedRadiator;
    TextView mSavedCar;
    TextView mSavedRadiator;
    TextView mTextviewOnScreen;
    TextView mSavedInTonnes;





    //uses a custom library to display bar graph as result from calculations
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_screen_first);
        seekBarName = new ArrayList<>();
        seekBarData = new ArrayList<>();
        totalSaved = 0;

        final CalculatorActivityData userInput = getIntent().getParcelableExtra(CalcActivity.DATA_PASSED_FROM_MAINACTIVITY);


        mPledgeSavings = findViewById(R.id.Pledge_saving);
        mBeefBar = findViewById(R.id.beef_seekbar);
        mBeanBar = findViewById(R.id.bean_seekbar);
        mChickenBar = findViewById(R.id.chicken_seekbar);
        mPorkBar = findViewById(R.id.pork_seekbar);
        mEggBar = findViewById(R.id.egg_seekbar);
        mFishBar = findViewById(R.id.fish_seekbar);
        mLambBar = findViewById(R.id.lamb_seekbar);
        mVeggieBar = findViewById(R.id.veggie_seekbar);
        mAdjustedCar = findViewById(R.id.car_adjusted);
        mAdjustedRadiator = findViewById(R.id.radiator_adjusted);
        mSavedRadiator = findViewById(R.id.radiator_saving);
        mSavedCar = findViewById(R.id.car_saving);
        mCurrentCar = findViewById(R.id.car_current);
        mCurrentRadiator = findViewById(R.id.radiator_current);
        mTextviewOnScreen = findViewById(R.id.saving_or_more);
        mSavedInTonnes = findViewById(R.id.Tonnes);


        try {
            foodData = new FoodData(this.getApplicationContext());
        } catch (IOException e) {
            e.printStackTrace();
        }

        final Integer totalEmission = (int) new ResultScreenFirstDataHandler(this).totalEmission(userInput, foodData);
        set_textview_for_current_diet(totalEmission);
        initializeSeekBarData(userInput);
        generateGraph();



        mBeefBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                setChangesToDataset();
                setChanges();
                totalSaved = setTextToTextview();
                generateGraph();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        mPorkBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                setChangesToDataset();
                setChanges();
                totalSaved = setTextToTextview();
                generateGraph();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        mFishBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                setChangesToDataset();
                setChanges();
                totalSaved = setTextToTextview();
                generateGraph();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        mChickenBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                setChangesToDataset();
                setChanges();
                totalSaved = setTextToTextview();
                generateGraph();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        mVeggieBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                setChangesToDataset();
                setChanges();
                totalSaved = setTextToTextview();
                generateGraph();

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        mEggBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                setChangesToDataset();
                setChanges();
                totalSaved = setTextToTextview();
                generateGraph();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        mBeanBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                setChangesToDataset();
                setChanges();
                totalSaved = setTextToTextview();
                generateGraph();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        mLambBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                setChangesToDataset();
                setChanges();
                totalSaved = setTextToTextview();
                generateGraph();

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });






        mPledgeSavings.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v) {
                Toast toast = Toast.makeText(getApplicationContext(), "Check Your Pledge On Your Pledge Page", Toast.LENGTH_SHORT);
                toast.show();
                mUser = FirebaseAuth.getInstance().getCurrentUser();
                String uid = mUser.getUid();
                mDatabase = FirebaseDatabase.getInstance().getReference();
                mDatabase.child("users").child(uid).child("pledge").setValue(totalSaved.toString());
                Intent intent = new Intent(ResultScreenFirst.this, Realtime_Pledge_Data.class);
                startActivity(intent);
            }

        });

    }//end of oncreate




    private void set_textview_for_current_diet( Integer totalEmission)
    {

        Integer km_driven_current = totalEmission * 9 / 4;
        mCurrentCar.setText(Integer.toString(km_driven_current));
        mCurrentRadiator.setText(Integer.toString(totalEmission/2));//ratio is 1

    }


    //bunch of functions

    private void initializeSeekBarData(CalculatorActivityData userInput)
    {
        boolean isPork = false;
        boolean isBeef = false;
        boolean isLamb = false;
        boolean isFish = false;
        boolean isChicken = false;
        boolean isEgg = false;
        boolean isBeans = false;
        boolean isVegetable = false;
        for (int i = 0; i < userInput.getArrayListSize(); i++)
        {
            if (userInput.getPairAtIndex(i).first.toString().equals("Pork"))

                isPork = true;

            if (userInput.getPairAtIndex(i).first.toString().equals("Lamb"))

                isLamb = true;

            if (userInput.getPairAtIndex(i).first.toString().equals("Beef"))

                isBeef = true;

            if (userInput.getPairAtIndex(i).first.toString().equals("Chicken"))

                isChicken = true;

            if (userInput.getPairAtIndex(i).first.toString().equals("Fish"))
                isFish = true;

            if (userInput.getPairAtIndex(i).first.toString().equals("Beans"))
                isBeans = true;

            if (userInput.getPairAtIndex(i).first.toString().equals("Eggs"))
                isEgg = true;

            if (userInput.getPairAtIndex(i).first.toString().equals("Vegetables"))
                isVegetable = true;

            seekBarName.add(userInput.getPairAtIndex(i).first.toString());
            seekBarData.add(Integer.parseInt(userInput.getPairAtIndex(i).second.toString()));
        }


        if (!isBeans) {
            seekBarName.add("Beans");
            seekBarData.add(0);
        }
        if (!isBeef) {
            seekBarName.add("Beef");
            seekBarData.add(0);
        }
        if (!isChicken)
        {
            seekBarName.add("Chicken");
            seekBarData.add(0);
        }
        if (!isEgg)
        {
            seekBarName.add("Eggs");
            seekBarData.add(0);
        }
        if (!isFish)
        {
            seekBarName.add("Fish");
            seekBarData.add(0);
        }
        if (!isLamb)
        {
            seekBarName.add("Lamb");
            seekBarData.add(0);
        }
        if (!isPork)
        {
            seekBarName.add("Pork");
            seekBarData.add(0);
        }
        if (!isVegetable)
        {
            seekBarName.add("Vegetables");
            seekBarData.add(0);
        }

        String name;
        Integer data;
        for(int i = 0; i< seekBarData.size(); i++)
        {
            name = seekBarName.get(i);
            data = seekBarData.get(i);
            if(name.equals("Pork"))
                mPorkBar.setProgress(data);
            if (name.equals("Beef"))
                mBeefBar.setProgress(data);
            if(name.equals("Lamb"))
                mLambBar.setProgress(data);
            if (name.equals("Fish"))
                mFishBar.setProgress(data);
            if(name.equals("Chicken"))
                mChickenBar.setProgress(data);
            if (name.equals("Egg"))
                mEggBar.setProgress(data);
            if(name.equals("Vegetables"))
                mVeggieBar.setProgress(data);
            if(name.equals("Beans"))
                mBeanBar.setProgress(data);

        }

    }



    public void generateGraph() {
        List pieData = new ArrayList<>(8);
        PieChartView dataChart;
        dataChart = findViewById(R.id.PieChart);
        for(int i = 0; i< seekBarName.size(); i++ )
            if(seekBarData.get(i)!=0)
                pieData.add(new SliceValue(seekBarData.get(i),Color.argb(255, i, 90+20*i, 0)).setLabel(seekBarName.get(i)));
        PieChartData pieChartData = new PieChartData(pieData);
        pieChartData.setHasLabels(true).setValueLabelTextSize(12);
        dataChart.setPieChartData(pieChartData);


    }

    public void setChangesToDataset()
    {
        String name;
        for(int i = 0; i< seekBarData.size(); i++)
        {
            name = seekBarName.get(i);
            if(name.equals("Pork"))
                seekBarData.set(i, mPorkBar.getProgress());
            if(name.equals("Beef"))
                seekBarData.set(i,mBeefBar.getProgress());
            if(name.equals("Chicken"))
                seekBarData.set(i,mChickenBar.getProgress());
            if(name.equals("Fish"))
                seekBarData.set(i,mFishBar.getProgress());
            if(name.equals("Beans"))
                seekBarData.set(i,mBeanBar.getProgress());
            if(name.equals("Eggs"))
                seekBarData.set(i,mEggBar.getProgress());
            if(name.equals("Vegetables"))
                seekBarData.set(i,mVeggieBar.getProgress());
            if(name.equals("Lamb"))
                seekBarData.set(i,mLambBar.getProgress());
        }
    }

    public void setChanges()
    {
        final Integer totalAfterAdjustment = (int) new ResultScreenFirstDataHandler(this).totalEmission(seekBarName, seekBarData, foodData);

        Integer car_adjusted = totalAfterAdjustment*9/4;
        Integer radiator_adjusted = totalAfterAdjustment/2;

        mAdjustedCar.setText(Integer.toString(car_adjusted));
        mAdjustedRadiator.setText(Integer.toString(radiator_adjusted));
        Integer carSaving;
        Integer heatSaving;
        carSaving = Integer.parseInt(mCurrentCar.getText().toString()) - car_adjusted;
        if (carSaving<0)
            carSaving = 0;
        mSavedCar.setText(Integer.toString(carSaving));
        heatSaving = Integer.parseInt(mCurrentRadiator.getText().toString())- radiator_adjusted;
        if (heatSaving<0)
            heatSaving = 0;
        mSavedRadiator.setText(Integer.toString(heatSaving));


    }

    public Integer setTextToTextview()
    {
        Integer totalSavings =  (Integer.parseInt(mCurrentCar.getText().toString()) - Integer.parseInt(mAdjustedCar.getText().toString()))*9/4000;
        totalSavings = totalSavings + (Integer.parseInt(mCurrentRadiator.getText().toString()) - Integer.parseInt(mAdjustedRadiator.getText().toString()))/1000;
        mTextviewOnScreen.setText("Your CO2 Saving In Tonnes ");
        mSavedInTonnes.setText(totalSavings.toString());
        if(totalSavings<0)
            totalSavings = 0;
        return totalSavings;
    }

}