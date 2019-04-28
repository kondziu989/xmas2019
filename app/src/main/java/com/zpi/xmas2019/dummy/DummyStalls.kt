package com.zpi.xmas2019.dummy

import kotlin.random.Random

object DummyStalls {

    var STALLS = ArrayList<Stall>()

    val COUNT = 100

    init {
        val tags = createTags()
        val imgList = ArrayList<Int>()
        for (i in 1 until COUNT){
            val stallTags = ArrayList<String>()
            for (tagIndex in 0 until Random(4).nextInt()){
                stallTags.add(tags[Random(tags.size-1).nextInt()])
            }

            STALLS.add(Stall(i, stallTags, imgList))
        }
    }

    private fun createTags() : ArrayList<String>{
        val tags = ArrayList<String>()
        tags.add("wino")
        tags.add("pierniki")
        tags.add("czapki")
        tags.add("sery")
        tags.add("miód pitny")
        tags.add("rękawiczki")
        tags.add("rękodzieła")
        tags.add("pierogi")
        tags.add("lampki")
        return tags
    }

    data class Stall(val number: Int, val tags: ArrayList<String>, val imgList: ArrayList<Int>)
}