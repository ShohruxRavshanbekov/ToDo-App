package uz.futuresoft.network

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import uz.futuresoft.network.interceptors.HeaderInterceptor

internal fun retrofit(): Retrofit {
    return Retrofit.Builder()
        .baseUrl("https://beta.mrdekk.ru/todo")
        .addConverterFactory(gsonConverterFactory())
        .client(okHttpClient())
        .build()
}

internal fun gsonConverterFactory(): GsonConverterFactory {
    return GsonConverterFactory.create()
}

internal fun okHttpClient(): OkHttpClient {
    return OkHttpClient.Builder()
        .addInterceptor(HeaderInterceptor())
        .build()
}