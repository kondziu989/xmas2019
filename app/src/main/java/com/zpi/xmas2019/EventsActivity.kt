package com.zpi.xmas2019

import android.content.Intent
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.transition.Slide
import android.transition.TransitionManager
import android.view.*
import android.widget.*
import com.zpi.xmas2019.adapter.CustomAdapter
import com.zpi.xmas2019.model.Event
import kotlinx.android.synthetic.main.activity_events.*
import java.lang.Exception
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

class EventsActivity : AppCompatActivity() {
    private lateinit var btnClosePopup : Button
    private lateinit var popupWindow : PopupWindow


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

        // Set a click listener for popup's button widget




        //Create recyclerView and bind it with our main RecyclerView from layout
        val recyclerView = findViewById(R.id.recyclerViewEvents) as RecyclerView

        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false)

        val events = ArrayList<Event>()


        /*var formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        var date = LocalDate.parse("31-12-2018", formatter);
        println(date)*/
        val date = Calendar.getInstance()
        val sdf = SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy", Locale.ENGLISH)
        date.time = sdf.parse("Mon Mar 14 16:02:37 GMT 2011")//

        events.add(Event("Event name", date))
        events.add(Event("Event name", date))
        events.add(Event("Event name", date))
        events.add(Event("Event name", date))
        events.add(Event("Event name", date))
        events.add(Event("Event name", date))
        events.add(Event("Event name", date))
        events.add(Event("Event name", date))
        events.add(Event("Event name", date))
        events.add(Event("Event name", date))
        events.add(Event("Event name", date))
        events.add(Event("Event name", date))


        val adapter = CustomAdapter(events)
        recyclerView.adapter = adapter

        /*if (btnClosePopup != null) {
            btnClosePopup.setOnClickListener {
            // Dismiss the popup window
            popupWindow.dismiss()
        }*/

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
                popupCalendar()
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

    private fun popupCalendar() {
        try {
            // We need to get the instance of the LayoutInflater
            val layoutInflater:LayoutInflater = LayoutInflater.from(applicationContext)

            val view: View = layoutInflater.inflate(R.layout.popup_calendar_view,ID_events_layout,true)


            popupWindow =  PopupWindow(view,LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT)

            // Set an elevation for the popup window
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                popupWindow.elevation = 10.0F
            }

            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                // Create a new slide animation for popup window enter transition
                val slideIn = Slide()
                slideIn.slideEdge = Gravity.TOP
                popupWindow.enterTransition = slideIn

                // Slide animation for popup window exit transition
                val slideOut = Slide()
                slideOut.slideEdge = Gravity.RIGHT
                popupWindow.exitTransition = slideOut

            }

            //popUpWindow.showAtLocation(view, Gravity.CENTER, 100,100)

            val calendar = view.findViewById<CalendarView>(R.id.ID_calendarView)
            btnClosePopup = view.findViewById<Button>(R.id.ID_calendar_cancel)

            calendar?.setOnDateChangeListener { view, year, month, dayOfMonth ->
                // Note that months are indexed from 0. So, 0 means January, 1 means february, 2 means march etc.
                val msg = "Selected date is " + dayOfMonth + "/" + (month + 1) + "/" + year
                Toast.makeText(this@EventsActivity, msg, Toast.LENGTH_SHORT).show()
            }


            // Finally, show the popup window on app

            TransitionManager.beginDelayedTransition(ID_events_layout)
            popupWindow.showAtLocation(
                ID_events_layout, // Location to display popup window
                Gravity.CENTER, // Exact position of layout to display popup
                50, // X offset
                100 // Y offset
            )

            //ID_events_layout.addView(view,1)


    } catch (e:Exception) {
        e.printStackTrace()
    }

}
}

