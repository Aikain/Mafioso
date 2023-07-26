package app.mafioso.role

import app.mafioso.R

class Crazy : Role() {

    override fun getName(): String = "Hullu"

    override fun getDescription(): String = "Lorem ipsum"

    override fun getImage(): Int = R.drawable.crazy

    override fun wakeUpAtNight(night: Int): Boolean = true

    override fun wakeUpPriority(): Int = 30

    companion object : RoleCompanion {
        override fun createRoles(playerCount: Int): List<Role> =
            List(if (playerCount >= 5) 1 else 0) { Crazy() }
    }
}
