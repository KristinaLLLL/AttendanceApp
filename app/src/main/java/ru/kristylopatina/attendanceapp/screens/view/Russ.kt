package ru.kristylopatina.attendanceapp.screens.view

import android.app.Application
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
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
import ru.kristylopatina.attendanceapp.ui.theme.Purple500

@Composable
fun RussianCard(
    navController: NavHostController,
    numClass: Int,
    viewModel: MainViewModel,
    mViewModel: MainViewModel
) {
    /*Surface(
        modifier = Modifier
            .fillMaxWidth()
            .background(Purple500),
        color = MaterialTheme.colors.surface
    ) {
        Column(modifier = Modifier
            .padding(10.dp, 10.dp)
            .background(Purple500)
            .fillMaxWidth()) {
            Text(
                text = "Русский язык",
                style = MaterialTheme.typography.body1,
                modifier = Modifier
                    .padding(10.dp, 0.dp),
                color = Color.White,
                fontSize = 30.sp
            )
            Row(
                modifier = Modifier
                    .padding(10.dp, 10.dp)
                    .background(Purple500)
                    .fillMaxWidth()

            ) {
                Text(
                    text = "$numClass класса",
                    style = MaterialTheme.typography.body1,
                    textAlign = TextAlign.Right,
                    color = Color.White
                )
            }
        }
    }*/
}

@Preview
@Composable
fun prevRussianCard() {
/*    val context = LocalContext.current
    val mViewModel:MainViewModel = viewModel(
        factory = MainViewModelFactory(context.applicationContext as Application)
    )
    RussianCard(navController = rememberNavController(), numClass = 3, viewModel = mViewModel)*/
}