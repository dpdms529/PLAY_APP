package com.example.play_app

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager.LayoutParams.*
import android.widget.Button
import android.widget.ImageButton
import androidx.appcompat.app.AlertDialog
import android.widget.*
import androidx.core.widget.addTextChangedListener
import com.example.play_app.db.PlayDatabase
import com.example.play_app.db.entity.Play
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import kotlinx.android.synthetic.main.activity_play_list.*

class PlayListActivity : AppCompatActivity() {
    var place_current:Boolean = false
    var cost_current:Boolean = false
    var num_current:Boolean = false
    var act_current:Boolean = false

    private lateinit var mAdpater : MyCustomAdapter

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_play_list)

        HomeActivity.mAdView = findViewById(R.id.adViewPlayList)
        val adRequest = AdRequest.Builder().build()
        HomeActivity.mAdView.loadAd(adRequest)

        var db: PlayDatabase ?= PlayDatabase.getInstance(this)
        var item = db?.playDao()?.getAll() as ArrayList<Play>

        play_list_back_button.setOnClickListener {
            finish()
        }
      
        val plus = findViewById<ImageButton>(R.id.list_add_btn)
        plus.setOnClickListener {
            showPlus(db!!,item)
        }

        val listView = findViewById<ListView>(R.id.listView)
        mAdpater = MyCustomAdapter(this, item, db!!)
        listView.adapter = mAdpater
        listView.choiceMode = ListView.CHOICE_MODE_MULTIPLE

        val delbtn = findViewById<ImageButton>(R.id.list_del_btn)

        delbtn.setOnClickListener()
        {

            val checkedItem = listView.checkedItemPositions

            if((mAdpater.count downTo(0)).filter{checkedItem.get(it)}.size == item.size)
            {
                Toast.makeText(this,"놀이 목록에는 놀이가 한 개 이상 존재해야 합니다.",Toast.LENGTH_SHORT).show()
            }
            else
            {
                (mAdpater.count downTo 0)
                    .filter { checkedItem.get(it) }
                    .forEach {
                        db?.playDao()?.delete(item.get(it))
                        item.removeAt(it) }
                listView.clearChoices()
                mAdpater.notifyDataSetChanged()
                Toast.makeText(this,"삭제되었습니다.",Toast.LENGTH_SHORT).show()
            }
        }

        val reset = findViewById<ImageButton>(R.id.reset_btn)
        reset.setOnClickListener()
        {
            PlayDatabase.destroyInstance()
            db = PlayDatabase.getInstance(this)
            item.clear()
            item.addAll(db?.playDao()?.getAll() as ArrayList<Play>)
            mAdpater.notifyDataSetChanged()
            Toast.makeText(this, "초기화되었습니다.",Toast.LENGTH_SHORT).show()
        }
    }

    fun showPlus(db:PlayDatabase,item:ArrayList<Play>){
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
                num_btn.setText("친구필요")
                num_current = true
            }
            else{
                num_btn.setText("혼자가능")
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
        save.setOnClickListener() {
            val addPlayName = view.findViewById<EditText>(R.id.add_playname)
            item.add(Play(item.last().play_id+1,addPlayName?.text.toString(),place_btn.text.toString(),cost_btn.text.toString(),num_btn.text.toString(),act_btn.text.toString()))
            db?.playDao()?.insert(item.last())
            mAdpater.notifyDataSetChanged()
            alertDialog.cancel()
            Toast.makeText(this@PlayListActivity,"추가되었습니다.",Toast.LENGTH_SHORT).show()
//            addPlayName.addTextChangedListener(object : TextWatcher {
//                override fun afterTextChanged(s: Editable?) {
//                    if(s.toString().toByteArray().size > 9 || s.toString().toByteArray().isEmpty())
//                    {
//                        val toast : Toast = Toast.makeText(this@PlayListActivity,"글자는 최대 9자, 최소 1자까지 입력 가능합니다.",Toast.LENGTH_SHORT)
//                        val group = toast.view as ViewGroup
//                        val msgTextView = group.getChildAt(0) as TextView
//                        msgTextView.setTextSize(TypedValue.COMPLEX_UNIT_DIP,13f)
//                        toast.show()
//                    }
//                    else
//                    {
//
//                    }
//                }
//
//                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
//                }
//
//                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
//                }
//            })

        }

        alertDialog.setView(view)
        alertDialog.show()
        alertDialog.window?.setLayout(WRAP_CONTENT, 1300)
        alertDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

    }

}
