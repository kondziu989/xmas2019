package com.zpi.xmas2019

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.view.ViewPager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.TextView
import com.zpi.xmas2019.adapter.GalleryImageAdapter
import com.zpi.xmas2019.adapter.GalleryRecycleViewAdapter
import com.zpi.xmas2019.model.Event

class GalleryActivity : AppCompatActivity() {

    val images : MutableList<String> = mutableListOf()
    lateinit var imgStats : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gallery)

        if(intent.hasExtra("ChosenEventFromEvent")){
            val chosenEvent : Event = intent.getSerializableExtra("ChosenEventFromEvent") as Event
            images.addAll(chosenEvent.imgUrls)
        }
        else{
            for (i in 53 until 75){
                images.add("http://www.jarmarkbozonarodzeniowy.com/images/galeria/19$i.jpg")
            }
        }
        imgStats = findViewById(R.id.imgStats2)
        val text = "1/${images.size}"
        imgStats.text = text

        val viewPager = findViewById<ViewPager>(R.id.galleryViewPager)
        val viewPagerAdapter = GalleryImageAdapter(this, images)

        viewPager.adapter = viewPagerAdapter

        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        val recyclerView = findViewById<RecyclerView>(R.id.gallery_recycle_view)
        recyclerView.layoutManager = layoutManager
        val recyclerViewAdapter = GalleryRecycleViewAdapter(images, imgStats, viewPager)
        recyclerView.adapter = recyclerViewAdapter

        viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {

            override fun onPageScrollStateChanged(state: Int) {
            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

            }
            override fun onPageSelected(position: Int) {
                val text = "${position + 1}/${images.size}"
                imgStats.text = text
            }

        })

    }

}
