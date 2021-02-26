package com.kiwilss.wanandroid.ui.home

import com.kiwilss.wanandroid.base.ArticleBean
import com.squareup.moshi.Json

/**
 *@FileName: bean
 *@author : Lss kiwilss
 * @e-mail : kiwilss@163.com
 * @time   : 2021/2/26
 * @desc   : {DESCRIPTION}
 */
data class HomeBannerBean(
    @Json(name = "desc")
    var desc: String,
    @Json(name = "id")
    var id: Int,
    @Json(name = "imagePath")
    var imagePath: String,
    @Json(name = "isVisible")
    var isVisible: Int,
    @Json(name = "order")
    var order: Int,
    @Json(name = "title")
    var title: String,
    @Json(name = "type")
    var type: Int,
    @Json(name = "url")
    var url: String
)

data class HomeArticalBean(
    @Json(name = "curPage")
    var curPage: Int,
    @Json(name = "datas")
    var datas: List<ArticleBean>,
    @Json(name = "offset")
    var offset: Int,
    @Json(name = "over")
    var over: Boolean,
    @Json(name = "pageCount")
    var pageCount: Int,
    @Json(name = "size")
    var size: Int,
    @Json(name = "total")
    var total: Int
)

