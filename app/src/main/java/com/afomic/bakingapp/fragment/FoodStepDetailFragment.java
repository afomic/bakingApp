package com.afomic.bakingapp.fragment;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.afomic.bakingapp.R;
import com.afomic.bakingapp.model.RecipeStep;
import com.afomic.bakingapp.util.VideoPlayer;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;

/**
 * Created by afomic on 6/20/17.
 */

public class FoodStepDetailFragment extends Fragment {
    private static final String BUNDLE_RECIPE_STEP="step";
    private RecipeStep recipeStep;
    private SimpleExoPlayer mExoPlayer;
    private VideoPlayer videoPlayer;
    SimpleExoPlayerView exoPlayerView;
    public static FoodStepDetailFragment newInstance(RecipeStep recipeStep) {
        Bundle args = new Bundle();
        args.putParcelable(BUNDLE_RECIPE_STEP,recipeStep);
        FoodStepDetailFragment fragment = new FoodStepDetailFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        recipeStep=getArguments().getParcelable(BUNDLE_RECIPE_STEP);
    }

    @Override
    public void onStart() {
        super.onStart();
        videoPlayer = new VideoPlayer.Builder(exoPlayerView)
                .exoPlayer(mExoPlayer)
                .context(getActivity())
                .uri(Uri.parse(recipeStep.getVideoUrl()))
                .build();
        videoPlayer.initializePlayer();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.food_step_detail,container,false);
        exoPlayerView=v.findViewById(R.id.exoplayer_view);
        TextView mDescription=(TextView) v.findViewById(R.id.tv_step_details);
        mDescription.setText(recipeStep.getDesription());


        return v;
    }

    @Override
    public void onPause() {
        videoPlayer.releasePlayer();
        super.onPause();
    }

}
