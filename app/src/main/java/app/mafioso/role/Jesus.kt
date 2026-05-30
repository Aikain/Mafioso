package app.mafioso.role

import app.mafioso.R

class Jesus : Villager() {

    override fun getName(): Int = R.string.jesus_name
    override fun getDescription(): Int = R.string.jesus_description
    override fun getImage(): Int = R.drawable.jesus

    companion object : RoleCompanion {
        override fun createRoles(playerCount: Int): List<Role> =
            List(if (playerCount >= 16) 1 else 0) { Jesus() }
    }
}
