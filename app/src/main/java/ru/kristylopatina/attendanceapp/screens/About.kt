package ru.kristylopatina.attendanceapp.screens

import android.app.Application
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.toSize
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.launch
import ru.kristylopatina.attendanceapp.MainViewModel
import ru.kristylopatina.attendanceapp.MainViewModelFactory
import ru.kristylopatina.attendanceapp.model.Attendance
import ru.kristylopatina.attendanceapp.navigation.NavRoute
import ru.kristylopatina.attendanceapp.ui.theme.AttendanceAppTheme
import ru.kristylopatina.attendanceapp.utils.Constants

import java.util.*

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun AboutScreen(navController: NavHostController, viewModel: MainViewModel, id: String?) {

    var name by remember { mutableStateOf("") }
    var flag by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }

    val bottomSheetState = rememberModalBottomSheetState(ModalBottomSheetValue.Hidden)
    val coroutineScope = rememberCoroutineScope()

    val attendances = viewModel.readAllAttendances().observeAsState(listOf()).value
    val attend = attendances.firstOrNull{ it.id == id?.toInt()}
        ?: Attendance(name = "none", date = "", flag = "", description = "", subject = "")

    ModalBottomSheetLayout(
        sheetState = bottomSheetState,
        sheetElevation = 5.dp,
        sheetShape = RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp),
        sheetContent = {
            Surface {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(all = 32.dp)
                ) {

                    OutlinedTextField(
                        value = flag,
                        onValueChange = { flag = it },
                        modifier = Modifier.padding(vertical = 5.dp, horizontal = 15.dp),
                        label = {Text("Введите оценку")},
                        isError = flag.isEmpty()
                    )
                    OutlinedTextField(
                        value = description,
                        onValueChange = { description = it },
                        modifier = Modifier.padding(vertical = 5.dp, horizontal = 15.dp),
                        label = {Text("Комментарий")},
                        isError = description.isEmpty()
                    )
                    Button(
                        modifier = Modifier.padding(top = 15.dp).fillMaxWidth(),
                        onClick = {
                            viewModel.updateAttendance(attendance =
                            Attendance(
                                id = attend.id,
                                name = attend.name,
                                flag = flag,
                                subject = attend.subject,
                                description = description,
                                date = attend.date
                            )){
                                navController.navigate(NavRoute.SchoolClass.route)
                            }
                        }) {
                        Text(text = "Изменить")
                    }
                }

            }
        }
    ) {
        Scaffold() {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(vertical = 10.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {
                Row(modifier = Modifier.padding(10.dp)) {
                    IconButton(modifier = Modifier
                        .padding(horizontal = 40.dp, vertical = 20.dp),
                        onClick = {
                            viewModel.deleteAttendance(attendance = attend){
                                navController.navigate(NavRoute.SchoolClass.route)
                            }
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Delete,
                            contentDescription = "Delete"
                        )
                    }
                    IconButton(modifier = Modifier
                        .padding(horizontal = 40.dp, vertical = 20.dp),
                        onClick = { coroutineScope.launch {
                            name = attend.name
                            flag = attend.flag
                            bottomSheetState.show()
                        } }
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Edit,
                            contentDescription = "Edit"
                        )
                    }
                }

                Row(modifier = Modifier.height(60.dp)) {
                    Text(
                        text = attend.date,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
                Row(modifier = Modifier.height(60.dp)) {
                    Text(
                        text = attend.name,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
                Row(modifier = Modifier.height(60.dp)) {
                    Text(
                        text = attend.flag,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
                Row(modifier = Modifier.height(60.dp)) {
                    Text(
                        text = attend.description,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
                Row(modifier = Modifier.height(60.dp)) {
                    Text(
                        text = attend.subject,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold
                    )
                }

                Button(
                    onClick = {
                        navController.navigate(route = NavRoute.SchoolClass.route)
                    },
                    modifier = Modifier.padding(10.dp)
                ) {
                    Text(text = "К списку")
                }
            }
        }
    }


}

@Composable
@Preview(showBackground = true)
fun prevAboutScreen(){
    AttendanceAppTheme {
        val context = LocalContext.current
        val mViewModel: MainViewModel = viewModel(
            factory = MainViewModelFactory(context.applicationContext as Application)
        )
        AboutScreen(navController = rememberNavController(), viewModel = mViewModel, id = "1")
    }
}