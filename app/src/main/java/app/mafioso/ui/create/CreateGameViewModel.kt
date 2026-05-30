package app.mafioso.ui.create

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.util.UUID


data class CreateGameUiState(
    val name: String = "",
    val createGameId: UUID? = null,
)

class GameViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(CreateGameUiState())
    val uiState: StateFlow<CreateGameUiState> = _uiState.asStateFlow()

    fun updateName(name: String) {
        _uiState.update {
            it.copy(name = name)
        }
    }

    fun createGame() {
        // TODO: create game

        _uiState.update {
            it.copy(createGameId = UUID.randomUUID())
        }
    }
}
