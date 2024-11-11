package uz.futuresoft.network

import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.HeaderMap
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import uz.futuresoft.network.models.request.NewTodo
import uz.futuresoft.network.models.request.SaveTaskRequest
import uz.futuresoft.network.models.request.SyncWithServerRequest
import uz.futuresoft.network.models.response.GetTaskResponse
import uz.futuresoft.network.models.response.GetTasksResponse
import uz.futuresoft.network.models.response.TodoDTO

interface TodosApi {

    @GET("/list")
    suspend fun getTodos(): GetTasksResponse<List<TodoDTO>>

    @GET("/list/{id}")
    fun getTaskById(
        @Path("id") taskId: String,
    ): GetTaskResponse<TodoDTO>

    @POST("/list")
    fun addTask(
        @HeaderMap header: Map<String, String>, // key = X-Last-Known-Revision
        @Body task: SaveTaskRequest<NewTodo>,
    ): GetTaskResponse<TodoDTO>

    @PATCH("/list")
    suspend fun syncWithServer(
        @HeaderMap header: Map<String, String>,
        @Body tasks: SyncWithServerRequest<List<TodoDTO>>,
    ): GetTasksResponse<List<TodoDTO>>

    @PUT("/list/{id}")
    fun editTask(
        @Path("id") taskId: String,
        @Body editedTask: SaveTaskRequest<NewTodo>,
    ): GetTaskResponse<TodoDTO>
}