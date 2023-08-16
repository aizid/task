package com.example.tasks.app.feature.activity.splash

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.viewModels
import com.example.tasks.BR
import com.example.tasks.R
import com.example.tasks.app.base.BaseActivity
import com.example.tasks.app.feature.activity.main.MainActivity
import com.example.tasks.databinding.ActivitySplashBinding
import com.example.tasks.ext.prefs.SharedPreferenceManager
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashActivity : BaseActivity<ActivitySplashBinding, SplashViewModel>() {

    override val binding: ActivitySplashBinding by lazy { ActivitySplashBinding.inflate(layoutInflater) }
    override val viewModel: SplashViewModel by viewModels()
    override val bindingVariable: Int = BR.vmActSplash
    //
    lateinit var prefManager: SharedPreferenceManager

    companion object {
        fun startIntent(activity: Activity) {
            val starter = Intent(activity, SplashActivity::class.java)
            activity.startActivity(starter)
            activity.finish()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.FullScreenTheme);
        super.onCreate(savedInstanceState)
    }

    override fun onInitViews() {
        setContentView(binding.root)
        prefManager = SharedPreferenceManager(applicationContext)

        if (isIntentFromFcm()) {
            processFcmIntent()
        } else {
            Handler(Looper.getMainLooper()).postDelayed({
                MainActivity.startIntent(this)
            }, 2000)
        }
    }

    private fun isIntentFromFcm(): Boolean {
        return intent.hasExtra("title") &&
                intent.hasExtra("body") &&
                intent.hasExtra("channel") &&
                intent.hasExtra("type")
    }

    private fun processFcmIntent() {

    }

}