package com.example.tasks.app.feature.home

import androidx.fragment.app.viewModels
import androidx.lifecycle.asLiveData
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tasks.BR
import com.example.tasks.app.base.BaseFragment
import com.example.tasks.app.domain.subscribers.Resource
import com.example.tasks.app.feature.home.adapter.HomeAdapter
import com.example.tasks.databinding.FragmentHomeBinding
import com.example.tasks.ext.activity.autoCleaned
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.scopes.FragmentScoped
import java.net.HttpURLConnection

@FragmentScoped
@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>() {

    @FragmentScoped
    override val bindingVariable: Int = BR.vmHome
    override val viewModel: HomeViewModel by viewModels()
    override val binding: FragmentHomeBinding by autoCleaned {(FragmentHomeBinding.inflate(layoutInflater))}
    //
    private val homeAdapter: HomeAdapter by autoCleaned { HomeAdapter() }

    companion object {
        fun newInstance() = HomeFragment()
    }

    override fun setupAdapter() {
        super.setupAdapter()
        val linearLayout = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
        binding.rcvHome.layoutManager = linearLayout
        binding.rcvHome.itemAnimator = DefaultItemAnimator()
        binding.rcvHome.isNestedScrollingEnabled = false
        binding.rcvHome.setHasFixedSize(true)
        binding.rcvHome.adapter = homeAdapter
    }

    override fun setupObserver() {
        super.setupObserver()
        viewModel.getSourceLiveData.asLiveData().observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Loading -> { showLoading() }
                is Resource.Success -> {
                    hideLoading()
                    it.data?.let { res ->
                        homeAdapter.setData(res.sources)
                    }
                }
                is Resource.Failure -> {
                    hideLoading()
//                    showDialogNetworkErrorSheet(
//                        isCallback = false, isCancelable = true,
//                        fragmentManager = parentFragmentManager, resTargetCode = null,
//                        failure = it.failureData
//                    )
                } else -> {}
            }
        }
    }

    override fun initAPI() {
        super.initAPI()
        viewModel.getSourceList()
    }

}