package com.zpi.xmas2019

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.support.design.widget.FloatingActionButton
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.*
import com.zpi.xmas2019.adapter.CustomAdapter
import com.zpi.xmas2019.adapter.RecyclerItemClickListener
import com.zpi.xmas2019.model.Event
import java.text.SimpleDateFormat
import java.util.*


class Events_ActivityItem : AppCompatActivity() {
    private lateinit var chosenEvent:Event

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_events_item)
        //Change name in action bar
        changeActionBar()

        chosenEvent = intent.getSerializableExtra("chosenEvent") as Event

        val eventImage = findViewById<ImageView>(R.id.ID_chosen_eventImage)
        val eventName = findViewById<TextView>(R.id.ID_chosen_eventName)
        val eventDescription = findViewById<TextView>(R.id.ID_chosen_eventDescription)

        eventName.text = chosenEvent.name
        eventImage.setImageDrawable(resources.getDrawable(R.drawable.jarmark1,theme))
        eventDescription.text = chosenEvent.description
       
        //toast(chosenEvent.toString())
        val button = findViewById<FloatingActionButton>(R.id.ID_galleryFloatingButton)
        button.setOnClickListener {
            //intent.putExtra("ChosenEventFromEvent",cho)//change chosen date to 0,0,0
            //startActivity(intent)
            toast("Go to gallery")
        }


    }
    //Toast for context
    fun Context.toast(message: CharSequence) =
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()


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
                //popupCalendar()
                startActivity((Intent(this, Events_ActivityCalendar::class.java)))
                //startActivity((Intent(this, MainActivity::class.java)))
                return true
            }

            R.id.menu_information_button ->  {
                println("information button was clicked")
                startActivity((Intent(this, EventsActivityMoreInf::class.java)))
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

}

