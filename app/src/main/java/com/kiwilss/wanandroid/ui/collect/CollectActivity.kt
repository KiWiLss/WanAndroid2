package com.kiwilss.wanandroid.ui.collect

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.alibaba.android.arouter.facade.annotation.Route
import com.kiwilss.wanandroid.base.BaseVMActivity
import com.kiwilss.wanandroid.config.ArouterPage
import com.kiwilss.wanandroid.databinding.ActivityCollectBinding
import com.kiwilss.wanandroid.ui.home.HomeArticleAdapter
import com.youth.banner.util.LogUtils

/**
 * @author : Administrator
 * e-mail : kiwilss@163.com
 * time   : 2021/03/01
 *  desc   :
 */
@Route(path = ArouterPage.COLLECT)
class CollectActivity : BaseVMActivity<ActivityCollectBinding, CollectViewModel>() {
    val mArticleAdapter by lazy { HomeArticleAdapter() }

    override fun providerVMClass(): Class<CollectViewModel> = CollectViewModel::class.java

    override fun initData() {
        viewModel.getCollectList(page).observe(this, Observer {
            LogUtils.e(it?.toString())
            mArticleAdapter.setList(it?.datas)
        })
    }

    override fun initEvent() {
    }

    override fun initInterface(savedInstanceState: Bundle?) {

        binding.rvList.run {
            layoutManager = LinearLayoutManager(this@CollectActivity)
            adapter = mArticleAdapter
        }

    }
}

