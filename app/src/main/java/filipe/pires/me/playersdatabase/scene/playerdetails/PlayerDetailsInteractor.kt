package filipe.pires.me.playersdatabase.scene.playerdetails

import android.support.annotation.VisibleForTesting
import filipe.pires.me.playersdatabase.R
import filipe.pires.me.playersdatabase.entity.PlayerDetails
import filipe.pires.me.playersdatabase.io.DatabaseCallback

class PlayerDetailsInteractor(
        private val presenter: PlayerDetailsContract.Presentation,
        private val playerDetailsWorker: PlayerDetailsContract.Business.GetPlayerDetails,
        private val router: PlayerDetailsContract.Routes,
        private val deletePlayerWorker: PlayerDetailsContract.Business.RemovePlayer
) : PlayerDetailsContract.Business {

    @VisibleForTesting
    lateinit var playerDetails: PlayerDetails

    override fun onCreate(playerId: String) {
        val callback = object : DatabaseCallback<PlayerDetails> {
            override fun onSuccess(response: PlayerDetails) {
                playerDetails = response
                presenter.presentPlayerDetails(response)
            }

            override fun onFailure() {
                presenter.presentGeneralError()
            }
        }
        playerDetailsWorker.fetchPlayerWithId(playerId, callback)
    }

    override fun onOptionsItemSelected(itemId: Int, playerId: String) {
        when (itemId) {
            R.id.menu_edit -> router.routeToEditPlayer(playerDetails)
            R.id.menu_delete -> presenter.presentConfirmationDialog(playerId)
        }
    }

    override fun onConfirmation(playerId: String) {
        val callback = object : DatabaseCallback<Any> {
            override fun onSuccess(response: Any) {
                router.routeToMain()
            }

            override fun onFailure() {
                presenter.presentGeneralError()
            }
        }
        deletePlayerWorker.deletePlayer(playerId, callback)
    }
}
