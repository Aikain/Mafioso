package app.mafioso

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import app.mafioso.ui.GameScreen
import app.mafioso.ui.HomeScreen

enum class MafiosoScreen() {
    HOME,
    GAME,
}

@Composable
fun MafiosoApp(
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = MafiosoScreen.HOME.name,
    ) {
        composable(route = MafiosoScreen.HOME.name) {
            HomeScreen()
        }
        composable(route = MafiosoScreen.GAME.name) {
            GameScreen()
        }
    }
}
