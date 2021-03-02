package com.kiwilss.wanandroid.ktx.view

import com.scwang.smart.refresh.layout.SmartRefreshLayout

/**
 * @author : Administrator
 * e-mail : kiwilss@163.com
 * time   : 2021/03/02
 *  desc   :
 */

fun SmartRefreshLayout.finishRefreshMore(){
    finishRefresh()
    finishLoadMore()
}

fun SmartRefreshLayout.finishRefreshMoreNoData(){
    finishRefresh()
    finishLoadMore()
    finishLoadMoreWithNoMoreData()
}