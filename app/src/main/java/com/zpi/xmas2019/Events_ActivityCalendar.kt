package com.zpi.xmas2019

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.util.DisplayMetrics
import android.view.KeyEvent
import android.view.View
import android.widget.Button
import android.widget.CalendarView
import android.widget.Toast
import java.util.*

class Events_ActivityCalendar : Activity() {
     private var chosenDate: IntArray = intArrayOf(0,0,0)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.popup_calendar_view)

        chosenDate = intArrayOf(Calendar.getInstance().time.day,Calendar.getInstance().time.month,Calendar.getInstance().time.year+1900)
        val dm = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(dm)

        val width = dm.widthPixels
        val heigh = dm.heightPixels



        window.setLayout((width*.75).toInt(),(heigh*.65).toInt())


        val calendar = findViewById<CalendarView>(R.id.ID_calendarView)

        calendar?.setOnDateChangeListener { view, year, month, dayOfMonth ->
            val msg = "Została wybrana data: " + String.format("%02d",dayOfMonth) + "/" + String.format("%02d",(month + 1)) + "/" + year
            Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
            chosenDate = intArrayOf(dayOfMonth,month,year)
        }

        val cancelButton :Button = findViewById(R.id.ID_calendar_cancel)
            cancelButton.setOnClickListener {
            finish()
        }

        val saveButton :Button = findViewById(R.id.ID_calendar_save)
        saveButton.setOnClickListener{
            val intent = Intent(this@Events_ActivityCalendar, EventsActivity::class.java)
            intent.putExtra("ChosenDate", chosenDate)
            startActivity(intent)
            //finish()
        }
    }
    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            finish()
            return true
        }
        return super.onKeyDown(keyCode, event)
    }

}
