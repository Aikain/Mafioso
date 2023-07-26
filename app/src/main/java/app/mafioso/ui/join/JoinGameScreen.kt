package app.mafioso.ui.join

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import app.mafioso.R
import app.mafioso.data.Game
import java.util.UUID

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun JoinGameScreen(
    goToCreateGameScreen: (UUID) -> Unit,
    backToHomeScreen: () -> Unit,
    modifier: Modifier = Modifier,
    joinGameViewModel: JoinGameViewModel = viewModel()
) {
    val joinGameUiState by joinGameViewModel.uiState.collectAsState()

    TopAppBar(
        title = {},
        navigationIcon = {
            IconButton(onClick = backToHomeScreen) {
                Icon(Icons.Filled.ArrowBack, null)
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.Transparent,
            navigationIconContentColor = Color.Black,
        ),
    )

    GameList(
        games = joinGameUiState.games,
        goToCreateGameScreen = goToCreateGameScreen,
        modifier = modifier,
    )
}

@Composable
fun GameList(
    games: List<Game>,
    goToCreateGameScreen: (UUID) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxHeight()
            .fillMaxWidth()
            .padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(32.dp),
    ) {
        Text(
            text = stringResource(R.string.join_game),
            fontSize = 32.sp,
            modifier = Modifier
        )
        LazyColumn {
            items(games) {
                Button(
                    onClick = { goToCreateGameScreen(it.id) },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(text = it.name)
                }
            }
        }
        if (games.isEmpty()) {
            Text(
                text = stringResource(R.string.games_empty),
                fontSize = 16.sp,
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun JoinGameScreenPreview() {
    GameList(
        games = listOf(
            Game(UUID.randomUUID(), "Peli 1"),
            Game(UUID.randomUUID(), "Peli 2"),
            Game(UUID.randomUUID(), "Peli 3"),
            Game(UUID.randomUUID(), "Peli 4"),
        ),
        goToCreateGameScreen = {},
    )
}

@Preview(showBackground = true)
@Composable
fun JoinGameScreenEmptyListPreview() {
    GameList(
        games = listOf(),
        goToCreateGameScreen = {},
    )
}
