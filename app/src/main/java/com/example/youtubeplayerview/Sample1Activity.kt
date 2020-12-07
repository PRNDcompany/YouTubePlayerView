package com.example.youtubeplayerview

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kr.co.prnd.YouTubePlayerView

class Sample1Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sample1)

        val youTubePlayerView = findViewById<YouTubePlayerView>(R.id.you_tube_player_view)
        youTubePlayerView.play(VIDEO_ID)
        //youTubePlayerView.playList(PLAYLIST_ID, true)
    }

    companion object {
        private const val VIDEO_ID = "m2SZ6RV_J7I"
        private const val PLAYLIST_ID = "PLMuIA_APijedfD2KIJcpq0tKx7Z2JSaBZ"
    }

}