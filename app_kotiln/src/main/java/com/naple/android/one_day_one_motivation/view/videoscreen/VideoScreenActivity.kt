package com.naple.android.one_day_one_motivation.view.videoscreen

import android.content.res.Configuration
import android.os.Bundle
import com.google.android.youtube.player.YouTubeBaseActivity
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import com.google.android.youtube.player.YouTubePlayerView
import com.naple.android.one_day_one_motivation.R
import com.naple.android.one_day_one_motivation.databinding.ActivityVideoScreenBinding
import com.naple.android.one_day_one_motivation.view.videoscreen.presenter.VideoScreenContract


class VideoScreenActivity : YouTubeBaseActivity() , VideoScreenContract.View{

    private lateinit var binding: ActivityVideoScreenBinding
    private lateinit var youtubeView: YouTubePlayerView
    private var listener : YouTubePlayer.OnInitializedListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityVideoScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        youtubeView = findViewById(R.id.YouTubePlayerView)

    }

    override fun onStart() {
        super.onStart()

        val bundle: Bundle? = intent.extras
        val videoId: String? = bundle?.getString("videoId")

        listener = object : YouTubePlayer.OnInitializedListener {
            override fun onInitializationSuccess(provider: YouTubePlayer.Provider, youTubePlayer: YouTubePlayer, b: Boolean) {
                youTubePlayer.setFullscreen(true)
                youTubePlayer.setShowFullscreenButton(false)
                youTubePlayer.loadVideo(videoId)
            }

            override fun onInitializationFailure(provider: YouTubePlayer.Provider, youTubeInitializationResult: YouTubeInitializationResult) {}
        }

        youtubeView.initialize(videoId, listener)

    }

    override fun onStop() {
        super.onStop()

        listener = null
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
    }
}