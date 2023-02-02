package ru.kristylopatina.attendanceapp

import android.app.Application
import android.os.Bundle
import android.widget.ImageButton
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import ru.kristylopatina.attendanceapp.navigation.AppNavHost
import ru.kristylopatina.attendanceapp.navigation.NavRoute
import ru.kristylopatina.attendanceapp.ui.theme.AttendanceAppTheme
import ru.kristylopatina.attendanceapp.ui.theme.DarkBlue
import ru.kristylopatina.attendanceapp.ui.theme.Milk


class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            AttendanceAppTheme {
                val context = LocalContext.current
                val mViewModel: MainViewModel = viewModel(
                    factory = MainViewModelFactory(context.applicationContext as Application)
                )
                // A surface container using the 'background' color from the theme

                Scaffold (
                    topBar = {
                        TopAppBar(
                            title = {
                                Text(text = "Электронный журнал")
                            },
                            backgroundColor = DarkBlue,
                            contentColor = Color.White,
                            elevation = 12.dp,
                            /*navigationIcon =
                                {
                                    IconButton(onClick = { navController.navigateUp() }) {
                                        Icon(
                                            imageVector = Icons.Filled.ArrowBack,
                                            contentDescription = "Back"
                                        )
                                    }
                                }*/
                            )

                    },
                    content = {
                        Surface(
                            modifier = Modifier.fillMaxSize(),
                            color = Milk
                        ){
                            AppNavHost(mViewModel)
                        }
                    })
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    AttendanceAppTheme {
        Greeting("Android")
    }
}