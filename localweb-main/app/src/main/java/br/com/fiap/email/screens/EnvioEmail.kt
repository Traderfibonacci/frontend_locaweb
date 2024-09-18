import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import br.com.fiap.email.retrofit.Email
import kotlinx.coroutines.*
import android.widget.Toast
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import java.time.LocalDateTime
import android.util.Patterns
import android.util.Log

@Composable
fun EnvioEmail(navController: NavController) {
    val context = LocalContext.current
    var recipient by remember { mutableStateOf("") }
    var sender by remember { mutableStateOf("") }
    var subject by remember { mutableStateOf("") }
    var content by remember { mutableStateOf("") }
    var isValidEmail by remember { mutableStateOf(true) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Voltar")
            }
            Text(
                text = "Enviar Email",
                fontSize = 24.sp,
                color = Color.Black,
                modifier = Modifier.padding(start = 8.dp)
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        TextField(
            value = recipient,
            onValueChange = { recipient = it },
            label = { Text("Destinatário") },
            modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp),
            isError = !isValidEmail
        )
        TextField(
            value = sender,
            onValueChange = { sender = it },
            label = { Text("Remetente") },
            modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp),
            isError = !isValidEmail
        )
        TextField(
            value = subject,
            onValueChange = { subject = it },
            label = { Text("Assunto") },
            modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)
        )
        TextField(
            value = content,
            onValueChange = { content = it },
            label = { Text("Conteúdo") },
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .padding(bottom = 16.dp)
        )
        Button(
            onClick = {
                Log.d("EnvioEmail", "Recipient: $recipient, Sender: $sender")
                isValidEmail = Patterns.EMAIL_ADDRESS.matcher(recipient).matches() && Patterns.EMAIL_ADDRESS.matcher(sender).matches()
                if (isValidEmail) {
                    val email = Email(
                        recipient = recipient,
                        sender = sender,
                        subject = subject,
                        content = content,
                        dateTime = LocalDateTime.now().toString(),
                        isRead = false,
                        id = 0L
                    )
                    sendEmail(email, context, navController)
                } else {
                    Toast.makeText(context, "Por favor, insira endereços de email válidos.", Toast.LENGTH_SHORT).show()
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Enviar Email")
        }
    }
}

fun sendEmail(email: Email, context: android.content.Context, navController: NavController) {
    val apiService = RetrofitClient.instance.create(ApiService::class.java)

    CoroutineScope(Dispatchers.IO).launch {
        try {
            apiService.sendEmail(email)
            withContext(Dispatchers.Main) {
                Toast.makeText(context, "Email enviado com sucesso!", Toast.LENGTH_SHORT).show()
                navController.navigate("dashboard") // Navegar para a tela de Dashboard
            }
        } catch (e: Exception) {
            withContext(Dispatchers.Main) {
                Toast.makeText(context, "Erro ao enviar email: ${e.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
