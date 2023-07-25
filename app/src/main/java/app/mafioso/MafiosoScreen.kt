package app.mafioso

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import app.mafioso.ui.CreateGameScreen
import app.mafioso.ui.GameScreen
import app.mafioso.ui.HomeScreen
import app.mafioso.ui.JoinGameScreen

enum class MafiosoScreen() {
    HOME,
    CREATE_GAME,
    JOIN_GAME,
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
            HomeScreen(
                goToCreateGameScreen = { navController.navigate(MafiosoScreen.CREATE_GAME.name) },
                goToJoinGameScreen = { navController.navigate(MafiosoScreen.JOIN_GAME.name) },
            )
        }
        composable(route = MafiosoScreen.CREATE_GAME.name) {
            CreateGameScreen()
        }
        composable(route = MafiosoScreen.JOIN_GAME.name) {
            JoinGameScreen()
        }
        composable(route = MafiosoScreen.GAME.name) {
            GameScreen()
        }
    }
}
