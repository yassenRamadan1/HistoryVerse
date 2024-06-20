package com.phdteam.historyverse.data.network.repositories

import com.google.ai.client.generativeai.Chat
import com.phdteam.historyverse.data.entity.Mentor
import com.phdteam.historyverse.data.entity.MuseumType
import com.phdteam.historyverse.data.entity.University
import com.phdteam.historyverse.data.network.model.Advertisement
import com.phdteam.historyverse.data.network.model.Artifact
import com.phdteam.historyverse.data.network.model.Museum

interface HistoryVerseRepository {
    //region Mentor
    suspend fun getMentors(): List<Mentor>

    //endregion

    //region Subject
    suspend fun getMuseumsTypes(): List<MuseumType>

    //endregion

    //region Universities
    suspend fun getUniversities(): List<University>

    //endregion
    fun generateContent(userContent: String, modelContent: String): Chat
    //region museum
    suspend fun getMuseums(): List<Museum>
    suspend fun getMuseumById(id: Int): Museum
    //endregion
    suspend fun getArtifacts(): List<Artifact>
    suspend fun getArtifactById(id: Int): Artifact

    //region Advertisement
    fun getAdvertisement(): List<Advertisement>
    //endregion

}