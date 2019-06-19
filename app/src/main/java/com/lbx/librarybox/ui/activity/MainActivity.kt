package com.lbx.librarybox.ui.activity

import android.Manifest
import android.databinding.ViewDataBinding
import android.media.MediaPlayer
import android.view.View
import com.lbx.librarybox.R
import com.lbx.librarybox.base.LibBaseActivity
import kotlinx.android.synthetic.main.activity_main.*
import lbx.xtoollib.XTools

class MainActivity : LibBaseActivity(), MediaPlayer.OnPreparedListener {

    companion object {
        private const val PERMISSION_WRITE = Manifest.permission.WRITE_EXTERNAL_STORAGE
        private const val PERMISSION_REQUESR_CODE = 0X010
    }

    override fun getActivityTitle(): String? = null

    override fun getSubTitle(): String? = null

    override fun getLayoutID(): Int = R.layout.activity_main

    override fun bg(): Int = -1

    override fun showBackBtn(): Boolean = false

    override fun getDataBinding(binding: ViewDataBinding?) {
    }


    override fun initView(view: View) {
    }

    override fun initData() {
        if (XTools.PermissionUtil().checkPermission(this,
                        PERMISSION_REQUESR_CODE, PERMISSION_WRITE)) {
            copyWithPlay()
        }
    }

    private fun copyWithPlay() {
        val path = XTools.FileUtil().copyFileUtils
                .copyRawFile2FilesDir(this, R.raw.home_video, "home_video.mp4")
        vv_home.setOnPreparedListener(this)
        vv_home.setVideoPath(path)
    }

    override fun onPrepared(mp: MediaPlayer?) {
        mp?.start()
    }

    fun borrow(v: View) {
        BorrowActivity.getIntent(this).start()
    }

    fun returns(v: View) {
        ReturnsActivity.getIntent(this).start()
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            PERMISSION_REQUESR_CODE -> {
                if (grantResults.firstOrNull() == 0) {
                    copyWithPlay()
                }
            }
        }
    }

}
