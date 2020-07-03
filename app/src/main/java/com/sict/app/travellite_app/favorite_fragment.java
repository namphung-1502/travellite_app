package com.sict.app.travellite_app;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.tabs.TabLayout;
import com.sict.app.travellite_app.adapter.favorite_adapter;
import com.sict.app.travellite_app.adapter.viewpager_adapter;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link favorite_fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class favorite_fragment extends Fragment {
    private ViewPager tabview;
    private TabLayout tabLayout;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public favorite_fragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment favorite_fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static favorite_fragment newInstance(String param1, String param2) {
        favorite_fragment fragment = new favorite_fragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_favorite_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        tabview = (ViewPager)view.findViewById(R.id.viewpager2);
        tabview.setAdapter(new favorite_adapter(getActivity().getSupportFragmentManager()));
        tabLayout= (TabLayout)view.findViewById(R.id.tablayout);
        tabLayout.setupWithViewPager(tabview);
        super.onViewCreated(view, savedInstanceState);
    }
}
