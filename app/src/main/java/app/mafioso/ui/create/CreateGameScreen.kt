package app.mafioso.ui.create

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import app.mafioso.R

@Composable
fun CreateGameScreen(
    modifier: Modifier = Modifier,
    createGameViewModel: GameViewModel = viewModel(),
) {
    val createGameUiState by createGameViewModel.uiState.collectAsState()

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
            label = { Text(stringResource(R.string.create_game_name_label))},
            value = createGameUiState.name,
            onValueChange = { createGameViewModel.updateName(it) },
        )
        Button(onClick = { /*TODO*/ }) {
            Text(text = stringResource(R.string.create_game_btn))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CreateGameScreenPreview() {
    CreateGameScreen()
}
