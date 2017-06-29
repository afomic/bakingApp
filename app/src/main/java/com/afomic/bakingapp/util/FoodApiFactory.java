package com.afomic.bakingapp.util;


import com.afomic.bakingapp.data.Constant;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by afomic on 05-May-17.
 *
 */
public class FoodApiFactory {
    private  FoodAPI mAPI;
    private static FoodApiFactory mFactory;
    public static FoodApiFactory getInstance(){
        if(mFactory==null){
            mFactory=new FoodApiFactory();
        }
        return mFactory;
    }
    private FoodApiFactory(){
        Gson mGson= new GsonBuilder().setLenient().create();
        Retrofit mRetrofit=new Retrofit.Builder()
                .baseUrl(Constant.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(mGson)).build();
        mAPI=mRetrofit.create(FoodAPI.class);

    }

    public FoodAPI getFoodApi(){
        return mAPI;
    }
}
