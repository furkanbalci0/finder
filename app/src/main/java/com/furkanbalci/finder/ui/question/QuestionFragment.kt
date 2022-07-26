package com.furkanbalci.finder.ui.question

import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.furkanbalci.finder.R
import com.furkanbalci.finder.databinding.FragmentQuestionBinding
import com.furkanbalci.finder.model.Playbook
import com.furkanbalci.finder.ui.other.WaitingFragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.gson.Gson
import org.json.JSONObject
import java.util.*


class QuestionFragment(private val playbook: Playbook) : Fragment() {

    private var _binding: FragmentQuestionBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentQuestionBinding.inflate(inflater, container, false)


        if (playbook.surveys.stream().filter { it.completed == true }.count() == playbook.surveys.size.toLong()) {
            this.completePlaybook()
            return binding.root
        }

        //Select survey.
        val survey = playbook.surveys.stream().filter { it.completed == false }.findFirst().get()
        binding.mfProgressBar.progress = 10 * (playbook.surveys.indexOf(survey) + 1) - 10
        binding.queue.text = "${binding.mfProgressBar.progress / 10} / ${playbook.surveys.size}"

        //Display false animation.
        binding.animationView.visibility = View.INVISIBLE

        val buttons = listOf(binding.button1, binding.button2, binding.button3)
        for (button in buttons) {

            val queue = buttons.indexOf(button)
            button.text = survey.options[queue]
            button.setOnClickListener {
                survey.selectedOption = survey.options[queue]
                binding.animationView.visibility = View.VISIBLE
                binding.animationView.playAnimation()
                object : CountDownTimer(1500L, 1000) {
                    override fun onTick(millisUntilFinished: Long) = Unit

                    override fun onFinish() {

                        survey.completed = true
                        activity?.supportFragmentManager?.beginTransaction()?.replace(
                            R.id.nav_host_fragment_activity_main,
                            QuestionFragment(playbook)
                        )?.addToBackStack(null)?.commit()
                    }
                }.start()
            }
        }
        return binding.root;
    }

    private fun completePlaybook() {
        val fragment = WaitingFragment("Sonuçlar\ntoplanıyor...", 2000) {}
        val map = HashMap<String, Any>()
        map["completed"] = true
        map["completedAt"] = Date()

        val miniMap = HashMap<String, Any>()
        for (survey in playbook.surveys) {
            miniMap[survey.id] = mapOf("category" to survey.category.name, "selected" to survey.selectedOption.toString())
        }
        map["surveys"] = miniMap
        map["user"] = FirebaseAuth.getInstance().currentUser?.uid.toString()

        FirebaseFirestore.getInstance()
            .collection("playbooks")
            .add(map).addOnSuccessListener {
                fragment.setMessage("Kişiler\neşleştiriliyor...")
            }

        playbook.completed = true
        activity?.supportFragmentManager?.beginTransaction()?.replace(
            R.id.nav_host_fragment_activity_main, fragment
        )?.addToBackStack(null)?.commit()
    }
}