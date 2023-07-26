package app.mafioso.role

import app.mafioso.R
import app.mafioso.data.Game

class Cupid : Villager() {

    override fun getName(): Int = R.string.cupid_name
    override fun getDescription(): Int = R.string.cupid_description
    override fun getImage(): Int = R.drawable.cupid

    // TODO: rakastavaiset
    override fun checkWin(game: Game): Boolean = false

    override fun wakeUpAtNight(night: Int): Boolean = night == 1
    override fun wakeUpPriority(): Int = 100

    companion object : RoleCompanion {
        override fun createRoles(playerCount: Int): List<Role> =
            List(if (playerCount >= 10) 1 else 0) { Cupid() }
    }
}
