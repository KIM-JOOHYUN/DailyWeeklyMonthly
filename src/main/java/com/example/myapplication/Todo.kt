package com.example.myapplication

import java.util.*

class Todo {
    var checked: Boolean?=null
    var s_name: String? = null
    var s_start: String? = null

    constructor(s_name: String?, s_start: String?) {
        this.s_name = s_name
        this.s_start = s_start

    }

    constructor(s_name: String?, s_start: String?, checked: Boolean) {
        this.s_name = s_name
        this.s_start = s_start
        this.checked=checked

    }

    constructor() {
        this.s_name = null
        this.s_start = null

    }
}
