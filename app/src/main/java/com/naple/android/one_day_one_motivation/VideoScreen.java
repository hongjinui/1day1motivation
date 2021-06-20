package com.naple.android.one_day_one_motivation;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.NonNull;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;


public class VideoScreen extends YouTubeBaseActivity {

    YouTubePlayerView youtubeView;
    YouTubePlayer.OnInitializedListener listener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_screen);

        Bundle bundle = getIntent().getExtras();
        String videoId = bundle.getString("videoId");

        youtubeView = findViewById(R.id.YouTubePlayerView);
        listener = new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                youTubePlayer.setFullscreen(true);
                youTubePlayer.setShowFullscreenButton(false);
                youTubePlayer.loadVideo(videoId); //
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

            }
        };

        youtubeView.initialize(videoId, listener);
    }
        @Override
        public void onConfigurationChanged (@NonNull Configuration newConfig){
            super.onConfigurationChanged(newConfig);
        }

}