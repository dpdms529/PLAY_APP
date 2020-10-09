package com.coala.play_app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.gms.ads.AdRequest
import kotlinx.android.synthetic.main.activity_settings.*

class SettingsActivity : AppCompatActivity() {
    companion object
    {
        lateinit var pref: PreferenceUtil
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        HomeActivity.mAdView = findViewById(R.id.adViewSettings)
        val adRequest = AdRequest.Builder().build()
        HomeActivity.mAdView.loadAd(adRequest)

        pref = PreferenceUtil(applicationContext)

        settings_back_button.setOnClickListener {
            finish()
        }

        switch_sound.isChecked = pref.getBoolean("sound",true)
        switch_sound.setOnClickListener{
            pref.setBoolean("sound",this,switch_sound.isChecked)
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