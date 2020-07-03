package com.sict.app.travellite_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.sict.app.travellite_app.model.TokenManager;
import com.sict.app.travellite_app.model.booking_hotel;
import com.sict.app.travellite_app.model.hotel;
import com.sict.app.travellite_app.model.user;
import com.sict.app.travellite_app.rest_api.client;
import com.sict.app.travellite_app.rest_api.restapi;

import org.w3c.dom.Text;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class confirm_booking_hotel extends AppCompatActivity {
    private hotel h;
    private TextView txt_name_customer, txt_phone, txt_nationality, txt_number_card, txt_name_hotel, txt_count_room, txt_date;
    private Button btn_confirm;
    private TokenManager tokenManager;
    private client client;
    private restapi restapi;
    private user u;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_booking_hotel);
        initdata();
    }

    private void initdata() {
        client = new client();
        restapi = client.getClient().create(restapi.class);
        txt_name_customer = (TextView)findViewById(R.id.txt_name_customer);
        txt_count_room = (TextView)findViewById(R.id.txt_count_room);
        txt_phone = (TextView)findViewById(R.id.txt_phone);
        txt_nationality = (TextView)findViewById(R.id.txt_nationality);
        txt_number_card = (TextView)findViewById(R.id.txt_number_card);
        txt_name_hotel = (TextView)findViewById(R.id.txt_name_hotel);
        txt_date = (TextView)findViewById(R.id.txt_date);
        btn_confirm = (Button)findViewById(R.id.btn_confirm);
        tokenManager = TokenManager.getInstance(getSharedPreferences("pref",MODE_PRIVATE));
        btn_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                booking_hotel();
            }
        });
    }
    public void booking_hotel(){
        Call<user> calluser = restapi.calluser("Bearer "+tokenManager.getToken().getToken());
        calluser.enqueue(new Callback<user>() {
            @Override
            public void onResponse(Call<user> call, Response<user> response) {
                if(response.isSuccessful()){
                    u = response.body();
                    Call<com.sict.app.travellite_app.model.booking_hotel> booking_hotelCall = restapi.booking_hotel(txt_name_customer.getText().toString(), u.getId(),
                            txt_phone.getText().toString(),txt_nationality.getText().toString(),txt_number_card.getText().toString(), h.getId(), Integer.parseInt(txt_count_room.getText().toString())
                            ,txt_date.getText().toString());
                    booking_hotelCall.enqueue(new Callback<booking_hotel>() {
                        @Override
                        public void onResponse(Call<booking_hotel> call, Response<booking_hotel> response) {
                            if (response.isSuccessful()){
                                Toast.makeText(confirm_booking_hotel.this, "Bạn đã đặt tour thành công !!!", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(confirm_booking_hotel.this, MainActivity.class);
                                startActivity(intent);
                            }
                        }

                        @Override
                        public void onFailure(Call<booking_hotel> call, Throwable t) {

                        }
                    });
                }
            }
            @Override
            public void onFailure(Call<user> call, Throwable t) {
                Toast.makeText(confirm_booking_hotel.this, "Error: "+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
    @Override
    protected void onPostResume() {
        super.onPostResume();
        txt_name_customer.setText(getIntent().getStringExtra("name"));
        txt_phone.setText(getIntent().getStringExtra("phone"));
        txt_nationality.setText(getIntent().getStringExtra("nationality"));
        txt_number_card.setText(getIntent().getStringExtra("number_card"));
        h = (hotel) getIntent().getSerializableExtra("hotel");
        txt_name_hotel.setText(h.getName());
        txt_count_room.setText(String.valueOf(getIntent().getIntExtra("count_room", 0)));
        txt_date.setText(getIntent().getStringExtra("date"));
    }
}
