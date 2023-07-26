package app.mafioso.data

import java.util.UUID

data class Game(
    val id: UUID,
    val name: String,
    val players: List<Player> = listOf(),
)
