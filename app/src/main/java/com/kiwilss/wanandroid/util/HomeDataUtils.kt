package com.kiwilss.wanandroid.util

import com.kiwilss.wanandroid.R
import com.kiwilss.wanandroid.config.ArouterPage

/**
 *@FileName: HomeDataUtils
 *@author : Lss kiwilss
 * @e-mail : kiwilss@163.com
 * @time   : 2021/2/25
 * @desc   : {DESCRIPTION}
 */
//首页底部列表数据
data class HomeDataBean(val title: String,val icon: Int, val page: String)
object HomeDataUtils{

    fun getHomeList() =
        arrayListOf<HomeDataBean>(
            HomeDataBean("设置", R.drawable.settings,ArouterPage.ABOUT),
            HomeDataBean("设置", R.drawable.settings,ArouterPage.ABOUT),
            HomeDataBean("设置", R.drawable.settings,ArouterPage.ABOUT),
            HomeDataBean("设置", R.drawable.settings,ArouterPage.ABOUT),
            HomeDataBean("设置", R.drawable.settings,ArouterPage.ABOUT),
            HomeDataBean("设置", R.drawable.settings,ArouterPage.ABOUT),
            HomeDataBean("设置", R.drawable.settings,ArouterPage.ABOUT),
            HomeDataBean("设置", R.drawable.settings,ArouterPage.ABOUT),
            HomeDataBean("设置", R.drawable.settings,ArouterPage.ABOUT),
            HomeDataBean("设置", R.drawable.settings,ArouterPage.ABOUT),
        )


}
