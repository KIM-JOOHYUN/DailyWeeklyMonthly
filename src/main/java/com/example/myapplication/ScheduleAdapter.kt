package com.example.myapplication

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView

class ScheduleAdapter(val context: Context, val scheduleList:ArrayList<Schedule>) : BaseAdapter() {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view : View = LayoutInflater.from(context).inflate(R.layout.daily_item,null)
        val s_Name = view.findViewById<TextView>(R.id.daily_name)
        val s_Time = view.findViewById<TextView>(R.id.daily_time)

        val schedule = scheduleList[position]
        s_Name.text = schedule.s_name
        if(schedule.s_start == null){
            s_Time.text = ""
        }
        else if(schedule.s_time ==null){
            s_Time.text = schedule.s_start+" ~ "+schedule.s_end
        }else{
            s_Time.text = schedule.s_start+" "+schedule.s_time+" ~ "+schedule.s_end
        }

        return view
    }
    override fun getItem(position:Int):Any{
        return scheduleList[position]
    }
    override fun getItemId(position: Int): Long {
        return 0
    }
    override fun getCount(): Int {
        return scheduleList.size
    }

}