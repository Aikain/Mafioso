package app.mafioso.ui.game

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import app.mafioso.R
import app.mafioso.data.Game
import app.mafioso.data.Player
import app.mafioso.role.Crazy
import app.mafioso.role.Mafia
import app.mafioso.role.Nurse
import app.mafioso.role.Police
import app.mafioso.role.Villager
import app.mafioso.ui.game.dialogs.AddPlayerDialog
import app.mafioso.ui.game.dialogs.ExitDialog
import java.util.UUID

@Composable
fun GameScreen(
    backToHomeScreen: () -> Unit,
    modifier: Modifier = Modifier,
    gameViewModel: GameViewModel = viewModel(),
) {
    val gameUiState by gameViewModel.uiState.collectAsState()
    var showExitDialog by remember { mutableStateOf(false) }

    BackHandler {
        showExitDialog = true
    }

    if (showExitDialog)
        ExitDialog(
            dismiss = { showExitDialog = false },
            confirm = {
                showExitDialog = false
                backToHomeScreen()
            },
            leader = gameUiState.leader,
        )

    GameView(
        gameUiState = gameUiState,
        addPlayer = { name -> gameViewModel.addPlayer(name) },
        removePlayer = { player -> gameViewModel.removePlayer(player) },
        startGame = { gameViewModel.startGame() },
        backToHomeScreen = backToHomeScreen,
        modifier = modifier,
    )
}

@Composable
fun GameView(
    gameUiState: GameUiState,
    addPlayer: (String) -> Unit,
    removePlayer: (Player) -> Unit,
    startGame: () -> Unit,
    backToHomeScreen: () -> Unit,
    modifier: Modifier = Modifier,
) {
    var showAddPlayerDialog by remember { mutableStateOf(false) }

    if (showAddPlayerDialog)
        AddPlayerDialog(
            dismiss = { showAddPlayerDialog = false },
            addPlayer = { name ->
                showAddPlayerDialog = false
                addPlayer(name)
            }
        )

    Column(
        modifier = modifier
            .fillMaxHeight()
            .fillMaxWidth()
            .padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(32.dp),
    ) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(4),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            items(
                gameUiState.game.players,
                key = { it.id }
            ) { player ->
                PlayerCard(
                    player = player,
                    status = gameUiState.status,
                    removePlayer = { removePlayer(player) },
                )
            }
        }
        when (gameUiState.status) {
            Status.INITIALIZE ->
                Row(
                    modifier = Modifier.padding(horizontal = 16.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                ) {
                    Button(
                        onClick = { showAddPlayerDialog = true },
                        modifier = Modifier.weight(1f),
                    ) {
                        Text(text = stringResource(R.string.add_plaeyer_btn))
                    }
                    Button(
                        onClick = startGame,
                        modifier = Modifier.weight(1f),
                    ) {
                        Text(text = stringResource(R.string.start_game_btn))
                    }
                }

            Status.RUNNING -> Text(text = "Peli käynnistetty")
            Status.FINISH -> {
                Text(text = stringResource(R.string.game_over))
                Button(onClick = backToHomeScreen) {
                    Text(stringResource(R.string.back_to_home_btn))
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GameViewPreview() {
    GameView(
        gameUiState = GameUiState(
            game = Game(
                id = UUID.randomUUID(),
                name = "Esimerkki peli",
                players = listOf(
                    Player(1, "Lumikki"),
                    Player(2, "Viisas"),
                    Player(3, "Jörö"),
                    Player(4, "Lystikäs"),
                    Player(5, "Unelias"),
                    Player(6, "Ujo"),
                    Player(7, "Nuhanenä"),
                    Player(8, "Vilkas"),
                )
            )
        ),
        addPlayer = {},
        removePlayer = {},
        startGame = {},
        backToHomeScreen = {},
    )
}


@Preview(showBackground = true)
@Composable
fun GameViewFinishPreview() {
    GameView(
        gameUiState = GameUiState(
            game = Game(
                id = UUID.randomUUID(),
                name = "Esimerkki peli",
                players = listOf(
                    Player(1, "Lumikki", role = Mafia()),
                    Player(2, "Viisas", role = Villager(), alive = false),
                    Player(3, "Jörö", role = Police(), alive = false),
                    Player(4, "Lystikäs", role = Nurse(), alive = false),
                    Player(5, "Unelias", role = Mafia(), alive = false),
                    Player(6, "Ujo", role = Crazy(), alive = false),
                    Player(7, "Nuhanenä", role = Villager(), alive = false),
                    Player(8, "Vilkas", role = Mafia()),
                ),
            ),
            status = Status.FINISH,
        ),
        addPlayer = {},
        removePlayer = {},
        startGame = {},
        backToHomeScreen = {},
    )
}
