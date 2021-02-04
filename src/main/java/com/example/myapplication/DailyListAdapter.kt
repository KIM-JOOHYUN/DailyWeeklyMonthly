package com.example.myapplication

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView

class DailyListAdapter(val context: Context, val dailyList:ArrayList<Daily>) : BaseAdapter() {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view : View = LayoutInflater.from(context).inflate(R.layout.daily_item,null)
        val dailyName = view.findViewById<TextView>(R.id.daily_name)
        val dailyTime = view.findViewById<TextView>(R.id.daily_time)

        val daily = dailyList[position]
        dailyName.text = daily.p_name
        //dailyTime.text = daily.p_time
        return view
    }
    override fun getItem(position:Int):Any{
        return dailyList[position]
    }
    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun getCount(): Int {
        return dailyList.size
    }

}