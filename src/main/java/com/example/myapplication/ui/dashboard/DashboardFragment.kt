package com.example.myapplication.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.Daily
import com.example.myapplication.DailyListAdapter
import com.example.myapplication.R
import com.example.myapplication.Todo

class DashboardFragment : Fragment() {

  private lateinit var dashboardViewModel: DashboardViewModel
  var todoList = arrayListOf<Todo>()
  var dailyList = arrayListOf<Daily>()

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    dashboardViewModel =
            ViewModelProvider(this).get(DashboardViewModel::class.java)
    val root = inflater.inflate(R.layout.fragment_dashboard, container, false)
    dailyList = arrayListOf<Daily>(
      Daily(2021,7,23,0,0,"Joohyun's birthday",""),
      Daily(2021,2,9,0,0,"Kyuyeon's birthday","")
    )
    val dailyListView = root.findViewById<ListView>(R.id.dailyList)
    val dailyAdapter = DailyListAdapter(root.context, dailyList)
    dailyListView.adapter = dailyAdapter
    return root
  }
}
