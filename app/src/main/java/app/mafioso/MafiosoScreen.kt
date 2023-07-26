package app.mafioso

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import app.mafioso.ui.HomeScreen
import app.mafioso.ui.create.CreateGameScreen
import app.mafioso.ui.game.GameScreen
import app.mafioso.ui.join.JoinGameScreen
import java.util.UUID

enum class MafiosoScreen() {
    HOME,
    CREATE_GAME,
    JOIN_GAME,
    GAME,
}

@Composable
fun MafiosoApp(
    navController: NavHostController = rememberNavController(),
) {
    NavHost(
        navController = navController,
        startDestination = MafiosoScreen.HOME.name,
    ) {
        composable(route = MafiosoScreen.HOME.name) {
            HomeScreen(
                goToCreateGameScreen = { navController.navigate(route = MafiosoScreen.CREATE_GAME.name) },
                goToJoinGameScreen = { navController.navigate(route = MafiosoScreen.JOIN_GAME.name) },
            )
        }
        composable(route = MafiosoScreen.CREATE_GAME.name) {
            CreateGameScreen(
                backToHomeScreen = {
                    navController.popBackStack(
                        route = MafiosoScreen.HOME.name,
                        inclusive = false
                    )
                },
                goToCreateGameScreen = { navController.navigate(route = MafiosoScreen.GAME.name + "/" + it) },
            )
        }
        composable(route = MafiosoScreen.JOIN_GAME.name) {
            JoinGameScreen(
                backToHomeScreen = {
                    navController.popBackStack(
                        route = MafiosoScreen.HOME.name,
                        inclusive = false
                    )
                },
                goToCreateGameScreen = { navController.navigate(route = MafiosoScreen.GAME.name + "/" + it) },
            )
        }
        composable(
            route = MafiosoScreen.GAME.name + "/{gameId}",
            arguments = listOf(navArgument("gameId") { type = NavType.StringType }),
        ) {
            GameScreen(
                backToHomeScreen = {
                    navController.popBackStack(
                        route = MafiosoScreen.HOME.name,
                        inclusive = false
                    )
                }
            )
        }
    }
}
