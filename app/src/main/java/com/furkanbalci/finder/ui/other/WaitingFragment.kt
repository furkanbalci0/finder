package com.furkanbalci.finder.ui.other

import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.furkanbalci.finder.databinding.FragmentWaitingBinding

//Flexible wait screen.
class WaitingFragment(private var message: String, private val waitTime: Long = 5000, val runnable: Runnable) : Fragment() {

    private var _binding: FragmentWaitingBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentWaitingBinding.inflate(inflater, container, false)
        binding.waitMessage.text = message

        object : CountDownTimer(waitTime, 1000) {
            override fun onTick(millisUntilFinished: Long) = Unit
            override fun onFinish() = runnable.run()
        }.start()

        return binding.root
    }

    fun setMessage(message: String) {
        this.message = message
        binding.waitMessage.text = message
    }
}