package com.t.teamten.greenfoodtracker.mealposts;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class MealPostTest {
    MealPost post;

    @Before
    public void setUp() throws Exception {
        post = new MealPost("mealname", "cat", "eat",
                "Burnaby", "testurl", "descriptive", "userID", "postID");
    }

    @Test
    public void getMealName() {
        assertEquals("mealname", post.getMealName());
    }

    @Test
    public void getMealProtein() {
        assertEquals("cat", post.getMealProtein());
    }

    @Test
    public void getRestaurantName() {
        assertEquals("eat", post.getRestaurantName());
    }

    @Test
    public void getRestaurantLocation() {
        assertEquals("Burnaby", post.getRestaurantLocation());
    }

    @Test
    public void getMealImageUrl() {
        assertEquals("testurl", post.getMealImageUrl());
    }

    @Test
    public void getMealDescription() {
        assertEquals("descriptive", post.getMealDescription());
    }

    @Test
    public void getUserId() {
        assertEquals("userID", post.getUserId());
    }

    @Test
    public void getPostId() {
        assertEquals("postID", post.getPostId());
    }

    @Test
    public void setMealName() {
        post.setMealName("testName");
        assertEquals("testName", post.getMealName());
    }

    @Test
    public void setMealProtein() {
        post.setMealProtein("goat");
        assertEquals("goat", post.getMealProtein());
    }

    @Test
    public void setRestaurantName() {
        post.setRestaurantName("---");
        assertEquals("---", post.getRestaurantName());
    }

    @Test
    public void setRestaurantLocation() {
        post.setRestaurantLocation("Surrey");
        assertEquals("Surrey", post.getRestaurantLocation());
    }

    @Test
    public void setMealImageUrl() {
        post.setMealImageUrl("/a url");
        assertEquals("/a url", post.getMealImageUrl());
    }

    @Test
    public void setMealDescription() {
        post.setMealDescription("a description");
        assertEquals("a description", post.getMealDescription());
    }

    @Test
    public void setUserId() {
        post.setUserId("123456");
        assertEquals("123456", post.getUserId());
    }

    @Test
    public void setPostId() {
        post.setPostId("123456");
        assertEquals("123456", post.getPostId());
    }
}