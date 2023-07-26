package app.mafioso.role

import app.mafioso.R

class Cupid : Role() {

    override fun getName(): String = "Amor"

    override fun getDescription(): String = "Lorem ipsum"

    override fun getImage(): Int = R.drawable.cupid

    override fun wakeUpAtNight(night: Int): Boolean = night == 1

    override fun wakeUpPriority(): Int = 100

    companion object : RoleCompanion {
        override fun createRoles(playerCount: Int): List<Role> =
            List(if (playerCount >= 10) 1 else 0) { Cupid() }
    }
}
