package com.zpi.xmas2019.adapter

import android.app.Activity
import android.content.Context
import android.support.v4.view.PagerAdapter
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.squareup.picasso.Picasso
import com.zpi.xmas2019.R
import java.lang.Exception

class StallImageAdapter(private val activity : Activity, private val images : MutableList<String>) : PagerAdapter() {

    var layoutInflater: LayoutInflater? = null

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }

    override fun getCount(): Int {
        return images.size
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        layoutInflater = activity.applicationContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val itemView: View = layoutInflater!!.inflate(R.layout.gallery_view_pager_item, container, false)

        val imageView: ImageView = itemView.findViewById(R.id.galleryImageViewItem)

        val dis = DisplayMetrics()
        activity.windowManager.defaultDisplay.getMetrics(dis)

        imageView.minimumHeight = dis.heightPixels
        imageView.minimumWidth = dis.widthPixels


        when (position) {
            0 -> imageView.setImageResource(R.drawable.stall1)
            1 -> imageView.setImageResource(R.drawable.stall2)
            2 -> imageView.setImageResource(R.drawable.stall3)
            else -> {
                imageView.setImageResource(R.drawable.stall1)
            }
        }
        container.addView(itemView)
        return itemView
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }
}

