package com.example.youtubeplayerview

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kr.co.prnd.YouTubePlayerView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val youTubePlayerView = findViewById<YouTubePlayerView>(R.id.you_tube_player_fragment_view)
        youTubePlayerView.play(VIDEO_ID)
    }

    companion object {
        private const val VIDEO_ID = "m2SZ6RV_J7I"
    }
}