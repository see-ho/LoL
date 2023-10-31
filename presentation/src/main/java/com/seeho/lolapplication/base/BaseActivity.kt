package com.seeho.lolapplication.base

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding

abstract class BaseActivity<VB: ViewBinding>(
    private val inflate: (LayoutInflater) -> VB,
) : AppCompatActivity(){

    private var _binding: VB? = null
    val binding get() = _binding!!

    val name = javaClass.simpleName
    override fun onCreate(savedInstanceState: Bundle?) {
        Log.e(name, "onCreate", )
        super.onCreate(savedInstanceState)
        _binding = inflate(layoutInflater)
        setContentView(binding.root)
        initData()
        initUI()
        initObserver()
        initListener()
    }

    override fun onStart() {
        Log.e(name, "onStart", )
        super.onStart()
    }

    override fun onResume() {
        Log.e(name, "onResume", )
        super.onResume()
    }

    override fun onPause() {
        Log.e(name, "onPause", )
        super.onPause()
    }

    override fun onRestart() {
        Log.e(name, "onRestart", )
        super.onRestart()
    }

    override fun onStop() {
        Log.e(name, "onStop", )
        super.onStop()
    }

    override fun onDestroy() {
        Log.e(name, "onDestroy", )
        super.onDestroy()
    }

    abstract fun initData()
    abstract fun initUI()
    abstract fun initListener()
    abstract fun initObserver()

    protected fun showShortToast(message: String?) =
        Toast.makeText(this, message ?: "", Toast.LENGTH_SHORT).show()

    protected fun showLongToast(message: String?) =
        Toast.makeText(this, message ?: "", Toast.LENGTH_LONG).show()

    protected fun loge(message: String) = Log.e(javaClass.simpleName,message)
}