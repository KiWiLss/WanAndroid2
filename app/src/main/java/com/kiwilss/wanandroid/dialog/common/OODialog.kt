package com.kiwilss.wanandroid.dialog.common

import android.content.Context
import android.view.LayoutInflater
import com.dylanc.viewbinding.inflate
import com.kiwilss.wanandroid.R
import com.kiwilss.wanandroid.databinding.ActivityTestBinding
import com.kiwilss.wanandroid.databinding.DialogOoBinding
import com.lxj.xpopup.core.CenterPopupView
import kotlinx.android.synthetic.main.dialog_oo.view.*

/**
 *@FileName: OODialog
 *@author : Lss kiwilss
 * @e-mail : kiwilss@163.com
 * @time   : 2021/2/23
 * @desc   : {DESCRIPTION}
 */
class OODialog(context: Context, private val title: String): CenterPopupView(context) {

//    private val binding: DialogOoBinding by inflate()
//    lateinit var binding: DialogOoBinding
    override fun getImplLayoutId(): Int {
        //binding = DialogOoBinding.inflate(LayoutInflater.from(context),this,false)
        return R.layout.dialog_oo
    }


    override fun getMaxWidth(): Int {
        return 0
    }

    override fun onCreate() {
        super.onCreate()

        popupContentView.tv_pw_oo_title.text = title

//        binding.run {
//            tvPwOoTitle.text = "hello"
//        }

    }





}