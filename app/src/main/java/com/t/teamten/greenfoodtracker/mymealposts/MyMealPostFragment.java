package com.t.teamten.greenfoodtracker.mymealposts;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.t.teamten.greenfoodtracker.R;
import com.t.teamten.greenfoodtracker.mealposts.MealPost;
import com.t.teamten.greenfoodtracker.mealposts.MealRecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

import firebaseuser.User;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyMealPostFragment extends Fragment {
    private DatabaseReference reference;
    private FirebaseAuth authentication;
    private List<MealPost> myPosts;
    private RecyclerView recyclerView;
    private MealRecyclerViewAdapter adapter;
    private User user;
    private String userId;

    public MyMealPostFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_my_meal_post, container, false);
    }

    @Override
    public void onViewCreated(View view, @NonNull Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = (RecyclerView) view.findViewById(R.id.myMealListView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        authentication = FirebaseAuth.getInstance();
        userId = authentication.getCurrentUser().getUid();

        reference = FirebaseDatabase.getInstance().getReference("users");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                user = dataSnapshot.child(userId).getValue(User.class);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        reference = FirebaseDatabase.getInstance().getReference("mealposts");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                myPosts = new ArrayList<>();
                for(DataSnapshot snapshot: dataSnapshot.getChildren()) {
                    MealPost post = snapshot.getValue(MealPost.class);
                    if(post.getUserId().equals(userId)) {
                        myPosts.add(post);
                    }

                }

                adapter = new MealRecyclerViewAdapter(getActivity(), myPosts, user);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
