package com.kiwilss.wanandroid.ktx.core

import androidx.lifecycle.LifecycleOwner
import com.kiwilss.wanandroid.ktx.livedata.StateLiveData


/**
 * 直接监听state状态
 */
//fun StateLayout.observeState(owner: LifecycleOwner,
//                             liveData: StateLiveData<*>,
//                             delay: Long = 0,
//                             onLoading: (() -> Unit)? = null,
//                             onSuccess: (() -> Unit)? = null,
//                             onError: (() -> Unit)? = null,
//                             onEmpty: (() -> Unit)? = null) {
//    liveData.state.observe(owner, Observer<StateLiveData.State> {
//        when (liveData.state.value) {
//            StateLiveData.State.Loading -> {
//                showLoading()
//                onLoading?.invoke()
//            }
//            StateLiveData.State.Success -> {
//                postDelayed({
//                    showContent()
//                    onSuccess?.invoke()
//                }, delay)
//            }
//            StateLiveData.State.Empty -> {
//                postDelayed({
//                    showEmpty()
//                    onEmpty?.invoke()
//                }, delay)
//            }
//            StateLiveData.State.Error -> {
//                postDelayed({
//                    showError()
//                    onError?.invoke()
//                }, delay)
//            }
//        }
//    })
//}
