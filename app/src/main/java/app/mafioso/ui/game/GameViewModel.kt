package app.mafioso.ui.game

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.mafioso.data.Game
import app.mafioso.data.Player
import app.mafioso.utils.map
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
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

    private val _uiState =
        MutableStateFlow(GameUiState(getGameById(UUID.fromString(savedStateHandle["gameId"]))))
    val uiState: StateFlow<GameUiState> = _uiState
        .map(coroutineScope = viewModelScope) {
            if (it.game.isGameOver()) it.copy(status = Status.FINISH) else it
        }

    private fun getGameById(id: UUID): Game = Game(id, "Test")

    fun addPlayer(name: String) {
        _uiState.update {
            it.copy(
                game = it.game.addPlayer(
                    Player(
                        (it.game.players.size + 1).toLong(),
                        name
                    )
                )
            )
        }
    }

    fun removePlayer(player: Player) {
        _uiState.update {
            it.copy(
                game = if (it.status == Status.INITIALIZE) it.game.removePlayer(player) else it.game.forceKillPlayer(
                    player
                )
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
