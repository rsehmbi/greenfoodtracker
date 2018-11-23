package com.t.teamten.greenfoodtracker;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.t.teamten.greenfoodtracker.calcactivities.CalcActivity;
import com.t.teamten.greenfoodtracker.homescreenactivity.HomeScreen;
import com.t.teamten.greenfoodtracker.loginactivities.AboutActivity;
import com.t.teamten.greenfoodtracker.loginactivities.FactsActivity;
import com.t.teamten.greenfoodtracker.loginactivities.FirebaseLogin;

import firebaseuser.Realtime_Pledge_Data;

//setting:about,manage account, delete user...
public class SettingsForUser extends AppCompatActivity {
    FirebaseAuth mAuth;
    float x1,x2,y1,y2;
    //Setting page so that the user can Signout or delete his account or he can manage his account.
    // Settings also has about to the source of information the user is using.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settingsforuser);
        mAuth = FirebaseAuth.getInstance();

        BottomNavigationView bottomNavigationView = (BottomNavigationView)findViewById(R.id.navigation_view);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId())
                {
                    case R.id.Calculator:
                        Toast.makeText(SettingsForUser.this,"Calculator",Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(SettingsForUser.this,CalcActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.Facts:
                        Toast.makeText(SettingsForUser.this,"Facts",Toast.LENGTH_SHORT).show();
                        Intent intent3 = new Intent(SettingsForUser.this,FactsActivity.class);
                        startActivity(intent3);
                        break;
                    case R.id.About:
                        Toast.makeText(SettingsForUser.this,"Settings",Toast.LENGTH_SHORT).show();
                        Intent intent2 = new Intent(SettingsForUser.this,SettingsForUser.class);
                        startActivity(intent2);
                        break;
                    case R.id.Pledge:
                        Toast.makeText(SettingsForUser.this,"Pledge",Toast.LENGTH_SHORT).show();
                        Intent movetoPledge = new Intent(SettingsForUser.this, Realtime_Pledge_Data.class);
                        startActivity(movetoPledge);
                        break;
                    case R.id.Newsfeed:
                        Toast.makeText(SettingsForUser.this,"HomeScreen",Toast.LENGTH_SHORT).show();
                        Intent movetoHomeScreen = new Intent (SettingsForUser.this,HomeScreen.class);
                        startActivity(movetoHomeScreen);
                        break;
                }
                return true;
            }
        });


    }
    public void moveToAboutActivity(View view) {
        Intent toAboutActivity = new Intent(SettingsForUser.this,AboutActivity.class);
        startActivity(toAboutActivity);
    }

    public void signoutProfile(View view) {
        FirebaseAuth.getInstance().signOut();
        Intent movetologin = new Intent(SettingsForUser.this, FirebaseLogin.class);
        startActivity(movetologin);
        finish();
    }

    public void deleteUserAccount(View view) {
        FirebaseUser currentuser = FirebaseAuth.getInstance().getCurrentUser();
        currentuser.delete()
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(SettingsForUser.this, "Userdeleted", Toast.LENGTH_LONG).show();
                            Intent movetoLoginScreen = new Intent(SettingsForUser.this, FirebaseLogin.class);
                            startActivity(movetoLoginScreen);
                        }
                    }
                });
    }

    public void manageAccount(View view) {
        Intent movetomanageactivity = new Intent(this, ManageAccount.class);
        startActivity(movetomanageactivity);
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
                    Intent i = new Intent(SettingsForUser.this,FactsActivity.class);
                    startActivity(i);
                }else if(x1 > x2){

                }
                break;
        }
        return false;
    }

}
