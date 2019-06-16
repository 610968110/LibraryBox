package com.lbx.librarybox.ui.fragment

import android.databinding.ViewDataBinding
import android.os.Bundle
import android.view.View
import com.lbx.librarybox.R
import com.lbx.librarybox.base.LibBaseFragment
import com.lbx.librarybox.ui.payaction.AliPay
import com.lbx.librarybox.ui.payaction.IPayType
import com.lbx.librarybox.ui.payaction.WeChatPay
import kotlinx.android.synthetic.main.fragment_select_pay.*

/**
 * Copyright © 2013-2019 Worktile. All Rights Reserved.
 * Author: liboxin
 * Email: liboxin@worktile.com
 * Date: 2019/6/16
 * Time: 00:32
 * Desc:
 */
class SelectPayFragment : LibBaseFragment() {

    companion object {
        @JvmStatic
        fun newInstance(): SelectPayFragment {
            val fragment = SelectPayFragment()
            val bundle = Bundle()
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun getLayoutID(): Int = R.layout.fragment_select_pay

    override fun getDataBinding(var1: ViewDataBinding?) {
    }

    override fun initView(var1: View?) {
    }

    override fun initData() {
    }

    override fun initListener() {
        super.initListener()
        btn_aliPay.setOnClickListener {
            selectPayListener?.payType(AliPay())
        }
        btn_wechatPay.setOnClickListener {
            selectPayListener?.payType(WeChatPay())
        }
    }

    var selectPayListener: OnSelectPayListener? = null

    interface OnSelectPayListener {
        fun payType(type: IPayType)
    }
}