package com.taio.taio.data.repository

import com.taio.taio.data.model.LoginRequest
import com.taio.taio.data.model.RegisterRequest
import com.taio.taio.data.network.ApiService
import com.taio.taio.domain.repository.ApiRepo
import retrofit2.Call
import okhttp3.ResponseBody
import javax.inject.Inject

class ApiRepoImpl @Inject constructor(private val apiService: ApiService) : ApiRepo {
    override suspend fun login(email: String, password: String): Call<ResponseBody> {
        val loginRequest = LoginRequest(email, password)
        return apiService.login(loginRequest)
    }

    suspend fun register(name: String, email: String, password: String): Call<ResponseBody> {
        val registrationRequest = RegisterRequest(name, email, password)
        return apiService.register(registrationRequest)
    }


}