package com.zpi.xmas2019

import android.app.Activity
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.util.DisplayMetrics
import android.view.View
import android.widget.CalendarView

class Events_ActivityCalendar : Activity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.popup_calendar_view)

        val dm = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(dm)

        val width = dm.widthPixels
        val heigh = dm.heightPixels


        window.setLayout((width*.75).toInt(),(heigh*.5).toInt())


    }
}
