package com.t.teamten.greenfoodtracker.pledgeposts;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.t.teamten.greenfoodtracker.R;

import java.util.List;

public class PledgeRecyclerViewAdapter extends RecyclerView.Adapter<PledgeRecyclerViewAdapter.PledgeViewHolder> {
    private List<PledgePost> posts;
    private Context context;
    private ProfileIconList iconList;

    public PledgeRecyclerViewAdapter(Context context, List<PledgePost> posts) {
        this.context = context;
        this.posts = posts;
        iconList = new ProfileIconList(context);
    }



    @NonNull
    @Override
    public PledgeViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.pledge_post, null);
        PledgeViewHolder viewHolder = new PledgeViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull PledgeViewHolder viewHolder, int i) {
        //viewHolder.setIsRecyclable(false);
        String profileImageName = posts.get(i).getProfileIconName();
        viewHolder.setProfileImageView(profileImageName);

        String name = posts.get(i).getName();
        viewHolder.setNameTextView(name);

        String location = posts.get(i).getLocation();
        viewHolder.setLocationTextView(location);

        String pledge = posts.get(i).getPledge();
        viewHolder.setPledgeTextView(pledge);
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    public class PledgeViewHolder extends RecyclerView.ViewHolder {
        private ImageView profileImageView;
        private TextView nameTextView;
        private TextView locationTextView;
        private TextView pledgeTextView;
        private LinearLayout parentLayout;

        public PledgeViewHolder(@NonNull View itemView) {
            super(itemView);

            profileImageView = itemView.findViewById(R.id.profileView);
            nameTextView = itemView.findViewById(R.id.nameTextView);
            locationTextView = itemView.findViewById(R.id.locationTextView);
            pledgeTextView = itemView.findViewById(R.id.pledge_TextView);
        }

        public void setProfileImageView(String profileImageName) {
            int profileImageId = iconList.getDrawableId(profileImageName);
            //Log.e("tag", profileImageName + " " + Integer.toString(profileImageId));
            profileImageView.setImageResource(profileImageId);
        }

        public void setNameTextView(String name) {
            nameTextView.setText(name);
        }

        public void setLocationTextView(String location) {
            locationTextView.setText(location);
        }

        public void setPledgeTextView(String pledge) {
            if(pledge.isEmpty()) {
                pledgeTextView.setText("No pledge Yet");
            } else {
                pledgeTextView.setText(pledge);
            }

        }
    }
}
