package com.sict.app.travellite_app.fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.sict.app.travellite_app.R;
import com.sict.app.travellite_app.adapter.favorite_hotel_adapter;
import com.sict.app.travellite_app.adapter.tophotels_adapter;
import com.sict.app.travellite_app.login;
import com.sict.app.travellite_app.model.TokenManager;
import com.sict.app.travellite_app.model.hotel;
import com.sict.app.travellite_app.model.user;
import com.sict.app.travellite_app.rest_api.client;
import com.sict.app.travellite_app.rest_api.restapi;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class favorite_hotel_fragment extends Fragment {
    private RecyclerView rcv_topfavorite, rcv_favorite;
    private tophotels_adapter adapter;
    private favorite_hotel_adapter adapter_favorite;
    private List<hotel> list_hotel_favorite = new ArrayList<>();
    private List<hotel> list_hotel = new ArrayList<>();
    private client client;
    private restapi restapi;
    private RelativeLayout layout_logined, layout_nologined;
    private TokenManager tokenManager;
    private Button btn_login;
    private user u;
    private TextView txt_nolove;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.favorite_hotel,container,false);
        layout_logined = (RelativeLayout) view.findViewById(R.id.layout_logined);
        layout_nologined = (RelativeLayout)view.findViewById(R.id.layout_nologined);
        tokenManager = TokenManager.getInstance(getActivity().getSharedPreferences("pref",getActivity().MODE_PRIVATE));
        rcv_topfavorite = (RecyclerView)view.findViewById(R.id.rcv_topfavorite);
        rcv_favorite = (RecyclerView)view.findViewById(R.id.rcv_favorite);
        txt_nolove = (TextView)view.findViewById(R.id.txt_nolove);

        btn_login = (Button)view.findViewById(R.id.btn_login);
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getContext(), login.class);
                startActivity(i);
            }
        });
       return view;
    }

    private void initdata() {
        u = new user();
        client = new client();
        restapi = client.getClient().create(restapi.class);
        final Call<List<hotel>> gettopfavorite = restapi.topfavoritehotel();
        gettopfavorite.enqueue(new Callback<List<hotel>>() {
            @Override
            public void onResponse(Call<List<hotel>> call, Response<List<hotel>> response) {
                if(!response.isSuccessful())
                {
                    Log.e("Loi nay :", response.message());
                }
                list_hotel.addAll(response.body());
                adapter = new tophotels_adapter(getContext(), list_hotel);
                rcv_topfavorite.setLayoutManager(new LinearLayoutManager(getContext()));
                rcv_topfavorite.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<hotel>> call, Throwable t) {
                Toast.makeText(getContext(), "Error"+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
    public void deletehotel(int id_user, int id_hotel){
       Call<String> delete = restapi.deletefavoritehotel(id_user, id_hotel);
       delete.enqueue(new Callback<String>() {
           @Override
           public void onResponse(Call<String> call, Response<String> response) {
           }

           @Override
           public void onFailure(Call<String> call, Throwable t) {
           }
       });
    }
    @Override
    public void onResume() {
        super.onResume();
        if(tokenManager.getToken().getToken() !=null){
            layout_logined.setVisibility(View.VISIBLE);
            layout_nologined.setVisibility(View.GONE);
            initdata();
            Call<user> calluser = restapi.calluser("Bearer "+tokenManager.getToken().getToken());
            calluser.enqueue(new Callback<com.sict.app.travellite_app.model.user>() {
                @Override
                public void onResponse(Call<com.sict.app.travellite_app.model.user> call, Response<com.sict.app.travellite_app.model.user> response) {
                           u = response.body();
                           Call<List<hotel>> callhotelfavorite = restapi.callfavoritehotel(u.getId());
                           callhotelfavorite.enqueue(new Callback<List<hotel>>() {
                               @Override
                               public void onResponse(Call<List<hotel>> call, Response<List<hotel>> response) {
                                  if (response.isSuccessful()){
                                      list_hotel_favorite.addAll(response.body());
                                      if(list_hotel_favorite.isEmpty()){
                                          txt_nolove.setVisibility(View.VISIBLE);
                                          rcv_favorite.setVisibility(View.GONE);
                                      }else{
                                          txt_nolove.setVisibility(View.GONE);
                                          rcv_favorite.setVisibility(View.VISIBLE);
                                          adapter_favorite = new favorite_hotel_adapter(list_hotel_favorite,getContext());
                                          adapter_favorite.setListener(new favorite_hotel_adapter.OnCallBack() {
                                              @Override
                                              public void OnItemClick(int i) {
                                                  deletehotel(u.getId(), list_hotel_favorite.get(i).getId());
                                                 if(list_hotel_favorite.size()>1){
                                                     list_hotel_favorite.remove(i);
                                                     adapter_favorite.notifyDataSetChanged();
                                                 }else{
                                                    list_hotel_favorite.clear();
                                                    adapter_favorite.notifyDataSetChanged();
                                                    rcv_favorite.setVisibility(View.GONE);
                                                    txt_nolove.setVisibility(View.VISIBLE);
                                                 }
                                              }
                                          });
                                          rcv_favorite.setAdapter(adapter_favorite);
                                          rcv_favorite.setLayoutManager(new LinearLayoutManager(getContext()));
                                      }
                                  }else{
                                      Toast.makeText(getContext(), "Do not load data", Toast.LENGTH_SHORT).show();
                                  }
                               }

                               @Override
                               public void onFailure(Call<List<hotel>> call, Throwable t) {
                                   Toast.makeText(getContext(), "Error"+t.getMessage(), Toast.LENGTH_SHORT).show();
                               }
                           });
                       }

                @Override
                public void onFailure(Call<com.sict.app.travellite_app.model.user> call, Throwable t) {
                }
            });

        }else{
            layout_nologined.setVisibility(View.VISIBLE);
            layout_logined.setVisibility(View.GONE);
        }
    }
}

