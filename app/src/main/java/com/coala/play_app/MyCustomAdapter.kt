package com.coala.play_app

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.TextView
import android.widget.ImageButton
import androidx.appcompat.app.AlertDialog
import com.coala.play_app.db.PlayDatabase
import com.coala.play_app.db.entity.Play
import android.view.WindowManager.LayoutParams.*

public class MyCustomAdapter(context: Context,item : ArrayList<Play>?,db:PlayDatabase) : BaseAdapter() {
    private val mContext: Context
    private val mitem = item
    private val mdb = db

    init {
        mContext = context
    }

    override fun getCount() = mitem!!.size

    override fun getItemId(position: Int) = position.toLong()

    override fun getItem(position: Int) = mitem?.get(position)

    override fun getView(position: Int, view: View?, viewgroup: ViewGroup?): View {


        val layoutInflater = LayoutInflater.from(mContext)
        val listlayout = layoutInflater.inflate(R.layout.listview_layout, viewgroup, false)

        val nameTextView = listlayout.findViewById<TextView>(R.id.list_item)
        nameTextView.text = mitem?.get(position)?.play_name


        val info = listlayout.findViewById<ImageButton>(R.id.btn_info)
        info?.setOnClickListener {
            showInfo(getItem(position),position)
        }

        return listlayout
    }

    fun showInfo(item:Play?,position:Int){

        val inflater = LayoutInflater.from(mContext)
        val view = inflater.inflate(R.layout.play_list_check_layout,null)
        val playName :TextView = view.findViewById<TextView>(R.id.title)
        playName.setText(item?.play_name)
        val placeInfoButton: Button = view.findViewById<Button>(R.id.place_info)
        if(item?.place=="실내") placeInfoButton.setText("실내")
        else placeInfoButton.setText("실외")
        placeInfoButton.setOnClickListener{
            if(placeInfoButton.text=="실내") placeInfoButton.setText("실외")
            else placeInfoButton.setText("실내")
        }

        val costInfoButton: Button = view.findViewById<Button>(R.id.cost_info)
        if(item?.cost=="무료") costInfoButton.setText("무료")
        else costInfoButton.setText("유료")
        costInfoButton.setOnClickListener{
            if(costInfoButton.text=="무료") costInfoButton.setText("유료")
            else costInfoButton.setText("무료")
        }

        val numInfoButton: Button= view.findViewById<Button>(R.id.num_info)
        if(item?.num=="혼자가능") numInfoButton.setText("혼자가능")
        else numInfoButton.setText("친구필요")
        numInfoButton.setOnClickListener{
            if(numInfoButton.text=="혼자가능")  numInfoButton.setText("친구필요")
            else numInfoButton.setText("혼자가능")
        }

        val actInfoButton: Button = view.findViewById<Button>(R.id.act_info)
        if(item?.act=="활동적") actInfoButton.setText("활동적")
        else actInfoButton.setText("비활동적")
        actInfoButton.setOnClickListener{
            if(actInfoButton.text=="활동적") actInfoButton.setText("비활동적")
            else actInfoButton.setText("활동적")
        }

        val alertDialog = AlertDialog.Builder(mContext).setCancelable(false).create()
        val close_button = view.findViewById<ImageButton>(R.id.close)
        close_button.setOnClickListener {
            alertDialog.cancel()
        }

        val modifyButton:Button = view.findViewById<Button>(R.id.modify_button)
        modifyButton.setOnClickListener{
            mitem?.set(position,Play(item!!.play_id,item.play_name,placeInfoButton.text.toString(),costInfoButton.text.toString(),numInfoButton.text.toString(),actInfoButton.text.toString()))
            mdb.playDao().update(mitem!!.get(position))
            println(mdb.playDao().getAll().get(position))
            this.notifyDataSetChanged()
            alertDialog.cancel()
        }

        alertDialog.setView(view)
        alertDialog.show()
        alertDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        alertDialog.window?.setLayout(WRAP_CONTENT, 1200)

    }

}