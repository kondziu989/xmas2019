package com.zpi.xmas2019

import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.text.style.RelativeSizeSpan
import android.view.Menu
import android.view.MenuItem
import android.widget.LinearLayout
import android.widget.TextView
import com.zpi.xmas2019.adapter.CustomAdapter
import com.zpi.xmas2019.model.Event
import kotlinx.android.synthetic.main.activity_events.*
import kotlinx.android.synthetic.main.activity_events_moreinf.*
import kotlinx.android.synthetic.main.activity_how_to_get.*
import kotlinx.android.synthetic.main.events_recycleview_list.*
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.Month
import java.time.MonthDay
import java.time.format.DateTimeFormatter
import java.util.*

class EventsActivityMoreInf : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_events_moreinf)
        changeActionBar()

        var tv1 : TextView = ID_moreinfo_title;
        val tv2 : TextView = ID_moreinfo_tv1;
        val tv3 : TextView = ID_moreinfo_tv2;
        val tv4 : TextView = ID_moreinfo_tv3;

        var v1 = SpannableString("ŚWIĄTECZNA SCENA\n")
        var v2 = SpannableString("(przy wrocławskiej choince)")
        v1.setSpan(ForegroundColorSpan(resources.getColor(R.color.colorOfMainHeader)),0,v1.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        v2.setSpan(ForegroundColorSpan(Color.BLACK),0,v2.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        v2.setSpan(RelativeSizeSpan(0.6f), 0,v2.length, 0); // set size
        tv1.text = ""
        tv1.append(v1)
        tv1.append(v2)

        v1 = SpannableString("Występy artystyczne\n")
        v2 = SpannableString("od 10 grudnia do 13 grudnia 2018,\n codziennie od godz. 16:00\n" +
                "od 14 grudnia do 15 grudnia, codziennie od godz. 15:00 \n" +
                "Specjalne wydarzenie 15 grudnia 2018 (sobota)\n - Wrocławskie Kolędowanie. \n" +
                "Organizator Wrocławskie Centrum Rozwoju Społecznego\n")
        v1.setSpan(ForegroundColorSpan(resources.getColor(R.color.colorOfMainHeader)),0,v1.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        v2.setSpan(ForegroundColorSpan(Color.BLACK),0,v2.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        v2.setSpan(ForegroundColorSpan(resources.getColor(R.color.colorOfMainHeader)),v2.indexOf("codziennie"),v2.indexOf("od 14 grudnia"), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        v2.setSpan(ForegroundColorSpan(resources.getColor(R.color.colorOfMainHeader)),v2.indexOf("codziennie od godz. 15:00"),v2.indexOf("Specjalne wydarzenie"), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        v2.setSpan(ForegroundColorSpan(resources.getColor(R.color.colorOfMainHeader)),v2.indexOf("Wrocławskie Centrum "),v2.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        tv2.text = ""
        tv2.append(v1)
        tv2.append(v2)

        v1 = SpannableString("Bezpłatne warsztaty świąteczne\n")
        v2 = SpannableString("Dla grup zorganizowanych z wrocławskich szkół i przedszkoli\n" +
                "- w Bajkowym Lasku i w trzypoziomowym \nDomku na Placu Solnym\n" +
                "- w Movie Gate - Galeria Sztuki Filmowej\n zlokalizowana w podziemnym schronie na Placu Solnym.\n Więcej informacji na www.jarmarkbozonarodzeniowy.com\n")
        v1.setSpan(ForegroundColorSpan(resources.getColor(R.color.colorOfMainHeader)),0,v1.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        v2.setSpan(ForegroundColorSpan(Color.BLACK),0,v2.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        v2.setSpan(ForegroundColorSpan(resources.getColor(R.color.colorOfMainHeader)),v2.indexOf("- w Bajkowym Lasku i w trzypoziomowym"),v2.indexOf("zlokalizowana w podziemnym schronie"), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        tv3.text = ""
        tv3.append(v1)
        tv3.append(v2)

        v1 = SpannableString("Pokazy \"Pomocników Szalonego Mikołaja\"\n")
        v2 = SpannableString("Realizowane w Movie Gate - Galeria Sztuki Filmowej zlokalizowana w podziemnym schronie na \n" +
                "Placu Solnym dla osób indywidualnych i grup zorganizowanych\n" +
                "poniedziałek - czwartek: godz. 11:00, 13:00, 15:00, 17:00\n" +
                "piątek - niedziela: godz. 11:00, 13:00, 15:00, 17:00, 19:00\n" +
                "rezerwacje, cennik i więcej informacji na www.moviegate.pl")
        v1.setSpan(ForegroundColorSpan(resources.getColor(R.color.colorOfMainHeader)),0,v1.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        v2.setSpan(ForegroundColorSpan(Color.BLACK),0,v2.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        v2.setSpan(ForegroundColorSpan(resources.getColor(R.color.colorOfMainHeader)),0,v2.indexOf("zlokalizowana w"), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)


        tv4.text = ""
        tv4.append(v1)
        tv4.append(v2)





    }

    //Manage action bar
    private fun changeActionBar() {


        // Now get the support action bar
        val actionBar = supportActionBar

        // Set toolbar title/app title
        actionBar!!.title = "Wydarzenia"

        // Set action bar elevation
        actionBar.elevation = 4.0F
    }

}

