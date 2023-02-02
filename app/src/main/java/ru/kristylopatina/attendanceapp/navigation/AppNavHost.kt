package ru.kristylopatina.attendanceapp.navigation

import ru.kristylopatina.attendanceapp.utils.Constants
import androidx.compose.runtime.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import ru.kristylopatina.attendanceapp.MainViewModel
import ru.kristylopatina.attendanceapp.screens.AboutScreen
import ru.kristylopatina.attendanceapp.screens.AddAttendanceScreen
import ru.kristylopatina.attendanceapp.screens.SchoolClassScreen
import ru.kristylopatina.attendanceapp.screens.SubjectScreen
import ru.kristylopatina.attendanceapp.screens.view.*
import ru.kristylopatina.attendanceapp.utils.*

sealed class NavRoute(val route: String){
    object Subject: NavRoute("subject_screen")
    object SchoolClass: NavRoute("school_class_screen")
    object AddAttendance: NavRoute("add_screen")
    object ChemistryCard: NavRoute("chem")
    object ComputerScienceCard: NavRoute("comp_sci")
    object EnglishCard: NavRoute("engl")
    object HistoryCard: NavRoute("hist")
    object LiteratureCard: NavRoute("hist")
    object MathematicsCard: NavRoute("math")
    object PhysicsCard: NavRoute("math")
    object RussianCard: NavRoute("russ")
    object About: NavRoute("about")
}

@Composable
fun AppNavHost(mViewModel: MainViewModel) {
    val navController = rememberNavController()
    var subject by remember { mutableStateOf("") }

    NavHost(navController = navController, startDestination = NavRoute.Subject.route) {
        composable(NavRoute.Subject.route) { SubjectScreen(navController = navController, viewModel=mViewModel) }
        composable(NavRoute.SchoolClass.route) {
            SchoolClassScreen(navController = navController, viewModel=mViewModel, subject = subject)
        }
        composable(NavRoute.AddAttendance.route) { AddAttendanceScreen(
            navController = navController, viewModel =mViewModel, subject = subject
        ) }
        composable(NavRoute.About.route + "/{${Constants.Key.ID}}") { backStackEntry -> AboutScreen(
            navController = navController,
            viewModel =mViewModel,
            id = backStackEntry.arguments?.getString(Constants.Key.ID)) }
        composable(NavRoute.ChemistryCard.route) {
            ChemistryCard(
                navController = navController,
                numClass = 3,
                viewModel = mViewModel,
                subject = Constants.Key.TYPE_CHEM
            )
        }
        composable(NavRoute.ComputerScienceCard.route) {
            ComputerScienceCard(
                navController = navController,
                numClass = 3,
                viewModel = mViewModel,
                subject = Constants.Key.TYPE_COMP
            )
        }
        composable(NavRoute.EnglishCard.route) {
            EnglishCard(
                navController = navController,
                numClass = 4,
                viewModel = mViewModel,
                subject = Constants.Key.TYPE_ENGL
            )
        }
        composable(NavRoute.HistoryCard.route) {
            HistoryCard(
                navController = navController,
                numClass = 4,
                viewModel = mViewModel,
                subject = Constants.Key.TYPE_HIST
            )
        }
        composable(NavRoute.LiteratureCard.route) {
            LiteratureCard(
                navController = navController,
                numClass = 4,
                viewModel = mViewModel,
                subject = Constants.Key.TYPE_CHEM
            )
        }
        composable(NavRoute.MathematicsCard.route) {
            MathematicsCard(
                navController = navController,
                numClass = 4,
                viewModel = mViewModel,
                subject = Constants.Key.TYPE_MATH
            )
        }
        composable(NavRoute.PhysicsCard.route) {
            PhysicsCard(
                navController = navController,
                numClass = 4,
                viewModel = mViewModel,
                subject = Constants.Key.TYPE_PHIS
            )
        }
    }
}