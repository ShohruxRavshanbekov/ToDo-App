package uz.futuresoft.todoapp.navigation

import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import uz.futuresoft.navigation.Routes
import uz.futuresoft.data.TodoItemsRepository
import uz.futuresoft.tasks.navigation.tasksRoute

@Composable
fun AppNavHost(
    todoItemsRepository: uz.futuresoft.data.TodoItemsRepository,
    darkTheme: Boolean,
    onChangeTheme: () -> Unit,
) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Routes.Home,
        enterTransition = { slideInHorizontally(animationSpec = tween(durationMillis = 300)) { it / 2 } },
        exitTransition = { slideOutHorizontally(animationSpec = tween(durationMillis = 300)) { -it / 2 } },
        popEnterTransition = { slideInHorizontally(animationSpec = tween(durationMillis = 300)) { -it / 2 } },
        popExitTransition = { slideOutHorizontally(animationSpec = tween(durationMillis = 300)) { it / 2 } },
    ) {
        tasksRoute(
            navHostController = navController,
            todoItemsRepository = todoItemsRepository,
            darkTheme = darkTheme,
            onChangeTheme = onChangeTheme,
        )
    }
}