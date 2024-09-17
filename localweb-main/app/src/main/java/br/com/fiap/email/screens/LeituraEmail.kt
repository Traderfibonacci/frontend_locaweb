import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import br.com.fiap.email.retrofit.Email
import kotlinx.coroutines.launch

@Composable
fun LeituraEmail(navController: NavController, emailId: Long) {
    var email by remember { mutableStateOf<Email?>(null) }
    var isLoading by remember { mutableStateOf(true) }
    var errorMessage by remember { mutableStateOf<String?>(null) }
    val api = RetrofitClient.instance.create(ApiService::class.java)
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(emailId) {
        try {
            Log.d("LeituraEmail", "Iniciando chamada de rede para o ID: $emailId")
            val response = api.getEmail(emailId)
            email = response
            Log.d("LeituraEmail", "Email recebido: $email")
        } catch (e: Exception) {
            errorMessage = e.message
            Log.e("LeituraEmail", "Erro ao buscar email", e)
        } finally {
            isLoading = false
        }
    }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Button(onClick = { navController.navigate("dashboard") }) {
            Text(text = "Voltar")
        }

        Spacer(modifier = Modifier.height(16.dp))

        if (isLoading) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        } else if (errorMessage != null) {
            Text(text = "Erro: $errorMessage", color = MaterialTheme.colorScheme.error)
        } else {
            email?.let {
                Column(modifier = Modifier.fillMaxWidth()) {
                    Text(text = "De:", style = MaterialTheme.typography.titleMedium)
                    Text(text = it.sender, style = MaterialTheme.typography.bodyMedium)
                    Divider(modifier = Modifier.padding(vertical = 8.dp))

                    Text(text = "Para:", style = MaterialTheme.typography.titleMedium)
                    Text(text = it.recipient, style = MaterialTheme.typography.bodyMedium)
                    Divider(modifier = Modifier.padding(vertical = 8.dp))

                    Text(text = "Assunto:", style = MaterialTheme.typography.titleMedium)
                    Text(text = it.subject, style = MaterialTheme.typography.bodyMedium)
                    Divider(modifier = Modifier.padding(vertical = 8.dp))

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color.LightGray, RoundedCornerShape(7.dp))
                            .padding(16.dp)
                    ) {
                        Text(text = it.content, style = MaterialTheme.typography.bodyLarge)
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    Button(
                        onClick = {
                            coroutineScope.launch {
                                try {
                                    Log.d("LeituraEmail", "Tentando deletar email com ID: $emailId")
                                    val response = api.deleteEmail(emailId)
                                    if (response.isSuccessful) {
                                        Log.d("LeituraEmail", "Email deletado com sucesso")
                                        navController.navigate("dashboard")
                                    } else {
                                        errorMessage = "Erro ao deletar email: ${response.errorBody()?.string()}"
                                        Log.e("LeituraEmail", errorMessage ?: "Erro desconhecido")
                                    }
                                } catch (e: Exception) {
                                    errorMessage = e.message
                                    Log.e("LeituraEmail", "Erro ao deletar email", e)
                                }
                            }
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = Color.Red)
                    ) {
                        Text(text = "Deletar", color = Color.White)
                    }
                }
            }
        }
    }
}
