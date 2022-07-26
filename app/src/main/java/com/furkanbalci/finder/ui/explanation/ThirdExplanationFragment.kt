package com.furkanbalci.finder.ui.explanation

import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.furkanbalci.finder.R
import com.furkanbalci.finder.databinding.FragmentExplanation3Binding
import com.furkanbalci.finder.manager.DataManager
import com.furkanbalci.finder.manager.SurveyManager
import com.furkanbalci.finder.model.Playbook
import com.furkanbalci.finder.model.Survey
import com.furkanbalci.finder.ui.other.WaitingFragment
import com.furkanbalci.finder.ui.question.QuestionFragment
import com.google.firebase.auth.FirebaseAuth

class ThirdExplanationFragment : Fragment() {

    private var _binding: FragmentExplanation3Binding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentExplanation3Binding.inflate(inflater, container, false)

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
        binding.button.alpha = 0.5f
        object : CountDownTimer(2000, 1000) {
            override fun onTick(millisUntilFinished: Long) {}

            override fun onFinish() {
                binding.button.isEnabled = true
                binding.button.alpha = 1f
            }
        }.start()
    }

    private fun listenButton() {
        binding.button.setOnClickListener {

            binding.animationView.playAnimation()
            binding.button.isEnabled = false
            binding.button.alpha = 0.2f

            object : CountDownTimer(2400, 1000) {
                override fun onTick(millisUntilFinished: Long) = Unit

                override fun onFinish() {
                    activity?.supportFragmentManager?.beginTransaction()?.replace(R.id.nav_host_fragment_activity_main,
                        WaitingFragment("Anket\noluşturuluyor...") {

                            //Create new playbook.
                            val playbook = Playbook(SurveyManager.giveRandomSurveys())

                            //Save playbook.
                            DataManager.currentPlaybook = playbook

                            activity?.supportFragmentManager?.beginTransaction()
                                ?.replace(R.id.nav_host_fragment_activity_main, QuestionFragment(playbook))?.commit()
                        })?.commit()
                }
            }.start()
        }
    }
}