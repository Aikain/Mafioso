package app.mafioso.role

import app.mafioso.R

class Mayor : Villager() {

    override fun getName(): Int = R.string.mayor_name
    override fun getDescription(): Int = R.string.mayor_description
    override fun getImage(): Int = R.drawable.mayor

    companion object : RoleCompanion {
        override fun createRoles(playerCount: Int): List<Role> =
            List(if (playerCount >= 13) 1 else 0) { Mayor() }
    }
}
