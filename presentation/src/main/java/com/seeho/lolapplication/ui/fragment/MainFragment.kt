package com.seeho.lolapplication.ui.fragment

import com.seeho.lolapplication.R

import androidx.navigation.fragment.findNavController
import com.seeho.lolapplication.base.BaseFragment
import com.seeho.lolapplication.databinding.FragmentMainBinding

class MainFragment : BaseFragment<FragmentMainBinding>(
    FragmentMainBinding::inflate
) {
    override fun initData() {}

    override fun initUI() {
        binding.apply {
            text.text = "메인 프레그먼트"
        }
    }

    override fun initListener() {
        binding.text.setOnClickListener{
            findNavController().navigate(R.id.action_mainFragment_to_championDetailFragment)
        }
    }

    override fun initObserver() {}

}