package com.lbx.librarybox.ui.activity

import android.content.Context
import android.databinding.ViewDataBinding
import android.view.View
import com.lbx.librarybox.R
import com.lbx.librarybox.base.LibBaseActivity
import com.lbx.librarybox.ui.fragment.PayFragment
import com.lbx.librarybox.ui.fragment.SelectPayFragment
import com.lbx.librarybox.ui.payaction.IPayType
import lbx.xtoollib.XIntent

/**
 * Copyright © 2013-2019 Worktile. All Rights Reserved.
 * Author: liboxin
 * Email: liboxin@worktile.com
 * Date: 2019/6/15
 * Time: 16:16
 * Desc:
 */
class BorrowActivity : LibBaseActivity(), SelectPayFragment.OnSelectPayListener, PayFragment.OnTimeOutListener {

    companion object {
        @JvmStatic
        fun getIntent(context: Context): XIntent = XIntent(context, BorrowActivity::class.java)
    }

    private lateinit var payType: IPayType
    private val payTypeFragment by lazy {
        val fragment = SelectPayFragment.newInstance()
        fragment.selectPayListener = this
        fragment
    }
    private val payFragment by lazy {
        val fragment = PayFragment.newInstance(payType.getPayIcon())
        fragment.timeOutListener = this
        fragment
    }

    override fun getLayoutID() = R.layout.activity_borrow

    override fun getActivityTitle(): String? = "导览机租借"

    override fun getSubTitle(): String? = null


    override fun getDataBinding(binding: ViewDataBinding?) {
    }

    override fun initView(v: View) {
        supportFragmentManager.beginTransaction()
                .replace(R.id.fl_borrow, payTypeFragment)
                .commitAllowingStateLoss()
    }

    override fun initData() {
    }

    override fun payType(type: IPayType) {
        payType = type
        setSubTitle("")
        supportFragmentManager.beginTransaction()
                .replace(R.id.fl_borrow, payFragment)
                .commitAllowingStateLoss()
    }

    override fun timeOut() {
        finish()
    }
}