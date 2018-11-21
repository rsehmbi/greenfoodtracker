package com.t.teamten.GreenFoodTracker.Pledge_Posts;


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

import com.t.teamten.GreenFoodTracker.Firebase_User.User;

/**
 * A simple {@link Fragment} subclass.
 */
public class Pledge_Fragment_Activity extends Fragment {
    private DatabaseReference reference;
    private List<Pledge_Post> posts;
    private RecyclerView recyclerView;
    private Pledge_Recycler_View_Adapter adapter;

    public Pledge_Fragment_Activity() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pledge, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @NonNull Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = (RecyclerView) view.findViewById(R.id.pledgeListView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        reference = FirebaseDatabase.getInstance().getReference("users");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                posts = new ArrayList<>();
                for(DataSnapshot snapshot: dataSnapshot.getChildren()) {
                    User user = snapshot.getValue(User.class);
                    if(user.getPledge().equals("")) {

                    } else {
                        Pledge_Post post = new Pledge_Post(user.getFirstNameWithLastNameInitial(), user.getCity(), user.getPledge(), user.getProfileIcon());
                        posts.add(post);
                    }

                }

                adapter = new Pledge_Recycler_View_Adapter(getActivity(), posts);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
