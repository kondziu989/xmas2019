
package com.zpi.xmas2019

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.CheckBox

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*


class MarketMapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private var showAll: Boolean = true
    private var showFood: Boolean = false
    private var showBeauty: Boolean = false
    private var showXmas: Boolean = false
    private var showArt: Boolean = false
    private var currentMarkers: ArrayList<Marker> = ArrayList()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_market_maps)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.maps_filter_menu, menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean{
        removeAllMarkers()
        item.setChecked(!item.isChecked())
        when(item.getItemId()) {
            R.id.show_all -> {

                if (item.isChecked()){
                showAll = true
                showFood = false
                showBeauty = false
                showXmas = false
                showArt = false
                }else {
                    showAll = false
                }
            }
            R.id.show_food -> {
                if (item.isChecked()){
                    showFood = true
                    showAll = false
                  }else{ showFood = false}
            }
            R.id.show_art -> {
                if (item.isChecked()){
                showArt = true
                showAll = false
                } else {showArt = false}
            }
            R.id.show_beauty -> {
                if (item.isChecked()){
                showBeauty = true
                showAll = false
                  } else { showBeauty = false}
            }
            R.id.show_xmas -> {
                if (item.isChecked()){
                showXmas = true
                showAll = false
                } else{ showXmas = false}
            }

        }
        dummyFilter()
        return true
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

    fun dummyFilter(){

            if(showArt or showAll){
                currentMarkers.add(mMap.addMarker(MarkerOptions().position(LatLng(51.108962, 17.033640)).title("Stoisko 103")))
                currentMarkers.add(mMap.addMarker(MarkerOptions().position(LatLng(51.109997, 17.03330)).title("Stoisko 29")  ))
            }
            if (showFood or showAll){
                currentMarkers.add(mMap.addMarker(MarkerOptions().position(LatLng(51.108518, 17.032859)).title("Stoisko 121")) )
                currentMarkers.add(mMap.addMarker(MarkerOptions().position(LatLng(51.110317, 17.033450)).title("Stoisko 129")  ))
            }
            if (showBeauty or showAll){
                currentMarkers.add(mMap.addMarker(MarkerOptions().position(LatLng(51.109235, 17.032358)).title("Stoisko 124")))
                currentMarkers.add(mMap.addMarker(MarkerOptions().position(LatLng(51.109235, 17.032937)).title("Stoisko 105")  ))
            }
            if(showXmas or showAll){
                currentMarkers.add(mMap.addMarker(MarkerOptions().position(LatLng(51.109422, 17.031519)).title("Stoisko 124")))
                currentMarkers.add(mMap.addMarker(MarkerOptions().position(LatLng( 51.109563, 17.030724)).title("Stoisko 105")  ))
                currentMarkers.add(mMap.addMarker(MarkerOptions().position(LatLng(  51.109563, 17.030724)).title("Stoisko 124")))
            }

       // setUpMap()
    }
    fun removeAllMarkers(){
        for (currentMarker in currentMarkers) {
            if(currentMarker!=null){
                currentMarker.remove()
            }
        }
        currentMarkers.clear()
    }
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
       /* val area_bounds: LatLngBounds= LatLngBounds(LatLng(51.107337, 17.027925), LatLng(51.112358, 17.036697))
        mMap.addGroundOverlay(GroundOverlayOptions().apply {
            image(BitmapDescriptorFactory.fromResource(R.drawable.mapa2222))
            positionFromBounds(area_bounds)
            //transparency(0.5f)
            clickable(true)
        })*/
        setUpMap()
        dummyFilter()

    }
    fun setUpMap(){
        val xMarketPosition = LatLng(51.109885, 17.032344)
        mMap.addMarker(MarkerOptions().position(xMarketPosition).title("Jarmark"))
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
    override fun onResume(){
        super.onResume()
        if(this::mMap.isInitialized) {
            dummyFilter()
        }
    }

}
