package com.sict.app.travellite_app.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.sict.app.travellite_app.fragment.favorite_hotel_fragment;
import com.sict.app.travellite_app.fragment.favorite_tour_fragment;

public class favorite_adapter extends FragmentStatePagerAdapter {
    String title[] = {"Tour","Khách sạn"};
    private favorite_hotel_fragment hotel;
    private favorite_tour_fragment tour;
    public favorite_adapter(@NonNull FragmentManager fm) {
        super(fm);
        hotel = new favorite_hotel_fragment();
        tour = new favorite_tour_fragment();
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        if (position == 0)
            return tour;
        else if(position == 1)
            return hotel;
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
