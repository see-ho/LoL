package com.seeho.lolapplication.base

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

typealias Inflate<T> = (LayoutInflater, ViewGroup?, Boolean) -> T

abstract class BaseFragment<VB: ViewBinding>(
    private val inflate: Inflate<VB>,
) : Fragment() {

    val name = javaClass.simpleName

    private var _binding: VB? = null
    val binding get() = _binding!!

    override fun onAttach(context: Context) {
        Log.e(name, "fragment onAttach", )
        super.onAttach(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.e(name, "fragment onCreate: ", )
        super.onCreate(savedInstanceState)
        initData()
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.e(name, "fragment onCreateView: ", )
        _binding = inflate.invoke(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Log.e(name, "fragment onViewCreated: ", )
        initUI()
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onResume() {
        Log.e(name, "fragment onResume: ", )
        super.onResume()
        initListener()
        initObserver()
    }

    abstract fun initData()
    abstract fun initUI()
    abstract fun initListener()
    abstract fun initObserver()

    override fun onDestroyView() {
        Log.e(name, "fragment onDestroyView: ", )
        super.onDestroyView()
        _binding = null
    }

    override fun onDestroy() {
        Log.e(name, "fragment onDestroy: ", )
        super.onDestroy()
    }

    override fun onDetach() {
        Log.e(name, "fragment onDetach: ", )
        super.onDetach()
    }

    fun showShortToast(message: String?){
        context?.let {
            Toast.makeText(it, message ?: "", Toast.LENGTH_SHORT).show()
        }
    }

    fun showLongToast(message: String?){
        context?.let {
            Toast.makeText(it, message ?: "", Toast.LENGTH_LONG).show()
        }
    }

    fun loge(message: String) = Log.e(javaClass.simpleName,message)
}