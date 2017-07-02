package com.afomic.bakingapp.fragment;

import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.media.MediaMetadataRetriever;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


import com.afomic.bakingapp.R;
import com.afomic.bakingapp.data.Constant;
import com.afomic.bakingapp.widget.IngredientProvider;

import java.util.HashMap;

/**
 * Created by afomic on 6/16/17.
 *
 */

public class FoodDetailFragment extends Fragment {
    public static final String BUNDLE_FOOD_ID="id";
    private int mFoodID=0;
    private SharedPreferences sharedPreferences;
    private int[] imageIDs={0, R.drawable.nutella,R.drawable.brownies,
            R.drawable.yellow_cake,R.drawable.cheese};
    private String[] names={"","Nutella pie","Brownies","Yellow Cake","Cheese cake"};
    public static  FoodDetailFragment getInstance(int foodID){
        FoodDetailFragment fragment =new FoodDetailFragment();
        Bundle args=new Bundle();
        args.putInt(BUNDLE_FOOD_ID,foodID);
        fragment.setArguments(args);
        return fragment;

    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        Bundle args=getArguments();
        if(args!=null){
            mFoodID=args.getInt(BUNDLE_FOOD_ID);
        }
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.food_detail_layout,container,false);

        final ImageView mFoodImage=(ImageView) v.findViewById(R.id.iv_food_image);

        TabLayout tabLayout=(TabLayout) v.findViewById(R.id.food_detail_tablayout);

        Toolbar toolbar=(Toolbar)v.findViewById(R.id.food_detail_toolbar);

        AppCompatActivity activity=(AppCompatActivity) getActivity();

        activity.setSupportActionBar(toolbar);

        mFoodImage.setImageResource(imageIDs[mFoodID]);

        FloatingActionButton populateActionButton=(FloatingActionButton) v.findViewById(R.id.fab);

        populateActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), IngredientProvider.class);
                intent.putExtra(Constant.BUNDLE_FOOD_NAME,names[mFoodID]);
                sharedPreferences.edit().putInt(Constant.BUNDLE_FOOD_ID,mFoodID).apply();
                getActivity().sendBroadcast(intent);

            }
        });
        sharedPreferences=getActivity().getSharedPreferences(getActivity().getPackageName(),
                Context.MODE_PRIVATE);

        ViewPager mPager=(ViewPager) v.findViewById(R.id.detail_pager);
        mPager.setAdapter(new FragmentStatePagerAdapter(getFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                if(position==0){
                    return IngredientFragment.getInstance(mFoodID);
                }
                return FoodStepsFragment.getInstance(mFoodID);
            }

            @Override
            public int getCount() {
                return 2;
            }

            @Override
            public CharSequence getPageTitle(int position) {
                if(position==0) return "Ingredients";
                return "Recipe Steps";
            }
        });
        tabLayout.setupWithViewPager(mPager);

        return v;
    }


}
