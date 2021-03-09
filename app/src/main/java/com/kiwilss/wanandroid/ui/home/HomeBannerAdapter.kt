package com.kiwilss.wanandroid.ui.home

import android.annotation.SuppressLint
import android.os.Build
import android.view.View
import android.view.ViewGroup
import android.widget.AbsListView.OnScrollListener.SCROLL_STATE_IDLE
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.module.LoadMoreModule
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.kiwilss.wanandroid.R
import com.kiwilss.wanandroid.base.ArticleBean
import com.kiwilss.wanandroid.ktx.core.gone
import com.kiwilss.wanandroid.ktx.core.visible
import com.youth.banner.adapter.BannerAdapter
import com.youth.banner.util.BannerUtils
import java.lang.Integer.max

/**
 *@FileName: HomeBannerAdapter
 *@author : Lss kiwilss
 * @e-mail : kiwilss@163.com
 * @time   : 2021/2/26
 * @desc   : {DESCRIPTION}
 */
class HomeBannerAdapter(val datas: List<HomeBannerBean>):
    BannerAdapter<HomeBannerBean,HomeBannerAdapter.HomeBannerHolder>(datas) {
    class HomeBannerHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var ivPic: ImageView? = itemView.findViewById(R.id.ivItemHomeBannerPic)
        var tvTitle: TextView? = itemView.findViewById(R.id.tvItemHomeBannerTitle)
        var tvPos: TextView? = itemView.findViewById(R.id.tvItemHomeBannerPos)
    }

    override fun onCreateHolder(parent: ViewGroup?, viewType: Int): HomeBannerHolder {
        return HomeBannerHolder(BannerUtils.getView(parent!!, R.layout.item_fg_home_banner))
    }

    @SuppressLint("SetTextI18n")
    override fun onBindView(
        holder: HomeBannerHolder?,
        data: HomeBannerBean?,
        position: Int,
        size: Int
    ) {
        holder?.run {
            ivPic?.load(data?.imagePath){
                crossfade(true)
            }
            tvTitle?.text = data?.title
            tvPos?.text = "${position + 1}/${datas.size}"
        }
    }
}

class HomeArticleAdapter: BaseQuickAdapter<ArticleBean,BaseViewHolder>(R.layout.item_fg_home_article) {
    // 预加载回调
    var onPreload: (() -> Unit)? = null
    // 预加载偏移量
    var preloadItemCount = 0
    // 列表滚动状态
    private var scrollState = SCROLL_STATE_IDLE
    // 增加预加载状态标记位
    var isPreloading = false

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)
        checkPreload(position)
    }

    //检测预加载
    private fun checkPreload(position: Int) {
        val thresold = Math.max(itemCount - 1 - preloadItemCount, 0)
        if (onPreload != null
            && position == thresold// 索引值等于阈值
            && scrollState != SCROLL_STATE_IDLE // 列表正在滚动
            && !isPreloading // 预加载不在进行中
        ) {
            isPreloading = true // 表示正在执行预加载
            onPreload?.invoke()
        }
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                // 更新滚动状态
                scrollState = newState
                super.onScrollStateChanged(recyclerView, newState)
            }
        })
    }

    override fun convert(holder: BaseViewHolder, item: ArticleBean) {

        holder.run {
            setText(R.id.tvItemHomeArticleName,item.author)
            setText(R.id.tvItemHomeArticleDate,item.niceDate)
            setText(R.id.tvItemHomeArticleTitle,item.title)
            setText(R.id.tvItemHomeArticleTag,item.superChapterName +"."+ item.chapterName)
        }
//        val ivPic = holder.getView<ImageView>(R.id.ivItemHomeArticlePic)
//        if (!item.envelopePic.isNullOrEmpty()) {
//            ivPic.load(item.envelopePic)
//            ivPic.visible()
//        }else{
//            ivPic.gone()
//        }

    }
}