package app.mafioso.role

import app.mafioso.data.Game

interface RoleCompanion {
    fun createRoles(playerCount: Int): List<Role>
}


abstract class Role {

    abstract fun getName(): Int
    abstract fun getDescription(): Int
    abstract fun getImage(): Int

    abstract fun checkWin(game: Game): Boolean

    abstract fun wakeUpAtNight(night: Int): Boolean
    open fun wakeUpPriority(): Int = 0

    companion object : RoleCompanion {

        override fun createRoles(playerCount: Int): List<Role> =
            Crazy.createRoles(playerCount) +
                    Cupid.createRoles(playerCount) +
                    Jesus.createRoles(playerCount) +
                    Mafia.createRoles(playerCount) +
                    Mayor.createRoles(playerCount) +
                    Nurse.createRoles(playerCount) +
                    Police.createRoles(playerCount) +
                    Villager.createRoles(playerCount)
    }
}
