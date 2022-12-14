package com.taio.taio.data.repository

import android.content.SharedPreferences
import com.taio.taio.data.model.ErrorResponse
import com.taio.taio.data.model.LoginRequest
import com.taio.taio.data.model.RegisterRequest
import com.taio.taio.data.network.ApiErrorException
import com.taio.taio.data.network.AuthApiService
import com.taio.taio.data.utils.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.ResponseBody
import retrofit2.Converter
import retrofit2.HttpException
import retrofit2.Retrofit
import java.io.IOException

class AuthRepositoryImpl(
    private val retrofit: Retrofit,
    private val service: AuthApiService,
    private val prefs: SharedPreferences
) : AuthRepository {
    override suspend fun login(
        email: String,
        password: String
    ): Result<out Any> {
        return withContext(Dispatchers.IO){
            try{
                val response = service.login(
                    request = LoginRequest(
                        email,
                        password
                    )
                )
                if (response.isSuccessful){
                    prefs.edit()
                        .putString("token_type", response.body()?.tokenType)
                        .putString("access_token", response.body()?.accessToken)
                        .putString("expires_in", response.body()?.expiresIn)
                        .putString("refresh_token", response.body()?.refreshToken)
                        .apply()

                    Result.Authorized(
                        data = getCurrentAuthUser()
                    )

                } else{
                    val errors = convertErrorBody(
                        response.errorBody()
                    )

                    Result.ApiError(
                        data = errors
                    )
                }
            } catch (e: HttpException){
                Result.Unathorized()
            } catch (e: IOException){
                Result.ApiError(
                    data = ErrorResponse(
                        message = "Please connect to the internet"
                    )
                )
            } catch (e: java.lang.Exception){
                Result.Unathorized()
            }
        }
    }

    private fun convertErrorBody(
        errorBody: ResponseBody?
    ): ErrorResponse?{
        val converter: Converter<ResponseBody, ErrorResponse> =
            retrofit.responseBodyConverter(ErrorResponse::class.java, arrayOfNulls<Annotation>(0))

        var apiError: ErrorResponse? = null

        try{
            apiError = converter.convert(errorBody)
        } catch (e: ApiErrorException){
            e.printStackTrace()
        }

        return apiError
    }

    override suspend fun register(
        name: String,
        email: String,
        username: String,
        password: String,
        password_confirmation: String
    ): Result<out Any> {
        return withContext(Dispatchers.IO){
            try{
                val response = service.register(
                    request = RegisterRequest(
                        name,
                        email,
                        username,
                        password,
                        password_confirmation
                    )
                )
                if (response.isSuccessful){
                    prefs.edit()
                        .putString("token_type", response.body()?.tokenType)
                        .putString("access_token", response.body()?.accessToken)
                        .putString("expires_in", response.body()?.expiresIn)
                        .putString("refresh_token", response.body()?.refreshToken)
                        .apply()

                    Result.Authorized(
                        data = getCurrentAuthUser()
                    )

                } else{
                    val errors = convertErrorBody(
                        response.errorBody()
                    )

                    Result.ApiError(
                        data = errors
                    )
                }
            } catch (e: HttpException){
                Result.Unathorized()
            } catch (e: IOException){
                Result.ApiError(
                    data = ErrorResponse(
                        message = "Please connect to the internet"
                    )
                )
            } catch (e: Exception){
                Result.Unathorized()
            }
        }
    }

    override suspend fun getCurrentAuthUser(): Result<out Any> {
        return withContext(Dispatchers.IO){
            try{
                val token = prefs.getString("access_token", null)
                val response = service.users("bearer $token")

                if(response.isSuccessful){
                    Result.Authorized(
                        data = response.body()
                    )
                } else {
                    val errors = convertErrorBody(
                        response.errorBody()
                    )
                    Result.ApiError(
                        data = errors
                    )
                }
            } catch (e: HttpException){
                Result.Unathorized()
            } catch (e: IOException){
                Result.ApiError(
                    data = ErrorResponse(
                        message = "Please Connect to Internet"
                    )
                )
            } catch (e: Exception){
                Result.Unathorized()
            }
        }
    }

    override suspend fun logout(): Result<out Any> {
        return withContext(Dispatchers.IO){
            try{
                val token = prefs.getString("access_token", null)

                val response = service.logout("Bearer Token")

                if(response.isSuccessful){
                    prefs.edit()
                        .putString("token_type",null)
                        .putString("access_token",null)
                        .putString("expires_in",null)
                        .putString("refresh_token",null)
                        .apply()

                    Result.Unathorized()
                } else {
                    Result.Authorized()
                }

            } catch (e: HttpException){
                Result.Unathorized()
            } catch (e: IOException){
                Result.ApiError(
                    data = ErrorResponse(
                        message = "Please connect to the internet"
                    )
                )
            } catch (e: Exception){
                Result.Unathorized()
            }
        }
    }
}