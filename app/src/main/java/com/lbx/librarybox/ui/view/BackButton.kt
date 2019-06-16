package com.lbx.librarybox.ui.view

import android.app.Activity
import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.util.TypedValue
import android.view.Gravity
import android.widget.TextView
import com.lbx.librarybox.R

/**
 * Copyright © 2013-2019 Worktile. All Rights Reserved.
 * Author: liboxin
 * Email: liboxin@worktile.com
 * Date: 2019/6/15
 * Time: 22:58
 * Desc:
 */
class BackButton : TextView {

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) :
            super(context, attrs, defStyleAttr) {
        text = "返回"
        setTextSize(TypedValue.COMPLEX_UNIT_SP, 28F)
        gravity = Gravity.CENTER
        setTextColor(Color.parseColor("#9C6C3E"))
        setBackgroundResource(R.drawable.tv_finish_bg)
        setOnClickListener {
            if (context is Activity) context.finish()
        }
    }
}
