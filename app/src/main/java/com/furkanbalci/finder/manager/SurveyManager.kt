package com.furkanbalci.finder.manager

import com.furkanbalci.finder.model.Category
import com.furkanbalci.finder.model.Survey
import com.google.firebase.firestore.FirebaseFirestore

class SurveyManager {

    companion object {
        private val surveyList = ArrayList<Survey>()

        fun loadAllSurveys() {
            val db = FirebaseFirestore.getInstance()
            db.collection("surveys").get().addOnSuccessListener { result ->
                for (document in result) {
                    val options = document.get("options") as ArrayList<String>
                    val survey = Survey(document.id, options, Category.valueOf(document.get("category").toString()))
                    surveyList.add(survey)
                }
            }
        }

        fun giveRandomSurveys(): Set<Survey> {
            val randomList = HashSet<Survey>()
            while (randomList.size < 10) {
                val randomIndex = (0 until surveyList.size).random()
                surveyList[randomIndex].let {
                    if (!randomList.contains(it)) randomList.add(it)
                }
            }
            return randomList
        }

    }


}