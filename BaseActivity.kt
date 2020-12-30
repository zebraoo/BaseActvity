package com.component.lib_base_ui.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.component.lib_base_ui.R
import com.zackratos.ultimatebarx.library.UltimateBarX

open class BaseActivity:AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        UltimateBarX.with(this)
            .fitWindow(true)
            .colorRes(R.color.colorWhite)
            .light(true)
            .applyStatusBar()
    }
}