package com.example.myapplication

import java.util.*

class Todo{
    val calendar:Calendar = Calendar.getInstance()
    var year = calendar.get(Calendar.YEAR)
    var mon = calendar.get(Calendar.MONTH)
    var date = calendar.get(Calendar.DAY_OF_MONTH)
    var hour = calendar.get(Calendar.HOUR_OF_DAY)
    var min = calendar.get(Calendar.MINUTE)
    var p_name = ""
    var p_desc = ""

    var end_year = 0
    var end_month = 0
    var end_date = 0
    var end_hour = 0
    var end_min = 0
    constructor(){
        val calendar:Calendar = Calendar.getInstance()
        var year = calendar.get(Calendar.YEAR)
        var mon = calendar.get(Calendar.MONTH)
        var date = calendar.get(Calendar.DAY_OF_MONTH)
        var hour = calendar.get(Calendar.HOUR_OF_DAY)
        var min = calendar.get(Calendar.MINUTE)
        var p_name = ""
        var p_desc = ""

        var end_year = 0
        var end_month = 0
        var end_date = 0
        var end_hour = 0
        var end_min = 0
    }
    constructor(year: Int, mon: Int, date: Int, hour: Int, min: Int, p_name: String, p_desc: String,
                end_year: Int, end_month: Int, end_date: Int, end_hour: Int, end_min: Int){
        this.year = year
        this.mon = mon
        this.date = date
        this.hour = hour
        this.min = min
        this.p_name = p_name
        this.p_desc = p_desc
        this.end_date = end_date
        this.end_hour = end_hour
        this.end_min = end_min
        this.end_month = end_month
        this.end_year = end_year
    }
    constructor(year: Int, mon: Int, date: Int, hour: Int, min: Int, p_name: String, p_desc: String){
        this.year = year
        this.mon = mon
        this.date = date
        this.hour = hour
        this.min = min
        this.p_name = p_name
        this.p_desc = p_desc

        var p_name = ""
        var p_desc = ""

        var end_year = 0
        var end_month = 0
        var end_date = 0
        var end_hour = 0
        var end_min = 0
    }
    constructor(year: Int, mon: Int, date: Int, p_name: String, p_desc: String){
        this.year = year
        this.mon = mon
        this.date = date
        this.hour = 0
        this.min = 0
        this.p_name = p_name
        this.p_desc = p_desc

        var p_name = ""
        var p_desc = ""

        var end_year = 0
        var end_month = 0
        var end_date = 0
        var end_hour = 0
        var end_min = 0
    }

    fun getName():String{
        return p_name
    }
    fun getDesc():String {
        return p_desc
    }
    fun setDate(year: Int, mon: Int, date: Int, hour: Int, min: Int){
        this.year = year
        this.mon = mon
        this.date = date
        this.hour = hour
        this.min = min
    }
    fun setDate(year: Int, mon: Int, date: Int){
        this.year = year
        this.mon = mon
        this.date = date
    }
}
