package com.component.lib_base_ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.viewbinding.ViewBinding

abstract class BaseVBFragmentNoReflex<T : ViewBinding> :BaseFragment() {

    private lateinit var viewBinding: T

    var mFragmentManager: FragmentManager? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mFragmentManager = childFragmentManager
        viewBinding = getViewBindingInflater()
        return viewBinding.root
    }
    protected abstract fun getViewBindingInflater(): T
    abstract fun initData()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initData()
    }

    fun getViewBinding(): T {
        return viewBinding
    }
}