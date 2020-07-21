package com.sict.app.travellite_app.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.sict.app.travellite_app.R;
import com.sict.app.travellite_app.adapter.favorite_tour_adaper;
import com.sict.app.travellite_app.adapter.toptourfavorite_adapter;
import com.sict.app.travellite_app.login;
import com.sict.app.travellite_app.model.TokenManager;
import com.sict.app.travellite_app.model.tour;
import com.sict.app.travellite_app.model.user;
import com.sict.app.travellite_app.rest_api.client;
import com.sict.app.travellite_app.rest_api.restapi;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class favorite_tour_fragment extends Fragment {
    private RelativeLayout layout_logined, layout_nologined;
    private RecyclerView rcv_top, rcv_tour_favorite;
    private favorite_tour_adaper favorite_adapter;
    private TextView txt_nolove;
    private TokenManager tokenManager;
    private client client;
    private restapi restapi;
    private Button btn_login;
    private toptourfavorite_adapter adapter;
    private List<tour> list = new ArrayList<>();
    private List<tour> list_tour_favorite = new ArrayList<>();
    private user u;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.favorite_tour, container,false);
        layout_logined = (RelativeLayout)view.findViewById(R.id.layout_logined);
        layout_nologined = (RelativeLayout)view.findViewById(R.id.layout_nologined);
        tokenManager = TokenManager.getInstance(getActivity().getSharedPreferences("pref",getActivity().MODE_PRIVATE));
        txt_nolove = (TextView)view.findViewById(R.id.txt_nolove);
        rcv_top = (RecyclerView)view.findViewById(R.id.rcv_topfavorite);
        rcv_tour_favorite = (RecyclerView)view.findViewById(R.id.rcv_tour_favorite);
        client = new client();
        restapi = client.getClient().create(restapi.class);
        btn_login = (Button)view.findViewById(R.id.btn_login);
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i =  new Intent(getContext(), login.class);
                startActivity(i);
            }
        });
        return view;
    }

    private void initdata() {
        u = new user();
        Call<List<tour>> calltour = restapi.toptourfavorite();
        calltour.enqueue(new Callback<List<tour>>() {
            @Override
            public void onResponse(Call<List<tour>> call, Response<List<tour>> response) {
                if (response.isSuccessful()){
                    list.addAll(response.body());
                    adapter = new toptourfavorite_adapter(list,getContext());
                    rcv_top.setAdapter(adapter);
                    rcv_top.setLayoutManager(new LinearLayoutManager(getContext()));
                    rcv_top.setHasFixedSize(true);
                }else{
                    Toast.makeText(getContext(), "Không thể load hình ảnh !!!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<tour>> call, Throwable t) {
                Toast.makeText(getContext(), "Error: "+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void deletetour(int id_tour, int id_user){
        Call<String> deletefavoritetour = restapi.deletefavoritetour(id_user, id_tour);
        deletefavoritetour.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if(response.isSuccessful()){}
                else
                    Toast.makeText(getContext(), "Do not delete", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Toast.makeText(getContext(), "Error:"+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        if(tokenManager.getToken().getToken() != null){
            layout_logined.setVisibility(View.VISIBLE);
            layout_nologined.setVisibility(View.GONE);
            initdata();
            Call<user> calluser = restapi.calluser("Bearer "+tokenManager.getToken().getToken());
            calluser.enqueue(new Callback<user>() {
                @Override
                public void onResponse(Call<user> call, Response<user> response) {
                    u = response.body();
                    Call<List<tour>> callfavoritetour = restapi.callfavoritetour(u.getId());
                    callfavoritetour.enqueue(new Callback<List<tour>>() {
                        @Override
                        public void onResponse(Call<List<tour>> call, Response<List<tour>> response) {
                            if (response.isSuccessful()){
                                list_tour_favorite.addAll(response.body());
                                if(list_tour_favorite.isEmpty()){
                                 rcv_tour_favorite.setVisibility(View.GONE);
                                 txt_nolove.setVisibility(View.VISIBLE);
                               }else{
                                    txt_nolove.setVisibility(View.GONE);
                                    favorite_adapter = new favorite_tour_adaper(list_tour_favorite,getContext());
                                    favorite_adapter.setDetailTour(new favorite_tour_adaper.OnDetailTour() {
                                        @Override
                                        public void detailTour(int i) {

                                        }
                                    });
                                    favorite_adapter.setListener(new favorite_tour_adaper.OnCallBack() {
                                        @Override
                                        public void OnItemClick(int i) {
                                           deletetour(list_tour_favorite.get(i).getId(), u.getId());
                                           if(list_tour_favorite.size()>1){
                                               list_tour_favorite.remove(i);
                                               favorite_adapter.notifyDataSetChanged();
                                           }else{
                                              list_tour_favorite.clear();
                                               favorite_adapter.notifyDataSetChanged();
                                               rcv_tour_favorite.setVisibility(View.GONE);
                                               txt_nolove.setVisibility(View.VISIBLE);
                                           }
                                        }
                                    });
                                    rcv_tour_favorite.setAdapter(favorite_adapter);
                                    rcv_tour_favorite.setLayoutManager(new LinearLayoutManager(getContext()));
                                    rcv_tour_favorite.setHasFixedSize(true);
                                }
                            }else{
                                Toast.makeText(getContext(), "Do not load data", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<List<tour>> call, Throwable t) {
                            Toast.makeText(getContext(), "Error: "+t.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }

                @Override
                public void onFailure(Call<user> call, Throwable t) {

                }
            });

        }else{
            layout_nologined.setVisibility(View.VISIBLE);
            layout_logined.setVisibility(View.GONE);
        }
    }
}
