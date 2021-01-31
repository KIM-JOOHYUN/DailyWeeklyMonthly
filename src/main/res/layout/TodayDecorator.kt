package com.example.myapplication


import com.example.myapplication.ui.home.HomeFragment
import com.prolificinteractive.materialcalendarview.CalendarDay
import com.prolificinteractive.materialcalendarview.DayViewDecorator
import com.prolificinteractive.materialcalendarview.DayViewFacade

class TodayDecorator(context: HomeFragment) : DayViewDecorator {
    private var date = CalendarDay.today()
    val drawable = context.resources.getDrawable(R.drawable.style_only_radius_10)

    override fun shouldDecorate(day: CalendarDay?): Boolean { //day가 today()와 같은지 확인 후 true라면 decorate()로 이동
        return day?.equals(date)!!
    }

    override fun decorate(view: DayViewFacade?) {
        view?.setBackgroundDrawable(drawable) //해당 view의 background 설정
    }
}

