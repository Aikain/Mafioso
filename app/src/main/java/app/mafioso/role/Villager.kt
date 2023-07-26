package app.mafioso.role

import app.mafioso.R

class Villager : Role() {

    override fun getName(): String = "Kyläläinen"

    override fun getDescription(): String = "Lorem ipsum"

    override fun getImage(): Int = R.drawable.villager

    override fun wakeUpAtNight(night: Int): Boolean = false

    companion object : RoleCompanion {
        override fun createRoles(playerCount: Int): List<Role> =
            List(
                if (playerCount >= 18) playerCount - 12
                else if (playerCount >= 15) 5
                else if (playerCount >= 12) 4
                else if (playerCount >= 8) 3
                else if (playerCount >= 6) 2
                else if (playerCount >= 4) 1
                else 0
            ) { Villager() }
    }
}
