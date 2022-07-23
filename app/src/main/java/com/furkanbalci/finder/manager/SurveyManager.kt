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
                    listOf(Option(1, "DKTT"), Option(2, "Pinhani"), Option(3, "Yüzyüzeyken Konuşuruz")),
                    Category.MUSIC
                )
            )
        }
    }


}