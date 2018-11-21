package com.t.teamten.GreenFoodTracker.Meal_Posts;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.t.teamten.GreenFoodTracker.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class MealFragment extends Fragment {
    private DatabaseReference reference;
    private List<MealPost> posts;
    private RecyclerView recyclerView;
    private MealRecyclerViewAdapter adapter;

    public MealFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_meal, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @NonNull Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = (RecyclerView) view.findViewById(R.id.mealListView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        reference = FirebaseDatabase.getInstance().getReference("meals");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                posts = new ArrayList<>();
                for(DataSnapshot snapshot: dataSnapshot.getChildren()) {
                    MealPost post = snapshot.getValue(MealPost.class);
                    posts.add(post);
                }

                adapter = new MealRecyclerViewAdapter(getActivity(), posts);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

}
