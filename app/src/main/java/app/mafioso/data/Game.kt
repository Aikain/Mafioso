package app.mafioso.data

import app.mafioso.role.Role
import java.util.UUID

data class Game(
    val id: UUID,
    val name: String,
    val players: List<Player> = listOf(),
    val updateTime: Long = 0,
) {

    fun getAlivePlayers(): List<Player> = players.filter { it.alive }

    fun initializeRoles(): Game {
        val roles: List<Role> = Role.createRoles(players.size).shuffled()
        players.forEachIndexed { index, player ->
            player.role = roles[index]
        }
        return this
    }

    fun addPlayer(player: Player): Game {
        return this.copy(
            updateTime = System.currentTimeMillis(),
            players = players.plus(player),
        )
    }

    fun removePlayer(player: Player): Game {
        return this.copy(
            updateTime = System.currentTimeMillis(),
            players = players.minus(player),
        )
    }

    fun forceKillPlayer(player: Player): Game {
        return this.copy(
            updateTime = System.currentTimeMillis(),
            players = players.map { if (it == player) it.copy(alive = false) else it },
        )
    }

    fun isGameOver(): Boolean {
        players.forEach {
            if (it.role?.checkWin(this) == true) return true
        }
        return false
    }
}
