package uz.futuresoft.tasks.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import uz.futuresoft.navigation.Routes
import uz.futuresoft.tasks.presentation.home.HomeScreen
import uz.futuresoft.tasks.presentation.task_events.TaskEventScreen

fun NavGraphBuilder.tasksRoute(navHostController: NavHostController) {
    composable<Routes.Home> {
        HomeScreen(navHostController = navHostController)
    }

    composable<Routes.TaskEvents> {
        val taskId = it.toRoute<Routes.TaskEvents>().taskId

        TaskEventScreen(
            taskId = taskId,
            navHostController = navHostController,
        )
    }
}