package com.kiwilss.wanandroid.base

import com.blankj.utilcode.util.LogUtils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 *@FileName: BaseRepository
 *@author : Lss kiwilss
 * @e-mail : kiwilss@163.com
 * @time   : 2021/2/22
 * @desc   : {DESCRIPTION}
 */
open class BaseRepository {

    suspend fun <T : Any> apiCall(call: suspend () -> BaseBean<T>): BaseBean<T> {
        return withContext(Dispatchers.IO){
            call.invoke()
        }.apply {
            //统一处理返回结果
            LogUtils.e("接口返回数据-----> $this")
            //这儿可以对返回结果errorCode做一些特殊处理，比如token失效等，可以通过抛出异常的方式实现
            //例：当token失效时，后台返回errorCode 为 100，下面代码实现,再到baseActivity通过观察error来处理
            when (errorCode) {
                //一般0和200是请求成功，直接返回数据
                0, 200 -> this
                100, 401 -> throw TokenInvalidException(errorMsg)
                403 -> throw NoPermissionsException(errorMsg)
                404 -> throw NotFoundException(errorMsg)
                500 -> throw InterfaceErrException(errorMsg)
                504 -> throw TimeOutErrException(errorMsg)
                else -> throw Exception(errorMsg)
            }
        }
    }

    class TokenInvalidException(msg: String = "token失效，请重新登录") : Exception(msg)
    class NoPermissionsException(msg: String = "您没有操作权限，请联系管理员开通") : Exception(msg)
    class NotFoundException(msg: String = "请求的地址不存在") : Exception(msg)
    class InterfaceErrException(msg: String = "接口请求出错") : Exception(msg)
    class TimeOutErrException(msg: String = "连接超时") : Exception(msg)

}