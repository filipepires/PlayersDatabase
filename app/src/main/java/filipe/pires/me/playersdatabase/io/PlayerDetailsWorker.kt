package filipe.pires.me.playersdatabase.io

import filipe.pires.me.playersapi.DatabasePlayer
import filipe.pires.me.playersapi.PlayersApi
import filipe.pires.me.playersapi.PlayersCallback
import filipe.pires.me.playersdatabase.entity.PlayerDetails
import filipe.pires.me.playersdatabase.scene.playerdetails.PlayerDetailsContract

class PlayerDetailsWorker(private val api: PlayersApi = PlayersApi()) : PlayerDetailsContract.Business.GetPlayerDetails {

    override fun fetchPlayerWithId(playerId: String, callback: DatabaseCallback<PlayerDetails>) {
        val apiCallback = object : PlayersCallback<DatabasePlayer> {
            override fun onSuccess(response: DatabasePlayer) {
                callback.onSuccess(PlayerDetails(response.id, response.playerName, response.playerDescription))
            }

            override fun onFailure() {
                callback.onFailure()
            }
        }
        api.getPlayer(playerId, apiCallback)
    }
}
