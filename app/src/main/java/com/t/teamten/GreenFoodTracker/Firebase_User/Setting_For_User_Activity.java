package com.t.teamten.GreenFoodTracker.Firebase_User;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.t.teamten.GreenFoodTracker.Calculator.Calculator_Activity;
import com.t.teamten.GreenFoodTracker.Homescreen.HomeScreen;
import com.t.teamten.GreenFoodTracker.Login_And_Registration.Facts_Activity;
import com.t.teamten.GreenFoodTracker.Login_And_Registration.Firebase_User_Login_Activity;
import com.t.teamten.GreenFoodTracker.Login_And_Registration.About_Activity;
import com.t.teamten.GreenFoodTracker.R;

//setting:about,manage account, delete user...
public class Setting_For_User_Activity extends AppCompatActivity {
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
                        Toast.makeText(Setting_For_User_Activity.this,"Calculator",Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(Setting_For_User_Activity.this,Calculator_Activity.class);
                        startActivity(intent);
                        break;
                    case R.id.Facts:
                        Toast.makeText(Setting_For_User_Activity.this,"Facts",Toast.LENGTH_SHORT).show();
                        Intent intent3 = new Intent(Setting_For_User_Activity.this,Facts_Activity.class);
                        startActivity(intent3);
                        break;
                    case R.id.About:
                        Toast.makeText(Setting_For_User_Activity.this,"Settings",Toast.LENGTH_SHORT).show();
                        Intent intent2 = new Intent(Setting_For_User_Activity.this, Setting_For_User_Activity.class);
                        startActivity(intent2);
                        break;
                    case R.id.Pledge:
                        Toast.makeText(Setting_For_User_Activity.this,"Pledge",Toast.LENGTH_SHORT).show();
                        Intent movetoPledge = new Intent(Setting_For_User_Activity.this, User_Profile_Activity.class);
                        startActivity(movetoPledge);
                        break;
                    case R.id.Newsfeed:
                        Toast.makeText(Setting_For_User_Activity.this,"HomeScreen",Toast.LENGTH_SHORT).show();
                        Intent movetoHomeScreen = new Intent (Setting_For_User_Activity.this,HomeScreen.class);
                        startActivity(movetoHomeScreen);
                        break;
                }
                return true;
            }
        });


    }
    public void movetoaboutactivity(View view) {
        Intent movetoaboutactivity = new Intent(Setting_For_User_Activity.this,About_Activity.class);
        startActivity(movetoaboutactivity);
    }

    public void signoutprofile(View view) {
        FirebaseAuth.getInstance().signOut();
        Intent movetologin = new Intent(Setting_For_User_Activity.this, Firebase_User_Login_Activity.class);
        startActivity(movetologin);
        finish();
    }

    public void deletetheuseraccount(View view) {
        FirebaseUser currentuser = FirebaseAuth.getInstance().getCurrentUser();
        currentuser.delete()
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(Setting_For_User_Activity.this, "Userdeleted", Toast.LENGTH_LONG).show();
                            Intent movetoLoginScreen = new Intent(Setting_For_User_Activity.this, Firebase_User_Login_Activity.class);
                            startActivity(movetoLoginScreen);
                        }
                    }
                });
    }

    public void manageaccount(View view) {
        Intent movetomanageactivity = new Intent(this, Manage_Account_Activity.class);
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
                    Intent i = new Intent(Setting_For_User_Activity.this,Facts_Activity.class);
                    startActivity(i);
                }else if(x1 > x2){

                }
                break;
        }
        return false;
    }

}
