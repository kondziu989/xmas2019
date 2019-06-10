
package com.zpi.xmas2019

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
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
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.LinearLayout

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*
import com.zpi.xmas2019.dummy.DummyStalls
import kotlinx.android.synthetic.main.activity_market_maps.*
import android.graphics.Bitmap
import android.graphics.Canvas
import android.provider.MediaStore.Images.Media.getBitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.text.Editable
import android.view.inputmethod.InputMethodManager
import com.zpi.xmas2019.adapter.StallTagAdapter
import android.text.TextWatcher


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
    private var searchBarVisible = false
    private var markers = ArrayList<Marker>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_market_maps)
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

        val tagSearchBox = findViewById<EditText>(R.id.search_tags_text)

        tagSearchBox.afterTextChanged {
            if(it.isNotEmpty()){
                createTagList(searchTags((it)))
            } else {
                createTagList(ArrayList())
            }
        }

//        val searchStallsButton = findViewById<ImageButton>(R.id.search_tags_button)

//        searchStallsButton.setOnClickListener {
//            onSearchClick()
//            hideKeyboard()
//        }
    }

    fun EditText.afterTextChanged(afterTextChanged: (String) -> Unit) {
        this.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun afterTextChanged(editable: Editable?) {
                afterTextChanged.invoke(editable.toString())
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.maps_filter_menu, menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean{
        if(item.itemId == R.id.app_bar_search){
            bottom_sheet.visibility=View.INVISIBLE
            searchBarVisible = !searchBarVisible
//            Log.i("Visible", searchBarVisible.toString())
            val searchTags = findViewById<LinearLayout>(R.id.search_tags)
            if(searchBarVisible)  {
                searchTags.visibility = View.VISIBLE
                searchTags.requestFocus()
                val keyboard = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                keyboard.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY)
            }
            else {
                searchTags.visibility = View.INVISIBLE
                hideKeyboard()
            }
            return true
        }
        if(item.itemId != R.id.filter) {
            mMap.clear()
            item.isChecked= !item.isChecked
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

    fun dummyFilter(){

        with(mMap){
            if(showArt or showAll){
                val filteredStalls = stalls.filter { it.tags.contains("rękawiczki") ||  it.tags.contains("lampki")} as ArrayList<DummyStalls.Stall>
                createMarkers(filteredStalls).forEach { addMarker(it) }
            }
            if (showFood or showAll){
                val filteredStalls = stalls.filter { it.tags.contains("sery") ||  it.tags.contains("pierniki") ||  it.tags.contains("pierogi") || it.tags.contains("wino")} as ArrayList<DummyStalls.Stall>
                createMarkers(filteredStalls).forEach { addMarker(it) }
            }
            if (showBeauty or showAll){
                val filteredStalls = stalls.filter { it.tags.contains("czapki") ||  it.tags.contains("rękawiczki")} as ArrayList<DummyStalls.Stall>
                createMarkers(filteredStalls).forEach { addMarker(it) }
            }
            if(showXmas or showAll){
                val filteredStalls = stalls.filter { it.tags.contains("rękawiczki") ||  it.tags.contains("lampki") || it.tags.contains("czapki")} as ArrayList<DummyStalls.Stall>
                createMarkers(filteredStalls).forEach { addMarker(it) }
            }
        }
        setUpMap()
    }
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        dummyFilter()

    }
    fun bitmapDescriptorFromVector( vectorResId: Int) :BitmapDescriptor{
        val  vectorDrawable :Drawable = getDrawable(vectorResId)
        vectorDrawable.setBounds(0, 0, vectorDrawable.getIntrinsicWidth(), vectorDrawable.getIntrinsicHeight());
        val bitmap :Bitmap  = Bitmap.createBitmap(vectorDrawable.getIntrinsicWidth(), vectorDrawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888)
         val canvas = Canvas(bitmap)
        vectorDrawable.draw(canvas)
        return BitmapDescriptorFactory.fromBitmap(bitmap)
}
    fun setUpMap(){
        val cameraPosition = LatLng(51.1099955, 17.0323775)
        val areaBounds = LatLngBounds(LatLng(51.107022, 17.027541), LatLng(51.112969, 17.037214))
        with(mMap){
            moveCamera(CameraUpdateFactory.newLatLng(cameraPosition))
            addGroundOverlay(GroundOverlayOptions().apply {
            image(bitmapDescriptorFromVector(R.drawable.ic_framedmap))
            positionFromBounds(areaBounds)
            clickable(true)
        })
            setLatLngBoundsForCameraTarget(LatLngBounds(LatLng(51.1093, 17.0298),LatLng(51.1107, 17.035)))
            setMinZoomPreference(17f)
            isMyLocationEnabled = true
            uiSettings.setAllGesturesEnabled(true)
            uiSettings.setMapToolbarEnabled(false)
        }
            mMap.setOnMapClickListener {
                bottom_sheet.visibility=View.INVISIBLE
            }
            mMap.setOnGroundOverlayClickListener {
                bottom_sheet.visibility=View.INVISIBLE
                hideKeyboard()
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

    fun searchTags(tag: String) : ArrayList<String>{
        tag.trim()
        val tags = DummyStalls.TAGS
        val foundTags = tags.filter { it.startsWith(tag, ignoreCase = true) }
        Log.i("Search", foundTags.toString())
        return foundTags as ArrayList<String>
    }

    fun searchStalls(tags: ArrayList<String>) : ArrayList<DummyStalls.Stall>{
        val foundStals = DummyStalls.STALLS.filter {
            it.tags.map {
                tags.contains(it)
            }.contains(true)
        }
        Log.i("SearchStall", foundStals.count().toString())
        return foundStals as ArrayList<DummyStalls.Stall>
    }

    fun createTagList(tags : ArrayList<String>){
        val tagsList = findViewById<RecyclerView>(R.id.found_tags_list)
        tagsList.layoutManager = LinearLayoutManager(this)
        tagsList.adapter = StallTagAdapter(tags, this)
        (tagsList.adapter as StallTagAdapter).onItemClick = { it ->
            val temp = ArrayList<String>()
            temp.add(it)
            stalls = searchStalls(temp)
            Log.i("Stall", temp.toString())
            Log.i("Stall", stalls.count().toString())
            val searchBox = findViewById<EditText>(R.id.search_tags_text)
            searchBox.text = Editable.Factory.getInstance().newEditable(it)
            searchBox.setSelection(searchBox.text.length)
            val searchTagsBox = findViewById<LinearLayout>(R.id.search_tags)
            searchTagsBox.visibility = View.INVISIBLE
            bottom_sheet.visibility=View.INVISIBLE
            hideKeyboard()
            markers.forEach{ it.remove()}
            markers.clear()
            with(mMap){
                createMarkers(stalls).forEach { markers.add(addMarker(it)) }
            }
            //setUpMap()
        }
    }

    fun createMarkers(stalls : ArrayList<DummyStalls.Stall>) : ArrayList<MarkerOptions> {
        return stalls.map {
            val height = 140
            val width = 120
            val bitmapdraw = getDrawable(R.mipmap.ic_launcher_foreground) as BitmapDrawable
            val b = bitmapdraw.bitmap
            val smallMarker = Bitmap.createScaledBitmap(b, width, height, false)
            MarkerOptions().position(LatLng(it.lat, it.lng)).title("Stoisko ${it.number}").icon(BitmapDescriptorFactory.fromBitmap(smallMarker))
        } as ArrayList<MarkerOptions>
    }

    fun hideKeyboard(){
        val searchTags = findViewById<LinearLayout>(R.id.search_tags)
        val keyboard = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        keyboard.hideSoftInputFromWindow(searchTags.windowToken, 0)
    }

//    fun onSearchClick(){
//        val searchBar = findViewById<EditText>(R.id.search_tags_text)
//        val tags = searchTags(searchBar.text.toString())
//        stalls = searchStalls(tags)
//
//        with(mMap){
//            createMarkers(stalls).forEach { addMarker(it) }
//        }
//        setUpMap()
//    }

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

    //TODO merge with master

}
