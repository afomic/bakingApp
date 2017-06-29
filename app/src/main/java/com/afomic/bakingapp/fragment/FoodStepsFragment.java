package com.afomic.bakingapp.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.afomic.bakingapp.R;
import com.afomic.bakingapp.adapter.StepAdapter;
import com.afomic.bakingapp.data.DataAccessLayer;
import com.afomic.bakingapp.model.RecipeStep;

import java.util.List;

/**
 * Created by afomic on 6/16/17.
 */

public class FoodStepsFragment extends Fragment {
    public static final String BUNDLE_FOOD_ID="id";
    private int mFoodID=0;
    public static  FoodStepsFragment getInstance(int foodID){
        FoodStepsFragment fragment =new FoodStepsFragment();
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
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.food_steps_layout,container,false);

        DataAccessLayer DbAcess=new DataAccessLayer(getActivity());

        RecyclerView foodSteps=(RecyclerView) v.findViewById(R.id.step_list);
        RecyclerView.LayoutManager manager= new LinearLayoutManager(getActivity());
        foodSteps.setLayoutManager(manager);
        List<RecipeStep> recipeSteps =DbAcess.getSteps(mFoodID);
        StepAdapter adapter=new StepAdapter(getActivity(),recipeSteps);

        foodSteps.setAdapter(adapter);
        return v;
    }
}
