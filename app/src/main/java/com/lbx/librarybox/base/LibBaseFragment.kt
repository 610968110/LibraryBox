package com.lbx.librarybox.base

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import lbx.xtoollib.XTools

/**
 * Copyright © 2013-2019 Worktile. All Rights Reserved.
 * Author: liboxin
 * Email: liboxin@worktile.com
 * Date: 2019/6/16
 * Time: 00:32
 * Desc:
 */
abstract class LibBaseFragment : Fragment() {

    private var mArguments: Bundle? = null
    private var mViewDataBinding: ViewDataBinding? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val layoutID = this.getLayoutID()
        val view = this.inflate(inflater, layoutID, container, savedInstanceState, false)
        this.getBaseArguments()
        this.getDataBinding(this.mViewDataBinding)
        return view

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        this.initView(view)
        this.initData()
        this.initListener()
    }

    open fun inflate(inflater: LayoutInflater,
                     layoutID: Int,
                     container: ViewGroup?,
                     savedInstanceState: Bundle?,
                     b: Boolean): View? {
        if (layoutID != -1 && layoutID != 0) {
            this.mViewDataBinding = DataBindingUtil.inflate(inflater, layoutID, container, b)
            return if (this.mViewDataBinding == null)
                inflater.inflate(layoutID, container, b)
            else
                this.mViewDataBinding!!.root
        }
        return null
    }

    fun getRootView(): View? {
        return this.view
    }

    abstract fun getLayoutID(): Int

    private fun getBaseArguments() {
        this.mArguments = this.arguments
        if (this.mArguments != null) {
            this.initArguments(this.mArguments)
        }

    }

    open fun initArguments(bundle: Bundle?) {}

    abstract fun getDataBinding(var1: ViewDataBinding?)

    abstract fun initView(var1: View?)

    abstract fun initData()

    open fun initListener() {}

    fun getArgument(): Bundle? {
        return this.mArguments
    }

    override fun onDestroyView() {
        XTools.UiUtil().removeAllMessage(this)
        super.onDestroyView()
    }
}