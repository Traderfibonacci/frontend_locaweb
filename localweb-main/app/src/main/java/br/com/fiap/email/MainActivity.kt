package br.com.fiap.email

import Dashboard
import EnvioEmail
import LeituraEmail
import Theme
import UserViewModel
import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.lifecycle.viewmodel.compose.viewModel
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
            val userViewModel: UserViewModel = viewModel()
            val context = LocalContext.current

            // Retrieve stored theme
            val storedTheme = getStoredTheme(context)
            userViewModel.currentTheme = storedTheme

            // Observe theme changes
            EmailTheme(
                darkTheme = userViewModel.currentTheme == Theme.DARK
            ) {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = if (userViewModel.currentTheme == Theme.DARK) Color.Gray else Color.White
                ) {
                    val navController = rememberNavController()
                    NavHost(navController = navController, startDestination = "login") {
                        composable(route = "login") {
                            Login(navController, userViewModel)
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

    private fun getStoredTheme(context: Context): Theme {

        return Theme.DARK
    }
}
