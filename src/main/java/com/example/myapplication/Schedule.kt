package com.example.myapplication

class Schedule {
    var s_name:String ?= null
    var s_desc:String ?= null
    var s_start:String ?= null
    var s_end:String ?= null
    var s_time:String ?= null
    var s_iter:String ?= null

    constructor(s_name:String?, s_desc:String?, s_start: String?, s_end:String?, s_time:String?, s_iter:String?){
        this.s_name = s_name
        this.s_desc = s_desc
        this.s_start = s_start
        this.s_end = s_end
        this.s_time = s_time
        this.s_iter = s_iter
    }
    constructor(){
        this.s_name = null
        this.s_desc = null
        this.s_start = null
        this.s_end = null
        this.s_time = null
        this.s_iter = null
    }

}