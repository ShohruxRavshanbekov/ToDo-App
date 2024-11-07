package uz.futuresoft.todoapp.navigation

import android.content.res.Configuration
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import uz.futuresoft.navigation.Routes
import uz.futuresoft.tasks.navigation.tasksRoute
import uz.futuresoft.ui.theme.ToDoAppTheme

@Composable
fun AppNavHost() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Routes.Home,
    ) {
        tasksRoute(navHostController = navController)
    }
}