package com.t.teamten.greenfoodtracker.mealposts;

public class Meal {
    private String mealName;
    private String mealLocation;
    private String restaurantName;
    private String mealProtein;
    private String mealDescription;

    Meal(String mealName, String mealLocation, String restaurantName, String mealProtein, String mealDescription) {
        this.mealName = mealName;
        this.mealLocation = mealLocation;
        this.mealDescription = mealDescription;
        this.restaurantName = restaurantName;
        this.mealProtein = mealProtein;
    }

    public String getMealName() {
        return this.mealName;
    }

    public String getRestaurantName() {
        return this.restaurantName;
    }

    public String getMealLocation() {
        return this.mealLocation;
    }

    public String getMealDescription() {
        return this.mealDescription;
    }

    public String getMealProtein() {
        return this.mealProtein;
    }
}
