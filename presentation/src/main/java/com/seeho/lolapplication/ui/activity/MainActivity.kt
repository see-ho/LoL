package com.seeho.lolapplication.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.seeho.lolapplication.base.BaseActivity
import com.seeho.lolapplication.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>(
    {ActivityMainBinding.inflate(it)}
) {


    override fun initData() {
    }

    override fun initUI() {
        loge("시작!")
    }

    override fun initListener() {
    }

    override fun initObserver() {
    }
}