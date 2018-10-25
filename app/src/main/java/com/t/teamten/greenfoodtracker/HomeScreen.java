package com.t.teamten.greenfoodtracker;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

public class HomeScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        BottomNavigationView bottomNavigationView = (BottomNavigationView)findViewById(R.id.navigation_view);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId())
                {
                    case R.id.Calculator:
                        Toast.makeText(HomeScreen.this,"Calculator",Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(HomeScreen.this,MainActivity.class);
                         startActivity(intent);
                        break;
                    case R.id.Facts:
                        Toast.makeText(HomeScreen.this,"Facts",Toast.LENGTH_SHORT).show();
                        Intent intent3 = new Intent(HomeScreen.this,FactsActivity.class);
                        startActivity(intent3);
                        break;
                    case R.id.About:
                        Toast.makeText(HomeScreen.this,"About",Toast.LENGTH_SHORT).show();
                        Intent intent2 = new Intent(HomeScreen.this,aboutactivity.class);
                        startActivity(intent2);
                        break;
                }
                return true;
            }
        });
    }
}
