package app.mafioso.data

import app.mafioso.role.Role
import java.util.UUID

data class Game(
    val id: UUID,
    val name: String,
    val players: List<Player> = listOf(),
) {

    fun initializeRoles(): Game {
        val roles: List<Role> = Role.createRoles(players.size).shuffled()
        players.forEachIndexed { index, player ->
            player.role = roles[index]
        }
        return this
    }
}
