package com.example.youtubeplayerview

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class Sample2Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sample2)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, Sample2Fragment.newInstance())
                .commitNow()
        }
    }

}