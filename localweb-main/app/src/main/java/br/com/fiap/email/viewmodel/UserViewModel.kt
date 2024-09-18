package br.com.fiap.email.viewmodel

import UserAccountExhibitDto
import UserAccountRegisterDto
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.fiap.email.service.UserAccountService
import kotlinx.coroutines.launch

class UserViewModel : ViewModel() {
    private val userService = UserAccountService()

    fun registerUser(user: UserAccountRegisterDto, onResult: (UserAccountExhibitDto?) -> Unit) {
        viewModelScope.launch {
            println("Registering user: $user")
            val result = userService.registerUser(user)
            println("Result: $result")
            onResult(result)
        }
    }


    fun getUserById(id: Long, onResult: (UserAccountExhibitDto?) -> Unit) {
        viewModelScope.launch {
            val result = userService.getUserById(id)
            onResult(result)
        }
    }
}
