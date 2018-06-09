package filipe.pires.me.playersdatabase.scene.editplayer

import filipe.pires.me.playersdatabase.entity.PlayerDetails

class EditPlayerInteractor(private val presenter: EditPlayerContract.Presentation) : EditPlayerContract.Business {

    override fun onCreate(playerDetails: PlayerDetails) {
        presenter.presentReceivedPlayer(playerDetails)
    }
}
