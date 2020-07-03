package com.sict.app.travellite_app;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.sict.app.travellite_app.model.user;
import com.sict.app.travellite_app.rest_api.client;
import com.sict.app.travellite_app.rest_api.restapi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class information_account extends AppCompatActivity {
    EditText edt_name, edt_phone, edt_address, edt_email, edt_pass;
    Button btn_save, btn_exit, btn_changepass;
    client client;
    restapi restapi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information_account);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        init();
    }

    private void init() {
        client = new client();
        restapi = client.getClient().create(restapi.class);
        btn_save = (Button)findViewById(R.id.btn_save);
        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveaccount();
            }
        });
        btn_exit = (Button)findViewById(R.id.btn_exit);
        btn_exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        btn_changepass = (Button)findViewById(R.id.btn_changepass);
        btn_changepass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(information_account.this,changepassword.class);
                i.putExtra("id",getIntent().getIntExtra("id",0));
                startActivity(i);

            }
        });
        edt_name = (EditText)findViewById(R.id.edt_name);
        edt_address = (EditText)findViewById(R.id.edt_address);
        edt_email = (EditText)findViewById(R.id.edt_email);
        edt_phone = (EditText)findViewById(R.id.edt_phone);
        edt_pass = (EditText)findViewById(R.id.edt_pass);
        edt_pass.setOnKeyListener(null);

        //get data from account
        edt_name.setText(getIntent().getStringExtra("name"));
        edt_address.setText(getIntent().getStringExtra("address"));
        edt_phone.setText(getIntent().getStringExtra("phone"));
        edt_email.setText(getIntent().getStringExtra("email"));
        edt_pass.setText(getIntent().getStringExtra("token"));
    }
    private void saveaccount(){
        user user_update = new user(edt_name.getText().toString(),edt_email.getText().toString(),edt_address.getText().toString(),edt_phone.getText().toString());
        Call<user> update = restapi.updateuser(getIntent().getIntExtra("id",0),user_update);
        update.enqueue(new Callback<user>() {
            @Override
            public void onResponse(Call<user> call, Response<user> response) {
                if (response.isSuccessful()){
                    onBackPressed();
                }else{
                    Toast.makeText(information_account.this, "Không thể cập nhật dữ liệu", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<user> call, Throwable t) {
                Toast.makeText(information_account.this, "Error: "+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
