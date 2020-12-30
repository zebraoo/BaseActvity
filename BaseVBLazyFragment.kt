package com.component.lib_base_ui.base

import android.os.Bundle
import android.view.View
import androidx.viewbinding.ViewBinding

abstract class BaseVBLazyFragment<V : ViewBinding> : BaseVBFragment<V>() {
    /**
     * Fragment 中的 View 是否创建完成
     */
    private var isViewCreated = false

    /**
     * Fragment 左右切换时，只在第一次显示时请求数据
     */
    private var isFirstLoad = true

    override fun initData() {
        isViewCreated = true
    }

    override fun onResume() {
        super.onResume()
        initLoad()
    }

    private fun initLoad() {
        if (isViewCreated && isFirstLoad) {
            loadData()
            isFirstLoad = false
        }
    }

    abstract fun loadData()
}