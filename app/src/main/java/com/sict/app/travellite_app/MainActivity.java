package com.sict.app.travellite_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.app.Dialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    private BottomNavigationView.OnNavigationItemSelectedListener onNavigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            switch (menuItem.getItemId()){
                case R.id.menu_home:
                    home_fragment f1 = new home_fragment();
                    FragmentTransaction fragmentTransaction1 = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction1.replace(R.id.fram, f1);
                    fragmentTransaction1.commit();
                    return true;
                case R.id.menu_favorite:
                    favorite_fragment f2 = new favorite_fragment();
                    FragmentTransaction fragmentTransaction2 = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction2.replace(R.id.fram, f2);
                    fragmentTransaction2.commit();
                    return true;
                case R.id.menu_yourtrip:
                    trip_fragment f3 = new trip_fragment();
                    FragmentTransaction fragmentTransaction3 = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction3.replace(R.id.fram, f3);
                    fragmentTransaction3.commit();
                    return true;
                case R.id.menu_account:
                    account_fragment f4 = new account_fragment();
                    FragmentTransaction fragmentTransaction4 = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction4.replace(R.id.fram, f4);
                    fragmentTransaction4.commit();
                    return true;
                case R.id.menu_yourhotel:
                    hotel_fragment f5 = new hotel_fragment();
                    FragmentTransaction fragmentTransaction5 = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction5.replace(R.id.fram, f5);
                    fragmentTransaction5.commit();
                    return true;
            }
            return false;
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView navigationView = (BottomNavigationView)findViewById(R.id.bottom);
        navigationView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener);
        home_fragment f1 = new home_fragment();
        FragmentTransaction fragmentTransaction1 = getSupportFragmentManager().beginTransaction();
        fragmentTransaction1.replace(R.id.fram, f1);
        fragmentTransaction1.commit();
    }
}

