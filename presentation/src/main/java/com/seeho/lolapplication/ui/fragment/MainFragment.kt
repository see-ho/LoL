package com.seeho.lolapplication.ui.fragment

import android.view.View
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.fragment.app.activityViewModels
import com.seeho.lolapplication.base.BaseFragment
import com.seeho.lolapplication.databinding.FragmentMainBinding
import androidx.fragment.app.viewModels
import androidx.lifecycle.asLiveData
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.seeho.lolapplication.ui.adapter.ChampionsAdapter
import com.seeho.lolapplication.uiState.ChampionsUiState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import com.seeho.lolapplication.R
import com.seeho.lolapplication.viewModel.MainViewModel

@AndroidEntryPoint
class MainFragment : BaseFragment<FragmentMainBinding>(
    FragmentMainBinding::inflate
) {
    private val viewModel: MainViewModel by activityViewModels()
    private lateinit var championsAdapter: ChampionsAdapter
    override fun initData() {
//        viewModel.getChampions()
    }

    override fun initUI() {
        binding.apply {
            //text.text = "메인 프레그먼트"
            //loge("dkdkdkdkdkdkkd${viewModel.uiState.value.champions}")
            championsAdapter = ChampionsAdapter{
                viewModel.setChampion(it)
                findNavController().navigate(R.id.action_mainFragment_to_championDetailFragment)
            }
            //championsAdapter.setData(viewModel.uiState.value.champions)
            rvChampions.adapter = championsAdapter
        }
    }

    override fun initListener() {
        
    }

    override fun initObserver() {
        lifecycleScope.launch {
            //ver 1
//            viewModel.errorFlow.collectLatest {
//                showShortToast(it.localizedMessage)
//            }
            viewModel.uiState.collectLatest {
                loge("앙농")
                //ver 1
                when (it) {
                    ChampionsUiState.Loading -> {
                        binding.loadingBar.visibility = View.VISIBLE
                    }

                    is ChampionsUiState.Champions -> {
                        loge("전달!")
                        binding.loadingBar.visibility = View.INVISIBLE
                        championsAdapter.setData(it.champions)
                    }
                }
                //ver 2
//            viewModel.uiState.collectLatest {
//                if(it.isLoading){
//                    binding.loadingBar.visibility = View.VISIBLE
//                }else if (it.error.isNotEmpty()) {
//                    binding.loadingBar.visibility = View.INVISIBLE
//                    showShortToast(it.error)
//                } else {
//                    binding.loadingBar.visibility = View.INVISIBLE
//                    championsAdapter.setData(it.champions)
//                }
//            }
            }
        }
        lifecycleScope.launch {
            viewModel.errorFlow.collectLatest {
                showLongToast(it.localizedMessage)
                binding.loadingBar.visibility = View.INVISIBLE
            }
        }


//        viewModel.liveData.observe(viewLifecycleOwner){
//            if(it.isLoading){
//                binding.loadingBar.visibility = View.VISIBLE
//            }else if (it.error.isNotEmpty()) {
//                binding.loadingBar.visibility = View.INVISIBLE
//                showShortToast(it.error)
//            } else {
//                binding.loadingBar.visibility = View.INVISIBLE
//                championsAdapter.setData(it.champions)
//            }
//        }
    }

}