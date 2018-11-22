package com.t.teamten.greenfoodtracker.mealposts;

import firebaseuser.User;

public class MealPost {
    private String mealName;
    private String mealProtein;
    private String restaurantName;
    private String restaurantLocation;
    private String mealImageUrl;
    private String mealDescription;
    private String userId;
    private String postId;

    public MealPost() {

    }

    public MealPost(String mealName, String mealProtein, String restaurantName,
                    String restaurantLocation, String mealImageUrl, String mealDescription,
                    String userId, String postId) {
        this.mealName = mealName;
        this.mealProtein = mealProtein;
        this.restaurantName = restaurantName;
        this.restaurantLocation = restaurantLocation;
        this.mealImageUrl = mealImageUrl;
        this.mealDescription = mealDescription;
        this.userId = userId;
        this.postId = postId;
    }

    public String getMealName() {
        return mealName;
    }

    public String getMealProtein() {
        return mealProtein;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public String getRestaurantLocation() {
        return restaurantLocation;
    }

    public String getMealImageUrl() {
        return mealImageUrl;
    }

    public String getMealDescription() {
        return mealDescription;
    }

    public String getUserId() {
        return userId;
    }

    public String getPostId() {
        return postId;
    }

    public void setMealName(String mealName) {
        this.mealName = mealName;
    }

    public void setMealProtein(String mealProtein) {
        this.mealProtein = mealProtein;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public void setRestaurantLocation(String restaurantLocation) {
        this.restaurantLocation = restaurantLocation;
    }

    public void setMealImageUrl(String mealImageUrl) {
        this.mealImageUrl = mealImageUrl;
    }

    public void setMealDescription(String mealDescription) {
        this.mealDescription = mealDescription;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }
}
