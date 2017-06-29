package com.afomic.bakingapp.util;



import com.afomic.bakingapp.model.Food;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by afomic on 6/15/17.
 *
 */

public interface FoodAPI {
    @GET("/android-baking-app-json")
    Call<List<Food>> getFood();

}
