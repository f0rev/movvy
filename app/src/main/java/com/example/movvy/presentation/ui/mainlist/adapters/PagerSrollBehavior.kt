package com.example.movvy.presentation.ui.mainlist.adapters

import android.annotation.SuppressLint
import android.os.Handler
import androidx.viewpager2.widget.ViewPager2
import java.util.*

class PagerSrollBehavior(private val viewPager: ViewPager2) {

    private var currentPage = 0
    private val handler = Handler()
    private var isTouched = false



    @SuppressLint("ClickableViewAccessibility")
    fun initAutoScrollBehavior() {

        val update = Runnable {
            if (currentPage == viewPager.adapter?.itemCount) {
                currentPage = 0
            }
            viewPager.setCurrentItem(currentPage++, true)
        }

        Timer().schedule(object : TimerTask() {
            override fun run() {
                if (!isTouched)
                    handler.post(update)
            }
        }, 3500, 3500)

    }


}