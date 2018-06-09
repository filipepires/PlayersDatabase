package filipe.pires.me.playersdatabase.io

import filipe.pires.me.playersapi.PlayerInformation
import filipe.pires.me.playersapi.PlayersApi
import filipe.pires.me.playersapi.PlayersCallback
import filipe.pires.me.playersdatabase.scene.createplayer.CreatePlayerContract

class CreatePlayerWorker(private val api: PlayersApi = PlayersApi()) : CreatePlayerContract.Business.DataManager {

    override fun createPlayer(name: String, description: String, callback: DatabaseCallback<Any>) {
        val apiCallback = object : PlayersCallback<Any> {
            override fun onSuccess(response: Any) {
                callback.onSuccess(Any())
            }

            override fun onFailure() {
                callback.onFailure()
            }
        }
        api.addPlayer(PlayerInformation(name, description), apiCallback)
    }

}
