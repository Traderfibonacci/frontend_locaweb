package br.com.fiap.email.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import br.com.fiap.email.R

@Composable
fun Login(navController: NavController) {
    var email by remember() {
        mutableStateOf("")
    }

    var password by remember {
        mutableStateOf("")
    }

    var erroEmail by remember {
        mutableStateOf(false)
    }

    val sizeMax = 15


    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
    ){
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(30.dp, 28.dp)
        ) {
            Row (
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
//                    .background(colorResource(id = R.color.purple_200))
            ){
                Spacer(modifier = Modifier.height(16.dp))

                Image(
                    painter = painterResource(id = R.drawable.icon),
                    contentDescription = "icon",
                    modifier = Modifier.size(40.dp)
                )

                Spacer(modifier = Modifier.width(11.dp))
                Text(
                    text = "LocalWeb email",
                    color = colorResource(id = R.color.black),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(30.dp, 10.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ){
                Image(
                    painter = painterResource(id = R.drawable.baseline_login_24),
                    contentDescription = "Teste"
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Faça seu login!",
                    color = colorResource(id = R.color.black),
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold
                )
            }
            Spacer(modifier = Modifier.height(2.dp))
            Row {
                Text(
                    text = "Entre com suas informações de cadastro.",
                    color = colorResource(id = R.color.black_text),
                    fontSize = 14.sp
                )
            }
        }

        Spacer(modifier = Modifier.height(32.dp))

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(30.dp)
        ) {
            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                modifier = Modifier.fillMaxWidth(),
                placeholder = {
                    Text(text = "joao@gmail.com")
                },
                leadingIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.baseline_email_24),
                        contentDescription = "Email Icon"
                    )
                },
                label = {
                    Text(text = "Email")
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                isError = erroEmail,
                textStyle = TextStyle(color = Color.Black)
            )
            Spacer(modifier = Modifier.height(16.dp))
            OutlinedTextField(
                value = password,
                onValueChange = {
                    if (it.length <= sizeMax) {
                        password = it
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                placeholder = {
                    Text(text = "********")
                },
                leadingIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.baseline_https_24),
                        contentDescription = "Password Icon"
                    )
                },
                label = {
                    Text(text = "Senha")
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password
                ),
                visualTransformation = PasswordVisualTransformation(),
                textStyle = TextStyle(color = Color.Black)
            )

            Spacer(modifier = Modifier.height(10.dp))

            Row (
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ){
                Row (
                    verticalAlignment = Alignment.CenterVertically
                ){
                    Text(
                        text = "Lembre-me",
                        color = colorResource(id = R.color.black_text),
                        fontSize = 14.sp
                    )
                    Checkbox(
                        checked = false,
                        onCheckedChange = {})
                }
                Text(
                    text = "Esqueci minha senha",
                    style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        color = colorResource(id = R.color.blue_project) // Cor do texto
                    ),
                    fontSize = 14.sp
                )
            }

            Spacer(modifier = Modifier.height(120.dp))

            Button(
                shape = RoundedCornerShape(10.dp),
                onClick = {
                    if(email.isEmpty()){
                        erroEmail = true
                    }else{
                        erroEmail = false
                        navController.navigate("dashboard")
                    }
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF00ACC8))
            ) {
                Text(
                    text = "Entrar",
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
            }

            Row (
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ){
                Text(
                    text = "Não tem uma conta? ",
                    color = colorResource(id = R.color.black_text),
                    fontSize = 14.sp
                )
                Button(
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
                    onClick = {
                        navController.navigate("cadastro")
                    }
                ) {
                    Text(
                        text = "Registre-se",
                        style = TextStyle(
                            fontWeight = FontWeight.Bold,
                            color = colorResource(id = R.color.blue_project)
                        ),
                        fontSize = 14.sp
                    )
                }

            }
        }

    }
}
