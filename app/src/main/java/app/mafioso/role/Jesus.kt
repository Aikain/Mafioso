package app.mafioso.role

import app.mafioso.R

class Jesus : Role() {

    override fun getName(): String = "Jeesus"

    override fun getDescription(): String = "Lorem ipsum"

    override fun getImage(): Int = R.drawable.jesus

    override fun wakeUpAtNight(night: Int): Boolean = false

    companion object : RoleCompanion {
        override fun createRoles(playerCount: Int): List<Role> =
            List(if (playerCount >= 16) 1 else 0) { Jesus() }
    }
}
