package com.example.myapplication


import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import android.widget.Toast

var DATABASE_NAME1 = "MyDB1"
var TABLE_NAME1 = "Todo"
var COL_NAME1 = "schedule_name1"
var COL_START1 = "date1"
var COL_CHECK = "checked"


class DataBaseHandler1(var context: Context) :SQLiteOpenHelper(context, DATABASE_NAME1,null,1){

    override fun onConfigure(db: SQLiteDatabase) {
        super.onConfigure(db)
        db.disableWriteAheadLogging()
    }


    override fun onCreate(db: SQLiteDatabase?) {

        var createTable1 = "CREATE TABLE if not exists $TABLE_NAME1 ($COL_NAME1 VARCHAR(50),$COL_START1 VARCHAR(20),$COL_CHECK VARCHAR(20))"
        db?.execSQL(createTable1)
        Log.d("test1232131232", "줫대써")
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
    }

    fun insertData(Insertion:Todo){
        var db = this.writableDatabase
        var cv = ContentValues()
        cv.put(COL_NAME1,Insertion.s_name)
        cv.put(COL_START1,Insertion.s_start)


        var result = db.insert(TABLE_NAME1,null,cv)

        if(result == (-1).toLong())
            Toast.makeText(context,"Failed",Toast.LENGTH_LONG).show()
        else
            Toast.makeText(context,"Success",Toast.LENGTH_LONG).show()
    }

    fun readData():MutableList<Todo>{
        var list :MutableList<Todo> = ArrayList()
        var db = this.readableDatabase
        var query = "Select * from $TABLE_NAME1"
        var result: Cursor = db.rawQuery(query,null)

        if(result.moveToFirst()){
            do {
                val schedule = Todo()
                schedule.s_name = result.getString(result.getColumnIndex(COL_NAME1))
                schedule.s_start = result.getString(result.getColumnIndex(COL_START1))
                schedule.checked = result.getString(result.getColumnIndex(COL_CHECK))

                list.add(schedule)
            }while (result.moveToNext())
        }else
            Toast.makeText(context,"There is no data.",Toast.LENGTH_LONG).show()

        result.close()
        db.close()
        return list
    }

    fun deleteData1(name1:String, date1:String){
        val db = this.writableDatabase
        //db.delete(TABLE_NAME, "$COL_ID=?", arrayOf("1"))
        db.execSQL("DELETE FROM $TABLE_NAME1 WHERE schedule_name1 = '$name1' AND date1 = '$date1'")
        db.close()
    }


}