package com.kiwilss.wanandroid.ui.collect

import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.alibaba.android.arouter.facade.annotation.Route
import com.blankj.utilcode.util.LogUtils
import com.kiwilss.wanandroid.base.BaseVMActivity
import com.kiwilss.wanandroid.config.ArouterPage
import com.kiwilss.wanandroid.databinding.ActivityCollectBinding
import com.kiwilss.wanandroid.ktx.view.finishRefreshMore
import com.kiwilss.wanandroid.ui.home.HomeArticleAdapter
import com.scwang.smart.refresh.layout.api.RefreshLayout
import com.scwang.smart.refresh.layout.listener.OnRefreshLoadMoreListener

/**
 * @author : Administrator
 * e-mail : kiwilss@163.com
 * time   : 2021/03/01
 *  desc   :
 */
@Route(path = ArouterPage.COLLECT)
class CollectActivity : BaseVMActivity<ActivityCollectBinding, CollectViewModel>() ,
    OnRefreshLoadMoreListener{
    val mArticleAdapter by lazy { HomeArticleAdapter() }

    var pageCount = 0
    override fun providerVMClass(): Class<CollectViewModel> = CollectViewModel::class.java

    override fun initData() {
//        viewModel.getCollectList(page).observe(this, Observer {
//            LogUtils.e(it?.toString())
//            pageCount = it?.pageCount ?: 0
//            if (page == 0){
//                mArticleAdapter.setList(it?.datas)
//                binding.srlRefresh.finishRefresh()
//            }else{
//                mArticleAdapter.addData(it?.datas!!)
//                binding.srlRefresh.finishLoadMore()
//            }
//            finishRefresh()
//        })

    }

    override fun requestError(it: Exception?) {
        super.requestError(it)
        finishRefresh()
    }

    private fun finishRefresh() {
        binding.srlRefresh.finishRefreshMore()
    }

    override fun initEvent() {


    }



    override fun initInterface(savedInstanceState: Bundle?) {

        //不设置监听,自动结束刷新和加载更多
//        binding.srlRefresh.setOnRefreshLoadMoreListener(this)
//        binding.srlRefresh.setEnableLoadMoreWhenContentNotFull(false);//取消内容不满一页时开启上拉加载功能
        binding.srlRefresh.setOnRefreshListener {
            Log.e("MMM", ": setOnRefreshListener")
            page = 0
            initCollectData()
        }
        binding.srlRefresh.setOnLoadMoreListener {
            page++
            initCollectData()
        }

        binding.rvList.run {
            layoutManager = LinearLayoutManager(this@CollectActivity)
            adapter = mArticleAdapter
        }
        binding.srlRefresh.setEnableLoadMoreWhenContentNotFull(false)
        binding.srlRefresh.autoRefresh()
        //binding.srlRefresh.setEnableFooterFollowWhenNoMoreData(true)

//        mArticleAdapter.loadMoreModule.preLoadNumber = 4
//        mArticleAdapter.loadMoreModule.setOnLoadMoreListener {
//            LogUtils.e("setOnLoadMoreListener")
//        }

        //设置预加载
        binding.rvList.addOnScrollListener(object :RecyclerView.OnScrollListener(){
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val layoutManager: LinearLayoutManager = binding.rvList.layoutManager as LinearLayoutManager
                val itemCount: Int = layoutManager.itemCount
                val lastPos: Int = layoutManager.findLastCompletelyVisibleItemPosition()
                val last = layoutManager.findLastVisibleItemPosition()
                LogUtils.e("onScrolled: $itemCount ------- $lastPos ----- $last")
                if (last == itemCount - 6){
                    LogUtils.e("触发加载条件")
                    page++
                    initCollectData()
                }
            }
        })


    }

    //https://github.com/KiWiLss/WanAndroid2.git
    //https://gitclone.com/github.com/KiWiLss/WanAndroid2.git

    private fun initCollectData() {
        viewModel.getCollectList(page).observe(this, Observer {
           // LogUtils.e(it?.toString())
            //finishRefresh()
            pageCount = it?.pageCount ?: 0
            Log.e("MMM", ": $page---$pageCount")
            when {
                page == 0 -> {
                    mArticleAdapter.setList(it?.datas)
                    binding.srlRefresh.finishRefresh()
                }
                page < pageCount -> {
                    Log.e("MMM", ": ------------")
                    mArticleAdapter.addData(it?.datas!!)
                    binding.srlRefresh.finishLoadMore()
                }
                else -> {
                    Log.e("MMM", ": ------else------")
                    binding.srlRefresh.finishLoadMoreWithNoMoreData()
                }
            }

        })
    }

    override fun onLoadMore(refreshLayout: RefreshLayout) {
        page++
       if (page < pageCount){
           initData()
       }else{
           binding.srlRefresh.finishLoadMoreWithNoMoreData()
//           binding.srlRefresh.setNoMoreData(false)
       }
    }

    override fun onRefresh(refreshLayout: RefreshLayout) {
        page = 0
        initData()
    }
}

