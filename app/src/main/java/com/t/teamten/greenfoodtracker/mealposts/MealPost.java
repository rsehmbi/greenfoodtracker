package com.t.teamten.greenfoodtracker.mealposts;

import firebaseuser.User;

public class MealPost {
    private String name;
    private String profileImage;
    private String userCity;
    private String mealName;
    private String mealProtein;
    private String restaurantName;
    private String restaurantLocation;
    private String mealImageUrl;
    private String mealDescription;
    private String userId;

    public MealPost() {

    }

    public MealPost(User user, String mealName, String mealProtein, String restaurantName,
                    String restaurantLocation, String mealImageUrl, String mealDescription) {
        this.name = user.getFirstNameWithLastNameInitial();
        this.profileImage = user.getProfileIcon();
        this.userCity = user.getCity();
        this.mealName = mealName;
        this.mealProtein = mealProtein;
        this.restaurantName = restaurantName;
        this.restaurantLocation = restaurantLocation;
        this.mealImageUrl = mealImageUrl;
        this.mealDescription = mealDescription;
        this.userId = user.getUserId();
    }

    public String getName() {
        return name;
    }

    public String getProfileImage() {
        return profileImage;
    }

    public String getUserCity() {
        return userCity;
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

    public void setName(String name) {
        this.name = name;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }

    public void setUserCity(String userCity) {
        this.userCity = userCity;
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
}
