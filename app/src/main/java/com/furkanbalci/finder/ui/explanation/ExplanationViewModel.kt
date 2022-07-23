package com.furkanbalci.finder.ui.explanation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ExplanationViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Hey"
    }
    val text: LiveData<String> = _text
}