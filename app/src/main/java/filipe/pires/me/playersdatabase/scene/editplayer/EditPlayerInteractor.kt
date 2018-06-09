package filipe.pires.me.playersdatabase.scene.editplayer

import filipe.pires.me.playersdatabase.entity.PlayerDetails
import filipe.pires.me.playersdatabase.io.DatabaseCallback

class EditPlayerInteractor(
        private val presenter: EditPlayerContract.Presentation,
        private val editPlayerWorker: EditPlayerContract.Business.DataManager,
        private val router : EditPlayerContract.Routes
) : EditPlayerContract.Business {

    override fun onCreate(playerDetails: PlayerDetails) {
        presenter.presentReceivedPlayer(playerDetails)
    }

    override fun onSaveClicked(playerId: String, name: String, description: String) {
        if (name.isEmpty())
            presenter.presenterMandatoryNameError()
        else {
            val callback = object : DatabaseCallback<Any> {
                override fun onSuccess(response: Any) {
                    router.routeToMain()
                }

                override fun onFailure() {
                    presenter.presentGeneralError()
                }

            }
            editPlayerWorker.updatePlayerDetails(PlayerDetails(playerId, name, description), callback)
        }
    }
}
