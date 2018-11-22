package com.t.teamten.greenfoodtracker.homescreenactivity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabItem;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.t.teamten.greenfoodtracker.R;
import com.t.teamten.greenfoodtracker.SettingsForUser;
import com.t.teamten.greenfoodtracker.calcactivities.CalcActivity;
import com.t.teamten.greenfoodtracker.loginactivities.FactsActivity;
import com.t.teamten.greenfoodtracker.mealposts.AddMealFragment;

import firebaseuser.User_Profile;

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
        //setSupportActionBar(toolbar); ///

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

        bottomNavigationView = (BottomNavigationView)findViewById(R.id.navigation_view);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId())
                {
                    case R.id.Calculator:
                        Toast.makeText(HomeScreen.this,"Calculator",Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(HomeScreen.this,CalcActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.Facts:
                        Toast.makeText(HomeScreen.this,"Facts",Toast.LENGTH_SHORT).show();
                        Intent intent3 = new Intent(HomeScreen.this,FactsActivity.class);
                        startActivity(intent3);
                        break;
                    case R.id.About:
                        Toast.makeText(HomeScreen.this,"Settings",Toast.LENGTH_SHORT).show();
                        Intent intent2 = new Intent(HomeScreen.this,SettingsForUser.class);
                        startActivity(intent2);
                        break;
                    case R.id.Pledge:
                        Toast.makeText(HomeScreen.this,"Pledge",Toast.LENGTH_SHORT).show();
                        Intent movetoPledge = new Intent(HomeScreen.this, User_Profile.class);
                        startActivity(movetoPledge);
                }
                return true;
            }
        });


    }
}
