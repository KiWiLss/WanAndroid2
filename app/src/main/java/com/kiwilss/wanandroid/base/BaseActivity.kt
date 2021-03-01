package com.kiwilss.wanandroid.base

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import com.alibaba.android.arouter.launcher.ARouter
import com.dylanc.loadinghelper.LoadingHelper
import com.dylanc.loadinghelper.ViewType
import com.dylanc.viewbinding.inflateBindingWithGeneric
import com.gyf.immersionbar.ImmersionBar
import com.gyf.immersionbar.ktx.immersionBar
import com.kiwilss.wanandroid.R
import com.kiwilss.wanandroid.dialog.loading.LoadingPopup
import com.kiwilss.wanandroid.dialog.loading.LoadingPopupTwo
import com.kiwilss.wanandroid.statuslayout.TitleAdapter
import com.lxj.xpopup.XPopup
import com.lxj.xpopup.core.BasePopupView

/**
 *@FileName: BaseActivity
 *@author : Lss kiwilss
 * @e-mail : kiwilss@163.com
 * @time   : 2021/2/21
 * @desc   : {DESCRIPTION}
 */
abstract class BaseActivity<VB : ViewBinding>: AppCompatActivity() , LoadingHelper.OnReloadListener {

    lateinit var binding: VB
    lateinit var mLoadingHelper: LoadingHelper
    var mTitleBar: CharSequence? = null
    var page = 0
    var pageSize = 20


    override fun onReload() {

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        beforeSetContent(savedInstanceState)
        super.onCreate(savedInstanceState)
        binding = inflateBindingWithGeneric(layoutInflater)
        setContentView(binding.root)
        //注入路由
        ARouter.getInstance().inject(this)
        //设置状态栏
        setStatusBar()
        //设置切换状态相关
        initLoadingHelp(initIsToolbar())
        //初始化界面
        initInterface(savedInstanceState)
        //初始化点击
        initEvent()
        //初始化数据
        initData()
    }

    //控制是否显示标题栏
    open fun initIsToolbar(): Boolean = true

    open fun initLoadingHelp(isUseToolbar: Boolean) {
        initTitleBar(null)
        mLoadingHelper = LoadingHelper(this)
        if (isUseToolbar) {
            val titleBar = mTitleBar ?: "标题"
            mLoadingHelper.register(ViewType.TITLE, TitleAdapter(titleBar.toString()))
            mLoadingHelper.addChildDecorHeader(ViewType.TITLE)
        }
        //监听重新点击
        mLoadingHelper.setOnReloadListener(this)
    }

    open fun initTitleBar(titleC: CharSequence?) {
        mTitleBar =
            if (titleC.isNullOrEmpty()) {
                title
            } else {
                titleC
            }
    }

    open fun setStatusBar() {
        immersionBar {
            fitsSystemWindows(true)
            statusBarColor(R.color.blue_74D3FF)
            statusBarDarkFont(false,0f)
            init()
        }
    }

    abstract fun initData()

    abstract fun initEvent()

    abstract fun initInterface(savedInstanceState: Bundle?)

    open fun beforeSetContent(savedInstanceState: Bundle?){

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        supportFragmentManager.fragments.forEach {
            it.onActivityResult(requestCode,resultCode, data)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        dismissLoadingDiloag()
    }
    //loading 相关
    var mBasePopup: BasePopupView? = null

    fun showLoadingDiloag(){
        showLoadingDiloag(R.string.loading)
    }

    fun showLoadingDiloagHint(){
        showLoadingDiloag(R.string.loading_hint)
    }

    fun showLoadingDiloag(message: Int){
        mBasePopup?.apply {
            dismiss()
        }
        //XPopup.setAnimationDuration(200)
        mBasePopup = XPopup.Builder(this)
            //.popupAnimation(PopupAnimation.NoAnimation)
            .hasShadowBg(false)
            .dismissOnBackPressed(false)
            .dismissOnTouchOutside(false)
            .asCustom(LoadingPopup(this).setTitle(getString(message)))
            .show()
    }

    fun dismissLoadingDiloag(){
        mBasePopup?.apply {
            if (isShow) dismiss()
        }
        //mBasePopup = null
    }

}