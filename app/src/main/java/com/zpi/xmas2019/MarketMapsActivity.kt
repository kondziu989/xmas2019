
package com.zpi.xmas2019

import android.support.v7.app.AppCompatActivity
import android.os.Bundle

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*


class MarketMapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_market_maps)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        val xMarketPosition = LatLng(51.109885, 17.032344)
        mMap.addMarker(MarkerOptions().position(xMarketPosition).title("Marker in Sydney"))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(xMarketPosition))
        val area_bounds: LatLngBounds= LatLngBounds(LatLng(51.107337, 17.027925), LatLng(51.112358, 17.036697))
        with(mMap){addGroundOverlay(GroundOverlayOptions().apply {
            image(BitmapDescriptorFactory.fromResource(R.drawable.mapa2222))
            positionFromBounds(area_bounds)
            //transparency(0.5f)

            clickable(true)
        })
            setLatLngBoundsForCameraTarget(LatLngBounds(LatLng(51.1078, 17.028),LatLng(51.112, 17.0355)))
            setMinZoomPreference(17f)
            isMyLocationEnabled = true
            uiSettings.setAllGesturesEnabled(true)
        }

    }
}
