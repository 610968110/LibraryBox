package com.lbx.librarybox.ui.activity

import android.databinding.ViewDataBinding
import android.view.View
import com.lbx.librarybox.R
import com.lbx.librarybox.base.LibBaseActivity

class MainActivity : LibBaseActivity() {

    override fun getActivityTitle(): String? = null

    override fun getSubTitle(): String? = null

    override fun getLayoutID(): Int = R.layout.activity_main

    override fun bg(): Int = R.drawable.home_bg

    override fun showBackBtn(): Boolean = false

    override fun getDataBinding(binding: ViewDataBinding?) {
    }


    override fun initView(view: View) {
    }

    override fun initData() {
    }

    fun borrow(v: View) {
        BorrowActivity.getIntent(this).start()
    }

    fun returns(v: View) {
        ReturnsActivity.getIntent(this).start()
    }

}
