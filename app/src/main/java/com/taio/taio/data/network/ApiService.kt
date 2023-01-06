package com.taio.taio.data.network

import com.taio.taio.data.model.LoginRequest
import com.taio.taio.data.model.RegisterRequest
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @POST("login")
    suspend fun login(@Body loginRequest: LoginRequest): Call<ResponseBody>

    @POST("register")
    suspend fun register(@Body registerRequest: RegisterRequest): Call<ResponseBody>

}