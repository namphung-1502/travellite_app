package com.sict.app.travellite_app;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.sict.app.travellite_app.model.tour;

import org.w3c.dom.Text;

import java.util.Calendar;

public class booking_tour extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {
    private ImageButton btn_showdate;
    private TextView txt_date, txt_baby, txt_young, txt_adult, txt_total_cost;
    private Button btn_incre_adult, btn_decre_adult,btn_incre_young, btn_decre_young,btn_incre_baby, btn_decre_baby, btn_bookingtour;
    private int idtour;
    private tour tour;
    private int sale, adult, young,baby;
    private EditText txt_name, txt_address, txt_phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_tour);
        idtour = getIntent().getIntExtra("idtour",0);
        tour = (com.sict.app.travellite_app.model.tour) getIntent().getSerializableExtra("tour");
        sale = getIntent().getIntExtra("sale",0);
        if(sale == 0){
            young = tour.getMoney().getYoung();
            adult = tour.getMoney().getAdult();
            baby = tour.getMoney().getBaby();
        }else{
            adult = tour.getMoney().getAdult() - (tour.getMoney().getAdult()*sale)/100;
            young = tour.getMoney().getYoung() - (tour.getMoney().getYoung()*sale)/100;
            baby = tour.getMoney().getBaby() - (tour.getMoney().getBaby()*sale)/100;
        }
        initdata();
    }

    private void initdata() {
        txt_name = (EditText)findViewById(R.id.txt_name);
        txt_address = (EditText)findViewById(R.id.txt_address);
        txt_phone = (EditText)findViewById(R.id.txt_phone);
        txt_baby = (TextView)findViewById(R.id.txt_baby);
        txt_adult = (TextView)findViewById(R.id.txt_adult);
        txt_young = (TextView)findViewById(R.id.txt_young);
        btn_decre_adult = (Button)findViewById(R.id.btn_decre_adult);
        btn_decre_adult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int nguoilon = Integer.parseInt(txt_adult.getText().toString());
                int treem = Integer.parseInt(txt_young.getText().toString());
                int embe = Integer.parseInt(txt_baby.getText().toString());
                if (nguoilon == 1){

                }
                else{
                    txt_adult.setText(String.valueOf(nguoilon-1));
                    int total = (nguoilon-1)*adult+treem*young+embe*baby;
                    txt_total_cost.setText(String.valueOf("$"+total));
                }


            }
        });
        btn_incre_adult = (Button)findViewById(R.id.btn_incre_adult);
        btn_incre_adult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int nguoilon = Integer.parseInt(txt_adult.getText().toString());
                int treem = Integer.parseInt(txt_young.getText().toString());
                int embe = Integer.parseInt(txt_baby.getText().toString());
                txt_adult.setText(String.valueOf(nguoilon+1));
                int total = (nguoilon+1)*adult+treem*young+embe*baby;
                txt_total_cost.setText(String.valueOf("$"+total));

            }
        });
        btn_decre_baby = (Button)findViewById(R.id.btn_decre_baby);
        btn_decre_baby.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int nguoilon = Integer.parseInt(txt_adult.getText().toString());
                int treem = Integer.parseInt(txt_young.getText().toString());
                int embe = Integer.parseInt(txt_baby.getText().toString());
                if (embe == 0){

                }
                else{
                    txt_baby.setText(String.valueOf(embe-1));
                    int total = nguoilon*adult+treem*young+(embe-1)*baby;
                    txt_total_cost.setText(String.valueOf("$"+total));
                }
            }
        });
        btn_incre_baby = (Button)findViewById(R.id.btn_incre_baby);
        btn_incre_baby.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int nguoilon = Integer.parseInt(txt_adult.getText().toString());
                int treem = Integer.parseInt(txt_young.getText().toString());
                int embe = Integer.parseInt(txt_baby.getText().toString());
                txt_baby.setText(String.valueOf(embe+1));
                int total = nguoilon*adult+treem*young+(embe+1)*baby;
                txt_total_cost.setText(String.valueOf("$"+total));
            }
        });
        btn_decre_young = (Button)findViewById(R.id.btn_decre_young);
        btn_decre_young.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int nguoilon = Integer.parseInt(txt_adult.getText().toString());
                int treem = Integer.parseInt(txt_young.getText().toString());
                int embe = Integer.parseInt(txt_baby.getText().toString());
                if (treem == 0){}
                else{

                    txt_young.setText(String.valueOf(treem-1));
                    int total = nguoilon*adult+(treem-1)*young+embe*baby;
                    txt_total_cost.setText(String.valueOf("$"+total));
                }
            }
        });
        btn_incre_young = (Button)findViewById(R.id.btn_incre_young);
        btn_incre_young.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int nguoilon = Integer.parseInt(txt_adult.getText().toString());
                int treem = Integer.parseInt(txt_young.getText().toString());
                int embe = Integer.parseInt(txt_baby.getText().toString());
                txt_young.setText(String.valueOf(treem+1));
                int total = nguoilon*adult+(treem+1)*young+embe*baby;
                txt_total_cost.setText(String.valueOf("$"+total));
            }
        });
        btn_showdate = (ImageButton)findViewById(R.id.btn_showdate);
        txt_date = (TextView)findViewById(R.id.txt_date);
        btn_showdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showdatePickerDialog();
            }
        });
        txt_total_cost = (TextView)findViewById(R.id.txt_total_cost);
        if (sale == 0){
            txt_total_cost.setText(String.valueOf("$"+tour.getMoney().getAdult()));
        }else{
            int rest = tour.getMoney().getAdult() - (tour.getMoney().getAdult()*sale)/100;
            txt_total_cost.setText(String.valueOf("$"+rest));
        }
        btn_bookingtour = (Button)findViewById(R.id.btn_bookingtour);
        btn_bookingtour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(booking_tour.this, confirm_bookingtour.class);
                int nguoilon = Integer.parseInt(txt_adult.getText().toString());
                int treem = Integer.parseInt(txt_young.getText().toString());
                int embe = Integer.parseInt(txt_baby.getText().toString());
                int rest = nguoilon*adult+treem*young+embe*baby;
                i.putExtra("tour", tour);
                i.putExtra("name",txt_name.getText().toString());
                i.putExtra("phone", txt_phone.getText().toString());
                i.putExtra("address",txt_address.getText().toString());
                i.putExtra("date",txt_date.getText().toString());
                i.putExtra("adult",nguoilon);
                i.putExtra("young",treem);
                i.putExtra("baby", embe);
                i.putExtra("total",rest);
                startActivity(i);

            }
        });
    }
    private void showdatePickerDialog(){
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this,
                this,
                Calendar.getInstance().get(Calendar.YEAR),
                Calendar.getInstance().get(Calendar.MONTH),
                Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
        );
        datePickerDialog.show();
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        String date = dayOfMonth+"/"+month+"/"+year;
        txt_date.setText(date);
    }
}