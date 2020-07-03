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

import com.sict.app.travellite_app.model.AccessToken;
import com.sict.app.travellite_app.model.TokenManager;
import com.sict.app.travellite_app.rest_api.client;
import com.sict.app.travellite_app.rest_api.restapi;

import org.w3c.dom.Text;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class login extends AppCompatActivity {
    private TextView txt_register, txt_login_error;
    private EditText edt_email, edt_pass;
    private Button btn_login;
    private client client;
    private restapi restapi;
    private AccessToken a = new AccessToken();
    private TokenManager tokenManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
         init();
    }

    private void init() {
        txt_login_error = (TextView)findViewById(R.id.txt_login_error);
        txt_login_error.setVisibility(View.GONE);
        txt_register = (TextView)findViewById(R.id.txt_register);
        txt_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(login.this, register.class);
                startActivity(i);
            }
        });

        edt_email = (EditText)findViewById(R.id.etLogGmail);
        edt_pass = (EditText)findViewById(R.id.etLoginPassword);
        btn_login = (Button)findViewById(R.id.btn_login);
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                action_login();
            }
        });
        client = new client();
        restapi =client.getClient().create(restapi.class);
        tokenManager = TokenManager.getInstance(getSharedPreferences("pref", MODE_PRIVATE));
    }

    private void action_login() {
        Call<AccessToken> calltoken =restapi.calltoken(edt_email.getText().toString(),edt_pass.getText().toString());
        calltoken.enqueue(new Callback<AccessToken>() {
            @Override
            public void onResponse(Call<AccessToken> call, Response<AccessToken> response) {
                if (response.isSuccessful()){
                    a = response.body();
                    tokenManager.saveToken(a);
                    Log.e("token", a.getToken());
                    Intent i = new Intent(login.this, MainActivity.class);
                    startActivity(i);
                }
                else
                    txt_login_error.setVisibility(View.VISIBLE);
            }

            @Override
            public void onFailure(Call<AccessToken> call, Throwable t) {
                Toast.makeText(login.this, "Error: "+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
