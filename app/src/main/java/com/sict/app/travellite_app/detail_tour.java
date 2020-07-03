package com.sict.app.travellite_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.widget.Toast;

import com.sict.app.travellite_app.model.tour_sale;

public class detail_tour extends AppCompatActivity {
    private ViewPager viewPager;
    private tour_sale tour_sale;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_tour);
        initdata();
    }

    private void initdata() {
        viewPager = (ViewPager)findViewById(R.id.viewpager);
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        tour_sale = (tour_sale) getIntent().getSerializableExtra("tour");
        Toast.makeText(this, "name is :"+tour_sale.getName(), Toast.LENGTH_SHORT).show();
    }
}
