package com.example.myapplication.ui.home


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CalendarView
import android.widget.CalendarView.OnDateChangeListener
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.R


class HomeFragment : Fragment() {

  private lateinit var homeViewModel: HomeViewModel

  // Define the variable of CalendarView type
  // and TextView type;

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)
    val root = inflater.inflate(R.layout.fragment_home, container, false)

    var calender: CalendarView? = null
    var date_view: TextView? =null
    // Add Listener in calendar
    calender?.setOnDateChangeListener(
      OnDateChangeListener { view, year, month, dayOfMonth ->
        // In this Listener have one method
        // and in this method we will
        // get the value of DAYS, MONTH, YEARS
        // Store the value of date with
        // format in String type Variable
        // Add 1 in month because month
        // index is start with 0
        val Date = (dayOfMonth.toString() + "-"
                + (month + 1) + "-" + year)

        // set this date in TextView for Display
        date_view?.setText(Date)
      })

    return root
  }
}