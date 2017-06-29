package com.afomic.bakingapp;

import android.content.res.Configuration;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.test.espresso.IdlingResource;
import android.support.test.espresso.core.deps.guava.annotations.VisibleForTesting;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.afomic.bakingapp.adapter.FoodAdapter;
import com.afomic.bakingapp.data.DataAccessLayer;
import com.afomic.bakingapp.model.Food;
import com.afomic.bakingapp.util.FoodAPI;
import com.afomic.bakingapp.util.FoodApiFactory;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements Callback<List<Food>> {

    RecyclerView mFoodList;
    boolean twoPane=false;
    DataAccessLayer accessLayer;
    RecyclerView.LayoutManager manager;
    ProgressBar mProgressBar;
    TextView emptyView;

    @Nullable
    private SimpleIdlingResource mIdlingResource;

    /**
     * Only called from test, creates and returns a new {@link SimpleIdlingResource}.
     */
    @VisibleForTesting
    @NonNull
    public IdlingResource getIdlingResource() {
        if (mIdlingResource == null) {
            mIdlingResource = new SimpleIdlingResource();
        }
        return mIdlingResource;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mProgressBar=(ProgressBar) findViewById(R.id.progress_bar);
        emptyView=(TextView) findViewById(R.id.tv_empty);

        twoPane=findViewById(R.id.main_container)!=null;
        Toolbar toolbar=(Toolbar) findViewById(R.id.main_toolabr);
        setSupportActionBar(toolbar);
        mFoodList=(RecyclerView) findViewById(R.id.food_list);
        if(isLandScape()&&(!twoPane)){
            int screenWith=getResources().getConfiguration().screenWidthDp;
            int numberOfColumn=screenWith/160;
            manager=new GridLayoutManager(this,numberOfColumn);

        }else {
            manager= new LinearLayoutManager(this);
        }

        mFoodList.setLayoutManager(manager);

        //get food api from the factory
        FoodAPI api= FoodApiFactory.getInstance().getFoodApi();
        //get the database access layer
        accessLayer=new DataAccessLayer(this);
        if(accessLayer.isDatabaseEmpty()){
            setIdlingResourceState(false);
            Call<List<Food>> mCall=api.getFood();
            mProgressBar.setVisibility(View.VISIBLE);
            mCall.enqueue(this);
        }else{
            List<Food> foods=accessLayer.getFood();
            FoodAdapter adapter=new FoodAdapter(this,foods,twoPane,getSupportFragmentManager());
            mFoodList.setAdapter(adapter);
        }


    }

    @Override
    public void onResponse(Call<List<Food>> call, Response<List<Food>> response) {
        mProgressBar.setVisibility(View.GONE);
        if(response.isSuccessful()){
            setIdlingResourceState(true);
            List<Food> foods=response.body();
            populateDatabase(foods);
            FoodAdapter adapter=new FoodAdapter(MainActivity.this,foods,twoPane,getSupportFragmentManager());
            mFoodList.setAdapter(adapter);
        }

    }

    @Override
    public void onFailure(Call<List<Food>> call, Throwable t) {
        emptyView.setVisibility(View.VISIBLE);
        mProgressBar.setVisibility(View.GONE);
        t.printStackTrace();

    }
    public void populateDatabase(List<Food> foods){
        accessLayer.addFoods(foods);
        for (Food food:foods){
            accessLayer.addIngredients(food.getIngredients(),food.getID());
            accessLayer.addSteps(food.getRecipeSteps(),food.getID());
        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {

        super.onConfigurationChanged(newConfig);
    }
    public boolean isLandScape(){
        return Configuration.ORIENTATION_LANDSCAPE==getResources().getConfiguration().orientation;
    }
    public void setIdlingResourceState(boolean state){
        if (mIdlingResource == null)return;
        mIdlingResource.setIdleState(state);
    }
}
