package com.mhm.islami.API;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIManager {

    private static Retrofit retrofit;
    private static Retrofit getInstance(){

        if (retrofit==null){
            retrofit = new Retrofit.Builder()
                    .baseUrl("https://mp3quran.net/api/radio/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }


      return retrofit;
    }

    public static WebServices getAPIs(){
        return getInstance().create(WebServices.class);
    }


}
