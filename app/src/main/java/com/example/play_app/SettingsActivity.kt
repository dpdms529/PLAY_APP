package com.example.play_app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_settings.*

class SettingsActivity : AppCompatActivity() {
    companion object {
        lateinit var pref: PreferenceUtil
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        pref = PreferenceUtil(applicationContext)

        settings_back_button.setOnClickListener {
            finish()
        }

        var soundOn = pref.getBoolean("sound", true)
        switch_sound.isChecked = soundOn
        switch_sound.setOnClickListener {
            soundOn = switch_sound.isChecked
            pref.setBoolean("sound", this, soundOn)
        }

        app_info_button.setOnClickListener {
            val intent = Intent(this, AppInfoActivity::class.java)
            startActivity(intent)
        }

        play_list_button.setOnClickListener {
            val intent = Intent(this, PlayListActivity::class.java)
            startActivity(intent)
        }
    }
}