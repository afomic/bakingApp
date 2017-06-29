package com.afomic.bakingapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.afomic.bakingapp.adapter.StepAdapter;
import com.afomic.bakingapp.data.Constant;
import com.afomic.bakingapp.data.DataAccessLayer;
import com.afomic.bakingapp.fragment.FoodStepDetailFragment;
import com.afomic.bakingapp.model.RecipeStep;


public class StepVideoActivity extends AppCompatActivity {
    private boolean twoPane;
    FragmentManager fm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step_video);
        twoPane=findViewById(R.id.step_list)!=null;
        DataAccessLayer dataSource=new DataAccessLayer(this);
        RecipeStep step=getIntent().getParcelableExtra(Constant.BUNDLE_RECIPE_STEP);
        if(twoPane){
            RecyclerView stepList=(RecyclerView) findViewById(R.id.step_list);
            StepAdapter adapter=new StepAdapter(this,dataSource.getSteps(step.getFoodID()));
            RecyclerView.LayoutManager manager=new LinearLayoutManager(this);
            stepList.setLayoutManager(manager);
            stepList.setAdapter(adapter);

        }

        fm=getSupportFragmentManager();
        Fragment fragment=fm.findFragmentById(R.id.step_details_container);
        if(fragment==null){
            fm.beginTransaction().add(R.id.step_details_container, FoodStepDetailFragment.newInstance(step))
                    .commit();
        }

    }
}
