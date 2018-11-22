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
import java.util.Random;

import firebaseuser.Realtime_Pledge_Data;
import foodandco2.FoodData;
import lecho.lib.hellocharts.model.PieChartData;
import lecho.lib.hellocharts.model.SliceValue;
import lecho.lib.hellocharts.view.PieChartView;

public class ResultScreenFirst extends AppCompatActivity {

    private ArrayList<String> seek_bar_name ;
    private ArrayList<Integer> seek_bar_data;
    private FoodData foodData;
    private FirebaseUser mUser;
    private DatabaseReference mDatabase;

    Integer Total_Saved;
    Button mPledge_Savings;
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
    TextView mTextview_On_Screen;
    TextView mSaved_In_Tonnes;





    //uses a custom library to display bar graph as result from calculations
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_screen_first);
        seek_bar_name = new ArrayList<>();
        seek_bar_data = new ArrayList<>();
        Total_Saved = 0;

        final CalculatorActivityData userInput = getIntent().getParcelableExtra(CalcActivity.DATA_PASSED_FROM_MAINACTIVITY);


        mPledge_Savings = findViewById(R.id.Pledge_saving);
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
        mTextview_On_Screen = findViewById(R.id.saving_or_more);
        mSaved_In_Tonnes = findViewById(R.id.Tonnes);


        try {
            foodData = new FoodData(this.getApplicationContext());
        } catch (IOException e) {
            e.printStackTrace();
        }

        final Integer totalEmission = (int) new ResultScreenFirstDataHandler(this).total_emission(userInput, foodData);
        set_textview_for_current_diet(totalEmission);
        initialize_seek_bar_data(userInput);
        generate_graph();



        mBeefBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                set_changes_to_dataset();
                set_changese_to_saving_and_adjusted();
                Total_Saved =set_text_to_textview();
                generate_graph();
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
                set_changes_to_dataset();
                set_changese_to_saving_and_adjusted();
                Total_Saved =set_text_to_textview();
                generate_graph();
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
                set_changes_to_dataset();
                set_changese_to_saving_and_adjusted();
                Total_Saved =set_text_to_textview();
                generate_graph();
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
                set_changes_to_dataset();
                set_changese_to_saving_and_adjusted();
                Total_Saved =set_text_to_textview();
                generate_graph();
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

                set_changes_to_dataset();
                set_changese_to_saving_and_adjusted();
                Total_Saved =set_text_to_textview();
                generate_graph();

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
                set_changes_to_dataset();
                set_changese_to_saving_and_adjusted();
                Total_Saved =set_text_to_textview();
                generate_graph();
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
                set_changes_to_dataset();
                set_changese_to_saving_and_adjusted();
                Total_Saved =set_text_to_textview();
                generate_graph();
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
                set_changes_to_dataset();
                set_changese_to_saving_and_adjusted();
                Total_Saved =set_text_to_textview();
                generate_graph();

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });






        mPledge_Savings.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v) {
                Toast toast = Toast.makeText(getApplicationContext(), "Check Your Pledge On Your Pledge Page", Toast.LENGTH_SHORT);
                toast.show();
                mUser = FirebaseAuth.getInstance().getCurrentUser();
                String uid = mUser.getUid();
                mDatabase = FirebaseDatabase.getInstance().getReference();
                mDatabase.child("users").child(uid).child("pledge").setValue(Total_Saved.toString());
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

    private void initialize_seek_bar_data(CalculatorActivityData userInput)
    {
        boolean set_pork = false;
        boolean set_beef = false;
        boolean set_lamb = false;
        boolean set_fish = false;
        boolean set_chicken = false;
        boolean set_egg = false;
        boolean set_beans = false;
        boolean set_veggies = false;
        for (int i = 0; i < userInput.getArrayListSize(); i++)
        {
            if (userInput.getPairAtIndex(i).first.toString().equals("Pork"))

                set_pork = true;

            if (userInput.getPairAtIndex(i).first.toString().equals("Lamb"))

                set_lamb = true;

            if (userInput.getPairAtIndex(i).first.toString().equals("Beef"))

                set_beef = true;

            if (userInput.getPairAtIndex(i).first.toString().equals("Chicken"))

                set_chicken = true;

            if (userInput.getPairAtIndex(i).first.toString().equals("Fish"))
                set_fish = true;

            if (userInput.getPairAtIndex(i).first.toString().equals("Beans"))
                set_beans = true;

            if (userInput.getPairAtIndex(i).first.toString().equals("Eggs"))
                set_egg = true;

            if (userInput.getPairAtIndex(i).first.toString().equals("Vegetables"))
                set_veggies = true;

            seek_bar_name.add(userInput.getPairAtIndex(i).first.toString());
            seek_bar_data.add(Integer.parseInt(userInput.getPairAtIndex(i).second.toString()));
        }


        if (!set_beans) {
            seek_bar_name.add("Beans");
            seek_bar_data.add(0);
        }
        if (!set_beef) {
            seek_bar_name.add("Beef");
            seek_bar_data.add(0);
        }
        if (!set_chicken)
        {
            seek_bar_name.add("Chicken");
            seek_bar_data.add(0);
        }
        if (!set_egg)
        {
            seek_bar_name.add("Eggs");
            seek_bar_data.add(0);
        }
        if (!set_fish)
        {
            seek_bar_name.add("Fish");
            seek_bar_data.add(0);
        }
        if (!set_lamb)
        {
            seek_bar_name.add("Lamb");
            seek_bar_data.add(0);
        }
        if (!set_pork)
        {
            seek_bar_name.add("Pork");
            seek_bar_data.add(0);
        }
        if (!set_veggies)
        {
            seek_bar_name.add("Vegetables");
            seek_bar_data.add(0);
        }

        String name;
        Integer data;
        for(int i = 0; i< seek_bar_data.size(); i++)
        {
            name = seek_bar_name.get(i);
            data = seek_bar_data.get(i);
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



    public void generate_graph() {
        List pieData = new ArrayList<>(8);
        PieChartView data_chart;
        data_chart = findViewById(R.id.PieChart);
        for(int i = 0; i<seek_bar_name.size(); i++ )
            if(seek_bar_data.get(i)!=0)
                pieData.add(new SliceValue(seek_bar_data.get(i),Color.argb(255, i, 90+20*i, 0)).setLabel(seek_bar_name.get(i)));
        PieChartData pieChartData = new PieChartData(pieData);
        pieChartData.setHasLabels(true).setValueLabelTextSize(12);
        data_chart.setPieChartData(pieChartData);


    }

    public void set_changes_to_dataset()
    {
        String name;
        for(int i = 0; i< seek_bar_data.size(); i++)
        {
            name = seek_bar_name.get(i);
            if(name.equals("Pork"))
                seek_bar_data.set(i, mPorkBar.getProgress());
            if(name.equals("Beef"))
                seek_bar_data.set(i,mBeefBar.getProgress());
            if(name.equals("Chicken"))
                seek_bar_data.set(i,mChickenBar.getProgress());
            if(name.equals("Fish"))
                seek_bar_data.set(i,mFishBar.getProgress());
            if(name.equals("Beans"))
                seek_bar_data.set(i,mBeanBar.getProgress());
            if(name.equals("Eggs"))
                seek_bar_data.set(i,mEggBar.getProgress());
            if(name.equals("Vegetables"))
                seek_bar_data.set(i,mVeggieBar.getProgress());
            if(name.equals("Lamb"))
                seek_bar_data.set(i,mLambBar.getProgress());
        }
    }

    public void set_changese_to_saving_and_adjusted()
    {
        final Integer total_after_adjustment = (int) new ResultScreenFirstDataHandler(this).total_emission(seek_bar_name,seek_bar_data, foodData);

        Integer car_adjusted = total_after_adjustment*9/4;
        Integer radiator_adjusted = total_after_adjustment/2;

        mAdjustedCar.setText(Integer.toString(car_adjusted));
        mAdjustedRadiator.setText(Integer.toString(radiator_adjusted));
        Integer car_saved;
        Integer radiator_saved;
        car_saved = Integer.parseInt(mCurrentCar.getText().toString()) - car_adjusted;
        if (car_saved<0)
            car_saved = 0;
        mSavedCar.setText(Integer.toString(car_saved));
        radiator_saved = Integer.parseInt(mCurrentRadiator.getText().toString())- radiator_adjusted;
        if (radiator_saved<0)
            radiator_saved = 0;
        mSavedRadiator.setText(Integer.toString(radiator_saved));


    }

    public Integer set_text_to_textview()
    {
        Integer Total_Savings =  (Integer.parseInt(mCurrentCar.getText().toString()) - Integer.parseInt(mAdjustedCar.getText().toString()))*9/4000;
        Total_Savings = Total_Savings + (Integer.parseInt(mCurrentRadiator.getText().toString()) - Integer.parseInt(mAdjustedRadiator.getText().toString()))/1000;
        mTextview_On_Screen.setText("Your CO2 Saving In Tonnes ");
        mSaved_In_Tonnes.setText(Total_Savings.toString());
        if (Total_Savings<0)
            Total_Savings = 0;
        return Total_Savings;
    }

}