package com.furkanbalci.finder.ui.explanation

import android.annotation.SuppressLint
import android.graphics.PorterDuff
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.furkanbalci.finder.R
import com.furkanbalci.finder.databinding.FragmentExplanation2Binding

class SecondExplanationFragment : Fragment() {

    private var _binding: FragmentExplanation2Binding? = null

    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentExplanation2Binding.inflate(inflater, container, false)

        //Disable button.
        this.disableButton()

        //Listen button.
        this.listenButton()

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun disableButton() {
        binding.button.isEnabled = false
        binding.button.alpha = 0.2f
        object : CountDownTimer(3000, 1000) {
            override fun onTick(millisUntilFinished: Long) {}

            override fun onFinish() {
                binding.button.isEnabled = true
                binding.button.alpha = 1f
            }
        }.start()
    }

    private fun listenButton() {
        binding.button.setOnClickListener {
            activity?.supportFragmentManager?.beginTransaction()?.replace(
                R.id.nav_host_fragment_activity_main,
                ThirdExplanationFragment()
            )?.addToBackStack(null)?.commit()
        }
    }
}