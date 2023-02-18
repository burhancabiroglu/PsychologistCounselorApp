package com.danisbana.danisbanaapp.domain.service

import com.danisbana.danisbanaapp.core.model.login.LoginRequest
import com.danisbana.danisbanaapp.core.model.register.RegisterRequest
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

interface FirebaseAuthService {
    val auth: FirebaseAuth get() = Firebase.auth
    suspend fun register(request: RegisterRequest): Result<AuthResult>
    suspend fun login(loginRequest: LoginRequest): Result<AuthResult>
}