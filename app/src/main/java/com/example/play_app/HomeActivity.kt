package com.example.play_app

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.filter_layout.*

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        setting.setOnClickListener {
            val intent = Intent(this,SettingsActivity::class.java)
            startActivity(intent)
            finish()
        }

        start_button.setOnClickListener{
            showResult()
        }

        filter_button.setOnClickListener {
            showFilter()
        }

    }
    fun showResult(){
        val inflater = getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = inflater.inflate(R.layout.popup_layout,null)
        val textView: TextView = view.findViewById<TextView>(R.id.result)
        textView.text = "결과"
        val alertDialog = AlertDialog.Builder(this).setCancelable(false).create()
        val close_button = view.findViewById<ImageButton>(R.id.close)
        close_button.setOnClickListener {
            alertDialog.cancel()
        }
        alertDialog.setView(view)
        alertDialog.show()
        alertDialog.window?.setLayout(1000,750)
    }

    fun showFilter(){
        val inflater = getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = inflater.inflate(R.layout.filter_layout,null)
        val alertDialog = AlertDialog.Builder(this).create()
        val finish_select_button = view.findViewById<Button>(R.id.finish_select_button)
        finish_select_button.setOnClickListener{
            alertDialog.cancel()
        }
        val indoor_button = view.findViewById<Button>(R.id.indoor_button)
        indoor_button.setOnClickListener{
            if(indoor_button.isSelected == false){
                indoor_button.isSelected = true
            }else{
                indoor_button.isSelected = false
            }
        }
        val outdoor_button = view.findViewById<Button>(R.id.outdoor_button)
        outdoor_button.setOnClickListener{
            if(outdoor_button.isSelected == false){
                outdoor_button.isSelected = true
            }else{
                outdoor_button.isSelected = false
            }
        }
        val free_button = view.findViewById<Button>(R.id.free_button)
        free_button.setOnClickListener{
            if(free_button.isSelected == false){
                free_button.isSelected = true
            }else{
                free_button.isSelected = false
            }
        }
        val pay_button = view.findViewById<Button>(R.id.pay_button)
        pay_button.setOnClickListener{
            if(pay_button.isSelected == false){
                pay_button.isSelected = true
            }else{
                pay_button.isSelected = false
            }
        }
        val alone_button = view.findViewById<Button>(R.id.alone_button)
        alone_button.setOnClickListener{
            if(alone_button.isSelected == false){
                alone_button.isSelected = true
            }else{
                alone_button.isSelected = false
            }
        }
        val friend_button = view.findViewById<Button>(R.id.friend_button)
        friend_button.setOnClickListener{
            if(friend_button.isSelected == false){
                friend_button.isSelected = true
            }else{
                friend_button.isSelected = false
            }
        }
        val active_button = view.findViewById<Button>(R.id.active_button)
        active_button.setOnClickListener{
            if(active_button.isSelected == false){
                active_button.isSelected = true
            }else{
                active_button.isSelected = false
            }
        }
        val inactive_button = view.findViewById<Button>(R.id.inactive_button)
        inactive_button.setOnClickListener{
            if(inactive_button.isSelected == false){
                inactive_button.isSelected = true
            }else{
                inactive_button.isSelected = false
            }
        }
        alertDialog.setView(view)
        alertDialog.show()
    }

}