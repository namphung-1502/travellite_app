package com.sict.app.travellite_app;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sict.app.travellite_app.adapter.slide_adapter;
import com.sict.app.travellite_app.model.area;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link home_fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class home_fragment extends Fragment {

    private ViewPager viewPager;
    private slide_adapter slideadapter;
    private Timer timer;
    int cur_index = 0;
    private List<area> list_area = new ArrayList<>();
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public home_fragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment home_fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static home_fragment newInstance(String param1, String param2) {
        home_fragment fragment = new home_fragment();
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
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        slide();
    }
    private void slide(){
        viewPager = (ViewPager) getView().findViewById(R.id.viewpager);
        list_area.add(new area("NEW YORK",R.drawable.backgr3));
        list_area.add(new area("VIET NAM",R.drawable.backgr5));
        list_area.add(new area("RUSSIA",R.drawable.backgr6));
        list_area.add(new area("CHINA",R.drawable.backgr7));
        viewPager = (ViewPager) getView().findViewById(R.id.viewpager);
        slideadapter = new slide_adapter(getContext(),list_area);
        viewPager.setAdapter(slideadapter);

        final Handler handler = new Handler();
        final Runnable runnable = new Runnable() {
            @Override
            public void run() {
                if(cur_index+1 >list_area.size()){
                    cur_index = 0;
                }else{
                    cur_index ++;
                }
                viewPager.setCurrentItem(cur_index,true);

            }
        };
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(runnable);
            }
        },500,3000);
    }
}
