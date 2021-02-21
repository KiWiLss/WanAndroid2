package com.kiwilss.wanandroid.ktx.util

import android.content.Context
import com.blankj.utilcode.constant.PermissionConstants
import com.blankj.utilcode.util.*
import java.io.File
import java.io.IOException
import java.net.URLEncoder


data class CommonUpdateInfo(
        var download_url: String? = null,
        var version_name: String? = null,
        var version_code: String? = null,
        var package_name: String? = null,
        var update_info: String? = null
)

/**
 * 版本更新工具，功能有2个：
 * 1. 弹出版本更新提示的弹窗，如果对UI有要求，可以自己实现弹窗，然后调用第2个方法
 * 2. 下载并安装Apk
 */
//object VersionUpdateUtil {
//    const val cacheKey = "_version_update_download_apk_"
//    private fun showUpdatePopup(context: Context, updateData: CommonUpdateInfo, path: String) {
//        XPopup.Builder(context)
//                .dismissOnBackPressed(false)
//                .dismissOnTouchOutside(false)
//                .asCustom(VersionUpdatePopup(context = context, updateInfo = updateData, onOkClick = {
//                    //删除缓存
//                    sp().putString(cacheKey, "")
//                    AppUtils.installApp(path)
//                }))
//                .show()
//    }
//
//    /**
//     * 下载并安装Apk，会自动检测是否有缓存过的apk文件，如果有则直接提示安装。如果没有则进行下载，一旦安装就删除缓存的文件路径
//     * @param updateData 更新相关信息
//     * @param onShowUpdateUI 默认会有个更新的提示，如果想自己实现UI，则实现这个监听器
//     * @param useCache 是否使用缓存的apk文件
//     */
//    fun downloadAndInstallApk(context: Context, updateData: CommonUpdateInfo, onShowUpdateUI: ((apkPath: String) -> Unit)? = null,
//                              useCache: Boolean = true) {
//        //检测是否有缓存的apk路径，如果有说明已经下载过了
//        DirManager.init(context)
//        val filename = "${URLEncoder.encode(updateData.download_url!!)}.apk"
//        val file = File("${DirManager.downloadDir}/${filename}")
//        val cacheApkPath = sp().getString(cacheKey, "")
//        if (cacheApkPath.isNotEmpty() && FileUtils.isFileExists(cacheApkPath) && cacheApkPath==file.absolutePath && useCache) {
//            LogUtils.e("新版本Apk已存在，无需下载，路径：$cacheApkPath")
//            if (onShowUpdateUI != null) {
//                onShowUpdateUI(cacheApkPath)
//            } else {
//                showUpdatePopup(context, updateData, cacheApkPath)
//            }
//            return
//        }
//        PermissionUtils.permission(PermissionConstants.STORAGE)
//                .callback(object : PermissionUtils.SimpleCallback {
//                    override fun onGranted() {
//                        if (updateData.download_url.isNullOrEmpty()) {
//                            return
//                        }
//                        FileUtils.createFileByDeleteOldFile(file)
//                        updateData.download_url!!.http(baseUrlTag = "")
//                                .savePath(file.absolutePath)
//                                .get<File>(object : HttpCallback<File> {
//                                    override fun onSuccess(t: File) {
//                                        LogUtils.e("新版本下载成功，路径为：${file.absolutePath}")
//                                        //缓存路径
//                                        sp().putString(cacheKey, t.absolutePath)
//                                        if (onShowUpdateUI != null) {
//                                            onShowUpdateUI(t.absolutePath)
//                                        } else {
//                                            showUpdatePopup(context, updateData, t.absolutePath)
//                                        }
//                                    }
//
//                                    override fun onFail(e: IOException) {
//                                        super.onFail(e)
//                                    }
//                                })
//                    }
//
//                    override fun onDenied() {
//                        ToastUtils.showShort("权限获取失败，无法下载新版本")
//                    }
//                }).request()
//    }
//
//}