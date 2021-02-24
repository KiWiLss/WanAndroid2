package com.kiwilss.wanandroid

import android.content.Intent
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Route
import com.blankj.utilcode.util.LogUtils
import com.chad.library.adapter.base.BaseQuickAdapter
import com.kiwilss.wanandroid.base.BaseActivity
import com.kiwilss.wanandroid.config.ArouterPage
import com.kiwilss.wanandroid.databinding.ActivityMainBinding
import com.kiwilss.wanandroid.ktx.core.click
import com.kiwilss.wanandroid.ktx.core.saveToAlbum
import com.kiwilss.wanandroid.ktx.startActivity
import com.kiwilss.wanandroid.ktx.startActivityForResult
import com.kiwilss.wanandroid.ui.about.AboutActivity
import kotlinx.android.synthetic.main.activity_main.*
@Route(path = ArouterPage.MAIN)
class MainActivity : BaseActivity<ActivityMainBinding>() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
//
//
//    }

    override fun initData() {

    }

    override fun initEvent() {
    }

    override fun initInterface(savedInstanceState: Bundle?) {
        binding.btnHttp.click {
            startActivity(ArouterPage.TEST_HTTP)
//            AboutActivity.startActivity(this,"hello"){resultCode,data ->
//                LogUtils.e(resultCode)
//            }
//            startActivityForResult(ArouterPage.ABOUT,{resultCode: Int, data: Intent? ->
//                LogUtils.e(resultCode)
//            },ArouterPage.KEY to "key")
        }
    }
}


