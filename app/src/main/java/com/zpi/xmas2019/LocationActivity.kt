package com.zpi.xmas2019

import android.content.Intent
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.FragmentTransaction
import android.util.Log
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.activity_location.*

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
            .findFragmentById(R.id.map3) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }



    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        // Add a marker in Sydney and move the camera
        val marketplace = LatLng(51.109990, 17.032788)
        mMap.addMarker(MarkerOptions().position(marketplace).title("Rynek Wrocławski"))
        val zoomLevel = 12f //This goes up to 21
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(marketplace, zoomLevel))

        //TODO ASK FOR PERMISSION
        mMap.isMyLocationEnabled = true
        mMap.uiSettings.setAllGesturesEnabled(true)
    }
}
