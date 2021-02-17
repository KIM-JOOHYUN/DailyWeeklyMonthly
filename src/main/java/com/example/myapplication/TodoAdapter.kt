package com.example.myapplication

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.CheckBox
import android.widget.ImageButton
import android.widget.TextView


class TodoAdapter(val context: Context, val TodoList: ArrayList<Todo>) : BaseAdapter() {
    var helper: SQLiteOpenHelper? = null

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view : View = LayoutInflater.from(context).inflate(R.layout.item,null)
        val s_Name = view.findViewById<TextView>(R.id.day_name)

        val todo = TodoList[position]
        s_Name.text = todo.s_name

        val s_Time = view.findViewById<TextView>(R.id.day_time)

        if(todo.s_start == null){
            s_Time.text = ""
        }
        else {
            s_Time.text = todo.s_start
        }

        var del: ImageButton=view.findViewById(R.id.img_button)
        val db = DataBaseHandler1(view.context)
        del.setOnClickListener() {
            db.deleteData1(todo.s_name.toString(), todo.s_start.toString())

            Log.d("test","name is ${todo.s_name}")
            TodoList.removeAt(position)
            notifyDataSetChanged()
        }

        var check_b: CheckBox=view.findViewById(R.id.check)
        check_b.setOnClickListener(){
            var newState: Boolean? =TodoList[position].checked
            TodoList[position].checked =newState
        }


        return view
    }
    override fun getItem(position:Int):Any{
        return TodoList[position]
    }
    override fun getItemId(position: Int): Long {
        return 0
    }
    override fun getCount(): Int {
        return TodoList.size
    }
    fun isChecked(position: Int): Boolean? {
        return TodoList[position].checked
    }



    // delete function
    fun delete(name: String) {
        var db = helper?.getWritableDatabase()
        // search specific data with name and delete data
        db?.delete(TABLE_NAME1, "$COL_NAME1=?", arrayOf(name))
        db?.close()

    }
    var db: SQLiteDatabase? = null
    fun dele(name: String){
        db?.execSQL("DELETE FROM $TABLE_NAME1 WHERE $COL_NAME1= '"+name+"';")
    }

}