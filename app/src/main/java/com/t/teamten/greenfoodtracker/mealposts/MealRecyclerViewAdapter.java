package com.t.teamten.greenfoodtracker.mealposts;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.t.teamten.greenfoodtracker.R;
import com.t.teamten.greenfoodtracker.profileicon.ProfileIconList;

import java.util.List;

public class MealRecyclerViewAdapter extends RecyclerView.Adapter<MealRecyclerViewAdapter.MealViewHolder> {
    private List<MealPost> posts;
    private Context context;
    private ProfileIconList iconList;

    public MealRecyclerViewAdapter(Context context, List<MealPost> posts) {
        this.context = context;
        this.posts = posts;
    }

    @NonNull
    @Override
    public MealViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.meal_post, null);
        MealViewHolder viewHolder = new MealViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MealViewHolder viewHolder, int i) {
        String profileImageName = posts.get(i).getProfileImage();
        viewHolder.setProfileImageView(profileImageName);

        String name = posts.get(i).getName();
        viewHolder.setNameText(name);

        String userCity = posts.get(i).getUserCity();
        viewHolder.setUserLocation(userCity);

        String meal = posts.get(i).getMealName();
        viewHolder.setMealText(meal);

        String protein = posts.get(i).getMealProtein();
        viewHolder.setProteinText(protein);

        String restaurantName = posts.get(i).getRestaurantName();
        viewHolder.setRestaurantNameText(restaurantName);

        String restaurantLocation = posts.get(i).getRestaurantLocation();
        viewHolder.setRestaurantLocationText(restaurantLocation);

        String imageUrl = posts.get(i).getMealImageUrl();
        if(imageUrl.equals("")) {
            viewHolder.setMealImageVisibility(false);
        } else {
            viewHolder.setMealImageVisibility(true);
            viewHolder.setMealImage(imageUrl);
        }

        String description = posts.get(i).getMealDescription();
        if(description.equals("")) {
            viewHolder.setMealDescriptionVisibility(false);
        } else {
            viewHolder.setMealDescriptionVisibility(true);
            viewHolder.setMealDescriptionText(description);
        }
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    public class MealViewHolder extends RecyclerView.ViewHolder {
        private ImageView profileImageView;
        private TextView nameText;
        private TextView userLocation;
        private TextView mealText;
        private TextView proteinText;
        private TextView restaurantNameText;
        private TextView restaurantLocationText;
        private ImageView mealImage;
        private TextView mealDescriptionText;


        public MealViewHolder(@NonNull View itemView) {
            super(itemView);

            profileImageView = itemView.findViewById(R.id.profilePic);
            nameText = itemView.findViewById(R.id.name);
            userLocation = itemView.findViewById(R.id.userLocation);
            mealText = itemView.findViewById(R.id.mealName);
            proteinText = itemView.findViewById(R.id.protein);
            restaurantNameText = itemView.findViewById(R.id.restaurantName);
            restaurantLocationText = itemView.findViewById(R.id.restaurantLocation);
            mealImage = itemView.findViewById(R.id.mealImage);
            mealDescriptionText = itemView.findViewById(R.id.mealDescription);
        }

        public void setProfileImageView(String profileImage) {
            int profileImageId = iconList.getDrawableId(profileImage);
            profileImageView.setImageResource(profileImageId);
        }

        public void setNameText(String name) {
            nameText.setText(name);
        }

        public void setUserLocation(String location) {
            userLocation.setText(location);
        }

        public void setMealText(String mealName) {
            mealText.setText(mealName);
        }

        public void setProteinText(String protein) {
            proteinText.setText(protein);
        }

        public void setRestaurantNameText(String restaurantName) {
            restaurantNameText.setText(restaurantName);
        }

        public void setRestaurantLocationText(String restaurantLocation) {
            restaurantLocationText.setText(restaurantLocation);
        }

        public void setMealImage(String imageUrl) {
            Picasso.with(context).load(imageUrl).into(mealImage);
            /////check context when error occurs
        }

        public void setMealDescriptionText(String mealDescription) {
            mealDescriptionText.setText(mealDescription);
        }

        public void setMealImageVisibility(boolean isVisible) {
            if(isVisible) {
                mealImage.setVisibility(View.VISIBLE);
            } else {
                mealImage.setVisibility(View.GONE);
            }
        }

        public void setMealDescriptionVisibility(boolean isVisible) {
            if(isVisible) {
                mealDescriptionText.setVisibility(View.VISIBLE);
            } else {
                mealDescriptionText.setVisibility(View.GONE);
            }
        }
    }
}
