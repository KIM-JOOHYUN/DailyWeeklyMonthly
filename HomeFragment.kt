package com.example.myapplication.ui.home


import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.CalendarView
import android.widget.CalendarView.OnDateChangeListener
import android.widget.ListView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.*
import com.example.myapplication.ui.com.example.myapplication.SundayDecorator
import com.github.clans.fab.FloatingActionButton
import com.prolificinteractive.materialcalendarview.CalendarDay
import com.prolificinteractive.materialcalendarview.MaterialCalendarView
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*


class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    var m_prefs:SharedPreferences ?= context?. getSharedPreferences("main", Context.MODE_PRIVATE)
    var schSet = mutableSetOf<String>()
    var s_name: String ?= null
    var sch_list = arrayListOf<Schedule>()
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
        val sundayDecorator = SundayDecorator()
        val saturdayDecorator = SaturdayDecorator()
        val todayDecorator = TodayDecorator(this)

        var cal: MaterialCalendarView= root.findViewById(R.id.calendar)
        cal.addDecorators(sundayDecorator, saturdayDecorator, todayDecorator)
        var sch: FloatingActionButton = root.findViewById(R.id.schedule)

        //Get Schedule list
        //schedule name set
        var db = DataBaseHandler(root.context)
        var a = db.readData()
        val today = CalendarDay.today()
//      for(i in a){
//          sch_list.add(i)
//          Log.d("testname",i.s_name.toString())
//          Log.d("testdesc",i.s_desc.toString())
//          Log.d("testst",i.s_start.toString())
//          Log.d("testend",i.s_end.toString())
//          Log.d("testtime",i.s_time.toString())
//          Log.d("testit",i.s_iter.toString())
//      }

        val format = SimpleDateFormat("yyyy.MM.dd")
        val dates: ArrayList<CalendarDay> = ArrayList()
        var arr= ArrayList<String>()

        for(i in a){

            var s_st = i.s_start?.split(".")
            var s_ed = i.s_end?.split(".")
            var st_date: Date = format.parse(i.s_start.toString())
            var ed_date: Date = format.parse(i.s_end.toString())

            var currentDate = st_date
            while (currentDate.compareTo(ed_date) <= 0) {
                arr.add(format.format(currentDate))
                val c = Calendar.getInstance()
                c.time = currentDate
                c.add(Calendar.DAY_OF_MONTH, 1)
                currentDate = c.time
            }
            for (days in arr){
                var daay=days.split(".")
                var day = CalendarDay.from(daay[0].toInt(), daay[1].toInt(),daay[2].toInt())
                dates.add(day)
            }
            if (s_st != null) {
                if(s_ed != null){
                    if(today.year.toInt() >= s_st[0].toInt() && today.year.toInt() <= s_ed[0].toInt() && today.month.toInt() >= s_st[1].toInt() && today.month.toInt() <= s_ed[1].toInt() && today.day.toInt() >= s_st[2].toInt() && today.day.toInt() <= s_ed[2].toInt()){
                        sch_list.add(i)
                    }
                    else if(today.year.toInt() == s_st[0].toInt() && today.month.toInt() == s_st[1].toInt() && today.day.toInt() == s_st[2].toInt()){
                        sch_list.add(i)
                    }
                }
            }
        }
        cal.addDecorator(EventDecorator(dates))

        var schAdapter = ScheduleAdapter(root.context, sch_list)
        var schListView = root.findViewById<ListView>(R.id.sch_list)

        schListView.adapter = schAdapter

        cal.setOnDateChangedListener(OnDateSelectedListener{ widget, date, selected ->
            val Year = date.year.toInt()
            val Month = date.month.toInt()
            val Day = date.day.toInt()
            var select_list = arrayListOf<Schedule>()
            for(i in a){
                var s_st = i.s_start?.split(".")
                var s_ed = i.s_end?.split(".")
                if (s_st != null) {
                    if(s_ed != null){
                        if(Year >= s_st[0].toInt() && Year <= s_ed[0].toInt() && Month >= s_st[1].toInt() && Month <= s_ed[1].toInt() && Day >= s_st[2].toInt() && Day <= s_ed[2].toInt()){
                            select_list.add(i)
                        }
                        else if(Year == s_st[0].toInt() && Month == s_st[1].toInt() && Day == s_st[2].toInt()){
                            select_list.add(i)
                        }
                    }
                }
            }
            schAdapter = ScheduleAdapter(root.context, select_list)
            schListView.adapter = schAdapter
        })

        sch.setOnClickListener{
            //activity?.supportFragmentManager?.beginTransaction()?.disallowAddToBackStack()?.commit()
            cal.visibility = View.GONE
            schListView.visibility = View.GONE
            activity?.supportFragmentManager?.beginTransaction()?.replace(R.id.home_fragment1,Scheduler())?.commit()
        }
        var todo: FloatingActionButton = root.findViewById(R.id.todo)
        todo.setOnClickListener{
            cal.visibility = View.GONE
            schListView.visibility = View.GONE
            activity?.supportFragmentManager?.beginTransaction()?.replace(R.id.home_fragment1,todoList())?.commit()
        }


        schListView.onItemClickListener = AdapterView.OnItemClickListener { parent, view, position, id ->
            val selectItem = parent.getItemAtPosition(position) as Schedule
            Log.d("testit",selectItem.toString())
            cal.visibility = View.GONE
            schListView.visibility = View.GONE

            var bundle : Bundle = Bundle()
            bundle.putString("name",selectItem.s_name)
            bundle.putString("desc",selectItem.s_desc)
            bundle.putString("start",selectItem.s_start)
            bundle.putString("end",selectItem.s_end)
            bundle.putString("time",selectItem.s_time)
            bundle.putString("iter",selectItem.s_iter)

            var fragment1 : UpdateSchedule = UpdateSchedule()
            fragment1.arguments = bundle
            activity?.supportFragmentManager?.beginTransaction()?.replace(R.id.home_fragment1,fragment1)?.commit()
        }
        return root

    }
}


object Main {
    @Throws(ParseException::class)
    @JvmStatic
    fun main(args: Array<String>) {
        val DATE_PATTERN = "yyyy-MM-dd"
        val inputStartDate = "2017-02-28"
        val inputEndDate = "2017-03-05"
        val sdf = SimpleDateFormat(DATE_PATTERN)
        val startDate = sdf.parse(inputStartDate)
        val endDate = sdf.parse(inputEndDate)
        val dates = ArrayList<String>()
        var currentDate = startDate
        while (currentDate.compareTo(endDate) <= 0) {
            dates.add(sdf.format(currentDate))
            val c = Calendar.getInstance()
            c.time = currentDate
            c.add(Calendar.DAY_OF_MONTH, 1)
            currentDate = c.time
        }
        for (date in dates) {
            println(date)
        }
    }
}

