package com.t.teamten.greenfoodtracker.homescreenactivity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.t.teamten.greenfoodtracker.mealposts.MealFragment;
import com.t.teamten.greenfoodtracker.pledgeposts.PledgeFragment;

public class NewsPageAdapter extends FragmentPagerAdapter {
    private int numberOfTabs;

    public NewsPageAdapter(FragmentManager fragmentManager, int numberOfTabs) {
        super(fragmentManager);
        this.numberOfTabs = numberOfTabs;
    }

    @Override
    public Fragment getItem(int position) {
        if(position == 0) {
            return new MealFragment();
        } else if(position == 1) {
            return new PledgeFragment();
        } else {
            return null;
        }
    }

    @Override
    public int getCount() {
        return numberOfTabs;
    }
}