package com.phdteam.historyverse.data.network.repositories

import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseUser
import com.phdteam.historyverse.data.network.model.User

interface AuthRepository {
    suspend fun signUp(email: String, password: String): AuthResult

    suspend fun addUserInfo(userInfo: User, user: FirebaseUser): Boolean

    suspend fun signIn(email: String, password: String): AuthResult
    suspend fun checkSignInState(): Boolean
    suspend fun setSignInState(signInState: Boolean)
    suspend fun signOut()
    suspend fun getUserToken(): String
    suspend fun setUserToken(token:String)
}