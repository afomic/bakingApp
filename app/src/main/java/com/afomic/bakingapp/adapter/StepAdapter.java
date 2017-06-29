package com.afomic.bakingapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.afomic.bakingapp.R;
import com.afomic.bakingapp.StepVideoActivity;
import com.afomic.bakingapp.data.Constant;
import com.afomic.bakingapp.model.RecipeStep;

import java.util.List;

;

/**
 * Created by afomic on 6/17/17.
 *
 */

public class StepAdapter  extends RecyclerView.Adapter<StepAdapter.StepHolder>{
    private Context context;
    private List<RecipeStep> steps;
    public StepAdapter(Context cxt,List<RecipeStep> array){
        context=cxt;
        steps=array;

    }
    @Override
    public StepHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context).inflate(R.layout.step_item,parent,false);
        return new StepHolder(v);
    }

    @Override
    public void onBindViewHolder(StepHolder holder, int position) {
        RecipeStep mItem=steps.get(position);
        holder.mStepName.setText(mItem.getShortDescription());
    }

    @Override
    public int getItemCount() {
        if(steps==null){
            return 0;
        }
        return steps.size();
    }

    public class StepHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        ImageView mStepVideoThumbnail;
        TextView mStepName;
        public StepHolder(View itemView) {
            super(itemView);
            mStepName=(TextView) itemView.findViewById(R.id.tv_step_name);
            mStepVideoThumbnail=(ImageView) itemView.findViewById(R.id.iv_food_image);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            RecipeStep mItem=steps.get(getAdapterPosition());
            Intent intent=new Intent(context, StepVideoActivity.class);
            intent.putExtra(Constant.BUNDLE_RECIPE_STEP,mItem);
            context.startActivity(intent);
        }
    }

}

