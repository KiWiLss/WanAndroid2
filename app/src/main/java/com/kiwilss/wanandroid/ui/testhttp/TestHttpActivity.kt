package com.kiwilss.wanandroid.ui.testhttp

import android.os.Bundle
import android.widget.Toast
import com.alibaba.android.arouter.facade.annotation.Route
import com.coder.zzq.smartshow.toast.SmartToast
import com.kiwilss.wanandroid.base.BaseVMActivity
import com.kiwilss.wanandroid.config.ArouterPage
import com.kiwilss.wanandroid.databinding.ActivityTestHttpBinding
import com.kiwilss.wanandroid.http.RetrofitClient
import com.kiwilss.wanandroid.ktx.core.click
import com.kiwilss.wanandroid.ktx.core.postDelay
import com.kiwilss.wanandroid.ktx.okhttp.get
import com.kiwilss.wanandroid.ktx.okhttp.http
import com.kiwilss.wanandroid.ktx.toast
import com.kiwilss.wanandroid.ktx.toastS
import com.lxj.xpopup.XPopup
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 *@FileName: TestHttpActivity
 *@author : Lss kiwilss
 * @e-mail : kiwilss@163.com
 * @time   : 2021/2/24
 * @desc   : {DESCRIPTION}
 */
@Route(path = ArouterPage.TEST_HTTP)
class TestHttpActivity: BaseVMActivity<ActivityTestHttpBinding,TestHttpViewModel>() {
    override fun providerVMClass(): Class<TestHttpViewModel> = TestHttpViewModel::class.java

    override fun initData() {
    }

    override fun initEvent() {
    }

    override fun initInterface(savedInstanceState: Bundle?) {

        binding.btnOkhttp.click {
            okhttpTest()
        }
        binding.btnNormal.click {
            viewModel.getHomeBanner().observe(this){
                //binding.tvShow.text = it.toString()
                it?.run {
                    binding.tvShow.text = it[0].desc
                }
            }
        }
        binding.btnLiveData.click {
            viewModel.launchUI {
                RetrofitClient.service.getHomeBanner2().observe(this@TestHttpActivity){
                    binding.tvShow.text = it.toString()
                }
            }

        }
        binding.btnLogin.click {
           XPopup.Builder(this)
               .asConfirm("hello","测试"){

               }.show()
//            viewModel.login("12","34").observe(this){
//                dismissLoadingDiloag()
//                binding.tvShow.text = it?.toString()
//            }
            //toast("hello")
//            Toast.makeText(this,"test",Toast.LENGTH_SHORT).show()
        }

    }

    private fun okhttpTest() {
        viewModel.launchUI {
            val homeBanner = withContext(Dispatchers.IO){
                "https://www.wanandroid.com/banner/json".http().get<BannerAll>().await()
            }
           binding.tvShow.text = homeBanner.toString()
        }

    }
}