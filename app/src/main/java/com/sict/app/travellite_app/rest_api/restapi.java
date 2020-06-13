package com.sict.app.travellite_app.rest_api;

import com.sict.app.travellite_app.model.bookguide;
import com.sict.app.travellite_app.model.hotel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface restapi {
   @GET("hotels")
   Call<List<hotel>> gethotel();

   @GET("bookguide")
   Call<List<bookguide>> getbookguide();
}
