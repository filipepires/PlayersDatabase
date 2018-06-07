package filipe.pires.me.playersdatabase.io

import filipe.pires.me.playersapi.DatabasePlayer
import filipe.pires.me.playersapi.PlayersApi
import filipe.pires.me.playersapi.PlayersCallback
import filipe.pires.me.playersdatabase.entity.Player
import filipe.pires.me.playersdatabase.scene.main.MainContract

class DatabaseWorker(private val api: PlayersApi = PlayersApi()) : MainContract.Business.DataManager {


    override fun fetchPlayers(callback: DatabaseCallback<List<Player>>) {
        val apiCallback = object : PlayersCallback<List<DatabasePlayer>> {
            override fun onSuccess(response: List<DatabasePlayer>) {
                callback.onSuccess(response.map { Player(it.id, it.playerName) })
            }

            override fun onFailure() {
                callback.onFailure()
            }
        }
        api.getPlayers(apiCallback)
    }

}
