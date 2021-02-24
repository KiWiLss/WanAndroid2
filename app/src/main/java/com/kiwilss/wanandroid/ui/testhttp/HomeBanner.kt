package com.kiwilss.wanandroid.ui.testhttp
import androidx.annotation.Keep
import com.squareup.moshi.Json



/**
 *@FileName: HomeBanner
 *@author : Lss kiwilss
 * @e-mail : kiwilss@163.com
 * @time   : 2021/2/24
 * @desc   : {DESCRIPTION}
 */

data class HomeBanner(
    val desc: String,
    val id: Int,
    val imagePath: String,
    val isVisible: Int,
    val order: Int,
    val title: String,
    val type: Int,
    val url: String
)

data class BannerAll(
    @Json(name = "data")
    var `data`: List<Data>,
    @Json(name = "errorCode")
    var errorCode: Int,
    @Json(name = "errorMsg")
    var errorMsg: String
)

data class Data(
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

data class Testt(
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

data class LoginInfo(
    @Json(name = "chapterTops")
    val chapterTops: List<Any>,
    @Json(name = "collectIds")
    val collectIds: List<Int>,
    @Json(name = "email")
    val email: String,
    @Json(name = "icon")
    val icon: String,
    @Json(name = "id")
    val id: Int,
    @Json(name = "password")
    val password: String,
    @Json(name = "token")
    val token: String,
    @Json(name = "type")
    val type: Int,
    @Json(name = "username")
    val username: String
)

