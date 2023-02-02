package ru.kristylopatina.attendanceapp.screens.view

import android.app.Application
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import ru.kristylopatina.attendanceapp.MainViewModel
import ru.kristylopatina.attendanceapp.MainViewModelFactory
import ru.kristylopatina.attendanceapp.navigation.NavRoute
import ru.kristylopatina.attendanceapp.ui.theme.BrownGray
import ru.kristylopatina.attendanceapp.ui.theme.Milk
import ru.kristylopatina.attendanceapp.ui.theme.Purple500
import ru.kristylopatina.attendanceapp.utils.Constants

@Composable
fun EnglishCard(
    navController: NavHostController,
    numClass: Int,
    viewModel: MainViewModel,
    subject: String
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            //.background(BrownGray)
            .clip(RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp, bottomEnd = 30.dp, bottomStart = 30.dp))
            .padding(vertical = 5.dp, horizontal = 8.dp)
            .clickable {
                viewModel.initDatabase(Constants.Key.TYPE_ROOM, subject){
                    navController.navigate(route = NavRoute.AddAttendance.route)
                }
            },
        elevation = 6.dp,
        shape = RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp, bottomEnd = 30.dp, bottomStart = 30.dp),
        color = BrownGray
    ) {
        Column(modifier = Modifier
            .padding(10.dp, 10.dp)
            .background(BrownGray)
            .fillMaxWidth()) {
            Text(
                text = "Английский язык",
                style = MaterialTheme.typography.body1,
                modifier = Modifier
                    .padding(10.dp, 0.dp),
                color = Color.White,
                fontSize = 30.sp
            )
            Row(
                modifier = Modifier
                    .padding(10.dp, 10.dp)
                    .background(BrownGray)
                    .fillMaxWidth()

            ) {
                Text(
                    text = "$numClass класса",
                    style = MaterialTheme.typography.body1,
                    textAlign = TextAlign.Right,
                    color = Milk
                )
            }
        }
    }
}

@Preview
@Composable
fun prevEnglishCard() {
    val context = LocalContext.current
    val mViewModel:MainViewModel = viewModel(
        factory = MainViewModelFactory(context.applicationContext as Application)
    )
    EnglishCard(navController = rememberNavController(), numClass = 5, viewModel = mViewModel,
        subject= Constants.Key.TYPE_ENGL)
}