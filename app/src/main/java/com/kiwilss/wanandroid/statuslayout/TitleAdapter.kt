package com.kiwilss.wanandroid.statuslayout

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dylanc.loadinghelper.LoadingHelper
import com.kiwilss.wanandroid.R
import kotlinx.android.synthetic.main.layout_toolbar.view.*


/**
 *@FileName: TitleAdapter
 *@author : Lss Administrator
 * @e-mail : kiwilss@163.com
 * @time   : 2020/8/28
 * @desc   : {DESCRIPTION}
 */
class TitleAdapter(val title: String) : LoadingHelper.Adapter<LoadingHelper.ViewHolder>() {
    lateinit var mHolder: LoadingHelper.ViewHolder
    override fun onBindViewHolder(holder: LoadingHelper.ViewHolder) {
        mHolder = holder
        holder.rootView.run {
            iv_layout_toolbar_back?.setOnClickListener {
                if (context is Activity) {
                    (context as Activity).finish()
                }
            }
            tv_layout_toolbar_title?.text = title
        }
    }

    fun onBackClick(onBackClick: (View?)-> Unit){
        mHolder.rootView.run {
            iv_layout_toolbar_back?.setOnClickListener(onBackClick)
        }
    }

    fun changeTitle(title: String){
        mHolder.rootView.run {
            tv_layout_toolbar_title?.text = title
        }
    }

    fun changeRight(msg: CharSequence,onRightClick: (View?)-> Unit){
        mHolder.rootView.run {
            tv_layout_toolbar_right?.visibility = View.VISIBLE
            tv_layout_toolbar_right?.text = msg
            tv_layout_toolbar_right?.setOnClickListener(onRightClick)
        }
    }

    fun changeRight(resId: Int,onRightClick: (View?)-> Unit){
        mHolder.rootView.run {
            iv_layout_toolbar_right?.visibility = View.VISIBLE
            iv_layout_toolbar_right?.setImageResource(resId)
            iv_layout_toolbar_right?.setOnClickListener(onRightClick)
        }
    }



    override fun onCreateViewHolder(
        inflater: LayoutInflater,
        parent: ViewGroup
    ): LoadingHelper.ViewHolder {
        return LoadingHelper.ViewHolder(inflater.inflate(R.layout.layout_toolbar,parent,false))
    }
}