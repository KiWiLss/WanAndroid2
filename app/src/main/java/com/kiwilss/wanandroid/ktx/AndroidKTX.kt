package com.kiwilss.wanandroid.ktx

import android.annotation.SuppressLint
import android.content.Context

/**
 * Description: 统一配置扩展方法中的变量
 * Create by lxj, at 2018/12/4
 */
@SuppressLint("StaticFieldLeak")
object AndroidKTX {
    lateinit var context: Context
    var isDebug = true
    var defaultLogTag = "androidktx"
    var sharedPrefName = "androidktx"

    /**
     * 初始化配置信息，必须调用
     * @param isDebug 是否是debug模式，默认为true
     */
    fun init(context: Context,
             isDebug: Boolean = true,
             defaultLogTag: String = AndroidKTX.defaultLogTag,
             sharedPrefName: String = AndroidKTX.sharedPrefName
    ) {
        AndroidKTX.context = context
        AndroidKTX.isDebug = isDebug
        AndroidKTX.defaultLogTag = defaultLogTag
        AndroidKTX.sharedPrefName = sharedPrefName
//        if(context is Application){
//            Utils.init(context)
//        }
//        ToastUtils.getDefaultMaker().setGravity(Gravity.CENTER, 0 , 0)
//        ToastUtils.getDefaultMaker().setBgResource(R.drawable._ktx_toast_bg)
//        ToastUtils.getDefaultMaker().setTextColor(Color.WHITE)
//        initRefresh()
//        IjkPlayerManager.setLogLevel(IjkMediaPlayer.IJK_LOG_SILENT)
    }

    fun initRefresh() {
        //设置全局的Header构建器
//        SmartRefreshLayout.setDefaultRefreshHeaderCreator { context, layout ->
////            layout.setPrimaryColorsId(R.color.bg_color, R.color.colorPrimary)
//            layout.setPrimaryColors(Color.parseColor("#f0f0f0"), Color.parseColor("#111111"))
//            ClassicsHeader(context)
//        }
//        //设置全局的Footer构建器
//        SmartRefreshLayout.setDefaultRefreshFooterCreator { context, layout ->
//            layout.setPrimaryColors(Color.parseColor("#f0f0f0"), Color.parseColor("#111111"))
//            ClassicsFooter(context)
//        }
    }
}