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
import com.afomic.bakingapp.adapter.IngredientAdapter;
import com.afomic.bakingapp.data.DataAccessLayer;
import com.afomic.bakingapp.model.Ingredient;

import java.util.List;

/**
 * Created by afomic on 6/16/17.
 *
 */

public class IngredientFragment extends Fragment {
    public static final String BUNDLE_FOOD_ID="id";
    private int mFoodID=0;
    public static  IngredientFragment getInstance(int foodID){
        IngredientFragment fragment =new IngredientFragment();
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
        View v=inflater.inflate(R.layout.ingredient_layout,container,false);

        DataAccessLayer DbAcess=new DataAccessLayer(getActivity());

        RecyclerView ingredientList=(RecyclerView) v.findViewById(R.id.ingredient_list);

        RecyclerView.LayoutManager manager= new LinearLayoutManager(getActivity());

        ingredientList.setLayoutManager(manager);

        List<Ingredient> ingredients=DbAcess.getIngredient(mFoodID);

        IngredientAdapter adapter=new IngredientAdapter(getActivity(),ingredients);

        ingredientList.setAdapter(adapter);

        return v;
    }
}
