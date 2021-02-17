package com.example.myapplication

import android.app.TimePickerDialog
import android.app.TimePickerDialog.OnTimeSetListener
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.text.InputType
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import com.google.android.material.datepicker.MaterialDatePicker
import java.text.SimpleDateFormat
import java.util.*

import android.widget.ArrayAdapter
import com.example.myapplication.ui.home.HomeFragment


class UpdateSchedule : Fragment() {
    var startDate: String ?=null
    var endDate: String ?=null
    var mOnTimeSetListener: TimePickerDialog.OnTimeSetListener? = null
    var s_name: String ?= null
    var s_desc:String ?= null
    var s_time:String ?= null
    var iter:String ?= null
    var schSet = mutableSetOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_update_schedule, null)

        val db = DataBaseHandler(view.context)

        var mBtn: Button =view.findViewById(R.id.date)
        var tBtn: Button =view.findViewById(R.id.time_info)
        var date : TextView = view.findViewById(R.id.date_info)
        //buttons
        var sBtn : Button =view.findViewById(R.id.updateBtn)
        var dBtn : Button = view.findViewById(R.id.deleteBtn)
        var bBtn : Button = view.findViewById(R.id.backBtn)

        var nameText : EditText =view.findViewById(R.id.name)
        var descText : EditText =view.findViewById(R.id.description)

        var time: TextView =view.findViewById(R.id.time_1)
        var bundle = arguments
        s_name = bundle?.getString("name")
        s_desc = bundle?.getString("desc")
        startDate = bundle?.getString("start")
        endDate = bundle?.getString("end")
        s_time = bundle?.getString("time")
        iter = bundle?.getString("iter")

        date.setText(startDate + " ~ " +endDate)
        time.setText(s_time)
        nameText.setText(s_name)
        descText.setText(s_desc)

        Log.d("test", "startDate: $startDate, endDate : $endDate")
        Log.d("test", "name: $s_name, desc : $s_desc")
        //date
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

        //time
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
                    //수정함
                    s_time = mTime
                }

        //repeat
        val items = resources.getStringArray(R.array.Iter)
        val spinner : Spinner = view.findViewById(R.id.spinner)
        val myAdapter = ArrayAdapter(
                view.context,
                android.R.layout.simple_list_item_1, items
        )
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = myAdapter

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {

                //아이템이 클릭 되면 맨 위부터 position 0-3번부터 순서대로 동작하게 됩니다.
                when(position) {
                    //no repeat
                    0  ->  {
                        iter = "no"
                    }
                    //every day
                    1  -> {
                        iter = "day"
                    }
                    //week
                    2 -> {
                        iter = "week"
                    }
                    //month
                    3 -> {
                        iter = "month"
                    }
                    //year
                    4 -> {
                        iter = "year"
                    }
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
            }
        }

        //update button(sBtn)
        sBtn.setOnClickListener(View.OnClickListener {

            if(sBtn.text.toString() == "Modify"){
                sBtn.text = "Save"
                db.deleteData(s_name!!,s_desc!!)
                mBtn.isEnabled = true
                tBtn.isEnabled = true
                nameText.isEnabled = true
                descText.isEnabled = true

            }
            else if(sBtn.text.toString() == "Save"){
                db.deleteData(s_name!!, s_desc!!)
                s_name = nameText.text.toString()
                s_desc = descText.text.toString()
                Log.d("test", "s_name: $s_name, s_desc : $s_desc")
                var db = DataBaseHandler(view.context)
                var sch:Schedule = Schedule(s_name,s_desc,startDate,endDate,s_time,iter)
                db.insertData(sch)


                var a = db.readData()
                for(i in a){
                    Log.d("test",i.s_name.toString())
                }

                mBtn.isEnabled = false
                tBtn.isEnabled = false
                nameText.isEnabled = false
                descText.isEnabled = false

                sBtn.text = "Modify"
            }


        })

        //delete button(dBtn)
        dBtn.setOnClickListener(View.OnClickListener {
//            s_name = nameText.text.toString()
//            s_desc = descText.text.toString()
            db.deleteData(s_name!!, s_desc!!)
//딜리트
            activity?.supportFragmentManager?.beginTransaction()?.replace(R.id.home_fragment1, HomeFragment())?.commit()
        })

        //back button
        bBtn.setOnClickListener(View.OnClickListener {
            activity?.supportFragmentManager?.beginTransaction()?.replace(R.id.home_fragment1, HomeFragment())?.commit()
        })

        return view
    }
}