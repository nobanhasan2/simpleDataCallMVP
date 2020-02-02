package com.noban.simpleAPi.core.api

/**
 * Created by Rafiqul Hasan Rony on 2/3/19.
 * Audacity It Solution.
 */


import com.noban.simpleAPi.core.App

import java.io.IOException

import okhttp3.Interceptor
import okhttp3.Response

/**
 * Interceptor to cache data and maintain it for a minute.
 *
 *
 * If the same network request is sent within a minute,
 * the response is retrieved from cache.
 */

class ResponseCacheInterceptor : Interceptor {
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        if (java.lang.Boolean.valueOf(request.header("ApplyResponseCache"))) {
            val originalResponse = chain.proceed(request)
            return originalResponse.newBuilder()
                .removeHeader("ApplyResponseCache")
                .header(
                    "Cache-Control",
                    if (App.app.isNetworkAvailable) "public, max-age=" + 60 else "public, only-if-cached, max-stale=" + 2419200
                )
                .build()
        } else {
            return chain.proceed(request)
        }
    }
}
