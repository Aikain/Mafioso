package app.mafioso.role

import app.mafioso.R
import app.mafioso.data.Game

class Crazy : Role() {

    override fun getName(): Int = R.string.crazy_name
    override fun getDescription(): Int = R.string.crazy_description
    override fun getImage(): Int = R.drawable.crazy

    override fun checkWin(game: Game): Boolean = game.getAlivePlayers().size ==1 && this == game.getAlivePlayers()[0].role

    override fun wakeUpAtNight(night: Int): Boolean = true
    override fun wakeUpPriority(): Int = 30

    companion object : RoleCompanion {
        override fun createRoles(playerCount: Int): List<Role> =
            List(if (playerCount >= 5) 1 else 0) { Crazy() }
    }
}
