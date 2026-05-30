package app.mafioso.ui.join

import androidx.lifecycle.ViewModel
import app.mafioso.data.Game
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

data class JoinGameUiState(
    val games: List<Game> = listOf()
)

class JoinGameViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(JoinGameUiState())
    val uiState: StateFlow<JoinGameUiState> = _uiState.asStateFlow()

}
