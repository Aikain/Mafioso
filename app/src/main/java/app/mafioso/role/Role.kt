package app.mafioso.role

interface RoleCompanion {
    fun createRoles(playerCount: Int): List<Role>
}


abstract class Role {

    abstract fun getName(): String
    abstract fun getDescription(): String
    abstract fun getImage(): Int

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
