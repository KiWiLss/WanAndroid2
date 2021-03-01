package com.kiwilss.wanandroid.ui.home

import android.os.Bundle
import android.view.Gravity
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.LinearLayoutManager
import com.blankj.utilcode.util.LogUtils
import com.google.android.material.appbar.AppBarLayout
import com.jeremyliao.liveeventbus.LiveEventBus
import com.kiwilss.lutils.res.ResUtils
import com.kiwilss.wanandroid.R
import com.kiwilss.wanandroid.base.ArticleBean
import com.kiwilss.wanandroid.base.BaseVMFragment
import com.kiwilss.wanandroid.config.EventConstant
import com.kiwilss.wanandroid.databinding.FragmentHomeBinding
import com.kiwilss.wanandroid.ktx.core.gone
import com.kiwilss.wanandroid.ktx.core.visible
import com.kiwilss.wanandroid.ktx.toast
import com.kiwilss.wanandroid.md.AppBarStateChangeListener
import com.scwang.smart.refresh.footer.ClassicsFooter
import com.scwang.smart.refresh.header.ClassicsHeader
import com.youth.banner.listener.OnBannerListener

/**
 *@FileName: HomeFragment
 *@author : Lss kiwilss
 * @e-mail : kiwilss@163.com
 * @time   : 2021/2/25
 * @desc   : {DESCRIPTION}
 */
class HomeFragment: BaseVMFragment<FragmentHomeBinding,HomeViewModel>() {

    var isFirstLoad = true
    val mArticles = ArrayList<ArticleBean>()
    var mArticleTop = ArrayList<ArticleBean>()
    var mArticle = ArrayList<ArticleBean>()
    var pageCount = 1

    val mArticleAdapter by lazy { HomeArticleAdapter() }

    override fun providerVMClass(): Class<HomeViewModel> = HomeViewModel::class.java

    override fun initData() {

    }

    override fun initEvent() {
//        binding.srlRefresh.setRefreshHeader(ClassicsHeader(context))
//        binding.srlRefresh.setRefreshFooter(ClassicsFooter(context))
        binding.srlRefresh.setOnRefreshListener {
            page = 1
            initArticles()
        }
        binding.srlRefresh.setOnLoadMoreListener {
            if (page <= pageCount){
                page++
                initArticles()
            }else{
                binding.srlRefresh.finishLoadMoreWithNoMoreData()
            }
        }
        binding.collapsing.run {
            title = "玩安卓"
            setExpandedTitleColor(ResUtils.getColor(R.color.translate))
            setCollapsedTitleTextColor(ResUtils.getColor(R.color.white))
            expandedTitleGravity = Gravity.BOTTOM
            collapsedTitleGravity = Gravity.CENTER
        }
        binding.appbar.addOnOffsetChangedListener(object : AppBarStateChangeListener(){

            override fun onStateChanged(appBarLayout: AppBarLayout?, state: State?, i: Int) {
                when (state) {
                    State.EXPANDED -> {
                        binding.toolbar.setPadding(0,0,0,0)
                        LiveEventBus.get(EventConstant.search).post(true)
                    }
                    State.COLLAPSED -> {
                        binding.toolbar.setPadding(0,ResUtils.getDimensionPixelOffset(R.dimen.m24),0,0)
                        LiveEventBus.get(EventConstant.search).post(false)
                    }
                    else -> {
                        binding.toolbar.setPadding(0,ResUtils.getDimensionPixelOffset(R.dimen.m24),0,0)
                    }
                }
            }

        })

        mArticleAdapter.addChildClickViewIds(R.id.ivItemHomeBannerLike)
        mArticleAdapter.setOnItemClickListener { adapter, view, position ->

        }
        mArticleAdapter.setOnItemChildClickListener { adapter, view, position ->
            toast("hello")
        }

    }

    override fun initInterface(view: View, savedInstanceState: Bundle?) {
        binding.rvList.run {
            layoutManager = LinearLayoutManager(context)
            adapter = mArticleAdapter
        }
        LiveEventBus.get(EventConstant.is_scroll_top,Boolean::class.java).observe(this){
            if (it){
                binding.appbar.setExpanded(true)
                binding.rvList.scrollToPosition(0)
            }
        }


    }

    override fun onResume() {
        super.onResume()
        if (isFirstLoad){
            isFirstLoad = false
            initLoadData()
        }
    }

    /**
     * 懒加载数据使用
    */
    private fun initLoadData() {
        //获取轮播图数据
        viewModel.getHomeBanner().observe(this){
            it?.run {
                val bannerAdapter = HomeBannerAdapter(it)
                binding.banner.run {
                    addBannerLifecycleObserver(this@HomeFragment)
                    adapter = bannerAdapter
                    //点击
                    setOnBannerListener { data, position ->

                    }
                }
            }
        }
        //获取置顶数据,获取首页数据
        viewModel.getTopArticle().observe(this){
            mArticleTop.clear()
            it?.run {
                //修改列表数据
                mArticleAdapter.data.addAll(0,it)
                mArticleAdapter.notifyDataSetChanged()
            }
        }
        initArticles()

    }

    override fun requestError(it: Exception?) {
        super.requestError(it)
        refreshFinish()
    }

    private fun refreshFinish() {
        binding.srlRefresh.finishRefresh()
        binding.srlRefresh.finishLoadMore()
    }

    private fun initArticles() {
        viewModel.getArticle(page).observe(this){
            refreshFinish()
            it?.run {
                pageCount = it.pageCount
                if (page == 0){
                    mArticleAdapter.setList(it.datas)
                }else{
                    mArticleAdapter.addData(it.datas)
                }
            }
        }
    }

    private fun modifyListData() {

    }
}


