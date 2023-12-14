package com.kotlincourse.foodapp;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class ViewPagerAdapter extends FragmentStateAdapter {
    public ViewPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0: return new Recipes();
            case 1: return new MaelPlanner();
            case 2: return new Blog();
            case 3: return new Contact();
            case 4: return new AboutMe();
            default: return new Recipes();
        }
    }

    @Override
    public int getItemCount() {
        return 5;
    }
}
