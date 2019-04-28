package com.zpi.xmas2019

import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_test.*
import android.util.Log

class TestActivity : AppCompatActivity(), StallDetails.OnFragmentInteractionListener {

    override fun onFragmentInteraction(uri: Uri) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.i("INFO", "Creating TestActivity")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)
        var tags = ArrayList<String>()
        for(i in 1..10){
            tags.add("Tag $i");
        }
        var stallFragment = StallDetails.newInstance(1, tags)
        var frameLayout = frameLayout
        supportFragmentManager.beginTransaction().replace(R.id.frameLayout, stallFragment).commit();
    }
}
