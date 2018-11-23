package com.t.teamten.greenfoodtracker.mealposts;

import android.content.Context;
import android.nfc.Tag;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;
import com.t.teamten.greenfoodtracker.profileicon.ProfileIconList;
import com.t.teamten.greenfoodtracker.R;

import java.util.List;

import firebaseuser.User;

public class MealRecyclerViewAdapter extends RecyclerView.Adapter<MealRecyclerViewAdapter.MealViewHolder> {
    private List<MealPost> posts;
    private Context context;
    private ProfileIconList iconList;
    private User user;

    public MealRecyclerViewAdapter(Context context, List<MealPost> posts, User user) {
        this.context = context;
        this.posts = posts;
        this.user = user;
        iconList = new ProfileIconList(context);
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
        String profileImageName = user.getProfileIcon();
        viewHolder.setProfileImageView(profileImageName);

        String name = user.getFirstNameWithLastNameInitial();
        viewHolder.setNameText(name);

        String userCity = user.getCity();
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

        String userId = posts.get(i).getUserId();
        FirebaseAuth auth = FirebaseAuth.getInstance();
        String currentUserId = auth.getCurrentUser().getUid();
        if(currentUserId.equals(userId)) {
            viewHolder.setDeleteImageViewVisibility(true);
            String postId = posts.get(i).getPostId();
            viewHolder.setDeleteImageView(postId, imageUrl);
        } else {
            viewHolder.setDeleteImageViewVisibility(false);
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
        private ImageView deleteImageView;


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
            deleteImageView = itemView.findViewById(R.id.deleteImageView);
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
            Picasso.get()
                    .load(imageUrl)
                    .resize(600, 500)
                    .centerCrop()
                    .into(mealImage);
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

        public void setDeleteImageViewVisibility(boolean isVisible) {
            if(isVisible) {
                deleteImageView.setVisibility(View.VISIBLE);
            } else {
                deleteImageView.setVisibility(View.GONE);
            }
        }

        public void setDeleteImageView(String id, String url) {
            final String post_Id = id;
            final String imageUrl = url;
            deleteImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DatabaseReference reference = FirebaseDatabase.getInstance().getReference("mealposts");
                    reference.child(post_Id).removeValue();
                    deleteImageFromFirebase(imageUrl);
                }
            });
        }

        public void deleteImageFromFirebase(String url) {
            if(url.equals("")) {

            } else {
                FirebaseStorage storage = FirebaseStorage.getInstance();
                StorageReference storageReference = storage.getReferenceFromUrl(url);
                storageReference.delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d("Tag", "File deleted from Fire Storage");
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d("Tag", "File was not delete from Fire Storage");
                    }
                });

            }
        }
    }
}
