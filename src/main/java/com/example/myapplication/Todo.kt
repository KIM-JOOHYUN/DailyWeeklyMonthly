package com.example.myapplication

import java.util.*

class Todo {
    val calendar: Calendar = Calendar.getInstance()
    var year = calendar.get(Calendar.YEAR)
    var mon = calendar.get(Calendar.MONTH)
    var day = calendar.get(Calendar.DAY_OF_MONTH)
    var hour = calendar.get(Calendar.HOUR_OF_DAY)
    var min = calendar.get(Calendar.MINUTE)
    var p_name = ""
    var p_desc = ""

    constructor(year: Int, mon: Int, day: Int, hour: Int, min: Int, p_name: String, p_desc: String){
        this.year = year
        this.mon = mon
        this.day = day
        this.hour = hour
        this.min = min
        this.p_name = p_name
        this.p_desc = p_desc
    }
    fun getName():String{
        return p_name
    }
    fun getDesc():String {
        return p_desc
    }
    fun setDate(year: Int, mon: Int, day: Int, hour: Int, min: Int){
        this.year = year
        this.mon = mon
        this.day = day
        this.hour = hour
        this.min = min
    }
    fun setDate(year: Int, mon: Int, day: Int){
        this.year = year
        this.mon = mon
        this.day = day
    }
}