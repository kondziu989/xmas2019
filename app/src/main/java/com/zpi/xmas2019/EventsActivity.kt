package com.zpi.xmas2019

import android.content.Context
import android.content.Intent
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
    private var chosenDate: IntArray? = intArrayOf(0,0,0)
    private var chosenArrayEvents = ArrayList<Event>()
    private var events = ArrayList<Event>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_events)
        //Change name in action bar
        changeActionBar()
        //show all button for events
        val showAllEvents:View = findViewById(R.id.ID_showAll)

        //Create recyclerView and bind it with our main RecyclerView from layout
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerViewEvents)
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false) as RecyclerView.LayoutManager?

        //Fulfill events array, tworzy za kazdym razem
        createEvents()

        //get chosenDate from Calendar
        chosenDate = intent.getIntArrayExtra("ChosenDate")

        //Customer adapter for RecuclerView
        //var adapter::CustomAdapter = CustomAdapter(events)

        //if chosen date null or year = 0 return all events else return events from chosen day
        if (chosenDate != null && chosenDate!![2] != 0) {
            showAllEvents.isVisible(true)
            showEventsForChosenDate()
            val msg = "Wydarzenia dla wybranej daty: " + String.format("%02d",chosenDate!![0]) + "/" + String.format("%02d",(chosenDate!![1] + 1)) + "/" + chosenDate!![2]
            Toast.makeText(this, msg, Toast.LENGTH_LONG).show()
        } else {
            chosenArrayEvents.addAll(events.filterNotNull())
            showAllEvents.isVisible(false)
            //recyclerView.adapter = CustomAdapter(events)
        }

        recyclerView.adapter = CustomAdapter(chosenArrayEvents)

        //onClick showAllEvents refresh activity with unchosen Date
        showAllEvents.setOnClickListener {
            intent.putExtra("ChosenDate",intArrayOf(0,0,0))//change chosen date to 0,0,0
            finish()
            startActivity(intent)
        }

        //onClick element RecyclerView
        recyclerView.addOnItemTouchListener(
            RecyclerItemClickListener(this,recyclerView,
                object : RecyclerItemClickListener.OnItemClickListener {
                    override fun onItemClick(view: View, position: Int) {
                        val intent = Intent(this@EventsActivity, Events_ActivityItem::class.java)
                        intent.putExtra("chosenEvent", chosenArrayEvents[position])
                        startActivity(intent)
                    }

                    override fun onLongItemClick(view: View?, position: Int) {
                        toast("Don't hold it sooooo logn!!!!!!!!!!!!!!!!!!!!!!!!")
                    }
                }
            )
        )

    }




    //Toast for context
    fun Context.toast(message: CharSequence) =
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()

    private fun showEventsForChosenDate() {
        events.forEach {
            if (it.date.get(Calendar.DAY_OF_MONTH) == chosenDate!![0] && it.date.get(Calendar.MONTH) == chosenDate!![1]
                && it.date.get(Calendar.YEAR) == chosenDate!![2])
            {
                chosenArrayEvents.add(it)
            }
        }
    }

    private fun View.isVisible(visible: Boolean) {
        visibility = if(visible) View.VISIBLE else View.GONE
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

    private fun createEvents() {
        val date = Calendar.getInstance()
        val date1 = Calendar.getInstance()
        val date2 = Calendar.getInstance()
        val date3 = Calendar.getInstance()
        val date4 = Calendar.getInstance()
        val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH)
        date.time = sdf.parse("2019-04-01 13:30:37")
        date1.time = sdf.parse("2019-04-02 16:02:37")
        date2.time = sdf.parse("2019-04-03 18:10:37")
        date3.time = sdf.parse("2019-04-04 20:15:37")
        date4.time = sdf.parse("2019-03-05 13:15:37")

        events.add(Event("Oficjalne otwarcie Jarmarku Bożonarodzeniowego.", date))
        events.add(Event("Magiczne Widowisko \"Święta zaklęte w bajkach", date))
        events.add(Event("Powitanie Mikołaja.", date))
        events.add(Event("Oficjalne rozświetlenie choinki", date2))
        events.add(Event("Mikołajowe występy artystyczne", date2))
        events.add(Event("Świąteczna Parada z Mikołajem z finałem na scenie przy choince", date3))
        events.add(Event("Parada wrocławskich Elfów", date3))
        events.add(Event("Wigilijny Korowód Kolędników", date3))
        events.add(Event("\"Wigilia dla Samotnych i Potrzebujących\" przy akompaniamencie zespołu \"Gieni Dudki\" i zespołu \"Servoos\" - Organizator Miasto Wrocław.", date3))
        events.add(Event("Wydarzenie artystyczne", date4))
        events.add(Event("Szukanie czapek", date4))
        events.add(Event("Picie wina", date4))
        events.add(Event("Wydarzenie niespodzianka", date4))
        events.add(Event("Wydarzenie niespodzianka", date4))
        events.add(Event("Wydarzenie niespodzianka", date4))
        events.add(Event("Oficjalne zamknięcie Jarmarku Bożonarodzeniowego.", date4))
    }
/* private fun popupCalendar() {
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

            calendar?.setOnDateChangeListener { _, year, month, dayOfMonth ->
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

}*/

}

