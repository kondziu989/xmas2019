package com.zpi.xmas2019.dummy

import android.util.Log
import com.google.android.gms.maps.model.LatLng
import java.util.stream.Collector
import kotlin.random.Random

object DummyStalls {

    var STALLS = ArrayList<Stall>()

    val COUNT = 100

    init {
        val tags = createTags()
        val imgList = ArrayList<Int>()
        val positions = generatePositions()
        for (i in 1 until COUNT){
            val stallTags = getRandomTags(tags)

            val keys : List<String> = ArrayList(positions.keys)
            val key = keys[Random.nextInt(0, keys.size - 1)]
            val generatedPosition = generateRandomPosition(positions[key]!!)

            STALLS.add(Stall(i, generatedPosition[0], generatedPosition[1], stallTags, imgList))
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

    private fun getRandomTags(tags : ArrayList<String>) : List<String> {
        val stallTags = HashSet<String>()
        val stallTagsSize = (1..4).random()
        while (stallTags.size < stallTagsSize){
            stallTags.add(tags[(0 until tags.size -1).random()])
        }
        return stallTags.toList()
    }

    private fun generatePositions() : MutableMap<String, ArrayList<Position>>{
        val positions = HashMap<String, ArrayList<Position>>()
        positions.put("RR", ArrayList())
        positions.get("RR")?.add(Position("RRU",51.110467, 17.033438))
        positions.get("RR")?.add(Position("RRD", 51.109214, 17.032913))
        positions.put("RL", ArrayList())
        positions.get("RL")?.add(Position("RLU",51.110497, 17.033438))
        positions.get("RL")?.add(Position("RLD",51.109328, 17.032555))
        positions.put("UU", ArrayList())
        positions.get("UU")?.add(Position("GGR",51.110532, 17.033449))
        positions.get("UU")?.add(Position("GGL",51.110985, 17.031077))
        positions.put("UD", ArrayList())
        positions.get("UD")?.add(Position("GDR",51.110475, 17.033347))
        positions.get("UD")?.add(Position("GDL",51.110925, 17.031045))
        positions.put("DU", ArrayList())
        positions.get("DU")?.add(Position("DGR",51.109232, 17.032803))
        positions.get("DU")?.add(Position("DGL",51.109695, 17.030799))
        positions.put("DD", ArrayList())
        positions.get("DD")?.add(Position("DDR",51.109190, 17.032698))
        positions.get("DD")?.add(Position("DDL",51.109640, 17.030753))

        return positions
    }

    private fun generateRandomPosition(positions : ArrayList<Position>) : ArrayList<Double> {
        val generatedPositions = ArrayList<Double>()
        if(positions[0].lat > positions[1].lat){
            generatedPositions.add(positions[1].lat + (positions[0].lat - positions[1].lat) * Random.nextDouble())
        } else {
            generatedPositions.add(positions[0].lat + (positions[1].lat - positions[0].lat) * Random.nextDouble())
        }
        if(positions[0].lng > positions[1].lng){
            generatedPositions.add(positions[1].lng + (positions[0].lng - positions[1].lng) * Random.nextDouble())
        } else {
            generatedPositions.add(positions[0].lng + (positions[1].lng - positions[0].lng) * Random.nextDouble())
        }
        return generatedPositions
    }

    data class Position(val name : String, val lat: Double, val lng: Double)

    data class Stall(val number: Int, val lat : Double, val lng: Double, val tags: List<String>, val imgList: ArrayList<Int>){
        override fun toString(): String = "$number lat: $lat lng: $lng $tags"
    }
}