package com.seeho.lolapplication.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.seeho.lolapplication.base.BaseFragment
import com.seeho.lolapplication.databinding.FragmentChampionDetailBinding

class ChampionDetailFragment : BaseFragment<FragmentChampionDetailBinding>(
    FragmentChampionDetailBinding::inflate
) {
    override fun initData() {}

    override fun initUI() {
        binding.detailtext.text="디테일 프레그먼트"
    }

    override fun initListener() {
    }

    override fun initObserver() {
    }

}
