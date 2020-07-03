package com.sict.app.travellite_app.model;

import android.content.SharedPreferences;

public class TokenManager {
    private SharedPreferences pref ;
    private SharedPreferences.Editor editor;
    private static TokenManager INSTANCE = null;

    public TokenManager(SharedPreferences pref){
        this.pref = pref;
        this.editor = pref.edit();
    }
     public static synchronized TokenManager getInstance(SharedPreferences pref){
         if(INSTANCE == null){
             INSTANCE = new TokenManager(pref);
         }
         return INSTANCE;
     }
    public void saveToken(AccessToken accessToken){
        editor.putString("ACCESS_TOKEN",accessToken.getToken()).commit();
    }
    public void removeToken(){
        editor.remove("ACCESS_TOKEN").commit();
    }
    public AccessToken getToken(){
        AccessToken a = new AccessToken();
        a.setToken(pref.getString("ACCESS_TOKEN",null));
        return a;
    }
}
