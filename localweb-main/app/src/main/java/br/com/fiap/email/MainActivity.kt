package br.com.fiap.email

import Dashboard
import EnvioEmail
import LeituraEmail
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import br.com.fiap.email.screens.Cadastro
import br.com.fiap.email.screens.Calendario
import br.com.fiap.email.screens.Login
import br.com.fiap.email.ui.theme.EmailTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EmailTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color.White
                ) {
                    val navController = rememberNavController()
                    NavHost(navController = navController, startDestination = "login") {
                        composable(route = "login") {
                            Login(navController)
                        }
                        composable(route = "cadastro") {
                            Cadastro(navController)
                        }
                        composable(route = "leituraEmail/{emailId}") { backStackEntry ->
                            val emailId = backStackEntry.arguments?.getString("emailId")?.toLong() ?: 0L
                            LeituraEmail(navController = navController, emailId = emailId)
                        }
                        composable(route = "calendario") {
                            Calendario(navController)
                        }
                        composable(route = "dashboard") {
                            Dashboard(navController)
                        }
                        composable(route = "envioEmail") {
                            EnvioEmail(navController)
                        }

                        }
                    }
                }
            }
        }
    }

