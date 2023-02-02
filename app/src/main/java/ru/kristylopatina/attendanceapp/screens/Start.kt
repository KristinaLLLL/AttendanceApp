package ru.kristylopatina.attendanceapp.screens

/*
import android.app.Application
import android.app.DatePickerDialog
import android.widget.DatePicker
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import ru.kristylopatina.attendanceapp.MainViewModel
import ru.kristylopatina.attendanceapp.MainViewModelFactory
import ru.kristylopatina.attendanceapp.model.Attendance
import ru.kristylopatina.attendanceapp.navigation.NavRoute
import ru.kristylopatina.attendanceapp.ui.theme.AttendanceAppTheme
import ru.kristylopatina.attendanceapp.utils.Constants

@Composable
fun StartClassScreen(
    navController: NavHostController,
    viewModel: MainViewModel,
    subject: String
) {

    //var mDate = remember { mutableStateOf("") }

    val attendances = viewModel.readAllAttendances().observeAsState(listOf()).value
*/
/*    val attend = attendances.firstOrNull{}
        ?: Attendance(name = "none", date = "", flag = "", description = "", subject = "")*//*


    val context = LocalContext.current
    val mViewModel:MainViewModel = viewModel(
        factory = MainViewModelFactory(context.applicationContext as Application))

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    mViewModel.initDatabase(Constants.Key.TYPE_ROOM, subject = subject){
                        navController.navigate(route = NavRoute.AddAttendance.route)
                    }
                }) {
                Icon(
                    imageVector = Icons.Filled.Add,
                    contentDescription = "Add Icons",
                    tint = Color.White)
            }
        }

    ) {
        Column() {

            LazyColumn {
                items(attendances){attendance ->
                    AttendanceItem(attendance = attendance, navController = navController)
                }
            }
        }
    }
}
@Composable
fun ClassItem(attendance: Attendance, navController: NavHostController){
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp, horizontal = 10.dp)
            .clickable {
                navController.navigate(NavRoute.AddAttendance.route)
            },
        elevation = 6.dp
    ) {
        Column(modifier = Modifier.padding(vertical = 8.dp),
            horizontalAlignment = Alignment.Start){
            Row() {
                Text(
                    text = "1 А",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "28 человек",
                    color = Color.Red,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(horizontal = 80.dp, vertical = 0.dp)

                )
            }

        }

    }
}



@Preview(showBackground = true)
@Composable
fun prevStartScreen(){
    AttendanceAppTheme {
        val context = LocalContext.current
        val mViewModel: MainViewModel = viewModel(
            factory = MainViewModelFactory(context.applicationContext as Application)
        )
        var subject = "liter"
        SchoolClassScreen(
            navController = rememberNavController(),
            viewModel = mViewModel,
            subject = subject
        )
    }
}*/
