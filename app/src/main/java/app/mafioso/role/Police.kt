package app.mafioso.role

import app.mafioso.R

class Police : Role() {

    override fun getName(): String = "Poliisi"

    override fun getDescription(): String = "Lorem ipsum"

    override fun getImage(): Int = R.drawable.police

    override fun wakeUpAtNight(night: Int): Boolean = true

    override fun wakeUpPriority(): Int = 40

    companion object : RoleCompanion {
        override fun createRoles(playerCount: Int): List<Role> =
            List(if (playerCount >= 2) 1 else 0) { Police() }
    }
}
