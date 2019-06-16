package com.lbx.librarybox.ui.activity

import android.content.Context
import android.databinding.ViewDataBinding
import android.view.View
import com.lbx.librarybox.R
import com.lbx.librarybox.base.LibBaseActivity
import lbx.xtoollib.XIntent

/**
 * Copyright © 2013-2019 Worktile. All Rights Reserved.
 * Author: liboxin
 * Email: liboxin@worktile.com
 * Date: 2019/6/15
 * Time: 16:16
 * Desc:
 */
class ReturnsActivity : LibBaseActivity() {

    override fun getActivityTitle(): String? = "还导览机"

    override fun getSubTitle(): String? = "请将导览机放入存储仓，并关闭仓门"

    companion object {
        @JvmStatic
        fun getIntent(context: Context): XIntent = XIntent(context, ReturnsActivity::class.java)
    }

    override fun getLayoutID() = R.layout.activity_returns

    override fun getDataBinding(binding: ViewDataBinding?) {
    }

    override fun initView(v: View) {
    }

    override fun initData() {
    }
}