package com.component.lib_base_ui.base

import android.os.Bundle
import android.widget.TextView
import androidx.viewbinding.ViewBinding
import com.component.lib_base_ui.R
import com.component.lib_base_ui.databinding.LayoutContentBinding
import com.component.lib_base_ui.databinding.ToolbarBinding


abstract class BaseVBNoReflexActivity<T : ViewBinding> : BaseActivity() {
    open var isContainToolBar = true
    open var hasBackIcon = true
    open var isCenter = false;
    private lateinit var baseBinding: LayoutContentBinding
    private lateinit var viewBinding: T
    private lateinit var mToolBarTitleLabel: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initToolBar()
        viewBinding = getViewBindingInflater();
        if (isContainToolBar) {
            baseBinding = LayoutContentBinding.inflate(layoutInflater);
            setSupportActionBar(baseBinding.toolbar.toolbarBase)
            supportActionBar?.title = ""
            if(hasBackIcon){
                supportActionBar?.setDisplayHomeAsUpEnabled(true)
                supportActionBar?.setHomeAsUpIndicator(R.drawable.abc_ic_ab_back_material)
            }
            mToolBarTitleLabel = baseBinding.toolbar.mToolBarTitleLabel
            baseBinding.root.addView(viewBinding.root, -1, -1)
            setContentView(baseBinding.root);
        } else {
            setContentView(viewBinding.root);
        }

        initData(savedInstanceState)
    }

    open fun initToolBar() {}//设置toolbar
    abstract fun getViewBindingInflater(): T
    abstract fun initData(savedInstanceState: Bundle?)

    protected open fun setToolbarTitle(title: String?) {
        if (isCenter) {
            mToolBarTitleLabel.text = title
        } else {
            supportActionBar?.title = title
        }
    }

    fun getViewBinding(): T {
        return viewBinding
    }

}