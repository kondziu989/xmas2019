package com.zpi.xmas2019.adapter

import android.support.v4.view.ViewPager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso
import com.zpi.xmas2019.R
import java.lang.Exception

class StallRecycleViewAdapter(val tags : ArrayList<String>?) : RecyclerView.Adapter<StallRecycleViewAdapter.ViewHolder>() {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val tag = tags?.get(position)
        holder.itemView.findViewById<Button>(R.id.tag).text=tag
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.tag, parent, false)

        return ViewHolder(view)
    }

    override fun getItemCount(): Int {

        return if(tags?.size==null) 0 else tags.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        //change View holder = single element inside recycle view
        val tag = itemView.findViewById(R.id.tag) as Button

    }
}
