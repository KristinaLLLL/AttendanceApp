package ru.kristylopatina.attendanceapp.screens

import android.app.Application
import android.app.DatePickerDialog
import android.widget.DatePicker
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.toSize
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import ru.kristylopatina.attendanceapp.MainViewModel
import ru.kristylopatina.attendanceapp.MainViewModelFactory
import ru.kristylopatina.attendanceapp.model.Attendance
import ru.kristylopatina.attendanceapp.navigation.NavRoute
import ru.kristylopatina.attendanceapp.ui.theme.AttendanceAppTheme
import ru.kristylopatina.attendanceapp.ui.theme.BrownGray
import ru.kristylopatina.attendanceapp.ui.theme.DarkBlue
import ru.kristylopatina.attendanceapp.ui.theme.Sand
import ru.kristylopatina.attendanceapp.utils.Constants

import java.util.*

@Composable
fun AddAttendanceScreen(navController: NavHostController, viewModel: MainViewModel, subject: String) {

    var name by remember { mutableStateOf("") }
    var flag by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    val mDate = remember { mutableStateOf("") }

    var mExpanded by remember { mutableStateOf(false) }

    val mNames = listOf("Аксёнова Е.В.", "Бачинин. Б.А.", "Варламова Д.В.", "Герасимов К.Г.")
    //var mSelectedText by remember { mutableStateOf("") }
    var mTextFieldSize by remember { mutableStateOf(Size.Zero)}
    val icon = if (mExpanded)
        Icons.Filled.KeyboardArrowUp
    else
        Icons.Filled.KeyboardArrowDown

    var isButtonEnabled by remember {
        mutableStateOf(false)
    }

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


    // Declaring DatePickerDialog and setting
    // initial values as current values (present year, month and day)
    val mDatePickerDialog = DatePickerDialog(
        mContext,
        { _: DatePicker, mYear: Int, mMonth: Int, mDayOfMonth: Int ->
            mDate.value = "$mDayOfMonth/${mMonth+1}/$mYear"
        }, mYear, mMonth, mDay
    )

    Scaffold() {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(vertical = 50.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Row(modifier = Modifier.height(60.dp).padding(10.dp)) {
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
                    Text(text = "Дата: ${mDate.value}", fontSize = 18.sp, textAlign = TextAlign.Center, )
                }

            }
            OutlinedTextField(
                value = name,
                onValueChange = { name = it
                    isButtonEnabled = flag.isNotEmpty() && description.isNotEmpty() && name.isNotEmpty() && mDate.value.isNotEmpty()
                },
                modifier = Modifier
                    .onGloballyPositioned { coordinates ->
                        // This value is used to assign to
                        // the DropDown the same width
                        mTextFieldSize = coordinates.size.toSize()
                    },
                label = {Text("Выберите ученика")},
                trailingIcon = {
                    Icon(icon,"contentDescription",
                        Modifier.clickable { mExpanded = !mExpanded })
                },
                isError = name.isEmpty()
            )
            DropdownMenu(
                expanded = mExpanded,
                onDismissRequest = { mExpanded = false },
                modifier = Modifier
                    .width(with(LocalDensity.current){mTextFieldSize.width.toDp()})
            ) {
                mNames.forEach { label ->
                    DropdownMenuItem(onClick = {
                        name = label
                        mExpanded = false
                    }) {
                        Text(text = label)
                    }
                }
            }

            //Text(text = "Дата: ${date.value}", fontSize = 18.sp, textAlign = TextAlign.Center)
            OutlinedTextField(
                value = flag,
                onValueChange = { flag = it
                    isButtonEnabled = flag.isNotEmpty() && description.isNotEmpty() && name.isNotEmpty() && mDate.value.isNotEmpty()
                },

                label = { Text(text = "Введите оценку или н") },
                isError = if (
                    flag == "н" || flag == "2" || flag == "3" || flag == "4" || flag == "5"
                ) {
                    flag.isEmpty()
                } else {
                    flag.isNotEmpty()
                }
            )
            OutlinedTextField(
                value = description,
                onValueChange = {
                    description = it
                    isButtonEnabled = flag.isNotEmpty() && description.isNotEmpty() && name.isNotEmpty() && mDate.value.isNotEmpty()
                                },
                label = { Text(text = "Комментарий") },
                isError = description.isEmpty()
            )
            Button(
                enabled = isButtonEnabled,
                colors = ButtonDefaults.buttonColors(backgroundColor = Sand),
                onClick = {
                    viewModel.initDatabase(Constants.Key.TYPE_ROOM, subject = subject) {
                        viewModel.addAttendance(
                            attendance = Attendance(
                                name = name,
                                flag = flag,
                                date = mDate.value,
                                description = description,
                                subject = subject
                            ),
                            subject = subject
                        ) {
                            navController.navigate(route = NavRoute.SchoolClass.route)
                        }
                    }


            },
                modifier = Modifier.padding(10.dp)
            ) {
                Text(text = "Сохранить")
            }
            Button(
                colors = ButtonDefaults.buttonColors(backgroundColor = DarkBlue),
                onClick = {
                    viewModel.initDatabase(Constants.Key.TYPE_ROOM, subject = subject) {
                            navController.navigate(route = NavRoute.SchoolClass.route)
                    }


                },
                modifier = Modifier.padding(10.dp)
            ) {
                Text(text = "К списку", color = Color.White)
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun prevAddScreen(){
    AttendanceAppTheme {
        val context = LocalContext.current
        val mViewModel:MainViewModel = viewModel(
            factory = MainViewModelFactory(context.applicationContext as Application))
        var subject = "hist"
        AddAttendanceScreen(
            navController = rememberNavController(),
            viewModel = mViewModel,
            subject = subject
        )
    }
}
