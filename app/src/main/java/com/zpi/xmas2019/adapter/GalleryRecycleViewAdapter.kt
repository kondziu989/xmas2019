package com.zpi.xmas2019.adapter

import android.support.v4.view.ViewPager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso
import com.zpi.xmas2019.R
import java.lang.Exception

class GalleryRecycleViewAdapter(val images : List<String>,val textView: TextView,val viewPager: ViewPager) : RecyclerView.Adapter<GalleryRecycleViewAdapter.ViewHolder>() {
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val image_url = images.get(position)

        holder.mainImage.setOnClickListener {
            viewPager.currentItem = position
            val text = "${position + 1}/${images.size}"
            textView.text = text
        }
        try {
            Picasso.get()
                .load(image_url)
                .placeholder(R.drawable.icon)
                .error(R.mipmap.ic_launcher_round)
                .into(holder.mainImage)
        } catch (e : Exception){

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.gallery_recycleview_image, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return images.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        //change View holder = single element inside recycle view
        val mainImage = itemView.findViewById(R.id.galleryImageViewHorizontal) as ImageView

    }
}