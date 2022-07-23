package com.furkanbalci.finder.model

//Progress scenario for 10 questions
class Playbook {

    companion object {
        val surveys: Set<Survey> = HashSet()
        var currentSurvey: Survey? = null
    }

}