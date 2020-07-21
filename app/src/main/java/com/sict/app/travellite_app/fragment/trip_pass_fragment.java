package com.sict.app.travellite_app.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.sict.app.travellite_app.R;
import com.sict.app.travellite_app.adapter.trip_pass_adapter;
import com.sict.app.travellite_app.adapter.trip_upcoming_adapter;
import com.sict.app.travellite_app.model.TokenManager;
import com.sict.app.travellite_app.model.bill;
import com.sict.app.travellite_app.model.user;
import com.sict.app.travellite_app.rest_api.client;
import com.sict.app.travellite_app.rest_api.restapi;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class trip_pass_fragment extends Fragment {
    private restapi restapi;
    private client client;
    private TokenManager tokenManager;
    private RecyclerView rcv_trip_pass;
    private trip_pass_adapter adapter;
    private List<bill> list = new ArrayList<>();
    private user user;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.trip_pass_fragment, container, false);
        rcv_trip_pass = (RecyclerView)view.findViewById(R.id.rcv_trip_pass);
        client = new client();
        restapi = client.getClient().create(restapi.class);
        tokenManager = TokenManager.getInstance(getActivity().getSharedPreferences("pref",getActivity().MODE_PRIVATE));
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (tokenManager.getToken().getToken() != null){
            Call<user> calluser = restapi.calluser("Bearer "+tokenManager.getToken().getToken());
            calluser.enqueue(new Callback<com.sict.app.travellite_app.model.user>() {
                @Override
                public void onResponse(Call<com.sict.app.travellite_app.model.user> call, Response<com.sict.app.travellite_app.model.user> response) {
                    if (response.isSuccessful()){
                        user = response.body();
                        Call<List<bill>> billbyuser = restapi.billbyuser(user.getId());
                        billbyuser.enqueue(new Callback<List<bill>>() {
                            @Override
                            public void onResponse(Call<List<bill>> call, Response<List<bill>> response) {
                                if (response.isSuccessful()) {
                                    List<bill> templist = new ArrayList<>();
                                    templist.addAll(response.body());
                                    DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
                                    String date = df.format(new Date());
                                    for (bill b: templist){
                                        try {
                                            Date date2 = df.parse(date);
                                            Date date1 = df.parse(b.getDepartureDay());
                                            if (date1.before(date2)){
                                                list.add(b);
                                            }
                                        } catch (ParseException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                    adapter = new trip_pass_adapter(list, getContext());
                                    rcv_trip_pass.setAdapter(adapter);
                                    rcv_trip_pass.setLayoutManager(new LinearLayoutManager(getContext()));
                                    rcv_trip_pass.setHasFixedSize(true);
                                }
                            }

                            @Override
                            public void onFailure(Call<List<bill>> call, Throwable t) {

                            }
                        });

                    }else
                        Toast.makeText(getContext(), "Do not load data", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onFailure(Call<com.sict.app.travellite_app.model.user> call, Throwable t) {
                    Toast.makeText(getContext(), "Error: "+t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
