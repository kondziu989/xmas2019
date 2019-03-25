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
import java.util.*

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

    @RequiresApi(Build.VERSION_CODES.O)//required APIs min 29 for dayOfMonth...
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        //Here we return events from eventsList to layout with RecycleView_list
        val event: Event = eventsList[position]

        //Show date work
        holder.textViewMainText.text = event.name


        val day = event.date.get(Calendar.DAY_OF_MONTH).toString()
        var month = SpannableString(event.date.get(Calendar.MONTH).toString())
        month.setSpan(ForegroundColorSpan(Color.YELLOW),0,month.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        val year = event.date.get(Calendar.YEAR).toString()

        holder.mainImageDate.text = ""
        holder.mainImageDate.append(day)
        holder.mainImageDate.append(month)
        holder.mainImageDate.append("\n".plus(year))
    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        //change View holder = single element inside recycle view
        val textViewMainText = itemView.findViewById(R.id.ID_recycleView_maintext) as TextView
        val mainImage = itemView.findViewById(R.id.ID_recycleView_mainImage) as ImageView
        val mainImageDate = itemView.findViewById(R.id.ID_recycleView_mainImage_date) as TextView
    }

}