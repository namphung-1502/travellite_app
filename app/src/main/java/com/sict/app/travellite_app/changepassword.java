package com.sict.app.travellite_app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.sict.app.travellite_app.model.user;
import com.sict.app.travellite_app.rest_api.client;
import com.sict.app.travellite_app.rest_api.restapi;

import org.w3c.dom.Text;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class changepassword extends AppCompatActivity {
    private EditText txt_newpass, txt_retype_pass;
    private TextView txt_retype_error;
    private client client;
    private restapi restapi;
    private Button btn_change, btn_exit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_changepassword);
        init();
        Toast.makeText(this, "id is"+ getIntent().getIntExtra("id",0), Toast.LENGTH_SHORT).show();
    }

    private void init() {
        txt_retype_error = (TextView)findViewById(R.id.txt_retype_error);
        txt_retype_error.setVisibility(View.GONE);
        txt_newpass = (EditText) findViewById(R.id.edt_pass);
        txt_retype_pass = (EditText) findViewById(R.id.edt_retype_pass);
        client = new client();
        restapi = client.getClient().create(restapi.class);
        btn_change = (Button)findViewById(R.id.btn_change);
        btn_change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(txt_retype_pass.getText().toString().compareTo(txt_newpass.getText().toString())==0) {
                    txt_retype_error.setVisibility(View.GONE);
                   changepasss();

                }else{
                    txt_retype_error.setVisibility(View.VISIBLE);
                }

            }
        });
        btn_exit = (Button)findViewById(R.id.btn_exit);
        btn_exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    private void changepasss() {
        int id = getIntent().getIntExtra("id",0);
        Call<user> callchangepass = restapi.changepass(id, txt_newpass.getText().toString());
        callchangepass.enqueue(new Callback<user>() {
            @Override
            public void onResponse(Call<user> call, Response<user> response) {
                if(response.isSuccessful()){
                  onBackPressed();
                }else{
                    Toast.makeText(changepassword.this, "Không thể thay đổi mật khẩu", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<user> call, Throwable t) {
                Toast.makeText(changepassword.this, "Error: "+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
