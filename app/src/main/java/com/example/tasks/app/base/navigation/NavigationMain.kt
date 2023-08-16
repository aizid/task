package com.example.tasks.app.base.navigation

import android.app.Activity
import androidx.fragment.app.FragmentManager
import com.example.tasks.R
import com.example.tasks.app.feature.activity.main.MainActivity
import com.example.tasks.app.feature.home.HomeFragment
import javax.inject.Inject

open class NavigationMain @Inject constructor(mainActivity: Activity) {

    private var containerId: Int = R.id.f_main_container
    private var fragmentManager: FragmentManager =
        (mainActivity as MainActivity).supportFragmentManager

    fun navigateHome() {
        val homeFragment = HomeFragment.newInstance()
        fragmentManager.beginTransaction()
            .replace(containerId, homeFragment)
            .addToBackStack(null)
            .commitAllowingStateLoss()
    }

}