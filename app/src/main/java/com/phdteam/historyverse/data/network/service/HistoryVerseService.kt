package com.phdteam.historyverse.data.network.service

import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.GET

interface HistoryVerseService {


    @GET("login/")
    suspend fun getLogin(
        @Field("phone_number") phoneNumber: String?,
        @Field("password") password: String?,
    ): Response<Any>

}