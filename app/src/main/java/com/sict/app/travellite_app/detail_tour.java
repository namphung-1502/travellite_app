package com.sict.app.travellite_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.sict.app.travellite_app.adapter.slide_detail_tour_adapter;
import com.sict.app.travellite_app.model.TokenManager;
import com.sict.app.travellite_app.model.favorite_tour;
import com.sict.app.travellite_app.model.tour;
import com.sict.app.travellite_app.model.tour_sale;
import com.sict.app.travellite_app.model.user;
import com.sict.app.travellite_app.rest_api.client;
import com.sict.app.travellite_app.rest_api.restapi;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class detail_tour extends AppCompatActivity {
    private ViewPager viewPager;
    private tour tour;
    private List list_image = new ArrayList();
    private int cur_index = 0;
    private slide_detail_tour_adapter adapter_image;
    private Timer timer;
    private TextView txt_name, txt_sale, txt_favorite, txt_cost_adult, txt_cost_young, txt_cost_baby;
    private ImageButton btn_favorite;
    private restapi restapi;
    private client client;
    private TokenManager tokenManager;
    private Button btn_bookingtour;
    private user u;
    private int sale;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_tour);

    }

    private void initdata() {
        tokenManager = TokenManager.getInstance(getSharedPreferences("pref",MODE_PRIVATE));

        btn_favorite = (ImageButton)findViewById(R.id.btn_favotrite);
        btn_favorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                favorite_tour();
            }
        });
        txt_cost_adult = (TextView)findViewById(R.id.txt_cost_adult);
        txt_cost_adult.setText(String.valueOf("$"+tour.getMoney().getAdult()));
        txt_cost_baby = (TextView)findViewById(R.id.txt_cost_baby);
        txt_cost_baby.setText(String.valueOf("$"+tour.getMoney().getBaby()));
        txt_cost_young = (TextView)findViewById(R.id.txt_cost_young);
        txt_cost_young.setText(String.valueOf("$"+tour.getMoney().getYoung()));
        txt_favorite = (TextView)findViewById(R.id.txt_favotrite);
        txt_name = (TextView)findViewById(R.id.txt_name);
        txt_name.setText(tour.getName());
        viewPager = (ViewPager)findViewById(R.id.viewpager);
        list_image.add(tour.getImage().getImage1());
        list_image.add(tour.getImage().getImage2());
        list_image.add(tour.getImage().getImage3());
        list_image.add(tour.getImage().getImage4());
        adapter_image = new slide_detail_tour_adapter(this, list_image);
        viewPager.setAdapter(adapter_image);

        final Handler handler = new Handler();
        final Runnable runnable = new Runnable() {
            @Override
            public void run() {
                if(cur_index+1 >list_image.size()){
                    cur_index = 0;
                }else{
                    cur_index ++;
                }
                viewPager.setCurrentItem(cur_index,true);

            }
        };
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(runnable);
            }
        },1000,3000);
        btn_bookingtour = (Button)findViewById(R.id.btn_bookingtour);
        btn_bookingtour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(detail_tour.this, booking_tour.class);
                it.putExtra("idtour",getIntent().getIntExtra("idtour",0));
                it.putExtra("tour",tour);
                it.putExtra("sale", sale);
                startActivity(it);
            }
        });
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        client = new client();
        restapi = client.getClient().create(restapi.class);
        final int idtour = getIntent().getIntExtra("idtour",0);
        Call<tour> tourbyid = restapi.tourbyid(idtour);
        tourbyid.enqueue(new Callback<tour>() {
            @Override
            public void onResponse(Call<tour> call, Response<tour> response) {
               if (response.isSuccessful()){
                   tour = response.body();
                   initdata();
               Call<Integer> callsaletour = restapi.getsaletour(idtour);
               callsaletour.enqueue(new Callback<Integer>() {
                   @Override
                   public void onResponse(Call<Integer> call, Response<Integer> response) {
                       if (response.isSuccessful()){
                           sale = response.body();
                           txt_sale = (TextView)findViewById(R.id.txt_sale);
                           txt_sale.setText(String.valueOf("Khuyến mãi: "+sale+"%"));
                           txt_favorite = (TextView)findViewById(R.id.txt_favorite);
                           txt_favorite.setText(String.valueOf("Lượt yêu thích: "+tour.getFavorite()));
                           initdata();
                       }else
                           Toast.makeText(detail_tour.this, "D co sale", Toast.LENGTH_SHORT).show();
                   }

                   @Override
                   public void onFailure(Call<Integer> call, Throwable t) {
                       Toast.makeText(detail_tour.this, "Error: " +t.getMessage(), Toast.LENGTH_SHORT).show();
                   }
               });

               }else
                   Toast.makeText(detail_tour.this, "Do not load data", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<tour> call, Throwable t) {
                Toast.makeText(detail_tour.this, "Error: "+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
    public void  favorite_tour(){
        if (tokenManager.getToken().getToken() !=null){
           Call<user> calluser = restapi.calluser("Bearer "+tokenManager.getToken().getToken());
           calluser.enqueue(new Callback<user>() {
               @Override
               public void onResponse(Call<user> call, Response<user> response) {
                   u = response.body();
                   Call<favorite_tour> addfavoritetour = restapi.addtourfavorite(u.getId(), tour.getId());
                   addfavoritetour.enqueue(new Callback<favorite_tour>() {
                       @Override
                       public void onResponse(Call<favorite_tour> call, Response<favorite_tour> response) {
                           if (response.isSuccessful()){
                               Toast.makeText(detail_tour.this, "Bạn vừa thích "+tour.getName(), Toast.LENGTH_SHORT).show();
                           }else {
                               Toast.makeText(detail_tour.this, "Bạn đã thích tour này rồi !!! ", Toast.LENGTH_SHORT).show();
                           }
                       }

                       @Override
                       public void onFailure(Call<favorite_tour> call, Throwable t) {
                           Toast.makeText(detail_tour.this, "Error: "+t.getMessage(), Toast.LENGTH_SHORT).show();
                       }
                   });
               }

               @Override
               public void onFailure(Call<user> call, Throwable t) {

               }
           });
        }else{
            AlertDialog.Builder login = new AlertDialog.Builder(this);
            login.setTitle("Xác nhận");
            login.setMessage("Bạn phải đăng nhập để thực hiện chức năng yêu thích !!!");
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
}
