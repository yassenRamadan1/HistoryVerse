package com.phdteam.historyverse.data.network.repositories

import com.google.ai.client.generativeai.Chat
import com.phdteam.historyverse.data.entity.Mentor
import com.phdteam.historyverse.data.entity.Subject
import com.phdteam.historyverse.data.entity.University

interface HistoryVerseRepository {
    //region Mentor
    suspend fun getMentors(): List<Mentor>

    //endregion


    //region Subject
    suspend fun getSubject(): List<Subject>

    //endregion


    //region Universities
    suspend fun getUniversities(): List<University>

    //endregion
    fun generateContent(userContent: String, modelContent: String): Chat
}