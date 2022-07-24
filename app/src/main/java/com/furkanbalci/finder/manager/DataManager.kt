package com.furkanbalci.finder.manager

import com.furkanbalci.finder.model.Playbook

class DataManager {

    companion object {
        val instance = DataManager()
    }

    private var currentPlaybook : Playbook? = null

    fun currentPlaybook(): Playbook? {
        return currentPlaybook
    }

    fun setCurrentPlaybook(playbook: Playbook) {
        currentPlaybook = playbook
    }

}