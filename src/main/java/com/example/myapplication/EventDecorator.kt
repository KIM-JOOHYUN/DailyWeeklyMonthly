package com.example.myapplication

import android.graphics.Color
import com.prolificinteractive.materialcalendarview.CalendarDay
import com.prolificinteractive.materialcalendarview.DayViewDecorator
import com.prolificinteractive.materialcalendarview.DayViewFacade
import com.prolificinteractive.materialcalendarview.spans.DotSpan
import java.util.*


/*
class EventDecorator constructor(
    val color: Int,
    dates: Collection<CalendarDay>
) :
    DayViewDecorator {
    val dates: HashSet<CalendarDay>
    override fun shouldDecorate(day: CalendarDay): Boolean {
        return dates.contains(day)
    }

    override fun decorate(view: DayViewFacade) {
        view.addSpan(DotSpan(5F, color))
    }

    init {
        this.dates = HashSet(dates)
    }
}

class TodayDecorator(context: HomeFragment) : DayViewDecorator {
    var date = CalendarDay.from(Local))
    val drawable = context.resources.getDrawable(R.drawable.style_only_radius_10)

    override fun shouldDecorate(day: CalendarDay?): Boolean { //day가 today()와 같은지 확인 후 true라면 decorate()로 이동
        return day?.equals(date)!!
    }

    override fun decorate(view: DayViewFacade?) {
        view?.setBackgroundDrawable(drawable) //해당 view의 background 설정
    }
}


class EventDecorator(context: Context) :
    DayViewDecorator {
    private val context: Context
    override fun shouldDecorate(day: CalendarDay): Boolean {
        return true
    }

    override fun decorate(view: DayViewFacade) {
        val drawable =
            ContextCompat.getDrawable(context, R.drawable.spinner_background)!!
        view.setBackgroundDrawable(drawable)
    }

    init {
        this.context = context
    }
}

class EventDecorator(context: Context) : DayViewDecorator {
    private val drawable: Drawable?
    var currentDay = CalendarDay.today()
    override fun shouldDecorate(day: CalendarDay): Boolean {
        return day == currentDay
    }

    override fun decorate(view: DayViewFacade) {
        view.setSelectionDrawable(drawable!!)
    }

    init {
        drawable = ContextCompat.getDrawable(context,R.drawable.spinner_background)
    }
}

class EventDecorator: DayViewDecorator {
    private val calendar = Calendar.getInstance()

    override fun shouldDecorate(day: CalendarDay): Boolean {
        return true
    }
    override fun decorate(view: DayViewFacade?) {
        view?.addSpan(object: ForegroundColorSpan(Color.RED){})
    }
}
*/
class EventDecorator(dates: Collection<CalendarDay>) :
    DayViewDecorator {
    private val dates: HashSet<CalendarDay>
    override fun shouldDecorate(day: CalendarDay?): Boolean {
        return dates.contains(day)
    }

    override fun decorate(view: DayViewFacade) {
        view.addSpan(DotSpan(5F, Color.MAGENTA))
    }

    init {
        this.dates = HashSet(dates)
    }
}
/*


class EventDecorator(var date: Date):DayViewDecorator {

    private val calendar = Calendar.getInstance()

    override fun shouldDecorate(day: CalendarDay): Boolean {
        return day.equals(date)
    }
    override fun decorate(view: DayViewFacade?) {
        view?.addSpan(DotSpan(5F, Color.CYAN))
    }
}
*/