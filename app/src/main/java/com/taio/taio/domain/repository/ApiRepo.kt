package com.taio.taio.domain.repository

import okhttp3.ResponseBody
import retrofit2.Call

interface ApiRepo {
    suspend fun login(email: String, password: String): Call<ResponseBody>
    suspend fun register(name: String, email: String, password: String): Call<ResponseBody>
}