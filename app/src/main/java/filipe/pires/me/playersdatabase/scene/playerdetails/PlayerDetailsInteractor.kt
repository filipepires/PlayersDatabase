package filipe.pires.me.playersdatabase.scene.playerdetails

class PlayerDetailsInteractor(
        private val presenter: PlayerDetailsContract.Presentation,
        private val playerDetailsWorker: PlayerDetailsContract.Business.DataManager
) : PlayerDetailsContract.Business {

    override fun onCreate(playerId: String) {
        playerDetailsWorker.fetchPlayerWithId(playerId)
    }
}
