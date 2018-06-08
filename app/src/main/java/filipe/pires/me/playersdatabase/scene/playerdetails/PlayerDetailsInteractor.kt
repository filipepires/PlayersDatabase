package filipe.pires.me.playersdatabase.scene.playerdetails

import filipe.pires.me.playersdatabase.entity.PlayerDetails
import filipe.pires.me.playersdatabase.io.DatabaseCallback

class PlayerDetailsInteractor(
        private val presenter: PlayerDetailsContract.Presentation,
        private val playerDetailsWorker: PlayerDetailsContract.Business.DataManager
) : PlayerDetailsContract.Business {

    override fun onCreate(playerId: String) {
        val callback = object : DatabaseCallback<PlayerDetails> {
            override fun onSuccess(response: PlayerDetails) {
                presenter.presentPlayerDetails(response)
            }

            override fun onFailure() {

            }
        }
        playerDetailsWorker.fetchPlayerWithId(playerId, callback)
    }
}
