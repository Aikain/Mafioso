package app.mafioso.ui.create

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import app.mafioso.R
import java.util.UUID

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateGameScreen(
    goToCreateGameScreen: (UUID) -> Unit,
    backToHomeScreen: () -> Unit,
    modifier: Modifier = Modifier,
    createGameViewModel: GameViewModel = viewModel(),
) {
    val createGameUiState by createGameViewModel.uiState.collectAsState()
    var isLoading by remember { mutableStateOf(false) }

    createGameUiState.createGameId?.let {
        LaunchedEffect(key1 = it) {
            goToCreateGameScreen(it)
        }
    }

    TopAppBar(
        title = {},
        navigationIcon = {
            IconButton(onClick = backToHomeScreen) {
                Icon(Icons.AutoMirrored.Filled.ArrowBack, null)
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.Transparent,
            navigationIconContentColor = Color.Black,
        ),
    )

    Column(
        modifier = modifier
            .fillMaxHeight()
            .fillMaxWidth()
            .padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(32.dp),
    ) {
        Text(text = stringResource(R.string.create_game_title), fontSize = 32.sp)
        TextField(
            label = { Text(stringResource(R.string.create_game_name_label)) },
            value = createGameUiState.name,
            onValueChange = { createGameViewModel.updateName(it) },
            enabled = !isLoading,
        )
        Button(
            onClick = {
                isLoading = true
                createGameViewModel.createGame()
            },
            enabled = !isLoading,
        ) {
            if (isLoading) {
                CircularProgressIndicator()
            } else {
                Text(text = stringResource(R.string.create_game_btn))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CreateGameScreenPreview() {
    CreateGameScreen(
        goToCreateGameScreen = {},
        backToHomeScreen = {},
    )
}
