package com.component.lib_base_ui.base


import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.widget.LinearLayout
import android.widget.TextView
import androidx.viewbinding.ViewBinding
import com.component.lib_base_ui.R
import com.component.lib_base_ui.base.helper.BindingReflex

abstract class BaseVBActivity<VB : ViewBinding> : BaseActivity() {
    open var isContainToolBar = true
    open val isCenter = false
    lateinit var mToolBarTitleLabel:TextView

    protected val mBinding: VB by lazy(mode = LazyThreadSafetyMode.NONE) {
        BindingReflex.reflexViewBinding(javaClass, layoutInflater)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
        initViewDataBinding()
        initData(savedInstanceState)
    }

    open fun initView() {}//设置toolbar
    abstract fun initData(savedInstanceState: Bundle?)

    private fun initViewDataBinding() {

        if (isContainToolBar) {
            val root =
                LayoutInflater.from(this).inflate(R.layout.layout_content, null) as LinearLayout
            setSupportActionBar(root.findViewById(R.id.toolbar_base))
            supportActionBar?.title = ""
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            supportActionBar?.setHomeAsUpIndicator(R.drawable.abc_ic_ab_back_material)
            mToolBarTitleLabel = root.findViewById<TextView>(R.id.mToolBarTitleLabel);
            root.addView(mBinding.root,-1,-1)
            setContentView(root)
        } else {
            setContentView(mBinding.root)
        }

    }

    protected open fun setToolbarTitle(title: String?) {
        if (isCenter) {
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