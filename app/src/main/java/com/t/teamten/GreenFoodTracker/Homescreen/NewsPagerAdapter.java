package com.t.teamten.GreenFoodTracker.Homescreen;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.t.teamten.GreenFoodTracker.Meal_Posts.MealFragment;
import com.t.teamten.GreenFoodTracker.Pledge_Posts.Pledge_Fragment_Activity;

public class NewsPagerAdapter extends FragmentPagerAdapter {
    private int numberOfTabs;

    public NewsPagerAdapter(FragmentManager fragmentManager, int numberOfTabs) {
        super(fragmentManager);
        this.numberOfTabs = numberOfTabs;
    }

    @Override
    public Fragment getItem(int position) {
        if(position == 0) {
            return new MealFragment();
        } else if(position == 1) {
            return new Pledge_Fragment_Activity();
        } else {
            return null;
        }
    }

    @Override
    public int getCount() {
        return numberOfTabs;
    }
}
