package com.lbx.librarybox

import android.app.Application
import lbx.xtoollib.XTools
import lbx.xtoollib.phone.xLogUtil.LEVEL_NONE
import lbx.xtoollib.phone.xLogUtil.LEVEL_VERBOSE



/**
 * Copyright © 2013-2019 Worktile. All Rights Reserved.
 * Author: liboxin
 * Email: liboxin@worktile.com
 * Date: 2019/6/15
 * Time: 15:02
 * Desc:
 */
class App : Application() {

    override fun onCreate() {
        super.onCreate()
        val debug = XTools.ApkUtil().isApkInDebug(this)
        val xTools = XTools.Builder()
                .log(/*是否打印log*/true)
                .logTag(/*设置log的tag*/"xys")
                .logLevel(/*设置显示log的级别*/if (debug) LEVEL_VERBOSE else LEVEL_NONE)
                .errLogFilePath(/*设置crashLog的文件存储路径*/"LibraryBox")
                .errLogFileName(/*设置crashLog的文件存储名*/"crash")
                .errLogFile(
                        /*是否打印到文件*/true,
                        /*是否打印到log*/debug)
                .logPrintFile(
                        /*是否打印log到文件*/true,
                        /*打印log文件在sd卡下的路径*/"LibraryBox/log",
                        /*打印到file的log是否加密(des+base64对称加密), null为不加密*/ null)
                .build(this)
        //初始化
        xTools.init()
    }
}