package uz.futuresoft.network

fun todosApi(): TodosApi {
    return retrofit().create(TodosApi::class.java)
}
