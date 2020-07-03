package com.sict.app.travellite_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.android.material.textfield.TextInputLayout;
import com.sict.app.travellite_app.model.hotel;

import org.w3c.dom.Text;

public class booking_hotel extends AppCompatActivity {
    private hotel h;
    private TextInputLayout txt_name_customer, txt_phone, txt_number_card, txt_date;
    private Spinner sp_nationality;
    private Button btn_incre, btn_descre, btn_send;
    private TextView txt_count_room, txt_error;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_hotel);
        initdata();
    }

    private void initdata() {
        h = (hotel) getIntent().getSerializableExtra("hotel");
        txt_error = (TextView)findViewById(R.id.txt_error);
        txt_error.setVisibility(View.GONE);
        txt_name_customer = (TextInputLayout)findViewById(R.id.txt_name_customer);
        txt_date = (TextInputLayout)findViewById(R.id.txt_date);
        txt_number_card = (TextInputLayout)findViewById(R.id.txt_card);
        txt_phone = (TextInputLayout)findViewById(R.id.txt_phone_customer);
        sp_nationality = (Spinner)findViewById(R.id.spn_nationality);
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.item_spinner_nationality, getResources().getStringArray(R.array.nationality));
        sp_nationality.setAdapter(adapter);
        int spinnerPosition = adapter.getPosition("Vietnam");
        sp_nationality.setSelection(spinnerPosition);
        btn_descre = (Button)findViewById(R.id.btn_descre);
        btn_incre = (Button)findViewById(R.id.btn_incre);
        txt_count_room = (TextView)findViewById(R.id.txt_count_room);
        btn_incre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int number = Integer.parseInt(txt_count_room.getText().toString());
                txt_count_room.setText(String.valueOf(number+1));
            }
        });
        btn_descre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int number = Integer.parseInt(txt_count_room.getText().toString());
                if(number == 0){}
                else{
                    txt_count_room.setText(String.valueOf(number-1));
                }
            }
        });
        btn_send = (Button)findViewById(R.id.btn_send_infor);
        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int number = Integer.parseInt(txt_count_room.getText().toString());
                if (number == 0){
                    txt_error.setVisibility(View.VISIBLE);
                }else{
                    txt_error.setVisibility(View.GONE);
                    Intent intent = new Intent(booking_hotel.this, confirm_booking_hotel.class);
                    intent.putExtra("hotel",h);
                    intent.putExtra("name",txt_name_customer.getEditText().getText().toString());
                    intent.putExtra("phone", txt_phone.getEditText().getText().toString());
                    intent.putExtra("nationality",sp_nationality.getSelectedItem().toString());
                    intent.putExtra("number_card", txt_number_card.getEditText().getText().toString());
                    intent.putExtra("date",txt_date.getEditText().getText().toString());
                    intent.putExtra("count_room",Integer.parseInt(txt_count_room.getText().toString()));
                    startActivity(intent);

                }
            }
        });

    }
}
