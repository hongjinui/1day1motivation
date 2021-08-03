package com.naple.android.one_day_one_motivation.view.videoscreen

import android.os.Bundle
import com.google.android.youtube.player.YouTubeBaseActivity
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import com.google.android.youtube.player.YouTubePlayerView
import com.naple.android.one_day_one_motivation.databinding.ActivityVideoScreenBinding

class VideoScreenActivity : YouTubeBaseActivity() {

    private lateinit var binding: ActivityVideoScreenBinding
    private lateinit var youtubeView: YouTubePlayerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityVideoScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val bundle: Bundle? = intent.extras
        val videoId: String? = bundle?.getString("videoId")

        youtubeView = binding.YouTubePlayerView
        youtubeView.initialize("video",object : YouTubePlayer.OnInitializedListener{
            override fun onInitializationSuccess(provider: YouTubePlayer.Provider?, youTubePlayer: YouTubePlayer?, b: Boolean) {
                youTubePlayer?.setFullscreen(true)
                youTubePlayer?.setShowFullscreenButton(false)
                youTubePlayer?.loadVideo(videoId)
            }

            override fun onInitializationFailure(provider: YouTubePlayer.Provider?, youTubeInitializationResult: YouTubeInitializationResult?) {
            }

        })

    }

}