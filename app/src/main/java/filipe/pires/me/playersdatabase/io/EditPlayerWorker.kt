package filipe.pires.me.playersdatabase.io

import filipe.pires.me.playersapi.DatabasePlayer
import filipe.pires.me.playersapi.PlayersApi
import filipe.pires.me.playersapi.PlayersCallback
import filipe.pires.me.playersdatabase.entity.PlayerDetails
import filipe.pires.me.playersdatabase.scene.editplayer.EditPlayerContract


class EditPlayerWorker(private val api: PlayersApi = PlayersApi()) : EditPlayerContract.Business.DataManager {

    override fun updatePlayerDetails(playerDetails: PlayerDetails, callback: DatabaseCallback<Any>) {
        val apiCallback = object : PlayersCallback<Any> {
            override fun onSuccess(response: Any) {
                callback.onSuccess(Any())
            }

            override fun onFailure() {
                callback.onFailure()
            }
        }
        api.updatePlayer(playerDetails.id, toDatabasePlayer(playerDetails), apiCallback)
    }

    private fun toDatabasePlayer(playerDetails: PlayerDetails): DatabasePlayer =
            DatabasePlayer(playerDetails.id, playerDetails.name, playerDetails.description)
}