package com.kiwilss.wanandroid.http

import com.franmontiel.persistentcookiejar.PersistentCookieJar
import com.franmontiel.persistentcookiejar.cache.SetCookieCache
import com.franmontiel.persistentcookiejar.persistence.SharedPrefsCookiePersistor
import com.kiwilss.wanandroid.app.MyApp
import com.kiwilss.wanandroid.config.HttpConstant
import com.kiwilss.wanandroid.http.interceptor.CacheInterceptor
import com.kiwilss.wanandroid.http.interceptor.HttpLogInterceptor
import com.kiwilss.wanandroid.ktx.okhttp.OkExt
import com.kiwilss.wanandroid.ktx.okhttp.cookie.PersistentCookieStore
import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit

/**
 *@FileName: RetrofitClient
 *@author : Lss kiwilss
 * @e-mail : kiwilss@163.com
 * @time   : 2021/2/23
 * @desc   : {DESCRIPTION}
 */
object RetrofitClient {

    //请求的地址
    val BASE_URL = "https://wanandroid.com/"

    //retrofit对象
    private var retrofit: Retrofit? = null

    //请求的api，可以根据不同的场景设置多个
    val service: ApiService by lazy {
        getRetrofit().create(ApiService::class.java)
    }

    private fun getRetrofit(): Retrofit {
        if (retrofit == null) {
            val okHttpClient = getOkHttpClient()
            retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(MoshiConverterFactory.create())
//                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .build()
            //OkExt.setClient(okHttpClient)
        }
        return retrofit!!
    }



    /**
     * 获取 OkHttpClient
     */
    private fun getOkHttpClient(): OkHttpClient {
        val builder = OkHttpClient().newBuilder()

        val httpLogInterceptor = HttpLogInterceptor()

        //设置 请求的缓存的大小跟位置
        val cacheFile = File(MyApp.instance.cacheDir, "cache")
        val cache = Cache(cacheFile, HttpConstant.MAX_CACHE_SIZE)

        // Cookie 持久化
        val cookieJar = PersistentCookieJar(
            SetCookieCache(),
            SharedPrefsCookiePersistor(MyApp.context)
        )

        builder.run {
            addInterceptor(httpLogInterceptor)
//            addInterceptor(HeaderInterceptor())
//            addInterceptor(SaveCookieInterceptor())
            addInterceptor(CacheInterceptor())
            cache(cache)  //添加缓存
            .cookieJar(cookieJar)
            connectTimeout(HttpConstant.DEFAULT_TIMEOUT, TimeUnit.SECONDS)
            readTimeout(HttpConstant.DEFAULT_TIMEOUT, TimeUnit.SECONDS)
            writeTimeout(HttpConstant.DEFAULT_TIMEOUT, TimeUnit.SECONDS)
            retryOnConnectionFailure(true) // 错误重连
            // cookieJar(CookieManager())
        }
        return builder.build()
    }

}