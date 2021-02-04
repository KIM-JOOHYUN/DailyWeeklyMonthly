package com.example.myapplication.ui.home


import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CalendarView
import android.widget.CalendarView.OnDateChangeListener
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.*
import android.support.v4.app.*
import androidx.core.view.isVisible
import androidx.fragment.app.replace
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.*
import com.example.myapplication.ui.com.example.myapplication.SundayDecorator
import com.github.clans.fab.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.prolificinteractive.materialcalendarview.MaterialCalendarView


class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    var m_prefs:SharedPreferences ?= context?. getSharedPreferences("main",0)
    var schSet : Set<String> ?= null
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

      //schedule name set
      schSet = m_prefs?.getStringSet("sch",schSet)

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
    val sundayDecorator = SundayDecorator()
    val saturdayDecorator = SaturdayDecorator()
    val todayDecorator = TodayDecorator(this)

    var cal: MaterialCalendarView= root.findViewById(R.id.calendar)
    cal.addDecorators(sundayDecorator, saturdayDecorator, todayDecorator)

    var sch: FloatingActionButton = root.findViewById(R.id.schedule)

    sch.setOnClickListener{
        //activity?.supportFragmentManager?.beginTransaction()?.disallowAddToBackStack()?.commit()
        cal.visibility = View.GONE
        activity?.supportFragmentManager?.beginTransaction()?.replace(R.id.home_fragment1,Scheduler())?.commit()

    }
    var todo: FloatingActionButton = root.findViewById(R.id.todo)
    todo.setOnClickListener{
      cal.visibility = View.GONE
      activity?.supportFragmentManager?.beginTransaction()?.replace(R.id.home_fragment1,Scheduler())?.commit()

    }

    return root

  }
}