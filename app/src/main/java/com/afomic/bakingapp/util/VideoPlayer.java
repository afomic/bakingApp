package com.afomic.bakingapp.util;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.net.Uri;

import com.afomic.bakingapp.R;
import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.ExoPlaybackException;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.LoadControl;
import com.google.android.exoplayer2.PlaybackParameters;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.Timeline;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelectionArray;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;

/**
 * Created by afomic on 6/25/17.
 *
 */

public class VideoPlayer implements ExoPlayer.EventListener{
    private SimpleExoPlayer exoPlayer;
    private final SimpleExoPlayerView exoPlayerView;
    private final Context context;
    private final Uri mediaUri;

    public static class Builder {
        //Required parameter
        private final SimpleExoPlayerView exoPlayerView;

        //Optional parameters
        private SimpleExoPlayer exoPlayer;
        private Context context;
        private Uri mediaUri;

        public Builder(SimpleExoPlayerView exoPlayerView) {
            this.exoPlayerView = exoPlayerView;
        }

        public Builder exoPlayer(SimpleExoPlayer value) {
            exoPlayer = value; return this;
        }

        public Builder context(Context value) {
            context = value; return this;
        }

        public Builder uri(Uri value) {
            mediaUri = value; return this;
        }

        public VideoPlayer build() {
            return new VideoPlayer(this);
        }
    }

    public VideoPlayer(Builder builder) {
        exoPlayer = builder.exoPlayer;
        exoPlayerView = builder.exoPlayerView;
        context = builder.context;
        mediaUri = builder.mediaUri;
    }

    public void initializePlayer() {
        if (exoPlayer == null) {
            // Create an instance of the ExoPlayer.
            TrackSelector trackSelector = new DefaultTrackSelector();
            LoadControl loadControl = new DefaultLoadControl();
            exoPlayer = ExoPlayerFactory.newSimpleInstance(context, trackSelector, loadControl);
            exoPlayerView.setPlayer(exoPlayer);

            exoPlayer.addListener(this);

            // Prepare the MediaSource.
            prepareMediaSource(mediaUri);
        }
    }

    public void prepareMediaSource(Uri mediaUri) {
        String userAgent = Util.getUserAgent(context, "Recipe");
        MediaSource mediaSource = new ExtractorMediaSource(mediaUri, new DefaultDataSourceFactory(
                context, userAgent), new DefaultExtractorsFactory(), null, null);
        exoPlayer.prepare(mediaSource);
        exoPlayer.setPlayWhenReady(true);
    }

    public void releasePlayer() {
        if(exoPlayer!=null){
            exoPlayer.stop();
            exoPlayer.release();
            exoPlayer = null;
        }

    }
    @Override
    public void onTimelineChanged(Timeline timeline, Object o) {

    }

    @Override
    public void onTracksChanged(TrackGroupArray trackGroupArray, TrackSelectionArray trackSelectionArray) {

    }

    @Override
    public void onLoadingChanged(boolean b) {

    }

    @Override
    public void onPlayerStateChanged(boolean b, int i) {

    }

    @Override
    public void onPlayerError(ExoPlaybackException e) {
        exoPlayerView.setDefaultArtwork(BitmapFactory.decodeResource
                (context.getResources(), R.drawable.download));
    }

    @Override
    public void onPositionDiscontinuity() {

    }

    @Override
    public void onPlaybackParametersChanged(PlaybackParameters playbackParameters) {

    }
}
