package com.example.play_app

import android.app.Activity
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock

class StartActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        SystemClock.sleep(1000)
        val intent = Intent(this,HomeActivity::class.java)
        val startpref : SharedPreferences = getSharedPreferences("checkFirst", Activity.MODE_PRIVATE)
        val checkfirst : Boolean = startpref.getBoolean("checkFirst",false)
        if(checkfirst==false) {
            val editor: SharedPreferences.Editor = startpref.edit()
            editor.putBoolean("checkFirst",true)
            editor.commit()

            val StartIntent :Intent = Intent(this,TutorialActivity::class.java)
            startActivity(StartIntent)
            finish()
        }
        else {
            startActivity(intent)
            finish()
        }

    }
}