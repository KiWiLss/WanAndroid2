package com.kiwilss.wanandroid

import android.content.Intent
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.alibaba.android.arouter.facade.annotation.Route
import com.blankj.utilcode.util.LogUtils
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.gyf.immersionbar.ktx.immersionBar
import com.jeremyliao.liveeventbus.LiveEventBus
import com.kiwilss.itemdecoration.IntervalItemDecoration
import com.kiwilss.lutils.res.ResUtils
import com.kiwilss.sticky.StickyDecoration
import com.kiwilss.wanandroid.base.BaseActivity
import com.kiwilss.wanandroid.config.ArouterPage
import com.kiwilss.wanandroid.config.EventConstant
import com.kiwilss.wanandroid.databinding.ActivityMainBinding
import com.kiwilss.wanandroid.ktx.core.click
import com.kiwilss.wanandroid.ktx.core.gone
import com.kiwilss.wanandroid.ktx.core.saveToAlbum
import com.kiwilss.wanandroid.ktx.core.visible
import com.kiwilss.wanandroid.ktx.startActivity
import com.kiwilss.wanandroid.ktx.startActivityForResult
import com.kiwilss.wanandroid.ui.about.AboutActivity
import com.kiwilss.wanandroid.ui.home.HomeFragment
import com.kiwilss.wanandroid.ui.my.MyFragment
import com.kiwilss.wanandroid.util.HomeDataBean
import com.kiwilss.wanandroid.util.HomeDataUtils
import kotlinx.android.synthetic.main.activity_main.*
@Route(path = ArouterPage.MAIN)
class MainActivity : BaseActivity<ActivityMainBinding>() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
//
//
//    }
    val mBottomAdapter by lazy { MainBottomAdapter() }
    lateinit var behavior: BottomSheetBehavior<LinearLayout>
    val mFragments = ArrayList<Fragment>()
    var isSearch = true

    override fun initData() {

    }

    override fun initEvent() {
        //导航点击
        binding.tvHome.click {
            closeBottom()
            changeViewPager(binding.tvHome,0)
        }
        binding.tvProject.click {
            closeBottom()
            changeViewPager(binding.tvProject,1)
        }
        binding.tvGzh.click {
            closeBottom()
            changeViewPager(binding.tvGzh,2)
        }
        binding.tvSystem.click {
            closeBottom()
            changeViewPager(binding.tvSystem,3)
        }
        binding.tvMy.click {
            closeBottom()
            changeViewPager(binding.tvMy,4)
        }
        //binding.fabUp.setImageResource(R.drawable.search_white)
        binding.fabUp.setOnClickListener {
            if (isSearch){

            }else{
                LiveEventBus.get(EventConstant.is_scroll_top).post(true)
            }
        }
    }

    private fun changeViewPager(tvText: TextView, next: Int) {
        val current = binding.vpMain.currentItem
        if (current == next) return
        when(current){
            0 -> binding.tvHome.isEnabled = true
            1 -> binding.tvProject.isEnabled = true
            2 -> binding.tvGzh.isEnabled = true
            3 -> binding.tvSystem.isEnabled = true
            4 -> binding.tvMy.isEnabled = true
        }
        tvText.isEnabled = false
        binding.vpMain.currentItem = next
    }

    override fun setStatusBar() {
        //super.setStatusBar()
        immersionBar {
            //fitsSystemWindows(true)
            transparentStatusBar()
            //statusBarColor(R.color.blue_74D3FF)
            statusBarDarkFont(false,0f)
            init()
        }
    }

    override fun initIsToolbar(): Boolean {
        return false
    }

    override fun initInterface(savedInstanceState: Bundle?) {
        //处理底部切换滑动拖拽相关
        initBottomBar()
        //初始化底部列表
        initBottomList()
        //初始化各个 fragment
        initFragment()
        LiveEventBus.get(EventConstant.search,Boolean::class.java).observe(this){
            if (it){
                binding.fabUp.setImageResource(R.drawable.search_white)
            }else{
                binding.fabUp.setImageResource(R.drawable.knowledge)
            }
            isSearch = it
        }

    }

    private fun initFragment() {
        mFragments.add(HomeFragment())
        mFragments.add(MyFragment())
        mFragments.add(HomeFragment())
        mFragments.add(HomeFragment())
        mFragments.add(HomeFragment())
        binding.vpMain.run {
            isUserInputEnabled = false//设置禁止滑动
            //orientation = ViewPager2.ORIENTATION_HORIZONTAL
            adapter = MainFragmentAdapter(this@MainActivity,mFragments)
            offscreenPageLimit = mFragments.size
        }

    }

    private fun initBottomList() {
        val decoration = IntervalItemDecoration.Builder()
            .dividerVertical(ResUtils.getDimensionPixelOffset(R.dimen.m20))
            .verticalMargin(ResUtils.getDimensionPixelOffset(R.dimen.m20))
            .build()
        binding.rvNavigation.run {
            layoutManager = GridLayoutManager(this@MainActivity,5)
            adapter = mBottomAdapter
            addItemDecoration(decoration)
        }
        mBottomAdapter.setList(HomeDataUtils.getHomeList())
        mBottomAdapter.setOnItemClickListener { adapter, view, position ->
           closeBottom()
            startActivity(mBottomAdapter.data[position].page)
        }

    }

    private fun closeBottom() {
        behavior.state = BottomSheetBehavior.STATE_COLLAPSED
    }

    private fun initBottomBar() {
        //把这个底部菜单和一个BottomSheetBehavior关联起来
        behavior = BottomSheetBehavior.from(binding.llBottom)
        behavior.addBottomSheetCallback(object :BottomSheetBehavior.BottomSheetCallback(){
            override fun onStateChanged(bottomSheet: View, newState: Int) {

            }

            override fun onSlide(bottomSheet: View, slideOffset: Float) {
                binding.vBg.alpha = slideOffset * 0.6f
                binding.llBottom.isSelected = slideOffset > 0
                //LogUtils.e(slideOffset)
                val scale = (1 - slideOffset) * 1f
                binding.fabUp.animate().scaleX(scale).scaleY(scale).start()
                if (slideOffset > 0){
                    binding.vBg.visible()
                }else{
                    binding.vBg.gone()
                }
            }

        })
        binding.vBg.click { closeBottom() }
    }
}


class MainBottomAdapter : BaseQuickAdapter<HomeDataBean,BaseViewHolder>(R.layout.item_main) {
    override fun convert(holder: BaseViewHolder, item: HomeDataBean) {
        holder.setText(R.id.tvMainText,item.title)
            .setImageResource(R.id.ivMainIcon,item.icon)
    }
}

class MainFragmentAdapter(fragmentActivity: FragmentActivity,
                          private val fragments: List<Fragment>): FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount(): Int = fragments.size

    override fun createFragment(position: Int): Fragment = fragments[position]
}




