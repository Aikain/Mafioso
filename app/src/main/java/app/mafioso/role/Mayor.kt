package app.mafioso.role

import app.mafioso.R

class Mayor : Role() {

    override fun getName(): String = "Pormestari"

    override fun getDescription(): String = "Lorem ipsum"

    override fun getImage(): Int = R.drawable.mayor

    override fun wakeUpAtNight(night: Int): Boolean = false

    companion object : RoleCompanion {
        override fun createRoles(playerCount: Int): List<Role> =
            List(if (playerCount >= 13) 1 else 0) { Mayor() }
    }
}
