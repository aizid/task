package com.example.tasks.app.base.dialog.withoutvm

import android.R
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.DialogFragment

abstract class BaseDialogFragment<VB : ViewDataBinding> :
    DialogFragment() {

    abstract val binding: VB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(false)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        dialog!!.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog!!.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        setStyle(STYLE_NO_FRAME, R.style.Theme)
        return binding.root
    }

    private fun performDataBinding(){
        binding.lifecycleOwner = viewLifecycleOwner
        binding.executePendingBindings()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        performDataBinding()
        setupArguments()
        setupComponent()
        setupAdapter()
        setupViewPager()

        setupListener()
        setupObserver()

        initAPI()
        initOnClick()
    }

    protected open fun setupComponent() {}
    protected open fun setupArguments(){}
    protected open fun setupAdapter(){}
    protected open fun setupViewPager(){}

    protected open fun setupListener(){}
    protected open fun setupObserver(){}

    protected open fun initAPI(){}
    protected open fun initOnClick(){}


}