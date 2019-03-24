package com.zpi.xmas2019

import android.content.Intent
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.Menu
import android.view.MenuItem
import android.widget.LinearLayout
import com.zpi.xmas2019.adapter.CustomAdapter
import com.zpi.xmas2019.model.Event
import kotlinx.android.synthetic.main.activity_events.*
import kotlinx.android.synthetic.main.activity_how_to_get.*
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.Month
import java.time.MonthDay
import java.util.*

class EventsActivity : AppCompatActivity() {

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_events)

        //setting toolbar
        //setSupportActionBar(findViewById(R.id.toolbar))
        //home navigation
        //supportActionBar?.setDisplayHomeAsUpEnabled(true)
        //load actionbar
        changeActionBar()




        //Create recyclerView and bind it with our main RecyclerView from layout
        val recyclerView = findViewById(R.id.recyclerViewEvents) as RecyclerView

        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false)

        val events = ArrayList<Event>()

        val date = LocalDateTime.of(2000,Month.AUGUST,12,12,32)
        println(date)

        events.add(Event("Event name", date))
        events.add(Event("Event name", date))
        events.add(Event("Event name", date))
        events.add(Event("Event name", date))
        events.add(Event("Event name", date))
        events.add(Event("Event name", date))
        events.add(Event("Event name", date))


        val adapter = CustomAdapter(events)
        recyclerView.adapter = adapter
    }

    //Manage action bar
    private fun changeActionBar() {


        // Now get the support action bar
        val actionBar = supportActionBar

        // Set toolbar title/app title
        actionBar!!.title = "Wydarzenia"

        // Set action bar elevation
        actionBar.elevation = 4.0F
    }


    //Add buttons to action bar
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_events_menu,menu)
        return true
    }

    //Add functionality for button in action bar
    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        when (item?.itemId) {
            R.id.menu_calendar_button -> {
                println("calendar button was clicked")
                startActivity((Intent(this, MainActivity::class.java)))
                return true
            }

            R.id.menu_information_button ->  {
                println("information button was clicked")
                startActivity((Intent(this, MainActivity::class.java)))
                return true
            }
        }

        return super.onOptionsItemSelected(item)
    }
}

