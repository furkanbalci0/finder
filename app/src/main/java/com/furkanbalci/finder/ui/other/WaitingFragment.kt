package com.furkanbalci.finder.ui.other

import android.os.Bundle
import android.os.CountDownTimer
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.furkanbalci.finder.R
import com.furkanbalci.finder.databinding.FragmentExplanation3Binding
import com.furkanbalci.finder.databinding.FragmentWaitingBinding
import com.furkanbalci.finder.model.Playbook
import com.furkanbalci.finder.model.Survey
import com.furkanbalci.finder.ui.question.QuestionFragment

class WaitingFragment(private var message: String) : Fragment() {

    private var _binding: FragmentWaitingBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentWaitingBinding.inflate(inflater, container, false)
        binding.waitMessage.text = message


        object : CountDownTimer(5000, 1000) {
            override fun onTick(millisUntilFinished: Long) {}

            override fun onFinish() {

                //Create new playbook.
                val playbook = Playbook(null)

                activity?.supportFragmentManager?.beginTransaction()
                    ?.replace(R.id.nav_host_fragment_activity_main, QuestionFragment())?.commit()
            }
        }.start()

        return binding.root
    }


}