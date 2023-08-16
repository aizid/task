package com.example.tasks.app.base.navigation

import android.app.Activity
import androidx.fragment.app.FragmentManager
import com.example.tasks.R
import com.example.tasks.app.feature.activity.middle.MiddleActivity
import javax.inject.Inject

open class NavigationMiddle @Inject constructor(middleActivity: Activity) {

    private var containerId: Int = R.id.f_middle_container
    private var fragmentManager: FragmentManager =
        (middleActivity as MiddleActivity).supportFragmentManager

    /** Detail */
    fun navigateDetail() {
//        val detailFragment = DetailFragment.newInstance()
//        fragmentManager.beginTransaction()
//            .replace(containerId, detailFragment)
//            .addToBackStack(null)
//            .commitAllowingStateLoss()
    }

}