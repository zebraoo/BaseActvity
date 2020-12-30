package com.component.lib_base_ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentManager
import androidx.viewbinding.ViewBinding
import com.component.lib_base_ui.base.helper.BindingReflex
import java.lang.reflect.ParameterizedType

abstract class BaseVBFragment< VB : ViewBinding> :BaseFragment() {

    var mFragmentManager: FragmentManager? = null

    protected val mBinding: VB by lazy(mode = LazyThreadSafetyMode.NONE) {
        BindingReflex.reflexViewBinding(javaClass, layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mFragmentManager = childFragmentManager
        return mBinding.root
    }
    abstract fun initData()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initData()
    }
}