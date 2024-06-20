package com.phdteam.historyverse.di

import com.phdteam.historyverse.data.network.service.HistoryVerseService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


val NetworkModule = module {
    single {
        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)

        val client = OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl("https://historyproject.somee.com/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()

        retrofit.create(HistoryVerseService::class.java)
    }
}
