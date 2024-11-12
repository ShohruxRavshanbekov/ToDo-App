package uz.futuresoft.network

object ApiService {
    val todosApi: TodosApi = RetrofitClient.retrofit().create(TodosApi::class.java)
}
