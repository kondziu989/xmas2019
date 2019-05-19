
package com.zpi.xmas2019

import android.net.Uri
import android.opengl.Visibility
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem

import android.support.design.widget.BottomSheetBehavior
import android.support.design.widget.BottomSheetDialog
import android.support.v4.widget.NestedScrollView
import android.view.View
import android.widget.LinearLayout

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*
import com.zpi.xmas2019.dummy.DummyStalls
import kotlinx.android.synthetic.main.activity_market_maps.*


class MarketMapsActivity : AppCompatActivity(), OnMapReadyCallback, StallDetails.OnFragmentInteractionListener {
    override fun onFragmentInteraction(uri: Uri) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private lateinit var mMap: GoogleMap
    private var showAll: Boolean = false
    private var showFood: Boolean = false
    private var showBeauty: Boolean = false
    private var showXmas: Boolean = false
    private var showArt: Boolean = false
    private var stalls = ArrayList<DummyStalls.Stall>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_market_maps)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        stalls.addAll(DummyStalls.STALLS)

        val sheetBehavior = BottomSheetBehavior.from<NestedScrollView>(bottom_sheet)

        bottom_sheet.visibility=View.INVISIBLE

        /**
         * bottom sheet state change listener
         * we are changing button text when sheet changed state
         * */
        sheetBehavior.setBottomSheetCallback(object : BottomSheetBehavior.BottomSheetCallback() {
            override fun onStateChanged(bottomSheet: View, newState: Int) {
                when (newState) {
                    BottomSheetBehavior.STATE_HIDDEN -> {
                    }
                    BottomSheetBehavior.STATE_EXPANDED ->{
                        swipe_button.rotation = 0.toFloat()
                    }
                    BottomSheetBehavior.STATE_COLLAPSED ->{
                        swipe_button.rotation = 180.toFloat()
                    }
                    BottomSheetBehavior.STATE_DRAGGING -> {
                    }
                    BottomSheetBehavior.STATE_SETTLING -> {
                    }
                }
            }

            override fun onSlide(bottomSheet: View, slideOffset: Float) {
            }
        })

        swipe_button.setOnClickListener {
            when(sheetBehavior.state){
                BottomSheetBehavior.STATE_COLLAPSED ->{
                    sheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED
                }

                BottomSheetBehavior.STATE_EXPANDED ->{
                    sheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
                }
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.maps_filter_menu, menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean{
        if(item.itemId != R.id.filter) {
            mMap.clear()
            item.setChecked(!item.isChecked())
            when (item.getItemId()) {
                R.id.show_all -> {

                    if (item.isChecked()) {
                        showAll = true
                        showFood = false
                        showBeauty = false
                        showXmas = false
                        showArt = false
                    } else {
                        showAll = false
                    }
                    dummyFilter()
                    return true
                }
                R.id.show_food -> {
                    System.out.println("food chosen")
                    if (item.isChecked()) {
                        showFood = true
                        showAll = false
                    } else {
                        showFood = false
                    }
                    dummyFilter()
                    return true
                }
                R.id.show_art -> {
                    if (item.isChecked()) {
                        showArt = true
                        showAll = false
                    } else {
                        showArt = false
                    }
                    dummyFilter()
                    return true
                }
                R.id.show_beauty -> {
                    if (item.isChecked()) {
                        showBeauty = true
                        showAll = false
                    } else {
                        showBeauty = false
                    }
                    dummyFilter()
                }
                R.id.show_xmas -> {
                    if (item.isChecked()) {
                        showXmas = true
                        showAll = false
                    } else {
                        showXmas = false
                    }
                    dummyFilter()
                    return true
                }

            }
        }
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

        with(mMap){
            if(showArt or showAll){
//                addMarker(MarkerOptions().position(LatLng(51.108962, 17.033640)).title("Stoisko 103"))
//                addMarker(MarkerOptions().position(LatLng(51.109997, 17.03330)).title("Stoisko 29")  )
                val filteredStalls = stalls.filter { it.tags.contains("rękawiczki") ||  it.tags.contains("lampki")}
                filteredStalls.forEach{
                    Log.i("Stalls", it.toString())
                    addMarker(MarkerOptions().position(LatLng(it.lat, it.lng)).title("Stoisko ${it.number}"))
                }
            }
            if (showFood or showAll){
//                addMarker(MarkerOptions().position(LatLng(51.108518, 17.032859)).title("Stoisko 121"))
//                addMarker(MarkerOptions().position(LatLng(51.110317, 17.033450)).title("Stoisko 129")  )
                val filteredStalls = stalls.filter { it.tags.contains("sery") ||  it.tags.contains("pierniki") ||  it.tags.contains("pierogi") || it.tags.contains("wino")}
                filteredStalls.forEach{
                    Log.i("Stalls", it.toString())
                    addMarker(MarkerOptions().position(LatLng(it.lat, it.lng)).title("Stoisko ${it.number}"))

                }
            }
            if (showBeauty or showAll){
//                addMarker(MarkerOptions().position(LatLng(51.109235, 17.032358)).title("Stoisko 124"))
//                addMarker(MarkerOptions().position(LatLng(51.109235, 17.032937)).title("Stoisko 105")  )
                val filteredStalls = stalls.filter { it.tags.contains("czapki") ||  it.tags.contains("rękawiczki")}
                filteredStalls.forEach{
                    Log.i("Stalls", it.toString())
                    addMarker(MarkerOptions().position(LatLng(it.lat, it.lng)).title("Stoisko ${it.number}"))
                }
            }
            if(showXmas or showAll){
//                addMarker(MarkerOptions().position(LatLng(51.109422, 17.031519)).title("Stoisko 124"))
//                addMarker(MarkerOptions().position(LatLng( 51.109563, 17.030724)).title("Stoisko 105")  )
//                addMarker(MarkerOptions().position(LatLng(  51.109563, 17.030724)).title("Stoisko 124"))
                val filteredStalls = stalls.filter { it.tags.contains("rękawiczki") ||  it.tags.contains("lampki") || it.tags.contains("czapki")}
                filteredStalls.forEach{
                    Log.i("Stalls", it.toString())
                    addMarker(MarkerOptions().position(LatLng(it.lat, it.lng)).title("Stoisko ${it.number}"))
                }
            }
        }
        setUpMap()
    }
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
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
            uiSettings.setMapToolbarEnabled(false)
        }
            mMap.setOnMapClickListener {
                bottom_sheet.visibility=View.INVISIBLE
            }
            mMap.setOnMarkerClickListener(object : GoogleMap.OnMarkerClickListener {
                override fun onMarkerClick(marker: Marker): Boolean {
                    var title = marker.title
                    val regex = "Stoisko ([0-9]+)".toRegex()
                    regex.matchEntire(title)
                        ?.destructured
                        ?. let {
                            (number) ->
                            val foundStall = stalls.find {
                                it.number == number.toInt()
                            }
                            var stallFragment = StallDetails.newInstance(foundStall!!.number, ArrayList<String>(foundStall.tags))
                            var frameLayout = map
                            supportFragmentManager.beginTransaction().replace(R.id.stallinfo_frame, stallFragment).commit();
                            bottom_sheet.visibility=View.VISIBLE

                        }
                    return false
                }
            })
    }

    override fun onBackPressed() {
        val sheetBehavior = BottomSheetBehavior.from<NestedScrollView>(bottom_sheet)
        if(sheetBehavior.state==BottomSheetBehavior.STATE_EXPANDED){
            sheetBehavior.state=BottomSheetBehavior.STATE_COLLAPSED
        }
        else super.onBackPressed()
    }
    override fun onResume(){
        super.onResume()
        if(this::mMap.isInitialized) {
            dummyFilter()
        }
    }

}
