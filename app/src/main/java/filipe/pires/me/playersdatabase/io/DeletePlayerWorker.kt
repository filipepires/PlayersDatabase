package filipe.pires.me.playersdatabase.io

import filipe.pires.me.playersapi.PlayersApi
import filipe.pires.me.playersapi.PlayersCallback
import filipe.pires.me.playersdatabase.scene.playerdetails.PlayerDetailsContract

class DeletePlayerWorker(private val api: PlayersApi = PlayersApi()) : PlayerDetailsContract.Business.RemovePlayer {

    override fun deletePlayer(playerId: String, callback: DatabaseCallback<Any>) {
        val apiCallback = object : PlayersCallback<Any> {
            override fun onSuccess(response: Any) {
                callback.onSuccess(Any())
            }

            override fun onFailure() {
                callback.onFailure()
            }
        }
        api.deletePlayer(playerId, apiCallback)
    }
}
