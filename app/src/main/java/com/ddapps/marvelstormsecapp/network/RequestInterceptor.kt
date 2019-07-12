package com.ddapps.marvelstormsecapp.network


import com.ddapps.marvelstormsecapp.util.Constants
import com.ddapps.marvelstormsecapp.util.HashGenerator
import okhttp3.Interceptor
import okhttp3.Response


class RequestInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val timeStamp = System.currentTimeMillis()

        val originalRequest = chain.request()
        val originalHttpUrl = originalRequest.url

        val url = originalHttpUrl.newBuilder().apply {
            addQueryParameter("apikey", Constants.PUBLIC_API_KEY)
            addQueryParameter(
                "hash",
                HashGenerator.generate(timeStamp, Constants.PRIVATE_API_KEY, Constants.PUBLIC_API_KEY)
            )
            addQueryParameter("ts", timeStamp.toString())
        }.build()

        val request = originalRequest.newBuilder().url(url).build()
        return chain.proceed(request)
    }

}


