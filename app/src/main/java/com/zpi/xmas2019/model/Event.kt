package com.zpi.xmas2019.model

import com.zpi.xmas2019.R
import java.io.Serializable
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.*

data class Event(val id: String,val name:String = "Unknown name", var date: Calendar, val imgUrls : MutableList<String>) : Serializable
{
    var description:String = "First description"
    var images : MutableList<String> = mutableListOf()
    constructor(id: String = "1", name:String = "Unknown name", date: Calendar, description:String, image:String, imgUrls: MutableList<String>):this(id, name, date, imgUrls)
    {
        val description: String = "Pokazy pomocników Szalonego Mikołaja" +
                "Realizowane w Movie Gate - Galeria Sztuki Filmowej zlokalizowana w podziemnym schronie na Placu Solnym dla osób indywidualnych i grup zorganizowanych" +
                "poniedziałek - czwartek: godz. 11:00, 13:00, 15:00, 17:00" +
                "piątek - niedziela: godz. 11:00, 13:00, 15:00, 17:00, 19:00" +
                "rezerwacje, cennik i więcej informacji na www.moviegate.pl"
        val image = "jarmark1"
    }

    override fun toString(): String {
        val msg = "Selected date is " + String.format("%02d",date.get(Calendar.DAY_OF_MONTH)) + "/" + String.format("%02d",(date.get(Calendar.MONTH) + 1)) + "/" + date.get(Calendar.YEAR)
        return "Event(name='$name', $msg)"
    }
}