package com.noban.simpleAPi.core.api

import android.content.Context


import com.noban.simpleAPi.BuildConfig
import com.noban.simpleAPi.R
import com.franmontiel.persistentcookiejar.PersistentCookieJar
import com.franmontiel.persistentcookiejar.cache.SetCookieCache
import com.franmontiel.persistentcookiejar.persistence.SharedPrefsCookiePersistor
import com.google.gson.GsonBuilder
import com.ihsanbal.logging.Level
import com.ihsanbal.logging.LoggingInterceptor

import java.io.File
import java.net.CookieManager
import java.net.CookiePolicy
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.internal.platform.Platform
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


/**
 * Created by Noban on 30/1/20.
 * Audacity It Solution.
 */
object RetrofitAPIFactory {
    private val cacheSize = (100 * 1024 * 1024).toLong()

    private var retrofit: Retrofit? = null
    private var okHttpClient: OkHttpClient? = null
    private var cache: Cache? = null

    var gson = GsonBuilder()
        .setLenient()
        .create()

    internal fun createService(context: Context, timeOut: Int): APIService {
        return getRetrofit(context, timeOut).create(APIService::class.java)
    }

    private fun getRetrofit(context: Context, timeOut: Int): Retrofit {
        if (retrofit == null) {
            retrofit = Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .client(getOkHttpClient(context, timeOut))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
        }

        return retrofit!!
    }

    private fun getOkHttpClient(context: Context, timeOut: Int): OkHttpClient {
        if (okHttpClient == null) {
            val okHttpClientBuilder = OkHttpClient.Builder()
            okHttpClientBuilder.connectTimeout(timeOut.toLong(), TimeUnit.SECONDS)
            okHttpClientBuilder.readTimeout(timeOut.toLong(), TimeUnit.SECONDS)
            okHttpClientBuilder.writeTimeout(timeOut.toLong(), TimeUnit.SECONDS)

            okHttpClientBuilder.cache(getCache(context))

            val cookieManager = CookieManager()
            cookieManager.setCookiePolicy(CookiePolicy.ACCEPT_ALL)

            okHttpClientBuilder.cookieJar(
                PersistentCookieJar(
                    SetCookieCache(),
                    SharedPrefsCookiePersistor(context)
                )
            )

            okHttpClientBuilder.addNetworkInterceptor(ResponseCacheInterceptor())
            okHttpClientBuilder.addInterceptor(OfflineResponseCacheInterceptor())

            okHttpClientBuilder.addInterceptor { chain -> chain.proceed(chain.request().newBuilder().build()) }

            val loggingInterceptor = LoggingInterceptor.Builder()
                .loggable(BuildConfig.DEBUG)
                .setLevel(Level.BASIC)
                .log(Platform.INFO)
                .tag(context.getString(R.string.app_name))
                .request("Request")
                .response("Response")
                .executor(Executors.newSingleThreadExecutor())
                .build()

            okHttpClientBuilder.addInterceptor(loggingInterceptor)

            okHttpClient = okHttpClientBuilder.build()
        }

        return okHttpClient!!
    }

    fun getCache(context: Context): Cache {
        if (cache == null) {
            val cacheDir = File(context.cacheDir, "okhttp-cache")
            cache = Cache(cacheDir, cacheSize)
        }
        return cache!!
    }

}
