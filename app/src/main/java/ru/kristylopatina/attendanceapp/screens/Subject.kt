package ru.kristylopatina.attendanceapp.screens

import android.annotation.SuppressLint
import android.app.Application
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.GridItemSpan
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import ru.kristylopatina.attendanceapp.MainViewModel
import ru.kristylopatina.attendanceapp.MainViewModelFactory
import ru.kristylopatina.attendanceapp.screens.view.*
import ru.kristylopatina.attendanceapp.ui.theme.AttendanceAppTheme
import ru.kristylopatina.attendanceapp.utils.*

@OptIn(ExperimentalFoundationApi::class)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun SubjectScreen(navController: NavHostController, viewModel: MainViewModel)
{
    val context = LocalContext.current
    val mViewModel:MainViewModel = viewModel(
        factory = MainViewModelFactory(context.applicationContext as Application))

    Scaffold(modifier = Modifier
        .fillMaxSize()
        .background(Color.Gray)) {
        Column (
            modifier = Modifier
                .fillMaxHeight()
                .background(Color.White),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ){

            LazyVerticalGrid(
                cells = GridCells.Fixed(4),
                verticalArrangement = Arrangement.spacedBy(5.dp),
                horizontalArrangement = Arrangement.spacedBy(6.dp)
            ) {

                item(span = { GridItemSpan(maxCurrentLineSpan) }) { MathematicsCard(
                    navController,
                    6,
                    mViewModel,
                    Constants.Key.TYPE_MATH
                ) }
                item(span = { GridItemSpan(maxCurrentLineSpan) }) { LiteratureCard(
                    navController,
                    6,
                    mViewModel,
                    Constants.Key.TYPE_LITER
                ) }
                item(span = { GridItemSpan(maxCurrentLineSpan) }) { HistoryCard(
                    navController,
                    4,
                    mViewModel,
                    Constants.Key.TYPE_HIST
                ) }
                item(span = { GridItemSpan(maxCurrentLineSpan) }) { PhysicsCard(
                    navController,
                    3,
                    mViewModel,
                    Constants.Key.TYPE_PHIS
                ) }
                item(span = { GridItemSpan(maxCurrentLineSpan) }) { ChemistryCard(
                    navController,
                    3,
                    mViewModel,
                    Constants.Key.TYPE_CHEM
                ) }
                item(span = { GridItemSpan(maxCurrentLineSpan) }) { EnglishCard(
                    navController,
                    5,
                    mViewModel,
                    Constants.Key.TYPE_ENGL
                ) }
                item(span = { GridItemSpan(maxCurrentLineSpan) }) { ComputerScienceCard(
                    navController,
                    4,
                    mViewModel,
                    Constants.Key.TYPE_COMP
                ) }
            }

        }

    }

}

@Preview(showBackground = true)
@Composable
fun pevSubjectScreen(){
    AttendanceAppTheme {
        val context = LocalContext.current
        val mViewModel: MainViewModel = viewModel(
            factory = MainViewModelFactory(context.applicationContext as Application)
        )
        SubjectScreen(navController = rememberNavController(), viewModel = mViewModel)
    }
}