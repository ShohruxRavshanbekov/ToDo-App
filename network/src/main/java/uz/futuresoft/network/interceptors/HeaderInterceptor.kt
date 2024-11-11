package uz.futuresoft.network.interceptors

import okhttp3.Interceptor
import okhttp3.Response

internal class HeaderInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
            .addHeader("Authorization", "Bearer token")
            .build()
        return chain.proceed(request)
    }
}