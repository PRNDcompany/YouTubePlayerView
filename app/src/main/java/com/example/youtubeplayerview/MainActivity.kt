package com.example.youtubeplayerview

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.btn_sample1).setOnClickListener {
            startActivity(Intent(this, Sample1Activity::class.java))
        }

        findViewById<Button>(R.id.btn_sample2).setOnClickListener {
            startActivity(Intent(this, Sample2Activity::class.java))
        }

    }

}