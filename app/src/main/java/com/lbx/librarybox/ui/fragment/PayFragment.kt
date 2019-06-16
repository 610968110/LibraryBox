package com.lbx.librarybox.ui.fragment

import android.databinding.ObservableInt
import android.databinding.ViewDataBinding
import android.os.Bundle
import android.view.View
import com.lbx.librarybox.R
import com.lbx.librarybox.base.LibBaseFragment
import com.lbx.librarybox.databinding.FragmentPayBinding
import io.reactivex.Observable
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.fragment_pay.*
import java.util.concurrent.TimeUnit

/**
 * Copyright © 2013-2019 Worktile. All Rights Reserved.
 * Author: liboxin
 * Email: liboxin@worktile.com
 * Date: 2019/6/16
 * Time: 12:13
 * Desc:
 */
class PayFragment : LibBaseFragment() {

    companion object {
        @JvmStatic
        fun newInstance(img: Int): PayFragment {
            val fragment = PayFragment()
            val bundle = Bundle()
            bundle.putInt("img", img)
            fragment.arguments = bundle
            return fragment
        }

        private const val MAX_TIME = 60
    }

    private val time = ObservableInt(0)
    private var disposable: Disposable? = null
    private var binding: FragmentPayBinding? = null
    private var img: Int? = null
    override fun getLayoutID(): Int = R.layout.fragment_pay
    override fun getDataBinding(var1: ViewDataBinding?) {
        this.binding = var1 as FragmentPayBinding?
    }

    override fun initArguments(bundle: Bundle?) {
        super.initArguments(bundle)
        this.img = bundle?.getInt("img")
    }

    override fun initView(var1: View?) {
        img?.run {
            im_pay_type_icon.setImageResource(this)
        }
        binding?.time = time
    }

    override fun initData() {
        disposable = Observable.interval(0, 1, TimeUnit.SECONDS)
                .subscribe {
                    val sTime = MAX_TIME - it.toInt()
                    if (sTime == 0) {
                        timeOutListener?.timeOut()
                        return@subscribe
                    }
                    time.set(MAX_TIME - it.toInt())
                }
    }

    override fun onDestroy() {
        super.onDestroy()
        disposable?.dispose()
    }

    var timeOutListener: OnTimeOutListener? = null

    interface OnTimeOutListener {
        fun timeOut()
    }
}