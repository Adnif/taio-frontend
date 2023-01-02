package com.taio.taio.data.api


import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApi {
    @POST("/api/login")
    fun login(@Body loginCredentials: LoginCredentials): Call<LoginResponse>

    //@POST("api/register")
    //fun register(@Body registerCredentials: RegisterCredentials) : Call<AccessToken>
}

data class LoginCredentials(val email: String, val password: String)
data class LoginResponse(val accessToken: String)

