package uz.futuresoft.network

import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import uz.futuresoft.network.models.TodoDTO
import uz.futuresoft.network.models.request.SaveTaskRequest
import uz.futuresoft.network.models.request.SyncWithServerRequest
import uz.futuresoft.network.models.response.GetTaskResponse
import uz.futuresoft.network.models.response.GetTasksResponse

interface TodosApi {

    @GET("list")
    suspend fun getTodos(): GetTasksResponse<List<TodoDTO>>

    @PATCH("list")
    suspend fun syncWithServer(
        @Header("X-Last-Known-Revision") revision: Int,
        @Body tasks: SyncWithServerRequest,
    ): GetTasksResponse<List<TodoDTO>>

    @GET("list/{id}")
    suspend fun getTaskById(
        @Path("id") taskId: String,
    ): GetTaskResponse<TodoDTO>

    @POST("list")
    suspend fun createTask(
        @Header("X-Last-Known-Revision") revision: Int,
        @Body task: SaveTaskRequest,
    ): GetTaskResponse<TodoDTO>

    @PUT("list/{id}")
    suspend fun updateTask(
        @Header("X-Last-Known-Revision") revision: Int,
        @Path("id") taskId: String,
        @Body taskToUpdate: SaveTaskRequest,
    ): GetTaskResponse<TodoDTO>

    @DELETE("list/{id}")
    suspend fun deleteTask(
        @Header("X-Last-Known-Revision") revision: Int,
        @Path("id") taskId: String,
    ): GetTaskResponse<TodoDTO>
}