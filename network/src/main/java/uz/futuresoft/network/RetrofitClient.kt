package uz.futuresoft.network

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import uz.futuresoft.network.interceptors.HeaderInterceptor

internal object RetrofitClient {

    fun retrofit(context: Context): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(gsonConverterFactory())
            .client(okHttpClient(context = context))
            .build()
    }

    private fun gsonConverterFactory(): GsonConverterFactory {
        return GsonConverterFactory.create()
    }

    private fun okHttpClient(context: Context): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(HeaderInterceptor())
            .addInterceptor(ChuckerInterceptor(context))
            .build()
    }
}