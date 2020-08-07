package com.example.play_app

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import android.widget.*
import kotlinx.android.synthetic.main.activity_play_list.*
var place_current:Boolean = false
var cost_current:Boolean = false
var num_current:Boolean = false
var act_current:Boolean = false

class PlayListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_play_list)
        play_list_back_button.setOnClickListener{
            finish()
        }
        val listView = findViewById<ListView>(R.id.listView)
        val item = arrayOf<String>(
            "1일 클래스", "PC방", "VR카페", "계곡가기", "공기놀이", "공방", "궁 가기", "노래방", "놀이공원", "농구", "달리기", "당구장", "동물원", "드라마보기",
            "등산", "디스코팡팡 타기", "딸기게임", "땅따먹기", "만화카페", "바니바니 게임", "바다가기", "방탈출카페", "밸런스게임", "번지점프", "베이킹하기", "보드게임",
            "볼링장", "빙고게임", "산책하기", "셀카찍기", "셀프네일하기", "손병호게임", "쇼핑", "수영", "스카이다이빙", "스케이트장", "스쿠버다이빙", "스키장 가기",
            "슬라임카페", "식물원", "썰매장", "쎄쎄쎄", "아이엠그라운드 게임", "암흑카페", "야구", "영화", "오락실", "오렌지게임", "오목", "요리하기", "이미지사진 찍기",
            "인생네컷 찍기", "장문복게임", "전시회", "종이접기", "진실게임", "쪽팔려게임", "축구", "카드게임", "카페투어", "컬러링북", "클라이밍", "클레오파트라 게임",
            "타로", "틀린그림찾기", "피크닉", "피포페인팅", "향수 만들기", "홍삼게임", "화장품 만들기"
        )
        listView.adapter = MyCustomAdapter(this, item)
        val info = findViewById<ImageButton>(R.id.btn_info)
        val plus = findViewById<ImageButton>(R.id.list_add_btn)
//        info.setOnClickListener {
//            showInfo()
//        }
        plus.setOnClickListener {
            showPlus()
        }
    }


    fun showPlus(){
        val inflater = getSystemService(AppCompatActivity.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = inflater.inflate(R.layout.add_popup_layout,null)
        val place_btn: Button = view.findViewById<Button>(R.id.place_button)
        val cost_btn: Button = view.findViewById<Button>(R.id.cost_button)
        val num_btn: Button = view.findViewById<Button>(R.id.num_button)
        val act_btn: Button = view.findViewById<Button>(R.id.act_button)

        fun p_btn(){
            if(!place_current){
                place_btn.setText("실외")
                place_current = true
            }
            else{
                place_btn.setText("실내")
                place_current = false
            }
        }
        place_btn.setOnClickListener(View.OnClickListener(){
            p_btn()
        }
        )

        fun c_btn(){
            if(!cost_current){
                cost_btn.setText("유료")
                cost_current = true
            }
            else{
                cost_btn.setText("무료")
                cost_current = false
            }
        }
        cost_btn.setOnClickListener(View.OnClickListener(){
            c_btn()
        }
        )

        fun n_btn(){
            if(!num_current){
                num_btn.setText("여럿이")
                num_current = true
            }
            else{
                num_btn.setText("혼자")
                num_current = false
            }
        }
        num_btn.setOnClickListener(View.OnClickListener(){
            n_btn()
        }
        )

        fun a_btn(){
            if(!act_current){
                act_btn.setText("비활동적")
                act_current = true
            }
            else{
                act_btn.setText("활동적")
                act_current = false
            }
        }
        act_btn.setOnClickListener(View.OnClickListener(){
            a_btn()
        }
        )

        val alertDialog =
            AlertDialog.Builder(this).setCancelable(false).create()


        val close_button = view.findViewById<ImageButton>(R.id.close)
        close_button.setOnClickListener {
            alertDialog.cancel()
        }
        val save = view.findViewById<Button>(R.id.save_button)
        save.setOnClickListener {
            alertDialog.cancel()//저장안되고 일단 닫기
        }
        alertDialog.setView(view)
        alertDialog.show()
        alertDialog.window?.setLayout(1000, 1350)
        alertDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

    }

//    fun showInfo(){
//
//        val inflater = getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
//        val view = inflater.inflate(R.layout.play_list_check_layout,null)
//        val textView1: TextView = view.findViewById<TextView>(R.id.place_info)
//        if(place_current==false) textView1.setText("실내")
//        else textView1.setText("실외")
//
//        val textView2: TextView = view.findViewById<TextView>(R.id.cost_info)
//        if(cost_current==false) textView1.setText("무료")
//        else textView2.setText("유료")
//
//        val textView3: TextView = view.findViewById<TextView>(R.id.num_info)
//        if(num_current==false) textView1.setText("혼자")
//        else textView3.setText("여럿이")
//
//        val textView4: TextView = view.findViewById<TextView>(R.id.act_info)
//        if(act_current==false) textView1.setText("활동적")
//        else textView4.setText("비활동적")
//
//        val alertDialog = AlertDialog.Builder(this).setCancelable(false).create()
//        val close_button = view.findViewById<ImageButton>(R.id.close)
//        close_button.setOnClickListener {
//            alertDialog.cancel()
//        }
//        alertDialog.setView(view)
//        alertDialog.show()
//        alertDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
//        val layout = alertDialog.window?.setLayout(1000, 850)
//
//    }

}



