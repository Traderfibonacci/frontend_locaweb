import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.fiap.email.service.UserAccountService
import kotlinx.coroutines.launch

class UserViewModel : ViewModel() {
    private val userService = UserAccountService()
    var currentTheme by mutableStateOf(Theme.LIGHT) // Adicione isso para gerenciar o tema atual

    fun registerUser(user: UserAccountRegisterDto, onResult: (UserAccountExhibitDto?) -> Unit) {
        viewModelScope.launch {
            println("Registering user: $user")
            val result = userService.registerUser(user)
            println("Result: $result")
            currentTheme = user.preferences?.theme ?: Theme.LIGHT // Atualize o tema com base nas preferências do usuário
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
