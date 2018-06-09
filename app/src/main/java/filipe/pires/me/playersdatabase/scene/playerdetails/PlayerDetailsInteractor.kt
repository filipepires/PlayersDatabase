package filipe.pires.me.playersdatabase.scene.playerdetails

import android.support.annotation.VisibleForTesting
import filipe.pires.me.playersdatabase.R
import filipe.pires.me.playersdatabase.entity.PlayerDetails
import filipe.pires.me.playersdatabase.io.DatabaseCallback

class PlayerDetailsInteractor(
        private val presenter: PlayerDetailsContract.Presentation,
        private val playerDetailsWorker: PlayerDetailsContract.Business.DataManager,
        private val router: PlayerDetailsContract.Routes
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

            }
        }
        playerDetailsWorker.fetchPlayerWithId(playerId, callback)
    }

    override fun onOptionsItemSelected(itemId: Int, playerId: String) {
        when (itemId) {
            R.id.menu_edit -> router.routeToEditPlayer(playerDetails)
        }
    }
}
