package app.mafioso.role

import app.mafioso.R

class Mafia : Role() {

    override fun getName(): String = "Mafia"

    override fun getDescription(): String = "Lorem ipsum"

    override fun getImage(): Int = R.drawable.mafia

    override fun wakeUpAtNight(night: Int): Boolean = true

    override fun wakeUpPriority(): Int = 50

    companion object : RoleCompanion {
        override fun createRoles(playerCount: Int): List<Role> =
            List(
                if (playerCount >= 17) 6
                else if (playerCount >= 14) 5
                else if (playerCount >= 11) 4
                else if (playerCount >= 9) 3
                else if (playerCount >= 7) 2
                else if (playerCount >= 1) 1
                else 0
            ) { Mafia() }
    }
}
