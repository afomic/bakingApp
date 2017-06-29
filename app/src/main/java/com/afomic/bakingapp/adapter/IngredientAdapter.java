package com.afomic.bakingapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.afomic.bakingapp.R;
import com.afomic.bakingapp.model.Ingredient;

import java.util.List;

/**
 * Created by afomic on 6/17/17.
 */

public class IngredientAdapter extends RecyclerView.Adapter<IngredientAdapter.IngredientHolder> {
    private Context context;
    private List<Ingredient> ingredients;
    public IngredientAdapter(Context cxt, List<Ingredient> array){
        context=cxt;
        ingredients=array;
    }
    @Override
    public IngredientHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context).inflate(R.layout.ingredient_item,parent,false);
        return new IngredientHolder(v);
    }

    @Override
    public void onBindViewHolder(IngredientHolder holder, int position) {
        Ingredient mItem=ingredients.get(position);
        holder.mIngredientText.setText(mItem.toString());
    }

    @Override
    public int getItemCount() {
        if(ingredients==null){
            return 0;
        }
        return ingredients.size();
    }

    public class IngredientHolder extends RecyclerView.ViewHolder{
        TextView mIngredientText;
        public IngredientHolder(View itemView) {
            super(itemView);
            mIngredientText=(TextView) itemView.findViewById(R.id.tv_ingredient);

        }
    }
}
