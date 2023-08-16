package com.example.tasks.app.base.dialog.withvm

import android.graphics.Color
import android.os.Bundle
import android.text.Spanned
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.DialogFragment
import com.google.android.material.snackbar.Snackbar
import com.example.tasks.R
import com.example.tasks.app.base.viewmodel.BaseViewModel

abstract class BaseDialogFullFragment<VB : ViewDataBinding, VM : BaseViewModel> :
    DialogFragment() {

    abstract val viewModel: VM
    abstract val binding: VB
    abstract val bindingVariable: Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(false)
    }

    override fun getTheme(): Int {
        return R.style.FullScreenDialogStyle
    }

    override fun onStart() {
        super.onStart()
        viewModel.showToast.observe(
            this) { Toast.makeText(activity, it, Toast.LENGTH_LONG).show() }
        viewModel.showSnack.observe(
            this) { showSnackBar(it.message, it.spanned, it.color) }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    private fun performDataBinding(){
        binding.setVariable(bindingVariable, viewModel)
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


    private fun showSnackBar(message: String?, spanned: Spanned?, color: String?) {
        var snackbar = Snackbar.make(requireContext(), this.requireView(), getString(R.string.default_error_message), Snackbar.LENGTH_SHORT)
        message?.let { snackbar = Snackbar.make(requireContext(), this.requireView(), it, Snackbar.LENGTH_SHORT) }
        spanned?.let { snackbar = Snackbar.make(requireContext(), this.requireView(), it, Snackbar.LENGTH_SHORT) }
        color?.let { snackbar.view.setBackgroundColor(Color.parseColor(it)) }
        snackbar.show()
    }

}