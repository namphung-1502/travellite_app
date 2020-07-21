package com.sict.app.travellite_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.sict.app.travellite_app.rest_api.client;
import com.sict.app.travellite_app.rest_api.restapi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class register extends AppCompatActivity {
    private EditText edt_name, edt_email, edt_phone, edt_address, edt_password, edt_retype_pass;
    private Button btn_register;
    private client client;
    private restapi restapi;
    private TextView txt_null, txt_fail_retype;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        init();
    }

    private void init() {
        txt_null = (TextView)findViewById(R.id.txt_null);
        txt_null.setVisibility(View.GONE);
        txt_fail_retype = (TextView)findViewById(R.id.txt_fail_retype) ;
        txt_fail_retype.setVisibility(View.GONE);
        edt_name = (EditText)findViewById(R.id.edt_name);
        edt_address = (EditText)findViewById(R.id.edt_address);
        edt_email = (EditText)findViewById(R.id.edt_email);
        edt_phone = (EditText)findViewById(R.id.edt_phone);
        edt_password = (EditText)findViewById(R.id.edt_password);
        edt_retype_pass = (EditText)findViewById(R.id.edt_retype_pass);
        btn_register = (Button)findViewById(R.id.btn_register);
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                register_account();
            }
        });
        client = new client();
        restapi = client.getClient().create(restapi.class);
    }

    private void register_account() {
        String name = edt_name.getText().toString();
        String address = edt_address.getText().toString();
        String email = edt_email.getText().toString();
        String phone = edt_phone.getText().toString();
        String pass = edt_password.getText().toString();
        String retype_pass = edt_retype_pass.getText().toString();
        if(name.isEmpty() || address.isEmpty() || email.isEmpty() || phone.isEmpty() || pass.isEmpty() || retype_pass.isEmpty()){
            txt_null.setVisibility(View.VISIBLE);
        }else{
            txt_null.setVisibility(View.GONE);
            if(pass.compareTo(retype_pass) != 0){
                txt_fail_retype.setVisibility(View.VISIBLE);
            }
            else{
                txt_fail_retype.setVisibility(View.GONE);
                final Call<String> register = restapi.register(name,address,email,phone,pass);
                register.enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        edt_name.setText(null);
                        edt_address.setText(null);
                        edt_password.setText(null);
                        edt_phone.setText(null);
                        edt_retype_pass.setText(null);
                        edt_email.setText(null);
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        Toast.makeText(register.this, "Loi nay: "+t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }


    }
}
