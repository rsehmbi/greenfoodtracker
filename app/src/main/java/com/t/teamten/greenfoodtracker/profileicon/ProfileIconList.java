package com.t.teamten.greenfoodtracker.profileicon;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.util.Log;

import com.t.teamten.greenfoodtracker.R;

import java.util.ArrayList;
import java.util.List;

public class ProfileIconList {
    private Context context;
    private List<ProfileIcon> icons;
    private Resources resources;
    int[] imageIcons;
    String[] imageNames;
    int imageIconId;
    int imageNamesId;

    public ProfileIconList(Context context) {
        this.context = context;
        resources = context.getResources();
        imageIconId = R.array.icon_Array;
        imageNamesId = R.array.icon_Name_Array;

        imageNames = resources.getStringArray(imageNamesId);

        TypedArray typedArray = resources.obtainTypedArray(R.array.icon_Array);
        int len = typedArray.length();
        imageIcons = new int[len];

        for(int i = 0; i < len; i++) {
            imageIcons[i] = typedArray.getResourceId(i, 0);
        }

        icons = new ArrayList<>();

        for(int i = 0; i < imageIcons.length; i++) {
            String imageName = imageNames[i];
            int imageIcon = imageIcons[i];
            ProfileIcon icon = new ProfileIcon(imageName, imageIcon);
            Log.e("tag", imageName + " " + imageIcon);
            icons.add(icon);
        }

    }

    public int getDrawableId(String profileImageName) {
        int drawableId = 0;
        for(ProfileIcon icon: icons) {
            if(icon.getProfileIconTitle().equals(profileImageName)) {
                drawableId = icon.getProfileIconId();
                return drawableId;
            }
        }

        return drawableId;
    }
}
