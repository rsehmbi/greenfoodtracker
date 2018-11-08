package com.t.teamten.greenfoodtracker.loginactivities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.t.teamten.greenfoodtracker.calcactivities.CalcActivity;
import com.t.teamten.greenfoodtracker.R;
import com.t.teamten.greenfoodtracker.pledgeposts.PledgePost;
import com.t.teamten.greenfoodtracker.pledgeposts.PledgeRecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

import firebaseuser.User;
import firebaseuser.settings;

import static com.t.teamten.greenfoodtracker.loginactivities.LoginUser.myprefs;

public class HomeScreen extends AppCompatActivity {
    private DatabaseReference reference;
    private List<PledgePost> posts;
    private RecyclerView recyclerView;
    private PledgeRecyclerViewAdapter adapter;
    private Spinner spinnerFilter;
    private Button buttonFilter;

    Button Signout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        recyclerView = (RecyclerView) findViewById(R.id.pledgeListView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(HomeScreen.this));

        reference = FirebaseDatabase.getInstance().getReference("users");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                posts = new ArrayList<>();
                for(DataSnapshot snapshot: dataSnapshot.getChildren()) {
                    User user = snapshot.getValue(User.class);
                    if(user.getPledge().equals("")) {

                    } else {
                        PledgePost post = new PledgePost(user.getFirstNameWithLastNameInitial(), user.getCity(), user.getPledge());
                        posts.add(post);
                    }

                }

                adapter = new PledgeRecyclerViewAdapter(HomeScreen.this, posts);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

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

        spinnerFilter = (Spinner) findViewById(R.id.spinnerFilter);
        buttonFilter = (Button) findViewById(R.id.buttonFilter);
        buttonFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String city = spinnerFilter.getSelectedItem().toString();
                List<PledgePost> filteredPosts = new ArrayList<>();
                filteredPosts = getFilterPosts(city);
                adapter = new PledgeRecyclerViewAdapter(HomeScreen.this, filteredPosts);
                recyclerView.setAdapter(adapter);
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

    public List<PledgePost> getFilterPosts(String city) {
        List<PledgePost> newPosts = new ArrayList<>();
        if(city.equals("Metro Vancouver")) {
            newPosts = posts;
            return newPosts;
        } else {
            for(PledgePost post: posts) {
                if(post.getLocation().equals(city)) {
                    newPosts.add(post);
                }
            }
            return newPosts;
        }
    }
}
