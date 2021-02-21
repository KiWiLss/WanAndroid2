package com.kiwilss.wanandroid

import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Route
import com.chad.library.adapter.base.BaseQuickAdapter
import com.kiwilss.wanandroid.ktx.core.saveToAlbum

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bp: Bitmap? = null
        bp?.saveToAlbum { path, uri ->


        }
    }
}


