package com.zpi.xmas2019

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView

class GalleryActivity : AppCompatActivity() {

    private val images : MutableList<Int> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        title = "Galeria"
        setContentView(R.layout.activity_gallery)


        createImagesList(10)
        createGallery()

        val selectedImg = findViewById<ImageView>(R.id.imageView)
        selectedImg.tag = 0
        selectedImg.setImageResource(images[0])


    }

    private fun createGallery(){
        val linearLayout = findViewById<LinearLayout>(R.id.imagesList)
        val imgStats = findViewById<TextView>(R.id.imgStats)
        val text = "1/${images.size}"
        imgStats.text = text
        for(i in 0 until images.size){
            val imageView = ImageView(this)
            imageView.setImageResource(images[i])
            imageView.tag = i
            imageView.setOnClickListener { view ->
                val currentImage = findViewById<ImageView>(R.id.imageView)
                val selectedImgText = findViewById<TextView>(R.id.imgStats)
                val selectedImgId  = images.get(view.tag as Int)
                val text = "${view.tag as Int + 1}/${images.size}"
                selectedImgText.text = text
                currentImage.setImageResource(selectedImgId)
            }
            linearLayout.addView(imageView)
        }
    }

    private fun createImagesList(size: Int){
        val imgResId = R.mipmap.ic_launcher
        val imgResIdRound = R.mipmap.ic_launcher_round
        for (x in 0 until size){
            when(x%2){
                0 -> images.add(imgResId)
                1 -> images.add(imgResIdRound)
            }
        }
    }

}
