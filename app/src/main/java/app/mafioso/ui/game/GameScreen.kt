package app.mafioso.ui.game

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import app.mafioso.R
import java.util.UUID

@Composable
fun GameScreen(
    gameId: UUID,
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
        AlertDialog(
            onDismissRequest = { showExitDialog = false },
            title = { Text(text = stringResource(R.string.game_exit_confirm_title)) },
            text = {
                Text(
                    text = stringResource(if (gameUiState.leader) R.string.game_exit_leader_text else R.string.game_exit_normal_text),
                )
            },
            confirmButton = {
                Button(onClick = {
                    showExitDialog = false
                    backToHomeScreen()
                }) {
                    Text(stringResource(R.string.game_exit_confirm_btn))
                }
            },
            dismissButton = {
                Button(onClick = { showExitDialog = false }) {
                    Text(stringResource(R.string.game_exit_dismiss_btn))
                }
            },
        )

    Column(
        modifier = modifier
            .fillMaxHeight()
            .fillMaxWidth()
    ) {
        Text(
            text = gameId.toString(),
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GameScreenPreview() {
    GameScreen(
        gameId = UUID.randomUUID(),
        backToHomeScreen = {}
    )
}
