package com.example.myapplication

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.ui.notifications.NotificationsViewModel
import com.github.clans.fab.FloatingActionButton
import com.google.android.material.datepicker.MaterialDatePicker
import java.text.SimpleDateFormat
import java.util.*


class RangeDateFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        showDateRangePicker()

    }
    // 기간을 선택하기 위한 datePicker
    fun showDateRangePicker(){
        val builder = MaterialDatePicker.Builder.dateRangePicker()
        // 타이틀 정하는 코드

        builder.setTitleText("Pick the date")
        val picker = builder.build()
        picker.show(activity?.supportFragmentManager!!, picker.toString())
        picker.addOnNegativeButtonClickListener{ picker.dismiss() }
        picker.addOnPositiveButtonClickListener {
            val startDate = SimpleDateFormat("yyyy.MM.dd", Locale.getDefault()).format(it.first)
            val endDate = SimpleDateFormat("yyyy.MM.dd", Locale.getDefault()).format(it.second)
            Log.d("test", "startDate: $startDate, endDate : $endDate")

            var bundle1: Bundle?=null
            bundle1?.putString("key1",startDate)
            bundle1?.putString("key2",endDate)

            val fragment: Fragment=Scheduler()
            var transaction: FragmentTransaction? = activity?.supportFragmentManager?.beginTransaction()
            fragment.arguments=bundle1


        }
    }
}