package com.sict.app.travellite_app.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.sict.app.travellite_app.fragment.domestic_fragment;
import com.sict.app.travellite_app.fragment.foreign_fragment;

public class viewpager_adapter extends FragmentStatePagerAdapter {
    private String nametab[] = {"Trong nước", "Nước ngoài"};
    private domestic_fragment domestic;
    private foreign_fragment foreign;
    public viewpager_adapter(@NonNull FragmentManager fm) {
        super(fm);
        domestic = new domestic_fragment();
        foreign = new foreign_fragment();
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        if (position == 0){
           return domestic;
        } else if(position == 1)
            return foreign;
        else{}
        return null;
    }

    @Override
    public int getCount() {
        return nametab.length;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return nametab[position];
    }
}
