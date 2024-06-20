package com.phdteam.historyverse.data.network.repositories

import com.google.ai.client.generativeai.Chat
import com.phdteam.historyverse.data.entity.Mentor
import com.phdteam.historyverse.data.entity.MuseumsTypes
import com.phdteam.historyverse.data.entity.University
import com.phdteam.historyverse.data.network.model.Artifact

interface HistoryVerseRepository {
    //region Mentor
    suspend fun getMentors(): List<Mentor>

    //endregion


    //region Subject
    suspend fun getMuseumsTypes(): List<MuseumsTypes>

    //endregion


    //region Universities
    suspend fun getUniversities(): List<University>

    //endregion
    fun generateContent(userContent: String, modelContent: String): Chat
    suspend fun getArtifacts(): List<Artifact>
}