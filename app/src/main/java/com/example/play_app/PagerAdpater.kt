package com.example.play_app

import android.content.Context
import android.graphics.drawable.DrawableContainer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat.getSystemService
import com.example.play_app.TutorialActivity.Companion.layouts

public abstract class PagerAdpater : androidx.viewpager.widget.PagerAdapter() {

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val layoutinflater: LayoutInflater = LayoutInflater.getSystemService(Context.LAYOUT_INFLATER_SERVICE)

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