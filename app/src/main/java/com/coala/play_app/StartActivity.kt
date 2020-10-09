package com.coala.play_app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock

class StartActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        SystemClock.sleep(1000)
        val intent = Intent(this,HomeActivity::class.java)
        startActivity(intent)
        finish()
    }
}