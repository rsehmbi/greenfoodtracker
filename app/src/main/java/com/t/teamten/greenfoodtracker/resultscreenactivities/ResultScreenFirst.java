package com.t.teamten.greenfoodtracker.resultscreenactivities;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import com.t.teamten.greenfoodtracker.R;
import com.t.teamten.greenfoodtracker.calcactivities.CalcActivity;
import com.t.teamten.greenfoodtracker.calcactivities.CalculatorActivityData;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import foodandco2.FoodData;
import lecho.lib.hellocharts.model.PieChartData;
import lecho.lib.hellocharts.model.SliceValue;
import lecho.lib.hellocharts.view.PieChartView;

public class ResultScreenFirst extends AppCompatActivity {

    private ArrayList<String> seek_bar_name ;
    private ArrayList<Integer> seek_bar_data;
    private FoodData foodData;


    Button mNext_Activity;
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





    //uses a custom library to display bar graph as result from calculations
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_screen_first);
        seek_bar_name = new ArrayList<>();
        seek_bar_data = new ArrayList<>();

        final CalculatorActivityData userInput = getIntent().getParcelableExtra(CalcActivity.DATA_PASSED_FROM_MAINACTIVITY);


        mNext_Activity = findViewById(R.id.go_to_next_activity);
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

        try {
            foodData = new FoodData(this.getApplicationContext());
        } catch (IOException e) {
            e.printStackTrace();
        }

        final Integer totalEmission = (int) new ResultScreenFirstDataHandler(this).total_emission(userInput, foodData);
        set_textview_for_current_diet(totalEmission);
        initialize_seek_bar_data(userInput);
       // initially_set_seekbar_data(seek_bar_data,seek_bar_name);
        generate_graph(seek_bar_data,seek_bar_name);



        mBeefBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                set_changes_to_dataset(seek_bar_data,seek_bar_name);
                set_changese_to_saving_and_adjusted(totalEmission);
                generate_graph(seek_bar_data,seek_bar_name);
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
                set_changes_to_dataset(seek_bar_data,seek_bar_name);
                set_changese_to_saving_and_adjusted(totalEmission);
                generate_graph(seek_bar_data,seek_bar_name);
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
                set_changes_to_dataset(seek_bar_data,seek_bar_name);
                set_changese_to_saving_and_adjusted(totalEmission);
                generate_graph(seek_bar_data,seek_bar_name);
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
                set_changes_to_dataset(seek_bar_data,seek_bar_name);
                set_changese_to_saving_and_adjusted(totalEmission);
                generate_graph(seek_bar_data,seek_bar_name);
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

                set_changes_to_dataset(seek_bar_data,seek_bar_name);
                set_changese_to_saving_and_adjusted(totalEmission);
                generate_graph(seek_bar_data,seek_bar_name);

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
                mBeefBar.setProgress(seek_bar_data.get(0));
                set_changes_to_dataset(seek_bar_data,seek_bar_name);
                set_changese_to_saving_and_adjusted(totalEmission);
                generate_graph(seek_bar_data,seek_bar_name);
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
                set_changes_to_dataset(seek_bar_data,seek_bar_name);
                set_changese_to_saving_and_adjusted(totalEmission);
                generate_graph(seek_bar_data,seek_bar_name);
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
                set_changes_to_dataset(seek_bar_data,seek_bar_name);
                set_changese_to_saving_and_adjusted(totalEmission);
                generate_graph(seek_bar_data,seek_bar_name);

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });






        mNext_Activity.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v) {
                Intent intent = new Intent(ResultScreenFirst.this, ResultScreenSecond.class);
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



    public void generate_graph(ArrayList<Integer> seek_bar_data, ArrayList<String> seek_bar_name) {
        Random rnd = new Random();
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

    public void set_changes_to_dataset(ArrayList<Integer> seek_bar_data, ArrayList<String> seek_bar_name)
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

    public void set_changese_to_saving_and_adjusted(Integer totalEmission)
    {
        final Integer total_after_adjustment = (int) new ResultScreenFirstDataHandler(this).total_emission(seek_bar_name,seek_bar_data, foodData);

        Integer car_adjusted = total_after_adjustment*9/4;
        Integer radiator_adjusted = total_after_adjustment/2;

        mAdjustedCar.setText(Integer.toString(car_adjusted));
        mAdjustedRadiator.setText(Integer.toString(radiator_adjusted));
        Integer car_saved;
        Integer radiator_saved;
        car_saved = Integer.parseInt(mCurrentCar.getText().toString()) - car_adjusted;
        mSavedCar.setText(Integer.toString(car_saved));
        radiator_saved = Integer.parseInt(mCurrentRadiator.getText().toString())- radiator_adjusted;
        mSavedRadiator.setText(Integer.toString(radiator_saved));


    }

}