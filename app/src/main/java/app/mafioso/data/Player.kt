package app.mafioso.data

import app.mafioso.role.Role

data class Player(
    val id: Long,
    val name: String,
    var role: Role? = null,
)
