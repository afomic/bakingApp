package com.afomic.bakingapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.afomic.bakingapp.FoodDetailActivity;
import com.afomic.bakingapp.R;
import com.afomic.bakingapp.data.Constant;
import com.afomic.bakingapp.fragment.FoodDetailFragment;
import com.afomic.bakingapp.model.Food;

import java.util.List;

/**
 * Created by afomic on 6/15/17.
 */

public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.FoodHolder>{
    private Context mContext;
    private List<Food> mFoods;
    private boolean twoPane;
    private FragmentManager fm;
    private int[] imageIDs={R.drawable.nutella, R.drawable.brownies,R.drawable.yellow_cake,R.drawable.cheese};
    public FoodAdapter(Context context, List<Food> foods, final boolean twoPane, FragmentManager fm){
        mContext=context;
        mFoods=foods;
        this.fm=fm;
        this.twoPane=twoPane;
    }
    @Override
    public FoodHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(mContext).inflate(R.layout.food_item,parent,false);
        return new FoodHolder(v);
    }

    @Override
    public void onBindViewHolder(FoodHolder holder, int position) {
        final Food mItem=mFoods.get(position);
        holder.mFoodName.setText(mItem.getName());
        holder.mFoodImage.setImageResource(imageIDs[position]);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(twoPane){
                    FoodDetailFragment fragment = FoodDetailFragment.getInstance(mItem.getID());
                    fm.beginTransaction().add(R.id.main_container,fragment).commit();
                }else {
                    Intent intent=new Intent(mContext,FoodDetailActivity.class);
                    intent.putExtra(Constant.BUNDLE_FOOD_ID,mItem.getID());
                    mContext.startActivity(intent);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        if(mFoods==null){
            return 0;
        }
        return mFoods.size();
    }

    public class FoodHolder extends RecyclerView.ViewHolder{
        ImageView mFoodImage;
        TextView mFoodName;
        int foodID;
        View itemView;
        public FoodHolder(View itemView) {
            super(itemView);
            mFoodImage=(ImageView) itemView.findViewById(R.id.food_image);
            mFoodName=(TextView) itemView.findViewById(R.id.tv_food_name);
            this.itemView= itemView;
            foodID=getAdapterPosition();
        }

    }

}
