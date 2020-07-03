package com.sict.app.travellite_app;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.text.Spanned;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.sict.app.travellite_app.model.TokenManager;
import com.sict.app.travellite_app.model.hotel;

public class detail_hotel extends AppCompatActivity {
    private ImageView img;
    private TextView txt_name, txt_des, txt_cost, txt_location, txt_phone;
    private Button btn_booking;
    private hotel  h;
    private TokenManager tokenManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_hotel);
        initdata();
    }

    private void initdata() {
        tokenManager = TokenManager.getInstance(getSharedPreferences("pref",MODE_PRIVATE));
        img = (ImageView)findViewById(R.id.img);
        txt_name = (TextView)findViewById(R.id.txt_name);
        txt_des = (TextView)findViewById(R.id.txt_des);
        txt_cost = (TextView)findViewById(R.id.txt_cost);
        txt_location = (TextView)findViewById(R.id.txt_location);
        txt_phone = (TextView)findViewById(R.id.txt_phone);
        btn_booking = (Button)findViewById(R.id.btn_booking);
        btn_booking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(tokenManager.getToken().getToken() != null){
                    Intent intent = new Intent(detail_hotel.this,booking_hotel.class);
                    intent.putExtra("hotel", h);
                    startActivity(intent);
                }else{
                    AlertDialog.Builder login = new AlertDialog.Builder(detail_hotel.this);
                    login.setTitle("Xác nhận");
                    login.setMessage("Bạn cần phải đăng nhập để đặt khách sạn !!!");
                    login.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });
                    AlertDialog a = login.create();
                    a.show();
                }
            }
        });
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        h = (hotel) getIntent().getSerializableExtra("hotel");
        Glide.with(this).load("http://10.0.2.2:8000/image/"+h.getImage()).into(img);
        txt_name.setText(h.getName());
        txt_location.setText(h.getPlace());
        txt_cost.setText("Giá: $"+h.getCost());
        Spanned sp = Html.fromHtml(h.getDescription());
        txt_des.setText(sp);
        txt_phone.setText(h.getPhonenumber());
    }
}
