package com.example.myapplication

import android.app.TimePickerDialog
import android.app.TimePickerDialog.OnTimeSetListener
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.google.android.material.datepicker.MaterialDatePicker
import java.text.SimpleDateFormat
import java.util.*


class Scheduler : Fragment() {
    var date: TextView?=null
    var startDate: String ?=null
    var endDate: String ?=null

    var mOnTimeSetListener: OnTimeSetListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.scheduler, null)
        var mBtn: Button =view.findViewById(R.id.date)
        var tBtn: Button=view.findViewById(R.id.time_info)
        date=view.findViewById(R.id.date_info)
        var time: TextView=view.findViewById(R.id.time_1)
        mBtn.setOnClickListener{

            val builder = MaterialDatePicker.Builder.dateRangePicker()
            // 타이틀 정하는 코드

            builder.setTitleText("Pick the date")
            val picker = builder.build()
            picker.show(activity?.supportFragmentManager!!, picker.toString())
            picker.addOnNegativeButtonClickListener{ picker.dismiss() }
            picker.addOnPositiveButtonClickListener {

                startDate = SimpleDateFormat("yyyy.MM.dd", Locale.getDefault()).format(it.first)
                endDate = SimpleDateFormat("yyyy.MM.dd", Locale.getDefault()).format(it.second)
                Log.d("test", "startDate: $startDate, endDate : $endDate")
                date?.setText(" $startDate ~ $endDate")
            }

       }

        tBtn.setOnClickListener(View.OnClickListener {
            val mCalendar = Calendar.getInstance()
            val hour = mCalendar[Calendar.HOUR_OF_DAY]
            val minute = mCalendar[Calendar.MINUTE]
            val mTimePickerDialog = TimePickerDialog(activity,
                android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                mOnTimeSetListener,
                hour, minute, true
            )
            mTimePickerDialog.window
                ?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            mTimePickerDialog.show()
        })

        mOnTimeSetListener =
            OnTimeSetListener { view, hourofday, minute ->
                val mTime = "$hourofday:$minute"
                time.setText(mTime)
            }


        return view
    }



}
