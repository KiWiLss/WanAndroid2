package com.kiwilss.wanandroid.ui.dialog

import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Route
import com.kiwilss.wanandroid.base.BaseActivity
import com.kiwilss.wanandroid.config.ArouterPage
import com.kiwilss.wanandroid.databinding.ActivityDialogBinding
import com.kiwilss.wanandroid.dialog.common.OODialog
import com.kiwilss.wanandroid.ktx.core.click
import com.lxj.xpopup.XPopup




/**
 *@FileName: DialogActivity
 *@author : Lss kiwilss
 * @e-mail : kiwilss@163.com
 * @time   : 2021/2/23
 * @desc   : {DESCRIPTION}
 */
@Route(path = ArouterPage.DIALOG)
class DialogActivity: BaseActivity<ActivityDialogBinding>() {
    override fun initData() {

    }

    override fun initEvent() {
        binding.btnOo.click {
            XPopup.Builder(this)
                .asCustom(OODialog(this,"测试一下"))
                .show()
        }

    }

    override fun initInterface(savedInstanceState: Bundle?) {



    }
}