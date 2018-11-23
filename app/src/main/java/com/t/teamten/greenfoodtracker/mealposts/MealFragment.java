package com.t.teamten.greenfoodtracker.mealposts;


import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Spinner;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.t.teamten.greenfoodtracker.R;
import com.t.teamten.greenfoodtracker.homescreenactivity.HomeScreen;
import com.t.teamten.greenfoodtracker.pledgeposts.PledgePost;
import com.t.teamten.greenfoodtracker.pledgeposts.PledgeRecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

import firebaseuser.User;

/**
 * A simple {@link Fragment} subclass.
 */
public class MealFragment extends Fragment {
    private DatabaseReference reference;
    private FirebaseAuth authentication;
    private List<MealPost> posts;
    private RecyclerView recyclerView;
    private MealRecyclerViewAdapter adapter;
    private User user;
    private String userId;
    private Dialog filterDialog;
    private FloatingActionButton mealFloatingButton;

    private List<MealPost> newPosts;
    private Spinner citySpinner;
    private Spinner proteinSpinner;
    private Spinner withImageSpinner;
    private Button filterButton;
    private String city;
    private String protein;
    private String hasImage;

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
                posts = new ArrayList<>();
                for(DataSnapshot snapshot: dataSnapshot.getChildren()) {
                    MealPost post = snapshot.getValue(MealPost.class);
                    posts.add(post);
                }

                adapter = new MealRecyclerViewAdapter(getActivity(), posts, user);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        mealFloatingButton = (FloatingActionButton) view.findViewById(R.id.mealFloatingButton);
        mealFloatingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                filterDialog = new Dialog(getActivity());
                filterDialog.setContentView(R.layout.meal_filter);
                filterDialog.show();

                citySpinner = (Spinner) filterDialog.findViewById(R.id.citySpinner);
                proteinSpinner = (Spinner) filterDialog.findViewById(R.id.proteinSpinner);
                withImageSpinner = (Spinner) filterDialog.findViewById(R.id.withImageSpinner);

                filterButton = (Button) filterDialog.findViewById(R.id.filterMealButton);
                filterButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        city = citySpinner.getSelectedItem().toString();
                        protein = proteinSpinner.getSelectedItem().toString();
                        hasImage = withImageSpinner.getSelectedItem().toString();
                        newPosts = setNewMealPosts(city, protein, hasImage);

                        adapter = new MealRecyclerViewAdapter(getActivity(), newPosts, user);
                        recyclerView.setAdapter(adapter);
                        filterDialog.dismiss();
                    }
                });
            }
        });
    }

    private List<MealPost> setNewMealPosts(String location, String meat, String withImage) {
        List<MealPost> updatePost = new ArrayList<>();
        for(MealPost post: posts) {
            if(post.getRestaurantLocation().equals(location) && post.getMealProtein().equals(meat)) {
                if(withImage.equals("With Image")) {
                    if(post.getMealImageUrl().equals("")) {

                    } else {
                        updatePost.add(post);
                    }
                }

                if(withImage.equals("No Image")) {
                    if(post.getMealImageUrl().equals("")) {
                        updatePost.add(post);
                    } else {

                    }
                }

                if(withImage.equals("Both")) {
                    updatePost.add(post);
                }
            }
        }
        return updatePost;
    }

}
