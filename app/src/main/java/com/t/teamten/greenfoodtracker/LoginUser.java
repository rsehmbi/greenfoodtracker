package com.t.teamten.greenfoodtracker;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class LoginUser extends AppCompatActivity {

    private int User;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_user);
        User = 5;
    }
}
