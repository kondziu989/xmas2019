package com.zpi.xmas2019.model

import java.io.Serializable
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.*

data class Event(val id: String,val name:String = "Unknown name", var date: Calendar, val imgUrls : MutableList<String>) : Serializable
{
    val description:String = "First description"
    val image:String = ""
    constructor(id: String = "1",name:String = "Unknown name",date: Calendar, description:String, image:String, imgUrls: MutableList<String>):this(id,name,date, imgUrls) {

    }


    override fun toString(): String {
        val msg = "Selected date is " + String.format("%02d",date.get(Calendar.DAY_OF_MONTH)) + "/" + String.format("%02d",(date.get(Calendar.MONTH) + 1)) + "/" + date.get(Calendar.YEAR)
        return "Event(name='$name', $msg)"
    }
}