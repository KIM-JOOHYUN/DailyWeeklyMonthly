package com.example.myapplication

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast

val DATABASE_NAME = "MyDB"
val TABLE_NAME = "Schedule"
val COL_NAME = "schedule_name"
val COL_DESC = "description"
val COL_START = "start_date"
val COL_END = "end_date"
val COL_TIME = "time"
val COL_ITER = "iter"

class DataBaseHandler(var context: Context) :SQLiteOpenHelper(context, DATABASE_NAME,null,1){

    override fun onCreate(db: SQLiteDatabase?) {

        val createTable = "CREATE TABLE if not exists $TABLE_NAME ($COL_NAME VARCHAR(50) , $COL_DESC VARCHAR(100),$COL_START VARCHAR(20),$COL_END VARCHAR(20),$COL_TIME VARCHAR(20),$COL_ITER VARCHAR(10))"
        db?.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
    }

    fun insertData(schedule:Schedule){
        val db = this.writableDatabase
        val cv = ContentValues()
        //schedule 속성 넣기
        cv.put(COL_NAME,schedule.s_name)
        cv.put(COL_DESC,schedule.s_desc)
        cv.put(COL_START,schedule.s_start)
        cv.put(COL_END,schedule.s_end)
        cv.put(COL_TIME,schedule.s_time)
        cv.put(COL_ITER,schedule.s_iter)

        val result = db.insert(TABLE_NAME,null,cv)

        if(result == (-1).toLong())
            Toast.makeText(context,"Failed",Toast.LENGTH_LONG).show()
        else
            Toast.makeText(context,"Success",Toast.LENGTH_LONG).show()
    }

    fun readData():MutableList<Schedule>{
        val list :MutableList<Schedule> = ArrayList()
        val db = this.readableDatabase
        val query = "Select * from $TABLE_NAME"
        val result: Cursor = db.rawQuery(query,null)

        if(result.moveToFirst()){
            do {
                val schedule = Schedule()
                schedule.s_name = result.getString(result.getColumnIndex(COL_NAME))
                schedule.s_desc = result.getString(result.getColumnIndex(COL_DESC))
                schedule.s_start = result.getString(result.getColumnIndex(COL_START))
                schedule.s_end = result.getString(result.getColumnIndex(COL_END))
                schedule.s_time = result.getString(result.getColumnIndex(COL_TIME))
                schedule.s_iter = result.getString(result.getColumnIndex(COL_ITER))

                list.add(schedule)
            }while (result.moveToNext())
        }else
            Toast.makeText(context,"There is no data.",Toast.LENGTH_LONG).show()

        result.close()
        db.close()
        return list
    }

    fun deleteData(){
        val db = this.writableDatabase
        //db.delete(TABLE_NAME, "$COL_ID=?", arrayOf("1"))
        db.delete(TABLE_NAME, null,null)
        db.close()
    }
}