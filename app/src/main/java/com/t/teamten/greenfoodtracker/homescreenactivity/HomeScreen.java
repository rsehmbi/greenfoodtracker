package com.t.teamten.greenfoodtracker.homescreenactivity;

import android.app.Dialog;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.t.teamten.greenfoodtracker.R;
import com.t.teamten.greenfoodtracker.mealposts.AddMealFragment;

//HomeScreen contains the bottom navigation view and News feed to show the Data of other users.
public class HomeScreen extends AppCompatActivity {
    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private NewsPagerAdapter pagerAdapter;
    private BottomNavigationView bottomNavigationView;
    private FloatingActionButton postButton;
    private Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("News Feed");
        setSupportActionBar(toolbar); ///

        tabLayout = findViewById(R.id.tablayout);

        pagerAdapter = new NewsPagerAdapter(getSupportFragmentManager(), tabLayout.getTabCount());

        viewPager = findViewById(R.id.viewPager);
        tabLayout.setupWithViewPager(viewPager);
        viewPager.setAdapter(pagerAdapter);

        tabLayout.getTabAt(0).setText(R.string.meals);
        tabLayout.getTabAt(1).setText(R.string.pledges);
        tabLayout.getTabAt(2).setText(R.string.my_meals);

        postButton = (FloatingActionButton) findViewById(R.id.postButton);
        postButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager manager = getSupportFragmentManager();
                AddMealFragment mealFragment = AddMealFragment.newInstanceFragment("Post");
                mealFragment.show(manager, "fragment_add_meal");
            }
        });



    }
}
