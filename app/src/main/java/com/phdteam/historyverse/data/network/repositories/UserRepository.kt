package com.phdteam.historyverse.data.network.repositories

import com.phdteam.historyverse.data.network.model.User

interface UserRepository {
    suspend fun getUSerData(id: String): User

}