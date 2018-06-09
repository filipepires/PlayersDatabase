package filipe.pires.me.playersdatabase.scene.main

import filipe.pires.me.playersdatabase.entity.Player

class MainPresenter(private val view: MainContract.View) : MainContract.Presentation {

    override fun presentPlayers(players: List<Player>) {
        if (players.isEmpty())
            view.displayEmptyListMessage()
        else
            view.displayPlayers(players)
    }

    override fun presentGeneralError() {
        view.displayGeneralError()
    }
}
