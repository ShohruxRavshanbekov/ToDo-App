package uz.futuresoft.network

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import uz.futuresoft.network.interceptors.HeaderInterceptor

internal object RetrofitClient {

    fun retrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(gsonConverterFactory())
            .client(okHttpClient())
            .build()
    }

    private fun gsonConverterFactory(): GsonConverterFactory {
        return GsonConverterFactory.create()
    }

    private fun okHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(HeaderInterceptor())
            .build()
    }
}