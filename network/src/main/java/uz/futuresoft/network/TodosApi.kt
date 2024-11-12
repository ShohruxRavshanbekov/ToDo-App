package uz.futuresoft.network

import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.HeaderMap
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import uz.futuresoft.network.models.request.SaveTaskRequest
import uz.futuresoft.network.models.request.SyncWithServerRequest
import uz.futuresoft.network.models.response.GetTaskResponse
import uz.futuresoft.network.models.response.GetTasksResponse
import uz.futuresoft.network.models.TodoDTO

interface TodosApi {

    @GET("/list")
    suspend fun getTodos(): GetTasksResponse<List<TodoDTO>>

    @PATCH("/list")
    suspend fun syncWithServer(
        @HeaderMap header: Map<String, Int>,
        @Body tasks: SyncWithServerRequest,
    ): GetTasksResponse<List<TodoDTO>>

    @GET("/list/{id}")
    suspend fun getTaskById(
        @Path("id") taskId: String,
    ): GetTaskResponse<TodoDTO>

    @POST("/list")
    fun createTask(
        @HeaderMap header: Map<String, Int>,
        @Body task: SaveTaskRequest,
    ): GetTaskResponse<TodoDTO>

    @PUT("/list/{id}")
    fun updateTask(
        @Path("id") taskId: String,
        @Body editedTask: SaveTaskRequest,
    ): GetTaskResponse<TodoDTO>

    @DELETE("/list/{id}")
    fun deleteTask(
        @Path("id") taskId: String,
    ): GetTaskResponse<TodoDTO>
}