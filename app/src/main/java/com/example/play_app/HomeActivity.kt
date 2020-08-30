package com.example.play_app

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.WindowManager.LayoutParams.*
import android.view.animation.AnimationUtils
import android.widget.*
import androidx.appcompat.app.AlertDialog
import com.example.play_app.db.PlayDatabase
import com.example.play_app.db.entity.Play
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {
    companion object {
        lateinit var pref: PreferenceUtil
        var is_runned: Boolean = false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)


        pref = PreferenceUtil(applicationContext)
        //TODO: 앱을 다시 켤때 마다 매번 초기화 해주는건가요??
//        pref.setBoolean("indoor",this,false)
//        pref.setBoolean("outdoor",this,false)
//        pref.setBoolean("free",this,false)
//        pref.setBoolean("pay",this,false)
//        pref.setBoolean("alone",this,false)
//        pref.setBoolean("friend",this,false)
//        pref.setBoolean("active",this,false)
//        pref.setBoolean("inactive",this,false)


        setting.setOnClickListener {
            val intent = Intent(this, SettingsActivity::class.java)
            startActivity(intent)
        }

        start_button.setOnClickListener {

            //TODO: 버튼을 연타 했을때 중복 실행 방지.
            if (!is_runned) {
                start_button.text = "STOP"
                is_runned = true

                val mediaPlayer1 = MediaPlayer.create(this, R.raw.popup4)
                //TODO: 저장된 옵션에서 설정 불러오기.
                if (pref.getBoolean("sound", true)) mediaPlayer1.start()
                val roulette: ImageView = findViewById<ImageView>(R.id.roulette)
                val rotate_animation = AnimationUtils.loadAnimation(this, R.anim.rotate)
                roulette.startAnimation(rotate_animation)
                Handler().postDelayed({
                    //TODO:중지 버튼을 눌렀을 경우를 고려.
                    if (is_runned) {
                        var indoor = if (pref.getBoolean("indoor", false)) "실내" else null
                        var outdoor = if (pref.getBoolean("outdoor", false)) "실외" else null
                        var free = if (pref.getBoolean("free", false)) "무료" else null
                        var pay = if (pref.getBoolean("pay", false)) "유료" else null
                        var alone = if (pref.getBoolean("alone", false)) "혼자가능" else null
                        var friend = if (pref.getBoolean("friend", false)) "친구필요" else null
                        var active = if (pref.getBoolean("active", false)) "활동적" else null
                        var inactive = if (pref.getBoolean("inactive", false)) "비활동적" else null
                        if (indoor == null && outdoor == null) {
                            indoor = "실내"
                            outdoor = "실외"
                        }
                        if (free == null && pay == null) {
                            free = "무료"
                            pay = "유료"
                        }
                        if (alone == null && friend == null) {
                            alone = "혼자가능"
                            friend = "친구필요"
                        }
                        if (active == null && inactive == null) {
                            active = "활동적"
                            inactive = "비활동적"
                        }
                        val db: PlayDatabase? = PlayDatabase.getInstance(this)
                        val result: Play? = db?.playDao()
                            ?.getResult(indoor, outdoor, free, pay, alone, friend, active, inactive)
                        if (result != null && showResult(result)) {
                            is_runned = false
                            start_button.text = "START"
                        } else Toast.makeText(this, "조건에 해당하는 놀이가 없습니다.", Toast.LENGTH_SHORT).show()
                    }


                }, 2500)

            } else {
                //TODO: STOP animation.  아직도 빠르게 클릭시 안잡히는 부분이 있는데 이런식으로 flag 를 둬서 한번 수정해보세요.
                is_runned = false
                start_button.text = "START"
                val roulette: ImageView = findViewById<ImageView>(R.id.roulette)
                roulette.clearAnimation()
            }

        }

        filter_button.setOnClickListener {
            showFilter()
        }

    }

    fun showResult(result: Play?): Boolean {
        val inflater = getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = inflater.inflate(R.layout.popup_layout, null)
        val textView: TextView = view.findViewById<TextView>(R.id.result)
        textView.text = result?.play_name
        if (textView.text.length > 7) textView.textSize = 40F
        val alertDialog = AlertDialog.Builder(this).setCancelable(false).create()
        alertDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        val close_button = view.findViewById<ImageButton>(R.id.close)
        val mediaPlayer2 = MediaPlayer.create(this, R.raw.popup1)
        if (pref.getBoolean("sound", true)) mediaPlayer2.start()
        close_button.setOnClickListener {
            alertDialog.cancel()
        }
        alertDialog.setView(view)
        alertDialog.show()
        alertDialog.window?.setLayout(WRAP_CONTENT, 750)

        return true
    }

    fun showFilter() {
        val inflater = getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = inflater.inflate(R.layout.filter_layout, null)
        val alertDialog = AlertDialog.Builder(this).create()
        alertDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        val finish_select_button = view.findViewById<Button>(R.id.finish_select_button)
        finish_select_button.setOnClickListener {
            alertDialog.cancel()
        }

        fun buttonSelected(id: Int) {
            val btn = view.findViewById<Button>(id)
            val indoorButton = view.findViewById<Button>(R.id.indoor_button)
            val outdoorButton = view.findViewById<Button>(R.id.outdoor_button)
            val freeButton = view.findViewById<Button>(R.id.free_button)
            val payButton = view.findViewById<Button>(R.id.pay_button)
            val aloneButton = view.findViewById<Button>(R.id.alone_button)
            val friendButton = view.findViewById<Button>(R.id.friend_button)
            val activeButton = view.findViewById<Button>(R.id.active_button)
            val inactiveButton = view.findViewById<Button>(R.id.inactive_button)

            when (btn) {
                indoorButton -> btn.isSelected = pref.getBoolean("indoor", false)
                outdoorButton -> btn.isSelected = pref.getBoolean("outdoor", false)
                freeButton -> btn.isSelected = pref.getBoolean("free", false)
                payButton -> btn.isSelected = pref.getBoolean("pay", false)
                aloneButton -> btn.isSelected = pref.getBoolean("alone", false)
                friendButton -> btn.isSelected = pref.getBoolean("friend", false)
                activeButton -> btn.isSelected = pref.getBoolean("active", false)
                inactiveButton -> btn.isSelected = pref.getBoolean("inactive", false)
            }
            btn.setOnClickListener {
                if (btn.isSelected == false) {
                    btn.isSelected = true
                    when (btn) {
                        indoorButton -> pref.setBoolean("indoor", this, true)
                        outdoorButton -> pref.setBoolean("outdoor", this, true)
                        freeButton -> pref.setBoolean("free", this, true)
                        payButton -> pref.setBoolean("pay", this, true)
                        aloneButton -> pref.setBoolean("alone", this, true)
                        friendButton -> pref.setBoolean("friend", this, true)
                        activeButton -> pref.setBoolean("active", this, true)
                        inactiveButton -> pref.setBoolean("inactive", this, true)
                    }
                } else {
                    btn.isSelected = false
                    when (btn) {
                        indoorButton -> pref.setBoolean("indoor", this, false)
                        outdoorButton -> pref.setBoolean("outdoor", this, false)
                        freeButton -> pref.setBoolean("free", this, false)
                        payButton -> pref.setBoolean("pay", this, false)
                        aloneButton -> pref.setBoolean("alone", this, false)
                        friendButton -> pref.setBoolean("friend", this, false)
                        activeButton -> pref.setBoolean("active", this, false)
                        inactiveButton -> pref.setBoolean("inactive", this, false)
                    }
                }
            }

        }
        buttonSelected(R.id.indoor_button)
        buttonSelected(R.id.outdoor_button)
        buttonSelected(R.id.free_button)
        buttonSelected(R.id.pay_button)
        buttonSelected(R.id.alone_button)
        buttonSelected(R.id.friend_button)
        buttonSelected(R.id.active_button)
        buttonSelected(R.id.inactive_button)
        alertDialog.setView(view)
        alertDialog.show()

    }

    var mBackWait: Long = 0
    override fun onBackPressed() {
        if (System.currentTimeMillis() - mBackWait >= 2000) {
            mBackWait = System.currentTimeMillis()
            val toast: Toast = Toast.makeText(this, "뒤로가기 버튼을 한번 더 누르면 종료됩니다.", Toast.LENGTH_SHORT)
            val group = toast.view as ViewGroup
            val msgTextView = group.getChildAt(0) as TextView
            msgTextView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 14f)
            toast.show()
        } else {
            finish()
        }
    }

}