package com.t.teamten.greenfoodtracker.mealposts;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class MealTest {
    Meal meal;
    @Before
    public void setup() {
        meal = new Meal("burger", "burnaby", "mcdonalds", "cow", "cheap");
    }

    @Test
    public void getMealName() {
        assertEquals("burger", meal.getMealName());
    }

    @Test
    public void getRestaurantName() {
        assertEquals("mcdonalds", meal.getRestaurantName());
    }

    @Test
    public void getMealLocation() {
        assertEquals("burnaby", meal.getMealLocation());
    }

    @Test
    public void getMealDescription() {
        assertEquals("cheap", meal.getMealDescription());
    }

    @Test
    public void getMealProtein() {
        assertEquals("cow", meal.getMealProtein());
    }
}