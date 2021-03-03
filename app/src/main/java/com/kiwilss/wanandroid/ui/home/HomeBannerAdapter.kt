package com.kiwilss.wanandroid.ui.home

import android.annotation.SuppressLint
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
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