package uz.futuresoft.tasks.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import uz.futuresoft.navigation.Routes
import uz.futuresoft.tasks.domain.repository.TodoItemsRepository
import uz.futuresoft.tasks.presentation.home.HomeScreen
import uz.futuresoft.tasks.presentation.task_events.TaskDetailsScreen

fun NavGraphBuilder.tasksRoute(
    navHostController: NavHostController,
    todoItemsRepository: TodoItemsRepository,
) {
    composable<Routes.Home> {
        HomeScreen(
            navHostController = navHostController,
            todoItemsRepository = todoItemsRepository,
        )
    }

    composable<Routes.TaskDetails> {
        val taskId = it.toRoute<Routes.TaskDetails>().taskId

        TaskDetailsScreen(
            taskId = taskId,
            navHostController = navHostController,
            todoItemsRepository = todoItemsRepository,
        )
    }
}