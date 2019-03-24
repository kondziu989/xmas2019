package com.zpi.xmas2019

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.LinearLayout
import com.zpi.xmas2019.adapter.CustomAdapter
import com.zpi.xmas2019.model.Event

class EventsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_events)


        //Create recyclerView and bind it with our main RecyclerView from layout
        val recyclerView = findViewById(R.id.recyclerViewEvents) as RecyclerView

        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false)

        val events = ArrayList<Event>()

        events.add(Event("Event name", "Additional Infrormation"))
        events.add(Event("Event name", "Additional Infrormation"))
        events.add(Event("Event name", "Additional Infrormation"))
        events.add(Event("Event name", "Additional Infrormation"))
        events.add(Event("Event name", "Additional Infrormation"))
        events.add(Event("Event name", "Additional Infrormation"))
        events.add(Event("Event name", "Additional Infrormation"))
        events.add(Event("Event name", "Additional Infrormation"))
        events.add(Event("Event name", "Additional Infrormation"))
        events.add(Event("Event name", "Additional Infrormation"))
        events.add(Event("Event name", "Additional Infrormation"))

        val adapter = CustomAdapter(events)
        recyclerView.adapter = adapter
    }
}
