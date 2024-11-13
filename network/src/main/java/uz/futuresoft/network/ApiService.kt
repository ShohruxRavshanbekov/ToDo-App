package uz.futuresoft.network

import android.content.Context

object ApiService {
    fun todosApi(context: Context): TodosApi {
        return RetrofitClient.retrofit(context = context).create(TodosApi::class.java)
    }
}
