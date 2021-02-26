package com.kiwilss.wanandroid.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.dylanc.loadinghelper.LoadingHelper
import com.dylanc.viewbinding.inflateBindingWithGeneric
import com.kiwilss.wanandroid.dialog.loading.LoadingPopup
import com.lxj.xpopup.XPopup
import com.lxj.xpopup.core.BasePopupView

/**
 *@FileName: BaseFragment
 *@author : Lss kiwilss
 * @e-mail : kiwilss@163.com
 * @time   : 2021/2/22
 * @desc   : {DESCRIPTION}
 */
abstract class BaseFragment<VB: ViewBinding>: Fragment() , LoadingHelper.OnReloadListener{

    private var _binding: VB? = null
    val binding: VB get() = _binding!!

    var page = 1
    var pageSize = 20

    fun getLoadingHelper(): LoadingHelper? {
        return if (activity !is BaseActivity<*>) null
        else (activity as BaseActivity<*>).mLoadingHelper
    }

    override fun onReload() {

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = inflateBindingWithGeneric(layoutInflater)
        getLoadingHelper()?.setOnReloadListener(this)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initInterface(view, savedInstanceState)
        initEvent()
        initData()

    }

    abstract fun initData()

    abstract fun initEvent()

    abstract fun initInterface(view: View, savedInstanceState: Bundle?)

    var mLoadingMessage = "加载中..."

    var mBasePopup: BasePopupView? = null

    fun showLoadingDiloag(){
        showLoadingDiloag(mLoadingMessage)
    }

    fun showLoadingDiloag(message: String){
        mBasePopup?.apply {
            if (isShow) {
                dismiss()
            }
        }
        mBasePopup = XPopup.Builder(context)
            .hasShadowBg(false)
            //.popupAnimation(PopupAnimation.NoAnimation)
            .dismissOnBackPressed(false)
            .dismissOnTouchOutside(false)
            .asCustom(LoadingPopup(context!!).setTitle(message))
            .show()
    }

    fun dismissLoadingDiloag(){
        mBasePopup?.apply {
            if (isShow)  dismiss()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        dismissLoadingDiloag()
        _binding = null
    }
}