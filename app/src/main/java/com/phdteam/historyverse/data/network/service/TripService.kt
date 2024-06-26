package com.phdteam.historyverse.data.network.service

import com.phdteam.historyverse.data.network.model.Trip
import com.phdteam.historyverse.data.network.model.TripChoice
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface TripService {
    @POST("/plan_trip")
    suspend fun recommendTrip(@Body request: TripChoice): Response<Trip>
}