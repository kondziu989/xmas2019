package com.zpi.xmas2019.dummy

import com.zpi.xmas2019.model.Event
import java.util.ArrayList
import java.util.HashMap
import java.util.Calendar
import java.util.Locale
import java.text.SimpleDateFormat

/**
 * Helper class for providing sample name for user interfaces created by
 * Android template wizards.
 *
 * TODO: Replace all uses of this class before publishing your app.
 */
object DummyEvents {

    /**
     * An array of sample (dummy) items.
     */
    var EVENTS = ArrayList<Event>()

    /**
     * A map of sample (dummy) items, by ID.
     */
    val EVENTS_MAP: MutableMap<String, Event> = HashMap()

    private val COUNT = 25

    init {
        val date = Calendar.getInstance()
        val date1 = Calendar.getInstance()
        val date2 = Calendar.getInstance()
        val date3 = Calendar.getInstance()
        val date4 = Calendar.getInstance()
        val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH)
        date.time = sdf.parse("2019-04-01 13:30:37")
        date1.time = sdf.parse("2019-04-02 16:02:37")
        date2.time = sdf.parse("2019-04-03 18:10:37")
        date3.time = sdf.parse("2019-04-04 20:15:37")
        date4.time = sdf.parse("2019-03-05 13:15:37")

        val elfy = mutableListOf<String>()
        val mikolaj = mutableListOf<String>()
        val widowisko = mutableListOf<String>()

        for (i in 53 until 75){
            elfy.add("http://www.jarmarkbozonarodzeniowy.com/images/galeria/19$i.jpg")
        }

        for (i in 1889 until 1903){
            widowisko.add("http://www.jarmarkbozonarodzeniowy.com/images/galeria/$i.jpg")
        }

        for (i in 1937 until 1952){
            mikolaj.add("http://www.jarmarkbozonarodzeniowy.com/images/galeria/$i.jpg")
        }

        addItem(Event("1", "Oficjalne otwarcie Jarmarku Bożonarodzeniowego.", date,elfy ))
        addItem(Event("2", "Magiczne Widowisko \"Święta zaklęte w bajkach", date, widowisko))
        addItem(Event("3", "Powitanie Mikołaja.", date, mikolaj))
        addItem(Event("4", "Oficjalne rozświetlenie choinki", date2, elfy))
        addItem(Event("5", "Mikołajowe występy artystyczne", date2, mikolaj))
        addItem(Event("6", "Świąteczna Parada z Mikołajem z finałem na scenie przy choince", date3, widowisko))
        addItem(Event("7", "Parada wrocławskich Elfów", date3, elfy))
        addItem(Event("8", "Wigilijny Korowód Kolędników", date3, elfy))
        addItem(Event("9", "\"Wigilia dla Samotnych i Potrzebujących\" przy akompaniamencie zespołu \"Gieni Dudki\" i zespołu \"Servoos\" - Organizator Miasto Wrocław.", date3, elfy))
        addItem(Event("10", "Wydarzenie artystyczne", date4, elfy))
        addItem(Event("11", "Szukanie czapek", date4, elfy))
        addItem(Event("12", "Picie wina", date4, elfy))
        addItem(Event("13", "Wydarzenie niespodzianka", date4, elfy))
        addItem(Event("14", "Wydarzenie niespodzianka", date4, elfy))
        addItem(Event("15", "Wydarzenie niespodzianka", date4, elfy))
        addItem(Event("16", "Oficjalne zamknięcie Jarmarku Bożonarodzeniowego.", date4, elfy))
    }

    private fun addItem(event: Event) {
        EVENTS.add(event)
        EVENTS_MAP.put(event.id, event)
    }

    private fun createDummyItem(position: Int, name: String ,urls : MutableList<String>): DummyEvent {
        return DummyEvent(position.toString(), name, makeDetails(position), urls)
    }

    private fun makeDetails(position: Int): String {
        val builder = StringBuilder()
        builder.append("Details about Item: ").append(position)
        for (i in 0..position - 1) {
            builder.append("\nMore details information here.")
        }
        return builder.toString()
    }

    /**
     * A dummy item representing a piece of name.
     */
    data class DummyEvent(val id: String, val name: String, val details: String, val imgUrls : MutableList<String>) {
        override fun toString(): String = name
    }
}
