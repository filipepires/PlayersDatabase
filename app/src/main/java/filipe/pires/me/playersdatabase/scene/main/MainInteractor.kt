package filipe.pires.me.playersdatabase.scene.main

import filipe.pires.me.playersdatabase.entity.Player
import filipe.pires.me.playersdatabase.io.DatabaseCallback

class MainInteractor(
        private val presenter: MainContract.Presentation,
        private val playerWorker: MainContract.Business.DataManager,
        private val router: MainContract.Routes
) : MainContract.Business {

    override fun onResume() {
        val callback = object : DatabaseCallback<List<Player>> {
            override fun onSuccess(response: List<Player>) {
                presenter.presentPlayers(response)
            }

            override fun onFailure() {
                presenter.presentGeneralError()
            }
        }
        playerWorker.fetchPlayers(callback)
    }

    override fun onPlayerClicked(id: String) {
        router.routeToPlayerDetailsWith(id)
    }

    override fun onAddClicked() {
        router.routeToCreatePlayer()
    }
}
