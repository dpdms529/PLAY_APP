package com.example.play_app

import android.content.ClipData
import android.content.Context
import android.content.Intent
import android.content.res.Resources
import android.graphics.Color
import android.graphics.Color.BLUE
import android.os.Build
import android.os.Bundle
import android.provider.CalendarContract
import android.text.Html
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.view.WindowManager
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView

import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import com.example.play_app.R
import kotlinx.android.synthetic.main.activity_tutorial.*

abstract class TutorialActivity : AppCompatActivity() {
    companion object {
        val layouts:Array<Int> = arrayOf(R.layout.page1, R.layout.page2, R.layout.page3, R.layout.page4)
        lateinit var dotsLayout : LinearLayout
        lateinit var viewPager : ViewPager
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tutorial)

        if(Build.VERSION.SDK_INT >=21) {
            window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN)
        }
        viewPager = findViewById(R.id.view_pager)
        val btnSkip:Button = findViewById(R.id.btn_skip)
        val btnNext:Button = findViewById(R.id.btn_next)
        dotsLayout = findViewById(R.id.layoutDots)

        addBottomDots(0)

        changeStatusBarColor()

        val pagerAdapter:PagerAdapter
        var context = applicationContext
        pagerAdapter=CustomPagerAdapter(context)
        viewPager.adapter = pagerAdapter
        viewPager?.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {
            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
            }

            override fun onPageSelected(position: Int) {
                addBottomDots(position)

                if(position == layouts.size -1) {
                    btnNext.setText(getString(R.string.start))
                    btnSkip.visibility = View.GONE
                }
                else {
                    btnNext.setText(getString(R.string.next))
                    btnSkip.visibility = View.VISIBLE
                }
            }
        })
        btnSkip.setOnClickListener(View.OnClickListener {
            fun onClick(v:View) {
                moveMainPage()
            }
        })
        btnNext.setOnClickListener(View.OnClickListener {
            fun onClick(v:View) {
                val current:Int = getItem(+1)
                if(current < layouts.size) {
                    viewPager.setCurrentItem(current)
                }
                else {
                    moveMainPage()
                }
            }
        })




    }

    private fun addBottomDots(currentPage : Int) {
        val dots = arrayOfNulls<TextView>(layouts.size)

//        val colorsActive = getResources().getIntArray(R.array.array_dot_active)
//        val colorsInactive = getResources().getIntArray(R.array.array_dot_inactive)

        dotsLayout.removeAllViews()
        for(i in 0..dots.size) {
            dots[i] = TextView(this)
            dots[i]?.setText(Html.fromHtml("&#8226;"))
            dots[i]?.setTextSize(TypedValue.COMPLEX_UNIT_SP,35f)
            dots[i]?.setTextColor(Color.parseColor("#ffffff"))
            dotsLayout.addView(dots[i])
        }
        if(dots.isNotEmpty())
            dots[currentPage]?.setTextColor(Color.parseColor("#000000"))
    }
    fun getItem(i : Int) : Int {
        return viewPager.currentItem + i
    }

    fun moveMainPage() {
        startActivity(Intent(this,HomeActivity::class.java))
        finish()
    }
    fun changeStatusBarColor() {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val window : Window = window
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.statusBarColor = Color.TRANSPARENT
        }
    }

}

