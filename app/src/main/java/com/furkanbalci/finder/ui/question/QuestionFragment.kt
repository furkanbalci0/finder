package com.furkanbalci.finder.ui.question

import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.furkanbalci.finder.R
import com.furkanbalci.finder.databinding.FragmentQuestionBinding
import com.furkanbalci.finder.manager.SurveyManager
import com.furkanbalci.finder.model.Category
import com.furkanbalci.finder.model.Option
import com.furkanbalci.finder.model.Survey


class QuestionFragment(survey: Survey? = null) : Fragment() {

    private var _binding: FragmentQuestionBinding? = null
    private lateinit var selectedSurvey: Survey
    private var _survey: Survey? = survey

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentQuestionBinding.inflate(inflater, container, false)

        //Display false animation.
        binding.animationView.visibility = View.INVISIBLE

        selectedSurvey = _survey ?: SurveyManager.surveyList[0]

        binding.button1.text = selectedSurvey.options[0].content
        binding.button1.setOnClickListener {
            selectedSurvey.selectedOption = selectedSurvey.options[0]
            binding.animationView.visibility = View.VISIBLE
            binding.animationView.playAnimation()
            object : CountDownTimer(1500L, 1000) {
                override fun onTick(millisUntilFinished: Long) {}

                override fun onFinish() {

                    activity?.supportFragmentManager?.beginTransaction()?.replace(
                        R.id.nav_host_fragment_activity_main,
                        QuestionFragment(
                            Survey(
                                2,
                                listOf(Option(1, "5 CM"), Option(2, "10 CM"), Option(3, "15 CM")),
                                Category.OTHER
                            )
                        )
                    )?.addToBackStack(null)?.commit()

                }
            }.start()
        }

        binding.button2.text = selectedSurvey.options[1].content
        binding.button2.setOnClickListener {
            selectedSurvey.selectedOption = selectedSurvey.options[1]

            binding.animationView.visibility = View.VISIBLE
            binding.animationView.playAnimation()
            object : CountDownTimer(1500L, 1000) {
                override fun onTick(millisUntilFinished: Long) {}

                override fun onFinish() {

                    activity?.supportFragmentManager?.beginTransaction()?.replace(
                        R.id.nav_host_fragment_activity_main,
                        QuestionFragment(
                            Survey(
                                2,
                                listOf(Option(1, "5 CM"), Option(2, "10 CM"), Option(3, "15 CM")),
                                Category.OTHER
                            )
                        )
                    )?.addToBackStack(null)?.commit()

                }
            }.start()
        }

        binding.button3.text = selectedSurvey.options[2].content
        binding.button3.setOnClickListener {
            selectedSurvey.selectedOption = selectedSurvey.options[2]

            binding.animationView.visibility = View.VISIBLE
            binding.animationView.playAnimation()
            object : CountDownTimer(1500L, 1000) {
                override fun onTick(millisUntilFinished: Long) {}

                override fun onFinish() {

                    activity?.supportFragmentManager?.beginTransaction()?.replace(
                        R.id.nav_host_fragment_activity_main,
                        QuestionFragment(
                            Survey(
                                2,
                                listOf(Option(1, "5 CM"), Option(2, "10 CM"), Option(3, "15 CM")),
                                Category.OTHER
                            )
                        )
                    )?.addToBackStack(null)?.commit()

                }
            }.start()
        }


        return binding.root;
    }
}