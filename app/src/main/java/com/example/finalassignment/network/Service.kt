package com.example.finalassignment.network

import com.example.finalassignment.datas.KeyPass
import com.example.finalassignment.datas.UsernamePassword
import retrofit2.http.Body
import retrofit2.http.POST

interface Service {

    @POST("footscray/auth")
    suspend fun login(@Body request: UsernamePassword): KeyPass

}


