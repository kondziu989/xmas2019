package com.zpi.xmas2019

import android.content.Context
import android.content.Intent
import android.graphics.Canvas
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.transition.Slide
import android.transition.TransitionManager
import android.transition.Visibility
import android.view.*
import android.widget.*
import com.zpi.xmas2019.adapter.CustomAdapter
import com.zpi.xmas2019.adapter.RecyclerItemClickListener
import com.zpi.xmas2019.dummy.DummyEvents
import com.zpi.xmas2019.model.Event
import kotlinx.android.synthetic.main.activity_events.*
import java.lang.Exception
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*
import android.support.v7.widget.DividerItemDecoration
import android.util.AttributeSet
import com.zpi.xmas2019.dummy.DummyStalls
import kotlin.collections.ArrayList


class EventsActivity : AppCompatActivity()  {
    private lateinit var btnClosePopup: Button
    private lateinit var popupWindow: PopupWindow
    private var chosenDate: IntArray? = intArrayOf(0, 0, 0)
    private var chosenArrayEvents = ArrayList<Event>()
    private var events = ArrayList<Event>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_events)
        //Change name in action bar
        changeActionBar()
        //show all button for events
        val showAllEvents: View = findViewById(R.id.ID_showAll)

        //Create recyclerView and bind it with our main RecyclerView from layout
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerViewEvents)
        recyclerView.layoutManager =
            LinearLayoutManager(this, LinearLayout.VERTICAL, false) as RecyclerView.LayoutManager?
        //recyclerView.addItemDecoration(DividerItemDecoration(this, 0)) //remove

        //Fulfill events array, tworzy za kazdym razem
        //createEvents()
        events.addAll(DummyEvents.EVENTS)

        //get chosenDate from Calendar
        chosenDate = intent.getIntArrayExtra("ChosenDate")

        //if chosen date null or year = 0 return all events else return events from chosen day
        if (chosenDate != null && chosenDate!![2] != 0) {
            showAllEvents.isVisible(true)
            showEventsForChosenDate()
            val msg = "Wydarzenia dla wybranej daty: " + String.format("%02d", chosenDate!![0]) + "/" + String.format(
                "%02d",
                (chosenDate!![1] + 1)
            ) + "/" + chosenDate!![2]
            Toast.makeText(this, msg, Toast.LENGTH_LONG).show()
        } else {
            chosenArrayEvents.addAll(events.filterNotNull())
            showAllEvents.isVisible(false)
            //recyclerView.adapter = CustomAdapter(events)
        }

        recyclerView.adapter = CustomAdapter(chosenArrayEvents)

        //onClick showAllEvents refresh activity with unchosen Date
        showAllEvents.setOnClickListener {
            intent.putExtra("ChosenDate", intArrayOf(0, 0, 0))//change chosen date to 0,0,0
            finish()
            startActivity(intent)
        }

        //onClick element RecyclerView
        recyclerView.addOnItemTouchListener(
            RecyclerItemClickListener(this, recyclerView,
                object : RecyclerItemClickListener.OnItemClickListener {
                    override fun onItemClick(view: View, position: Int) {
                        val intent = Intent(this@EventsActivity, Events_ActivityItem::class.java)
                        intent.putExtra("chosenEvent", chosenArrayEvents[position])
                        startActivity(intent)
                    }

                    override fun onLongItemClick(view: View?, position: Int) {
                        toast("Don't hold it sooooo long!!!!!!!!!!!!!!!!!!!!!!!!")
                    }
                }
            )
        )

    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            finish()
            return true
        }
        return super.onKeyDown(keyCode, event)
    }

    //Toast for context
    fun Context.toast(message: CharSequence) =
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()

    private fun showEventsForChosenDate() {
        events.forEach {
            if (it.date.get(Calendar.DAY_OF_MONTH) == chosenDate!![0] && it.date.get(Calendar.MONTH) == chosenDate!![1]
                && it.date.get(Calendar.YEAR) == chosenDate!![2]
            ) {
                chosenArrayEvents.add(it)
            }
        }
    }

    private fun View.isVisible(visible: Boolean) {
        visibility = if (visible) View.VISIBLE else View.GONE
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
        menuInflater.inflate(R.menu.menu_events_menu, menu)
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

            R.id.menu_information_button -> {
                println("information button was clicked")
                startActivity((Intent(this, EventsActivityMoreInf::class.java)))
                return true
            }
        }

        return super.onOptionsItemSelected(item)
    }
}