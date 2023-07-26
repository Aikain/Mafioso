package app.mafioso.ui.game

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import app.mafioso.data.Game
import app.mafioso.data.Player
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.util.UUID


enum class Status {
    INITIALIZE,
    RUNNING,
    FINISH,
}

enum class Phase {
    SUNSET,
    NIGHT,
    SUNRISE,
    DAY,
}

data class GameUiState(
    val game: Game,
    val leader: Boolean = true,
    val status: Status = Status.INITIALIZE,
    val phase: Phase? = null,
)

class GameViewModel(
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _uiState = MutableStateFlow(GameUiState(getGameById(UUID.fromString(savedStateHandle["gameId"]))))
    val uiState: StateFlow<GameUiState> = _uiState.asStateFlow()

    private fun getGameById(id: UUID): Game = Game(id, "Test")

    fun addPlayer(name: String) {
        _uiState.update {
            it.copy(
                game = it.game.copy(players = it.game.players.plus(Player((it.game.players.size + 1).toLong(), name)))
            )
        }
    }

    fun startGame() {
        _uiState.update {
            it.copy(
                game = it.game.initializeRoles(),
                status = Status.RUNNING,
                phase = Phase.SUNSET,
            )
        }
    }
}
