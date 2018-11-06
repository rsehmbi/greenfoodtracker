package com.t.teamten.greenfoodtracker.loginactivities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.t.teamten.greenfoodtracker.calcactivities.CalcActivity;
import com.t.teamten.greenfoodtracker.R;
import com.t.teamten.greenfoodtracker.settings;

import static com.t.teamten.greenfoodtracker.loginactivities.LoginUser.myprefs;

public class HomeScreen extends AppCompatActivity {
    Button Signout;
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
                        Intent intent = new Intent(HomeScreen.this,CalcActivity.class);
                         startActivity(intent);
                        break;
                    case R.id.Facts:
                        Toast.makeText(HomeScreen.this,"Facts",Toast.LENGTH_SHORT).show();
                        Intent intent3 = new Intent(HomeScreen.this,FactsActivity.class);
                        startActivity(intent3);
                        break;
                    case R.id.About:
                        Toast.makeText(HomeScreen.this,"About",Toast.LENGTH_SHORT).show();
                        Intent intent2 = new Intent(HomeScreen.this,settings.class);
                        startActivity(intent2);
                        break;
                    case R.id.Pledge:
                        Toast.makeText(HomeScreen.this,"Pledge",Toast.LENGTH_SHORT).show();
                        Intent movetoPledge = new Intent(HomeScreen.this,FirebaseLogin.class);
                        startActivity(movetoPledge);
                }
                return true;
            }
        });

        Signout=(Button) findViewById(R.id.Signoutbutton);
        Signout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor edit = myprefs.edit();
                edit.clear();
                edit.commit();

                Intent gotobackscreen = new Intent(getApplicationContext(),LoginUser.class);
                startActivity(gotobackscreen);

            }
        });
    }
}
