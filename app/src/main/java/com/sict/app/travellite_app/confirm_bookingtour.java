package com.sict.app.travellite_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.sict.app.travellite_app.model.TokenManager;
import com.sict.app.travellite_app.model.tour;
import com.sict.app.travellite_app.model.user;
import com.sict.app.travellite_app.rest_api.client;
import com.sict.app.travellite_app.rest_api.restapi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class confirm_bookingtour extends AppCompatActivity {
    private tour tour;
    private String name, phone, address, date;
    private int young, adult, baby, total;
    private TextView txt_name,txt_name_tour,txt_date,txt_adult,txt_young, txt_baby,txt_total;
    private Button btn_cancel, btn_confirm;
    private client client;
    private restapi restapi;
    private TokenManager tokenManager;
    private user user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_bookingtour);

    }

    private void initdata() {
        tokenManager = TokenManager.getInstance(getSharedPreferences("pref", MODE_PRIVATE));
        client = new client();
        restapi = client.getClient().create(restapi.class);
        txt_name = (TextView)findViewById(R.id.txt_name);
        txt_name_tour = (TextView)findViewById(R.id.txt_name_tour);
        txt_date = (TextView)findViewById(R.id.txt_date);
        txt_adult = (TextView)findViewById(R.id.txt_adult);
        txt_young = (TextView)findViewById(R.id.txt_young);
        txt_baby = (TextView)findViewById(R.id.txt_baby);
        txt_total = (TextView)findViewById(R.id.txt_total);
        txt_name.setText(name);
        txt_name_tour.setText(tour.getName());
        txt_date.setText(date);
        txt_adult.setText(String.valueOf(adult+" người"));
        txt_young.setText(String.valueOf(young+" người"));
        txt_baby.setText(String.valueOf(baby+" người"));
        txt_total.setText(String.valueOf("$"+total));
        btn_cancel = (Button)findViewById(R.id.btn_cancel);
        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(confirm_bookingtour.this, MainActivity.class);
                startActivity(it);
            }
        });
        btn_confirm = (Button)findViewById(R.id.btn_confirm);
        btn_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Call<user> calluser = restapi.calluser("Bearer "+tokenManager.getToken().getToken());
                calluser.enqueue(new Callback<com.sict.app.travellite_app.model.user>() {
                    @Override
                    public void onResponse(Call<com.sict.app.travellite_app.model.user> call, Response<com.sict.app.travellite_app.model.user> response) {
                        if (response.isSuccessful()){
                            user = response.body();
                            Call<String> bookingtour = restapi.bookingtour(tour.getId(), user.getId(),tour.getName(), date, name, phone, address, adult, young, baby, total);
                            bookingtour.enqueue(new Callback<String>() {
                                @Override
                                public void onResponse(Call<String> call, Response<String> response) {
                                    if (response.isSuccessful()){
                                        Intent i = new Intent(confirm_bookingtour.this, MainActivity.class);
                                        Toast.makeText(confirm_bookingtour.this, "Bạn đã đặt tour thành công !!", Toast.LENGTH_SHORT).show();
                                        startActivity(i);
                                    }else
                                        Toast.makeText(confirm_bookingtour.this, "Đã xảy ra lỗi không thể đặt tour !!"+response.code(), Toast.LENGTH_SHORT).show();
                                }

                                @Override
                                public void onFailure(Call<String> call, Throwable t) {
                                    Toast.makeText(confirm_bookingtour.this, "Error: "+t.getMessage(), Toast.LENGTH_SHORT).show();

                                }
                            });
                        }else
                            Toast.makeText(confirm_bookingtour.this, "Do not booking tour", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<com.sict.app.travellite_app.model.user> call, Throwable t) {
                        Toast.makeText(confirm_bookingtour.this, "Error:"+t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        tour = (com.sict.app.travellite_app.model.tour) getIntent().getSerializableExtra("tour");
        name = getIntent().getStringExtra("name");
        phone = getIntent().getStringExtra("phone");
        address = getIntent().getStringExtra("address");
        date = getIntent().getStringExtra("date");
        adult = getIntent().getIntExtra("adult",0);
        young = getIntent().getIntExtra("young",0);
        baby = getIntent().getIntExtra("baby",0);
        total = getIntent().getIntExtra("total",0);
        initdata();
    }
}
