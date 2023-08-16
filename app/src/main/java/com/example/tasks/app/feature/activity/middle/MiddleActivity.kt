package com.example.tasks.app.feature.activity.middle

import android.app.Activity
import android.content.Intent
import androidx.activity.viewModels
import com.example.tasks.BR
import com.example.tasks.R
import com.example.tasks.app.base.BaseActivity
import com.example.tasks.app.base.navigation.NavigationMiddle
import com.example.tasks.databinding.ActivityMiddleBinding
import com.example.tasks.ext.constant.Args
import com.example.tasks.ext.constant.NavKeys
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MiddleActivity : BaseActivity<ActivityMiddleBinding, MiddleViewModel>() {

    override val binding: ActivityMiddleBinding by lazy { ActivityMiddleBinding.inflate(layoutInflater) }
    override val viewModel: MiddleViewModel by viewModels()
    override val bindingVariable: Int = BR.vmActMiddle

    @Inject
    lateinit var navigationMiddle: NavigationMiddle
    //
    private var paramStrOne: String? = null
    private var paramStrTwo: String? = null
    //
    private var paramIntOne: Int? = null
    private var paramIntTwo: Int? = null

    companion object {
        fun startIntentParamWithFinish(activity: Activity, strOne: String, strTwo: String) {
            val starter = Intent(activity, MiddleActivity::class.java)
                .putExtra(NavKeys.KEY_NAV_MIDDLE, NavKeys.KEY_MID_DETAIL)
                .putExtra(Args.EXTRA_PARAM_STR_ONE, strOne)
                .putExtra(Args.EXTRA_PARAM_STR_TWO, strTwo)
            activity.startActivity(starter)
            activity.finish()
        }
        fun startIntentWithFinish(activity: Activity, keyNavigate: String) {
            val starter = Intent(activity, MiddleActivity::class.java)
                .putExtra(NavKeys.KEY_NAV_MIDDLE, keyNavigate)
            activity.startActivity(starter)
            activity.finish()
        }
        fun startIntent(activity: Activity, keyNavigate: String) {
            val starter = Intent(activity, MiddleActivity::class.java)
                .putExtra(NavKeys.KEY_NAV_MIDDLE, keyNavigate)
            activity.startActivity(starter)
        }
        fun startIntentParam(activity: Activity, keyNav: String, paramOne: Any, paramTwo: Any) {
            val starter = Intent(activity, MiddleActivity::class.java)
                .putExtra(NavKeys.KEY_NAV_MIDDLE, keyNav)
            when(paramOne) {
                is String -> {starter.putExtra(Args.EXTRA_PARAM_STR_ONE, paramOne)}
                is Int -> {starter.putExtra(Args.EXTRA_PARAM_INT_ONE, paramOne)}
                else -> {}
            }
            when(paramTwo) {
                is String -> {starter.putExtra(Args.EXTRA_PARAM_STR_TWO, paramTwo)}
                is Int -> {starter.putExtra(Args.EXTRA_PARAM_INT_TWO, paramTwo)}
                else -> {}
            }
            activity.startActivity(starter)
        }
    }

    override fun setupArguments() {
        intent?.let {
            paramStrOne = intent.getStringExtra(Args.EXTRA_PARAM_STR_ONE)
            paramStrTwo = intent.getStringExtra(Args.EXTRA_PARAM_STR_TWO)
            paramIntOne = intent.getIntExtra(Args.EXTRA_PARAM_INT_ONE, 0)
            paramIntTwo = intent.getIntExtra(Args.EXTRA_PARAM_INT_TWO, 0)

            when(intent.getStringExtra(NavKeys.KEY_NAV_MIDDLE)) {
                NavKeys.KEY_MID_DETAIL -> navigationMiddle.navigateDetail()
            }
        }
    }

    override fun onInitViews() {
        setContentView(binding.root)

    }

    override fun onBackPressed() {
        val fragment = supportFragmentManager.findFragmentById(R.id.f_middle_container)
        if (fragment != null && (fragment !is OnBackPressedListener ||
                    !(fragment as OnBackPressedListener).onBackPressed())) {
            super.onBackPressed()
        }
    }

    interface OnBackPressedListener {
        fun onBackPressed(): Boolean
    }

}