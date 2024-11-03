package com.freelance.bprfront

import com.freelance.bprfront.model.WeekRoutine

class DataSource {

    companion object {
        fun createDataSet(): ArrayList<WeekRoutine> {
            val list = ArrayList<WeekRoutine>()
            list.add(WeekRoutine("PPL","Push pull legs routine"))
            list.add(WeekRoutine("Fullbody","Full body routine"))
            return list
        }

    }
}