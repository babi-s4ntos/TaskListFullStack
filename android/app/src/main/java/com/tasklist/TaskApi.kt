package com.tasklist

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface TaskApi {
    @GET("api/tasks")
    suspend fun getTasks(): List<Task>

    @POST("api/tasks")
    suspend fun createTask(@Body task: Task): Task
}

object RetrofitInstance {
    private const val BASE_URL = "http://10.0.2.2:8080/"

    val api: TaskApi by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(TaskApi::class.java)
    }
}
