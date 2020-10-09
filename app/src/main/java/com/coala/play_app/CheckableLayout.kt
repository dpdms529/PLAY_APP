package com.coala.play_app

import android.content.Context
import android.util.AttributeSet
import android.widget.Checkable
import android.widget.RelativeLayout
import kotlinx.android.synthetic.main.listview_layout.view.*

class CheckableLayout(context : Context , attributeSet: AttributeSet) : RelativeLayout(context,attributeSet),Checkable {
    override fun isChecked(): Boolean {
        return list_item.isChecked
    }

    override fun toggle() {
        isChecked = !list_item.isChecked
    }

    override fun setChecked(checked: Boolean) {
        if(list_item.isChecked != checked ) list_item.isChecked = checked
    }


}