package com.phdteam.historyverse.data.network.repositories

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.toObject
import com.phdteam.historyverse.data.network.model.User
import kotlinx.coroutines.tasks.await

class UserRepositoryImpl : UserRepository {
    private val firestore = FirebaseFirestore.getInstance()

    override suspend fun getUSerData(id: String): User {
        val ref = firestore.collection("users").whereEqualTo("id", id).get().await()
        return ref.documents.first().toObject<User>()!!
    }
}