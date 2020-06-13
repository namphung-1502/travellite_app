package com.sict.app.travellite_app;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.material.tabs.TabLayout;
import com.sict.app.travellite_app.adapter.hotel_hoz_adapter;
import com.sict.app.travellite_app.adapter.slide_adapter;
import com.sict.app.travellite_app.adapter.viewpager_adapter;
import com.sict.app.travellite_app.model.bookguide;
import com.sict.app.travellite_app.model.hotel;
import com.sict.app.travellite_app.model.place;
import com.sict.app.travellite_app.rest_api.client;
import com.sict.app.travellite_app.rest_api.restapi;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link home_fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class home_fragment extends Fragment {
    private ViewPager tabview;
    private TabLayout tabLayout;
    private client client;
    private ViewPager viewPager;
    private slide_adapter slideadapter;
    private Timer timer;
    int cur_index = 0;
    private List<place> list_area = new ArrayList<>();
    private RecyclerView rcv_hotel;
    private List<hotel> list_hotel = new ArrayList<>();
    private hotel_hoz_adapter hotel_hoz_adapter;
    private restapi restapi;
    private RecyclerView rcv_bookguide;
    private List<bookguide> bookguideList = new ArrayList<>();
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
        View view = inflater.inflate(R.layout.fragment_home_fragment, container, false);
        rcv_hotel = (RecyclerView)view.findViewById(R.id.rcv_hotel);
        rcv_bookguide = (RecyclerView)view.findViewById(R.id.rcv_bookguide);
        initdata();
        return view;
    }



    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tabview = (ViewPager)view.findViewById(R.id.viewpager2);
        tabview.setAdapter(new viewpager_adapter(getActivity().getSupportFragmentManager()));
        tabLayout= (TabLayout)view.findViewById(R.id.tablayout);
        tabLayout.setupWithViewPager(tabview);
        slide();
    }


    private void initdata() {
        client = new client();
        restapi = client.getClient().create(restapi.class);
        //set data for hotel recycleview
        Call<List<hotel>> callhotel = restapi.gethotel();
        callhotel.enqueue(new Callback<List<hotel>>() {
            @Override
            public void onResponse(Call<List<hotel>> call, Response<List<hotel>> response) {
                if(!response.isSuccessful())
                {
                    Log.e("Loi nay :", response.message());
                }
            list_hotel.addAll(response.body());
            hotel_hoz_adapter = new hotel_hoz_adapter(getContext(),list_hotel);
            LinearLayoutManager hor =new LinearLayoutManager(getActivity(),
                        LinearLayoutManager.HORIZONTAL,false);
            rcv_hotel.setLayoutManager(hor);
            rcv_hotel.setAdapter(hotel_hoz_adapter);
            rcv_hotel.setHasFixedSize(true);
            }

            @Override
            public void onFailure(Call<List<hotel>> call, Throwable t) {

            }
        });

        //set data for bookguide recyclerview
        Call<List<bookguide>> getbookguide = restapi.getbookguide();
        getbookguide.enqueue(new Callback<List<bookguide>>() {
            @Override
            public void onResponse(Call<List<bookguide>> call, Response<List<bookguide>> response) {
                if(!response.isSuccessful())
                {
                    Log.e("Loi nay :", response.message());
                }
                bookguideList.addAll(response.body());

            }

            @Override
            public void onFailure(Call<List<bookguide>> call, Throwable t) {

            }
        });

    }

    private void slide(){
        viewPager = (ViewPager) getView().findViewById(R.id.viewpager);
        list_area.add(new place("0ia","Đảo Santorini, Hy Lạp",R.drawable.oia));
        list_area.add(new place("Marrakesh","Thành phố du lịch của Morocco",R.drawable.marrakech));
        list_area.add(new place("Đảo Bora Bora","Quần đảo Leeward, Pháp",R.drawable.bora));
        list_area.add(new place("Positano","Italy",R.drawable.positano));
        list_area.add(new place("Thác Havasu","Công viên Grand Canyon, Arizona, Mỹ",R.drawable.havasu));
        list_area.add(new place("Tử cấm thành ","Bắc Kinh, Trung Quốc",R.drawable.torres));
        list_area.add(new place("Krabi","Bờ biển phía nam Thái Lan",R.drawable.karabi));
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
        },1000,3000);
    }

}
