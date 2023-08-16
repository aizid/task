package com.example.tasks.app.feature.activity.main

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import com.example.tasks.BR
import com.example.tasks.R
import com.example.tasks.app.base.BaseActivity
import com.example.tasks.app.base.navigation.NavigationMain
import com.example.tasks.databinding.ActivityMainBinding
import com.example.tasks.ext.constant.NavKeys
import com.example.tasks.ext.prefs.SharedPreferenceManager
import dagger.hilt.android.AndroidEntryPoint
import java.util.*
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>() {

    override val bindingVariable: Int = BR.vmActMain
    override val viewModel: MainViewModel by viewModels()
    override val binding: ActivityMainBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    //
    @Inject lateinit var navigationMain: NavigationMain
    //
    lateinit var prefManager: SharedPreferenceManager
    //
    private var activeNavigation: Int = 0

    companion object {
        fun startIntent(activity: Activity) {
            val starter = Intent(activity, MainActivity::class.java)
            activity.startActivity(starter)
            activity.finish()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        prefManager = SharedPreferenceManager(this)
    }

    override fun onInitViews() {
        setContentView(binding.root)
    }

    override fun setupComponent() {
        super.setupComponent()
        binding.bottomNavigation.itemIconTintList = null

        binding.bottomNavigation.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.navigation_home -> {
                    activeNavigation = 0
                    navigationMain.navigateHome()
                    invalidateOptionsMenu()
                }
            }
            true
        }
    }

    override fun setupArguments() {
        super.setupArguments()
        navigationMain.navigateHome()
    }

    override fun setupFMListener() {
        super.setupFMListener()
        supportFragmentManager.setFragmentResultListener(
            NavKeys.KEY_FM_MAIN_ACTIVITY, this
        ) { _, bundle ->
            val result = bundle.getString(NavKeys.KEY_FM_MAIN_ACTIVITY)
            val valueResultStr: String?
            val valueResultInt: Int
//            when (result) {
//                NavKeys.CONST_RES_NOT_PREMIUM -> {
//                    valueResultStr = bundle.getString(NavKeys.CONST_RES_NOT_PREMIUM)
//                    if (!valueResultStr.isNullOrEmpty()) { /* Go to Premium Package */ }
//                }
//            }
        }
    }

    override fun onBackPressed() {
        if (activeNavigation != 0) {
            binding.bottomNavigation.selectedItemId = R.id.navigation_home
        } else {
            finish()
        }
    }

}