package com.example.myapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.ui.notifications.NotificationsViewModel
import com.github.clans.fab.FloatingActionButton
import com.google.android.material.datepicker.MaterialDatePicker
import java.util.*


class Scheduler : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.scheduler, null)
        var mBtn: Button =view.findViewById(R.id.date)
        mBtn.setOnClickListener{
            activity?.supportFragmentManager?.beginTransaction()?.disallowAddToBackStack()
           activity?.supportFragmentManager?.beginTransaction()?.replace(R.id.nav_host_fragment,RangeDateFragment())?.commit()

       }
        var date: TextView= view.findViewById(R.id.date_info)
        var startDate = arguments?.getString("key1")
        var endDate=arguments?.getString("key2")
        date.setText(startDate+" ~ "+endDate)

        return view
    }


}
