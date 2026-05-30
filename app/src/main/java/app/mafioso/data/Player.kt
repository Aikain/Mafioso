package app.mafioso.data

import app.mafioso.role.Role

data class Player(
    val id: Long,
    val name: String,
    var role: Role? = null,
    var alive: Boolean = true,
) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        other as Player
        return id == other.id
    }

    override fun hashCode(): Int {
        return id.hashCode()
    }
}
