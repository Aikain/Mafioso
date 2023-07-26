package app.mafioso.role

import app.mafioso.R

class Nurse : Villager() {

    override fun getName(): Int = R.string.nurse_name
    override fun getDescription(): Int = R.string.nurse_description
    override fun getImage(): Int = R.drawable.nurse

    override fun wakeUpAtNight(night: Int): Boolean = true
    override fun wakeUpPriority(): Int = 20

    companion object : RoleCompanion {
        override fun createRoles(playerCount: Int): List<Role> =
            List(if (playerCount >= 3) 1 else 0) { Nurse() }
    }
}
