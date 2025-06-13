package com.example.quickquiz.data

import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface QuizApiService {
    @GET("cases")
    suspend fun startSession(): Response<ResponseBody>

    @POST("generate_cases")
    suspend fun getQuestions(@Body body: RequestBody): Response<QuizResponse>
}


