package com.example.myapplication


import android.app.DatePickerDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import com.example.myapplication.ui.dashboard.DashboardFragment
import java.text.SimpleDateFormat
import java.util.*


class todoList() : Fragment() {

    var myCalendar = Calendar.getInstance()
    var listView: ListView? = null


    var myDatePicker = DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
        myCalendar[Calendar.YEAR] = year
        myCalendar[Calendar.MONTH] = month
        myCalendar[Calendar.DAY_OF_MONTH] = dayOfMonth
        updateLabel()
    }
    private fun updateLabel() {
        val myFormat = "yyyy/MM/dd" // 출력형
        val sdf = SimpleDateFormat(myFormat, Locale.KOREA)
        val et_date = view?.findViewById(R.id.date_info1) as TextView
        et_date.setText(sdf.format(myCalendar.time))
    }

    var date: TextView?=null
    var s_name: String ?= null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

        override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                                  savedInstanceState: Bundle?): View? {

            val view = inflater.inflate(R.layout.todo, null)

            val db1 = DataBaseHandler1(view.context)

            var mBtn: Button =view.findViewById(R.id.date1)
            var sBtn : Button =view.findViewById(R.id.saveBtn1)
            var nameText : EditText =view.findViewById(R.id.name1)


            //date
            mBtn.setOnClickListener(View.OnClickListener {
                   DatePickerDialog(view.context, myDatePicker,  myCalendar.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH), myCalendar.get(Calendar.DAY_OF_MONTH)).show();
                })


            //save button
            sBtn.setOnClickListener(View.OnClickListener {
                s_name = nameText.text.toString()
                Log.d("test", "s_name: $s_name")
                var db1 = DataBaseHandler1(view.context)
                val et_date = view?.findViewById(R.id.date_info1) as TextView
                var date=et_date.text
                var todo = Todo(s_name!!, date as String?)
                db1.insertData(todo)


                var a = db1.readData()
                for(i in a){
                    Log.d("test12",i.s_name.toString())
                    Log.d("test12",date.toString())
                }

                activity?.supportFragmentManager?.beginTransaction()?.replace(R.id.home_fragment1, DashboardFragment())?.commit()

            })

            return view
        }


}


