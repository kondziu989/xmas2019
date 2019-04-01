package com.zpi.xmas2019

import android.content.Intent
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.FragmentTransaction
import android.util.Log
import android.widget.ScrollView
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.*
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.maps.model.PolylineOptions
import kotlinx.android.synthetic.main.activity_location.*
import com.zpi.xmas2019.map.WorkaroundMapFragment





class LocationActivity : AppCompatActivity(), OnMapReadyCallback {
    private lateinit var mMap: GoogleMap


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_location)


        title="Lokalizacja"
        drive_link.setOnClickListener{
            startActivity(Intent(this, HowToGetActivity::class.java))
        }

        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map3) as WorkaroundMapFragment

        mapFragment.getMapAsync(this)
        val mScrollView = findViewById<ScrollView>(R.id.scrollView)

        (supportFragmentManager.findFragmentById(R.id.map3) as WorkaroundMapFragment).setListener(object :
            WorkaroundMapFragment.OnTouchListener {
            override fun onTouch() {
                mScrollView.requestDisallowInterceptTouchEvent(true)
            }
        })

    }




    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap



        // Add a marker in Sydney and move the camera
        //TODO ASK FOR PERMISSION
        mMap.isMyLocationEnabled = true

        val marketplace = LatLng(51.109990, 17.032788)
       // val mylocation = LatLng(mMap.myLocation.latitude, mMap.myLocation.longitude)
        //val way = PolylineOptions().add(marketplace).add(mylocation)
        mMap.addMarker(MarkerOptions().position(marketplace).title("Rynek Wrocławski"))
        val zoomLevel = 12f //This goes up to 21
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(marketplace, zoomLevel))

        //mMap.addPolyline(way);
        mMap.uiSettings.setAllGesturesEnabled(true)
    }
}
