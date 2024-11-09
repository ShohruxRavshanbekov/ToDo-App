package uz.futuresoft.todoapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import uz.futuresoft.navigation.Routes
import uz.futuresoft.tasks.domain.repository.TodoItemsRepository
import uz.futuresoft.tasks.navigation.tasksRoute

@Composable
fun AppNavHost(
    todoItemsRepository: TodoItemsRepository,
) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Routes.Home,
    ) {
        tasksRoute(
            navHostController = navController,
            todoItemsRepository = todoItemsRepository,
        )
    }
}