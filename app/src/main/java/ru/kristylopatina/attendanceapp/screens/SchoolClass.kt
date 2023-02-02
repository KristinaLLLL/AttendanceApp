package ru.kristylopatina.attendanceapp.screens

import android.app.Application
import android.app.DatePickerDialog
import android.widget.DatePicker
import android.widget.ImageButton
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import ru.kristylopatina.attendanceapp.MainViewModel
import ru.kristylopatina.attendanceapp.MainViewModelFactory
import ru.kristylopatina.attendanceapp.model.Attendance
import ru.kristylopatina.attendanceapp.navigation.NavRoute
import ru.kristylopatina.attendanceapp.ui.theme.*
import ru.kristylopatina.attendanceapp.utils.Constants
import java.util.*

@Composable
fun SchoolClassScreen(
    navController: NavHostController,
    viewModel: MainViewModel,
    subject: String
) {

    var mDate = remember { mutableStateOf("") }

    val attendances = viewModel.readAllAttendances().observeAsState(listOf()).value
/*    val attend = attendances.firstOrNull{ it.date == mDate?.value}
        ?: Attendance(name = "none", date = "", flag = "", description = "", subject = "")*/
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
                    tint = BrownGray)
                }
        }

    ) {
            Column() {
                MyContent(mDate)

                LazyColumn {
                    items(attendances){attendance ->
                        if(attendance.date == mDate.value) {
                            AttendanceItem(
                                attendance = attendance,
                                navController = navController,
                                attend = attendance
                            )
                        }
                    }
                }

            }
    }
}
@Composable
fun AttendanceItem(attendance: Attendance, navController: NavHostController, attend: Attendance){
    Surface(color = Milk,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp, horizontal = 10.dp)
            .clickable {
                navController.navigate(NavRoute.About.route + "/${attend.id}")
            },
        elevation = 6.dp
    ) {
        Column(modifier = Modifier.padding(vertical = 8.dp),
            horizontalAlignment = Alignment.Start){
            Row(modifier = Modifier.padding(5.dp)) {
                Text(
                    text = attendance.name,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = BrownGray
                )
                if (attendance.flag == "н"){
                    Text(
                        text = attendance.flag,
                        color = DarkRed,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(horizontal = 60.dp, vertical = 0.dp)

                    )
                } else{
                    Text(
                    text = attendance.flag,
                    color = DarkBlue,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(horizontal = 60.dp, vertical = 0.dp)

                )}

                Row() {
                    Text(
                        text = attendance.subject,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold
                    )
                }

            }

        }

    }

}

@Composable
fun MyContent(mDate : MutableState<String>){

    // Fetching the Local Context
    val mContext = LocalContext.current

    // Declaring integer values
    // for year, month and day
    val mYear: Int
    val mMonth: Int
    val mDay: Int

    // Initializing a Calendar
    val mCalendar = Calendar.getInstance()

    // Fetching current year, month and day
    mYear = mCalendar.get(Calendar.YEAR)
    mMonth = mCalendar.get(Calendar.MONTH)
    mDay = mCalendar.get(Calendar.DAY_OF_MONTH)

    mCalendar.time = Date()

    // Declaring a string value to
    // store date in string format
    //val mDate = remember { mutableStateOf("") }

    // Declaring DatePickerDialog and setting
    // initial values as current values (present year, month and day)
    val mDatePickerDialog = DatePickerDialog(
        mContext,
        { _: DatePicker, mYear: Int, mMonth: Int, mDayOfMonth: Int ->
            mDate.value = "$mDayOfMonth/${mMonth+1}/$mYear"
        }, mYear, mMonth, mDay
    )

    Column(modifier = Modifier.height(50.dp), verticalArrangement = Arrangement.Top, horizontalAlignment = Alignment.Start) {
        Row() {
            // Creating a button that on
            // click displays/shows the DatePickerDialog
            Button(onClick = {
                mDatePickerDialog.show()
            }, colors = ButtonDefaults.buttonColors(backgroundColor = Sand) ) {
                Text(text = "Выбрать дату", color = BrownGray)
            }

            Column(modifier = Modifier
                .fillMaxSize()
                .padding(10.dp)) {
                // Displaying the mDate value in the Text
                Text(text = "Дата: ${mDate.value}", fontSize = 18.sp, textAlign = TextAlign.Center, fontWeight = FontWeight.Bold, color = BrownGray)
            }

        }
    }
}

@Preview(showBackground = true)
@Composable
fun prevMainScreen(){
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
}