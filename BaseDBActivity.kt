package com.component.lib_base_ui.base


import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.widget.LinearLayout
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

import com.component.lib_base_ui.R

abstract class BaseDBActivity<VDB : ViewDataBinding> : BaseActivity() {
    lateinit  var binding: VDB;
    open var isContainToolBar = true
    open val isCenter = false
    lateinit var mToolBarTitleLabel:TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
        initViewDataBinding()
        initData(savedInstanceState)
    }

    open fun initView() {}//设置toolbar
    abstract fun initData(savedInstanceState: Bundle?)
    /**
     * 初始化根布局
     *
     * @return 布局layout的id
     */
    abstract fun initContentView(): Int

    private fun initViewDataBinding() {
        if (isContainToolBar) {
            val root =
                LayoutInflater.from(this).inflate(R.layout.layout_content, null) as LinearLayout
            setSupportActionBar(root.findViewById(R.id.toolbar_base))
            supportActionBar?.title = ""
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            supportActionBar?.setHomeAsUpIndicator(R.drawable.abc_ic_ab_back_material)
            mToolBarTitleLabel = root.findViewById<TextView>(R.id.mToolBarTitleLabel);
            binding= DataBindingUtil.inflate(
                LayoutInflater.from(this),
                initContentView(),
                null,
                false
            )
            root.addView((binding as ViewDataBinding).root)
            setContentView(root)

        } else {
            binding = DataBindingUtil.setContentView(this, initContentView())
        }

    }

    protected open fun setToolbarTitle(title: String?) {
        if (mToolBarTitleLabel != null && isCenter) {
            mToolBarTitleLabel.text = title
        } else {
            supportActionBar?.title = title
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            android.R.id.home -> finish()
        }
        return super.onOptionsItemSelected(item)
    }

}