package com.component.lib_base_ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.FragmentManager

abstract class BaseDBFragment< VDB :ViewDataBinding> :BaseFragment() {
    lateinit var mViewDataBinding: VDB
    protected var mFragmentManager: FragmentManager? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mFragmentManager = childFragmentManager
        mViewDataBinding = DataBindingUtil.inflate(inflater, getLayoutResId(), container, false)
        return mViewDataBinding.root;
    }

    abstract fun getLayoutResId(): Int
    abstract fun initData()
    abstract fun initView()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initData()
        initView()
    }
}