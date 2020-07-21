package com.sict.app.travellite_app.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.sict.app.travellite_app.fragment.trip_pass_fragment;
import com.sict.app.travellite_app.fragment.trip_upcoming_fragment;

public class trip_adapter extends FragmentStatePagerAdapter {
    String title[] = {"Tour sắp đến","Tour đã qua"};
    private trip_pass_fragment trippass;
    private trip_upcoming_fragment tripcoming;
    public trip_adapter(@NonNull FragmentManager fm) {
        super(fm);
        trippass = new trip_pass_fragment();
        tripcoming = new trip_upcoming_fragment();
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        if (position == 0)
            return tripcoming;
        else if(position == 1)
            return trippass;
        else{}
        return null;
    }

    @Override
    public int getCount() {
        return title.length;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return title[position];
    }
}
