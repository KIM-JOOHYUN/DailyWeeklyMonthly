package com.example.myapplication.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.DataBaseHandler1
import com.example.myapplication.R
import com.example.myapplication.Todo
import com.example.myapplication.TodoAdapter
import com.prolificinteractive.materialcalendarview.CalendarDay
import java.text.SimpleDateFormat
import java.util.*
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.system.Os.remove
import android.widget.*


class DashboardFragment : Fragment() {

  private lateinit var dashboardViewModel: DashboardViewModel
  var todoList = arrayListOf<Todo>()
  var todayList= arrayListOf<Todo>()
  var futureList= arrayListOf<Todo>()
  var helper: SQLiteOpenHelper? = null
  var DATABASE_NAME1 = "MyDB1"
  var TABLE_NAME1 = "Todo"
  var COL_NAME1 = "schedule_name1"
  var COL_START1 = "date1"


  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    dashboardViewModel =
            ViewModelProvider(this).get(DashboardViewModel::class.java)
    val root = inflater.inflate(R.layout.fragment_dashboard, container, false)

    var db = DataBaseHandler1(root.context)
    var a = db.readData()

    todoList= a as ArrayList
    val today = CalendarDay.today()


    var iter=todoList.listIterator()

    while(iter.hasNext()){
      var s=iter.next()
      var s_st = s.s_start?.split("/")
      if (s_st != null) {
        if (today.year.toInt() == s_st[0].toInt() && today.month.toInt() == s_st[1].toInt() && today.day.toInt() == s_st[2].toInt()) {
          todayList.add(s)
        }

      }
    }

    iter=todoList.listIterator()
    val format = SimpleDateFormat("yyyy/MM/dd")


    while(iter.hasNext()){
      var s=iter.next()
      val strDate = format.parse(s.s_start.toString())
      if (Date().before(strDate)) {
        futureList.add(s)
      }
    }


    val todoAdapter = TodoAdapter(root.context,todayList)
    val todoListView = root.findViewById<ListView>(R.id.TodayList)
    //todoListView.choiceMode(ListView.CHOICE_MODE_MULTIPLE)
    todoListView.choiceMode = ListView.CHOICE_MODE_SINGLE
    todoListView.adapter = todoAdapter

    val futuretodoAdapter = TodoAdapter(root.context, futureList)
    val futuretodoListView = root.findViewById<ListView>(R.id.todoList)
    futuretodoListView.adapter = futuretodoAdapter



    return root
  }



}

