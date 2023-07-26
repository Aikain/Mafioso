package app.mafioso.role

import app.mafioso.R

class Nurse : Role() {

    override fun getName(): String = "Hoitaja"

    override fun getDescription(): String = "Lorem ipsum"

    override fun getImage(): Int = R.drawable.nurse

    override fun wakeUpAtNight(night: Int): Boolean = true

    override fun wakeUpPriority(): Int = 20

    companion object : RoleCompanion {
        override fun createRoles(playerCount: Int): List<Role> =
            List(if (playerCount >= 3) 1 else 0) { Nurse() }
    }
}
