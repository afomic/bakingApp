package com.afomic.bakingapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import com.afomic.bakingapp.data.Constant;
import com.afomic.bakingapp.fragment.FoodDetailFragment;


public class FoodDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_detail);
        Bundle mBundle=getIntent().getExtras();
        int foodID=0;
        if(mBundle!=null){
            foodID=mBundle.getInt(Constant.BUNDLE_FOOD_ID);
        }
        FragmentManager fm=getSupportFragmentManager();
        Fragment fragment=fm.findFragmentById(R.id.food_detail_container);
        if(fragment==null){
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.food_detail_container, FoodDetailFragment.getInstance(foodID)).commit();
        }

    }
}
