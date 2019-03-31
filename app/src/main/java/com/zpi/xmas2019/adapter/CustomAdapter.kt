package com.zpi.xmas2019.adapter

import android.graphics.Color
import android.os.Build
import android.support.annotation.RequiresApi
import android.support.v7.widget.RecyclerView
import android.text.Spannable
import android.text.SpannableString
import android.text.Spanned
import android.text.style.BackgroundColorSpan
import android.text.style.ForegroundColorSpan
import android.view.LayoutInflater
import android.view.TextureView
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.zpi.xmas2019.R
import com.zpi.xmas2019.model.Event
import java.time.format.DateTimeFormatter
import java.util.*
import java.util.EnumSet.range

//CustomerAdapter which extend RecycleView.Adapter and get as argument listOfEvents
data class CustomAdapter(val eventsList: ArrayList<Event>) : RecyclerView.Adapter<CustomAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        //Get our recycle view layout from events_recycleview_list
        //and return subclass with modified View = v
        val v = LayoutInflater.from(parent.context).inflate(R.layout.events_recycleview_list, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return eventsList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        //Here we return events from eventsList to layout with RecycleView_list
        val event: Event = eventsList[position]

        //Show date work
        holder.textViewMainText.text = event.name



        val day = String.format("%02d",event.date.get(Calendar.DAY_OF_MONTH))
        val month = String.format("%02d", event.date.get(Calendar.MONTH) + 1)
        val year = String.format("%02d", event.date.get(Calendar.YEAR))
        val time = String.format("%02d", event.date.get(Calendar.HOUR_OF_DAY)) + ":" + String.format("%02d", event.date.get(Calendar.MINUTE))

        holder.mainImageDay.text = day
        holder.mainImageMonth.text = month
        holder.mainImageYear.text = year
        holder.textViewMainTime.text = time
    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        //change View holder = single element inside recycle view
        val textViewMainText = itemView.findViewById(R.id.ID_recycleView_maintext) as TextView
        val textViewMainTime = itemView.findViewById(R.id.ID_recycleView_hour) as TextView
        val mainImageDay = itemView.findViewById(R.id.ID_recycleView_mainImage_day) as TextView
        val mainImageMonth = itemView.findViewById(R.id.ID_recycleView_mainImage_month) as TextView
        val mainImageYear = itemView.findViewById(R.id.ID_recycleView_mainImage_year) as TextView
    }

}

