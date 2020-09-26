package com.example.play_app

import android.content.Context
import android.graphics.drawable.DrawableContainer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.core.content.ContextCompat.getSystemService
import com.example.play_app.TutorialActivity.Companion.layouts
import java.security.AccessController.getContext

public class PagerAdpater /*constructor(context: Context)*/ : androidx.viewpager.widget.PagerAdapter() {
    private lateinit var layoutinflater:LayoutInflater
//    private val mContext:Context = context
//    constructor(context: Context) : this() {
//        mContext=context
//    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {

        layoutinflater = getSystemService(Context.LAYOUT_INFLATER_SERVICE)

        val view: View = layoutinflater.inflate(layouts[position],container,false)
        container.addView(view)

        return view
    }

    override fun getCount(): Int {
        return layouts.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view==`object`
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        val view:View=`object` as View
        container.removeView(view)
    }

}