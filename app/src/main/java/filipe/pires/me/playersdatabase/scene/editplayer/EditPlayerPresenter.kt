package filipe.pires.me.playersdatabase.scene.editplayer

import filipe.pires.me.playersdatabase.entity.PlayerDetails

class EditPlayerPresenter(private val view: EditPlayerContract.View) : EditPlayerContract.Presentation {

    override fun presentReceivedPlayer(playerDetails: PlayerDetails) {
        view.displayReceivedPlayer(playerDetails)
    }

    override fun presenterMandatoryNameError() {
        view.displayMandatoryNameError()
    }

    override fun presentGeneralError() {
        view.displayGeneralError()
    }
}
