package com.t.teamten.greenfoodtracker.Pledge;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Pair;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.t.teamten.greenfoodtracker.R;

import java.util.ArrayList;

public class PledgePage extends AppCompatActivity
{
    private FirebaseDatabase firebaseDatabase;
    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener authStateListener;
    private DatabaseReference databaseReference1;
    private DatabaseReference databaseReference2;
    private DatabaseReference databaseReference3;
    private ArrayList<Pair<String,Integer>> user_initial_and_pledge;
    private ListView listView;
    private Spinner city_spinner;
    private ArrayAdapter<Pair<String,Integer>> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pledge);

        databaseReference1 = FirebaseDatabase.getInstance().getReference();
        //databaseReference2 = FirebaseDatabase.getInstance().getReference().child("lastName");
        //databaseReference3 = FirebaseDatabase.getInstance().getReference().child("pledge");
        adapter = new ArrayAdapter<Pair<String, Integer>>(this,android.R.layout.simple_list_item_1, user_initial_and_pledge);
        listView = findViewById(R.id.pledge_list);

    }
}
