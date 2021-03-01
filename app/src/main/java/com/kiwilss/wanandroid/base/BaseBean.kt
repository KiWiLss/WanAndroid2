package com.kiwilss.wanandroid.base

import com.squareup.moshi.Json


data class BaseBean<T>(@Json(name = "data") val data: T?,
                       @Json(name = "errorCode") val errorCode: Int,
                       @Json(name = "errorMsg") val errorMsg: String)

// 通用的带有列表数据的实体
data class BaseBeanList<T>(
    val curPage: Int,
    val datas: List<T>,
    val offset: Int,
    val over: Boolean,
    val pageCount: Int,
    val size: Int,
    val total: Int
)

//通用文章
data class ArticleBean(
    @Json(name = "apkLink")
    var apkLink: String,
    @Json(name = "audit")
    var audit: Int,
    @Json(name = "author")
    var author: String,
    @Json(name = "canEdit")
    var canEdit: Boolean,
    @Json(name = "chapterId")
    var chapterId: Int,
    @Json(name = "chapterName")
    var chapterName: String,
    @Json(name = "collect")
    var collect: Boolean,
    @Json(name = "courseId")
    var courseId: Int,
    @Json(name = "desc")
    var desc: String,
    @Json(name = "descMd")
    var descMd: String,
    @Json(name = "envelopePic")
    var envelopePic: String,
    @Json(name = "fresh")
    var fresh: Boolean,
    @Json(name = "host")
    var host: String,
    @Json(name = "id")
    var id: Int,
    @Json(name = "link")
    var link: String,
    @Json(name = "niceDate")
    var niceDate: String,
    @Json(name = "niceShareDate")
    var niceShareDate: String,
    @Json(name = "origin")
    var origin: String,
    @Json(name = "prefix")
    var prefix: String,
    @Json(name = "projectLink")
    var projectLink: String,
    @Json(name = "publishTime")
    var publishTime: Long,
    @Json(name = "realSuperChapterId")
    var realSuperChapterId: Int,
    @Json(name = "selfVisible")
    var selfVisible: Int,
    @Json(name = "shareDate")
    var shareDate: Long,
    @Json(name = "shareUser")
    var shareUser: String,
    @Json(name = "superChapterId")
    var superChapterId: Int,
    @Json(name = "superChapterName")
    var superChapterName: String,
    @Json(name = "tags")
    var tags: List<Tag>,
    @Json(name = "title")
    var title: String,
    @Json(name = "type")
    var type: Int,
    @Json(name = "userId")
    var userId: Int,
    @Json(name = "visible")
    var visible: Int,
    @Json(name = "zan")
    var zan: Int
)

data class Tag(
    @Json(name = "name")
    var name: String,
    @Json(name = "url")
    var url: String
)