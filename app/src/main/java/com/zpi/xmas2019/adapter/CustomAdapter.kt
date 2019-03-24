package com.zpi.xmas2019.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.TextureView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.zpi.xmas2019.R
import com.zpi.xmas2019.model.Event

//CustomerAdapter which extend RecycleView.Adapter and get as argument listOfEvents
data class CustomAdapter(val eventsList: ArrayList<Event>) : RecyclerView.Adapter<CustomAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        //Get our recycle view layout from events_recycleview_list
        //and return subclass with modified View = v
        val v = LayoutInflater.from(parent?.context).inflate(R.layout.events_recycleview_list, parent, false)
        return ViewHolder(v);
    }

    override fun getItemCount(): Int {
        return eventsList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        //Here we return events from eventsList to layout with RecycleView_list
        val event: Event = eventsList[position]

        holder?.textViewMainText?.text = event.name
        holder?.textViewMainText?.text = event.address
    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        //change View holder = single element inside recycle view
        val textViewMainText = itemView.findViewById(R.id.ID_recycleView_maintext) as TextView
        val textViewSubText = itemView.findViewById(R.id.ID_recycleView_subtext) as TextView
    }

}