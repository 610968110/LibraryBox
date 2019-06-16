package com.lbx.librarybox.base

import android.content.Intent
import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.os.Bundle
import android.support.annotation.CallSuper
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.lbx.librarybox.R
import com.lbx.librarybox.databinding.LayoutBaseBinding
import com.lbx.screenadapter.ScreenAdapt
import kotlinx.android.synthetic.main.layout_base.*
import lbx.xtoollib.XTools

/**
 * Copyright © 2013-2019 Worktile. All Rights Reserved.
 * Author: liboxin
 * Email: liboxin@worktile.com
 * Date: 2019/6/15
 * Time: 15:18
 * Desc:
 */
abstract class LibBaseActivity : AppCompatActivity(), View.OnClickListener {

    private var baseIntent: Intent? = null

    @CallSuper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ScreenAdapt.adaptScreen(this, 720F, true)
        this.beforeLayout()
        val baseBinding: LayoutBaseBinding = DataBindingUtil.setContentView(this, R.layout.layout_base)
        val layoutID = this.getLayoutID()
        var childBinding: ViewDataBinding? = null
        if (layoutID != -1 && layoutID != 0) {
            childBinding = DataBindingUtil.inflate(layoutInflater, layoutID, fl_content, true)
        } else {
            fl_content.addView(getLayout())
        }
        setTitle(getActivityTitle())
        setSubTitle(getSubTitle())
        XTools.ActivityUtil().addActivity(this)
        fl_bg.setBackgroundResource(bg())
        val view = this.findViewById<View>(android.R.id.content)
        this.getBaseIntent()
        getDataBinding(childBinding)
        this.initView(view)
        this.initData()
        this.initListener()
        if (!showBackBtn()) tv_back.visibility = View.GONE
    }

    open fun beforeLayout() {}

    abstract fun getLayoutID(): Int

    abstract fun getActivityTitle(): String?

    abstract fun getSubTitle(): String?

    abstract fun getDataBinding(binding: ViewDataBinding?)

    abstract fun initView(var1: View)

    abstract fun initData()

    open fun initListener() {}

    open fun onClick(view: View, id: Int) {}

    override fun onClick(v: View) {
        this.onClick(v, v.id)
    }

    open fun getLayout(): View {
        return View(this)
    }

    private fun getBaseIntent() {
        this.baseIntent = this.intent
        if (this.baseIntent != null) {
            this.initIntent(this.baseIntent)
        }
    }

    open fun initIntent(intent: Intent?) {}

    open fun bg() = R.drawable.normal_bg


    open fun showBackBtn() = true

    @CallSuper
    override fun onDestroy() {
        XTools.ActivityUtil().removeActivity(this)
        if (this.closeProgressDialogWithDestroy()) {
            XTools.UiUtil().closeProgressDialog()
        }
        super.onDestroy()
    }

    open fun closeProgressDialogWithDestroy(): Boolean {
        return true
    }

    fun setTitle(title: String?) {
        title?.run {
            tv_title.text = title
        }
    }

    fun setSubTitle(title: String?) {
        title?.run {
            tv_subTitle.text = title
        }
    }

}