package com.kiwilss.wanandroid.ui.about

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.blankj.utilcode.util.LogUtils
import com.dylanc.loadinghelper.ViewType
import com.jeremyliao.liveeventbus.LiveEventBus
import com.kiwilss.wanandroid.base.BaseActivity
import com.kiwilss.wanandroid.config.ArouterPage
import com.kiwilss.wanandroid.databinding.ActivityAboutBinding
import com.kiwilss.wanandroid.help.ActivityHelperK
import com.kiwilss.wanandroid.ktx.RouterKtx
import com.kiwilss.wanandroid.ktx.RouterKtx.startActivity
import com.kiwilss.wanandroid.ktx.core.click
import com.kiwilss.wanandroid.ktx.core.postDelay
import com.kiwilss.wanandroid.statuslayout.TitleAdapter

/**
 *@FileName: AboutActivity
 *@author : Lss kiwilss
 * @e-mail : kiwilss@163.com
 * @time   : 2021/2/22
 * @desc   : {DESCRIPTION}
 */
@Route(path = ArouterPage.ABOUT)
class AboutActivity : BaseActivity<ActivityAboutBinding>(){

    @Autowired(name = ArouterPage.KEY)
    @JvmField
    var key: String? = null

    companion object{
        //跳转相关
        fun startActivity(activity: FragmentActivity,key: String,callback: (Int,Intent?) -> Unit){
            ActivityHelperK.init(activity)
                .startActivityForResult(ArouterPage.ABOUT,callback,ArouterPage.KEY to key)
        }

        fun startActivity2(){
            RouterKtx.startActivity(ArouterPage.ABOUT)
        }
    }



    override fun initData() {
        LiveEventBus.get(ArouterPage.KEY, String::class.java)
            .observe(this){
                LogUtils.e(it)
            }

//        mLoadingHelper.getAdapter<TitleAdapter>(ViewType.TITLE).onBackClick{
//
//        }


    }

    override fun initEvent() {
        binding.tvAboutShow.click {
            LiveEventBus.get(ArouterPage.KEY).post("hello")
//            setResult(RESULT_OK)
//            finish()
        }


    }

    override fun initInterface(savedInstanceState: Bundle?) {
        showLoadingDiloag()
        LogUtils.e(key)
        binding.tvAboutShow.text = key

        postDelay (2000){
            dismissLoadingDiloag()
        }
    }
}