package com.phdteam.historyverse.data.network.service

import com.phdteam.historyverse.data.network.model.Artifact
import com.phdteam.historyverse.data.network.model.Museum
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.GET
import retrofit2.http.Path

interface HistoryVerseService {

    @GET("Artifacts/")
    suspend fun getArtifacts(
    ): Response<List<Artifact>>
    @GET("Museums/")
    suspend fun getMuseums(
    ): Response<List<Museum>>
    @GET("Museums/{id}")
    suspend fun getMuseumById(
        @Path("id") id: Int
    ): Response<Museum>
    @GET("Artifacts/{id}")
    suspend fun getArtifactById(
        @Path("id") id: Int
    ): Response<Artifact>

}