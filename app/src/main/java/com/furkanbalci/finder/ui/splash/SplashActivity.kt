package com.furkanbalci.finder.ui.splash

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.WindowInsets
import android.view.WindowInsetsController
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.furkanbalci.finder.MainActivity
import com.furkanbalci.finder.R
import com.furkanbalci.finder.databinding.ActivitySplashBinding

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onResume() {
        super.onResume()
        //Hide status bar and navigation bar.
        window.insetsController?.let {
            //https://medium.com/swlh/modifying-system-ui-visibility-in-android-11-e66a4128898b
            it.systemBarsBehavior = WindowInsetsController.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
            it.hide(WindowInsets.Type.systemBars())
            WindowCompat.setDecorFitsSystemWindows(window, false)
        }

        binding.logo.visibility = android.view.View.INVISIBLE

        //Timer 3s

        val waitTime = 4500L //4500
        object : CountDownTimer(waitTime, 1000) {
            override fun onTick(millisUntilFinished: Long) {}

            override fun onFinish() {

                //Hide animation view
                binding.animationView.visibility = android.view.View.INVISIBLE
                binding.logo.visibility = android.view.View.VISIBLE

                object : CountDownTimer(2500L, 1000) {
                    override fun onTick(millisUntilFinished: Long) {}

                    override fun onFinish() {

                        //Open main activity
                        val intent = Intent(this@SplashActivity, MainActivity::class.java)
                        startActivity(intent)

                    }
                }.start()
            }
        }.start()
    }
}