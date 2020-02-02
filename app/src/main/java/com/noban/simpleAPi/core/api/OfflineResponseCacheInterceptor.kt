package com.noban.simpleAPi.core.api


import com.noban.simpleAPi.core.App

import java.io.IOException
import okhttp3.Interceptor
import okhttp3.Response

/**
 * Interceptor to cache data and maintain it for four weeks.
 * If the device is offline, stale (at most four weeks old)
 * response is fetched from the cache.
 */

class OfflineResponseCacheInterceptor : Interceptor {
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        if (java.lang.Boolean.valueOf(request.header("ApplyOfflineCache"))) {
            if (!App.app.isNetworkAvailable) {
                request = request.newBuilder()
                    .removeHeader("ApplyOfflineCache")
                    .header("Cache-Control", "public, only-if-cached, max-stale=" + 2419200)
                    .build()
            }
        }
        return chain.proceed(request)
    }
}
