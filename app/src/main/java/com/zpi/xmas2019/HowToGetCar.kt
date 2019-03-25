package com.zpi.xmas2019

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.fragment_how_to_get_tram.view.*
import android.content.Intent
import android.net.Uri
import kotlinx.android.synthetic.main.fragment_how_to_get_car.*


class HowToGetCar : Fragment(), OnMapReadyCallback {
    private lateinit var mMap: GoogleMap

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_how_to_get_car, container, false)
        val mapFragment = childFragmentManager
            .findFragmentById(R.id.map2) as SupportMapFragment
        mapFragment.getMapAsync(this)
        return rootView
    }


     /* Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        // Add a marker in Sydney and move the camera
        val marketplace = LatLng(51.109990, 17.032788)
        mMap.addMarker(MarkerOptions().position(marketplace).title("Rynek Wrocławski"))
         val zoomLevel = 14.0f //This goes up to 21
         mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(marketplace, zoomLevel))

         //TODO ASK FOR PERMISSION
         mMap.isMyLocationEnabled = true
         mMap.uiSettings.setAllGesturesEnabled(true)


         open_googlemaps_link.setOnClickListener {
             // Create a Uri from an intent string. Use the result to create an Intent.
             val gmmIntentUri = Uri.parse("google.navigation:q=Wrocław, +Rynek")
// Create an Intent from gmmIntentUri. Set the action to ACTION_VIEW
             val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
// Make the Intent explicit by setting the Google Maps package
             mapIntent.setPackage("com.google.android.apps.maps")
             startActivity(mapIntent)
         }

    }
}