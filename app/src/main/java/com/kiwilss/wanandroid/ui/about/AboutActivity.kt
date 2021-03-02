package com.kiwilss.wanandroid.ui.about

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.blankj.utilcode.util.LogUtils
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.dylanc.loadinghelper.ViewType
import com.jeremyliao.liveeventbus.LiveEventBus
import com.kiwilss.wanandroid.R
import com.kiwilss.wanandroid.base.BaseActivity
import com.kiwilss.wanandroid.config.ArouterPage
import com.kiwilss.wanandroid.databinding.ActivityAboutBinding
import com.kiwilss.wanandroid.help.ActivityHelperK
import com.kiwilss.wanandroid.ktx.RouterKtx
import com.kiwilss.wanandroid.ktx.RouterKtx.startActivity
import com.kiwilss.wanandroid.ktx.core.click
import com.kiwilss.wanandroid.ktx.core.postDelay
import com.kiwilss.wanandroid.statuslayout.TitleAdapter
import com.scwang.smart.refresh.layout.api.RefreshLayout
import com.scwang.smart.refresh.layout.listener.OnRefreshLoadMoreListener

/**
 *@FileName: AboutActivity
 *@author : Lss kiwilss
 * @e-mail : kiwilss@163.com
 * @time   : 2021/2/22
 * @desc   : {DESCRIPTION}
 */
@Route(path = ArouterPage.ABOUT)
class AboutActivity : BaseActivity<ActivityAboutBinding>(){

    @Autowired(name = ArouterPage.KEY)
    @JvmField
    var key: String? = null

    companion object{
        //跳转相关
        fun startActivity(activity: FragmentActivity,key: String,callback: (Int,Intent?) -> Unit){
            ActivityHelperK.init(activity)
                .startActivityForResult(ArouterPage.ABOUT,callback,ArouterPage.KEY to key)
        }

        fun startActivity2(){
            RouterKtx.startActivity(ArouterPage.ABOUT)
        }
    }



    override fun initData() {
        LiveEventBus.get(ArouterPage.KEY, String::class.java)
            .observe(this){
                LogUtils.e(it)
            }

//        mLoadingHelper.getAdapter<TitleAdapter>(ViewType.TITLE).onBackClick{
//
//        }


    }

    override fun initEvent() {
        binding.tvAboutShow.click {
            LiveEventBus.get(ArouterPage.KEY).post("hello")
//            setResult(RESULT_OK)
//            finish()
        }


    }

    override fun initInterface(savedInstanceState: Bundle?) {
        //showLoadingDiloag()
        LogUtils.e(key)
        binding.tvAboutShow.text = key

        postDelay (2000){
            dismissLoadingDiloag()
        }

        val aboutAdapter = AboutAdapter()
        binding.run {
            rvList.layoutManager = LinearLayoutManager(this@AboutActivity)
            rvList.adapter = aboutAdapter
        }

        aboutAdapter.setList(arrayListOf("1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1",))

        binding.srlRefresh.setOnRefreshLoadMoreListener(object :OnRefreshLoadMoreListener{
            override fun onLoadMore(refreshLayout: RefreshLayout) {
                LogUtils.e("onLoadMore")
                binding.srlRefresh.finishLoadMore()
            }

            override fun onRefresh(refreshLayout: RefreshLayout) {
                LogUtils.e("onRefresh")
                binding.srlRefresh.finishRefresh()
            }

        })
        binding.srlRefresh.autoRefresh()
    }
}

class AboutAdapter: BaseQuickAdapter<String,BaseViewHolder>(R.layout.item_style_delivery){
    override fun convert(holder: BaseViewHolder, item: String) {

    }

}