package com.zpi.xmas2019

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        title="Lokalizacja";
        events_btn.setOnClickListener {
            startActivity(Intent(this, EventsActivity::class.java))
        }
        map_btn.setOnClickListener {
            startActivity(Intent(this, MapActivity::class.java))
        }
        restaurants_btn.setOnClickListener {
            startActivity(Intent(this, RestaurantsActivity::class.java))
        }
        kontakt_link.setOnClickListener {
            startActivity(Intent(this, ContactActivity::class.java))
        }
        location_link.setOnClickListener {
            startActivity(Intent(this, LocationActivity::class.java))
        }
        media_link.setOnClickListener{
            startActivity(Intent(this, GalleryActivity::class.java))
        }
    }
}

