package filipe.pires.me.playersdatabase.scene.createplayer

import filipe.pires.me.playersdatabase.io.DatabaseCallback


class CreatePlayerInteractor(
        private val presenter: CreatePlayerContract.Presentation,
        private val createPlayerWorker: CreatePlayerContract.Business.DataManager,
        private val router: CreatePlayerContract.Routes
) : CreatePlayerContract.Business {

    override fun onSaveClicked(name: String, description: String) {
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
            createPlayerWorker.createPlayer(name, description, callback)
        }
    }
}