package com.phdteam.historyverse.di

import com.google.firebase.storage.FirebaseStorage
import com.phdteam.historyverse.data.network.service.ChatBotService
import com.phdteam.historyverse.data.network.service.HistoryVerseService
import com.phdteam.historyverse.data.network.service.TripService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


val NetworkModule = module {
    single(named("historyRetrofit")) { provideRetrofit(BASE_URL_HISTORY) }
    single(named("chatBotRetrofit")) { provideRetrofit(BASE_URL_CHATBOT) }

    single { get<Retrofit>(named("historyRetrofit")).create(HistoryVerseService::class.java) }
    single { get<Retrofit>(named("chatBotRetrofit")).create(ChatBotService::class.java) }

    single { FirebaseStorage.getInstance() }
    single {
        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)

        val client = OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl("https://trip-planner-flask.onrender.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()

        retrofit.create(TripService::class.java)
    }
}

private fun provideRetrofit(baseUrl: String): Retrofit {
    val logging = HttpLoggingInterceptor()
    logging.setLevel(HttpLoggingInterceptor.Level.BODY)

    val client = OkHttpClient.Builder()
        .addInterceptor(logging)
        .build()

    return Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()
}

const val BASE_URL_HISTORY = "https://historyproject.somee.com/api/"
const val BASE_URL_CHATBOT = "https://6a0b-197-48-223-160.ngrok-free.app"