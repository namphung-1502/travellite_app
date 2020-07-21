package com.sict.app.travellite_app.rest_api;

import com.sict.app.travellite_app.booking_hotel;
import com.sict.app.travellite_app.model.AccessToken;
import com.sict.app.travellite_app.model.bill;
import com.sict.app.travellite_app.model.bookguide;
import com.sict.app.travellite_app.model.favorite_hotel;
import com.sict.app.travellite_app.model.favorite_tour;
import com.sict.app.travellite_app.model.hotel;
import com.sict.app.travellite_app.model.tour;
import com.sict.app.travellite_app.model.tour_sale;
import com.sict.app.travellite_app.model.user;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface restapi {
   @GET("hotels")
   Call<List<hotel>> gethotel();

   @GET("tophotels")
   Call<List<hotel>> topfavoritehotel();

   @GET("favoritehotels/{id}")
   Call<List<hotel>> callfavoritehotel(@Path("id") int id);

   @GET("hotelbyid/{id}")
   Call<hotel> callhotel(@Path("id") int id);

   @DELETE("favoritehotel")
   Call<String> deletefavoritehotel(@Query("iduser") int iduser, @Query("idhotel") int idhotel);

   @POST("addhotelfavorite")
   Call<favorite_hotel> addfavoritehotel(@Query("iduser") int iduser, @Query("idhotel") int idhotel);

   @POST("bookinghotel")
   Call<com.sict.app.travellite_app.model.booking_hotel> booking_hotel(@Query("name_customer") String name_customer, @Query("id_user") int id_user,
                                     @Query("phone") String phone, @Query("nationality") String nationality, @Query("card_number") String card_number,
                                     @Query("id_hotel") int id_hotel, @Query("count_room") int count_room, @Query("date") String date);

   @GET("toptourfavorite")
   Call<List<tour>> toptourfavorite();

   @GET("favoritetour/{id}")
   Call<List<tour>> callfavoritetour(@Path("id") int id);

   @DELETE("favoritetour")
   Call<String> deletefavoritetour(@Query("iduser") int iduser, @Query("idtour") int idtour);

   @POST("addtourfavorite")
   Call<favorite_tour> addtourfavorite(@Query("iduser") int iduser, @Query("idtour") int idtour);

   @GET("tourbyid/{id}")
   Call<tour> tourbyid(@Path("id") int id);

   @GET("getsaletour/{id}")
   Call<Integer> getsaletour(@Path("id") int id);

   @POST("bookingtour")
   Call<String> bookingtour(@Query("idtour") int idtour, @Query("iduser") int iduser, @Query("nametour") String nametour, @Query("departureDay") String departureDay,
                            @Query("namecustomer") String namecustomer, @Query("phone") String phone, @Query("address") String address,
                            @Query("adult") int adult, @Query("young") int young, @Query("baby") int baby, @Query("total") int total);
   @GET("billbyuser/{iduser}")
   Call<List<bill>> billbyuser(@Path("iduser") int iduser);

   @DELETE("deletebill/{id}")
   Call<String> deletebill(@Path("id") int id);

   @GET("bookguide")
   Call<List<bookguide>> getbookguide();

   @FormUrlEncoded
   @POST("register")
   Call<String> register(@Field("name") String name, @Field("address") String address,
                         @Field("email") String email, @Field("phone") String phone, @Field("password") String password);

   @FormUrlEncoded
   @POST("login")
   Call<AccessToken> calltoken(@Field("email") String email, @Field("password") String pass);

   @Headers("Content-Type: application/json")
   @POST("details")
   Call<user> calluser(@Header("Authorization") String token);

   @GET("toursale")
   Call<List<tour_sale>> gettoursale();

   //User
   @PUT("user/{id}")
   Call<user> updateuser(@Path("id") int id,@Body user user);

   @FormUrlEncoded
   @PUT("changepass")
   Call<user> changepass(@Field("id") int id, @Field("newpass") String newpass);
}
