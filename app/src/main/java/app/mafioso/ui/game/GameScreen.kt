package app.mafioso.ui.game

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import java.util.UUID

@Composable
fun GameScreen(
    gameId: UUID,
    modifier: Modifier = Modifier,
    viewModel: GameViewModel = viewModel(),
) {
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
    )
}
