package br.com.fiap.email.service

import UserAccountApi
import UserAccountExhibitDto
import UserAccountRegisterDto
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserAccountService {
    private val api = RetrofitClient.instance.create(UserAccountApi::class.java)

    suspend fun registerUser(user: UserAccountRegisterDto): UserAccountExhibitDto? {
        return try {
            val response = api.registerUser(user)
            println("API Response: $response")
            response
        } catch (e: Exception) {
            println("Error: ${e.message}")
            e.printStackTrace()
            null
        }
    }


    suspend fun getUserById(id: Long): UserAccountExhibitDto? {
        return try {
            api.getUserById(id)
        } catch (e: Exception) {
            null
        }
    }
}
