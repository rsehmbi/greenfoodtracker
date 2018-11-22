package com.t.teamten.greenfoodtracker.homescreenactivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.t.teamten.greenfoodtracker.ManageAccount;
import com.t.teamten.greenfoodtracker.R;
import com.t.teamten.greenfoodtracker.calcactivities.CalcActivity;
import com.t.teamten.greenfoodtracker.loginactivities.AboutActivity;
import com.t.teamten.greenfoodtracker.loginactivities.FactsActivity;
import com.t.teamten.greenfoodtracker.loginactivities.FirebaseLogin;
import com.t.teamten.greenfoodtracker.profileicon.ProfileIconList;
import com.t.teamten.greenfoodtracker.resultscreenactivities.SocialMediaActivity;

import java.util.Objects;

import firebaseuser.Realtime_Pledge_Data;
import firebaseuser.User;

public class DrawerFromSide extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private NewsPageAdapter pagerAdapter;
    private FloatingActionButton postButton;
    private String user_id;
    private ProfileIconList iconList;
    private User userInfo;
    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    TextView textToShow;
    TextView email;
    ImageView icon;


    private Dialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawerfromside);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("users");
        user_id = Objects.requireNonNull(firebaseAuth.getCurrentUser()).getUid();
        userInfo =  new User();

        iconList = new ProfileIconList(this);
        dialog = new Dialog(this);

        postButton = findViewById(R.id.postButton);
        postButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.setContentView(R.layout.activity_add_meal_acitivty);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        View headerView = navigationView.getHeaderView(0);
        textToShow = headerView.findViewById(R.id.nametoshow);
        email = headerView.findViewById(R.id.emailofuser);
        icon = headerView.findViewById(R.id.imageicon);
        displayName();

        viewPager = findViewById(R.id.viewPager2);
        tabLayout = findViewById(R.id.tablayout2);
        tabLayout.setupWithViewPager(viewPager);

        pagerAdapter = new NewsPageAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(pagerAdapter);

        tabLayout.getTabAt(0).setText(R.string.meals);
        tabLayout.getTabAt(1).setText(R.string.pledges);
    }

    private void displayName() {
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot ds:dataSnapshot.getChildren()) {

                    User user = ds.getValue(User.class);
                    if (user.getUserId().equals(user_id)) {
                        userInfo = user;
                        textToShow.setText(user.getFirstName()+" "+user.getLastName());
                        email.setText(user.getEmail());
                        icon.setImageResource(iconList.getDrawableId(user.getProfileIcon()));
                    }
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.drawerfromside, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.AboutheuserID) {
            Intent movetoaboutpage = new Intent(DrawerFromSide.this, AboutActivity.class);
            startActivity(movetoaboutpage);
        } else if (id == R.id.SignoutuserID) {
            FirebaseAuth.getInstance().signOut();
            Intent movetologin = new Intent(DrawerFromSide.this, FirebaseLogin.class);
            startActivity(movetologin);
            finish();

        } else if (id == R.id.DeleteUserID) {
            AlertDialog.Builder builder= new AlertDialog.Builder(DrawerFromSide.this);
            builder.setMessage("Are you sure to delete your account ?").setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    FirebaseUser currentuser = FirebaseAuth.getInstance().getCurrentUser();
                    currentuser.delete()
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(DrawerFromSide.this, "Userdeleted", Toast.LENGTH_LONG).show();
                                        Intent movetoLoginScreen = new Intent(DrawerFromSide.this, FirebaseLogin.class);
                                        startActivity(movetoLoginScreen);
                                        finish();
                                    }
                                }
                            });

                }
            }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });
            AlertDialog alert =builder.create();
            alert.setTitle("Alert !!");
            alert.show();

        } else if (id == R.id.manageuserID) {
            Intent moveToManageAccount = new Intent (DrawerFromSide.this, ManageAccount.class);
            startActivity(moveToManageAccount);
        } else if (id == R.id.HomeID)
        {
            Toast.makeText(DrawerFromSide.this, "This is Home Page ", Toast.LENGTH_LONG).show();
        } else if (id == R.id.PledgeID) {
            Intent moveToPledgePage = new Intent (DrawerFromSide.this, Realtime_Pledge_Data.class);
            startActivity(moveToPledgePage);
        }else if (id == R.id.CalculatorID) {
            Intent moveToCalc = new Intent(DrawerFromSide.this, CalcActivity.class);
            startActivity(moveToCalc);
        }else if (id == R.id.factsscreenID) {
            Intent moveToFacts = new Intent(DrawerFromSide.this,FactsActivity.class);
            startActivity(moveToFacts);
        }else if (id == R.id.nav_share) {
            Intent moveToSocialMedia = new Intent(DrawerFromSide.this, SocialMediaActivity.class);
            startActivity(moveToSocialMedia);
        } else if (id == R.id.nav_send) {

        }
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
