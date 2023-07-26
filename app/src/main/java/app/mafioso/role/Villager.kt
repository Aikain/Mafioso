package app.mafioso.role

import app.mafioso.R
import app.mafioso.data.Game

open class Villager : Role() {

    override fun getName(): Int = R.string.villager_name
    override fun getDescription(): Int = R.string.villager_description
    override fun getImage(): Int = R.drawable.villager

    override fun checkWin(game: Game): Boolean {
        game.getAlivePlayers().forEach {
            if (it.role !is Villager) return false
        }
        return game.getAlivePlayers().isNotEmpty()
    }

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
