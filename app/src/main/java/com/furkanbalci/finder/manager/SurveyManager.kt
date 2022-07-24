package com.furkanbalci.finder.manager

import com.furkanbalci.finder.model.Category
import com.furkanbalci.finder.model.Option
import com.furkanbalci.finder.model.Survey

class SurveyManager {

    companion object {
        val surveyList = ArrayList<Survey>()

        init {
            surveyList.add(
                Survey(
                    1,
                    listOf(Option(1, "DKTT"), Option(2, "Evdeki Saat"), Option(3, "Son Feci Bisiklet")),
                    Category.MUSIC
                )
            )
        }
    }

    fun giveRandomSurveys(): List<Survey> {
        val randomList = ArrayList<Survey>()
        for (i in 0..9) {
            val randomIndex = (0 until surveyList.size).random()
            surveyList[randomIndex].let {
                randomList.add(it)
            }
        }
        return randomList
    }


}