package br.com.fiap.email.screens

import android.annotation.SuppressLint
import android.widget.CalendarView
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavHostController
import br.com.fiap.email.R
import java.util.Calendar
import java.util.Locale

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Calendario(navController: NavHostController) {
    var date by remember {
        mutableStateOf("")
    }

    val calendar = Calendar.getInstance()
    val currentMonth = calendar.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault())
    val currentYear = calendar.get(Calendar.YEAR)

    Column (
        modifier = Modifier.fillMaxSize()
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text(text = "Calendario") },
                )
            },
            content = {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp)
                ) {

                    Text(
                        text = "$currentMonth $currentYear",
                        modifier = Modifier.padding(vertical = 8.dp)
                    )

                    AndroidView(factory = { CalendarView(it) }, update = {
                        it.setOnDateChangeListener { calendarView, year, month, day ->
                            date = "$day-${month + 1}-$year"
                        }
                    })
                    Text(text = "Data: $date", color = Color.Black)

                    Spacer(modifier = Modifier.height(16.dp))

                    Text(text = "Sexta, 23 de Agosto", color = Color.Black)

                    Spacer(modifier = Modifier.height(8.dp))

                    Column(
                        modifier = Modifier
                            .clip(shape = RoundedCornerShape(10.dp))
                            .fillMaxWidth()
                            .background(colorResource(id = R.color.blue_project))
                            .padding(10.dp, 15.dp)
                    ) {
                        Text(
                            text = "Consulta Médica",
                            color = colorResource(id = R.color.black_text ),
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = "Consulta com o Dr.João",
                            color = colorResource(id = R.color.black_text ),
                            fontSize = 12.sp
                        )
                        Text(
                            text = "15:00",
                            color = colorResource(id = R.color.black_text ),
                            fontWeight = FontWeight.Bold,
                            fontSize = 10.sp
                        )
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    Column(
                        modifier = Modifier
                            .clip(shape = RoundedCornerShape(10.dp))
                            .fillMaxWidth()
                            .background(colorResource(id = R.color.green_project))
                            .padding(10.dp, 15.dp)
                    ) {
                        Text(
                            text = "Futebol",
                            color = colorResource(id = R.color.black_text ),
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = "Futebol no sintético",
                            color = colorResource(id = R.color.black_text ),
                            fontSize = 12.sp
                        )
                        Text(
                            text = "19:00",
                            color = colorResource(id = R.color.black_text ),
                            fontWeight = FontWeight.Bold,
                            fontSize = 10.sp
                        )
                    }
                }



            },
            bottomBar = {
                Column(
                    modifier = Modifier
                        .height(70.dp)
                        .background(colorResource(id = R.color.white_project))
                        .fillMaxWidth()
                        .padding(50.dp, 10.dp)
                ) {
                    Row (
                        horizontalArrangement = Arrangement.Center
                    ){
                        Column (
                            modifier = Modifier.clickable {
                                navController.navigate("dashboard")
                            },
                            horizontalAlignment = Alignment.CenterHorizontally
                        ){
                            Image(
                                painter = painterResource(id = R.drawable.baseline_inbox_24),
                                contentDescription = "Responder Icon"
                            )
                            Text(
                                text = "Inbox",
                                fontSize = 12.sp
                            )
                        }

                        Spacer(modifier = Modifier.width(90.dp))

                        Column (
                            modifier = Modifier.clickable {
                                navController.navigate("leituraEmail")
                            },
                            horizontalAlignment = Alignment.CenterHorizontally
                        ){
                            Image(
                                painter = painterResource(id = R.drawable.baseline_star_border_24),
                                contentDescription = "Responder Icon"
                            )
                            Text(
                                text = "Favoritos",
                                fontSize = 12.sp
                            )
                        }

                        Spacer(modifier = Modifier.width(80.dp))

                        Column (
                            modifier = Modifier.clickable {
                                navController.navigate("calendario")
                            },
                            horizontalAlignment = Alignment.CenterHorizontally
                        ){
                            Image(
                                painter = painterResource(id = R.drawable.baseline_calendar_today_black_24),
                                contentDescription = "Responder Icon"
                            )
                            Text(
                                text = "Agenda",
                                color = colorResource(id = R.color.blue_project),
                                fontSize = 12.sp
                            )
                        }
                    }
                }
            }
        )


    }


}