package filipe.pires.me.playersdatabase.scene.playerdetails

import filipe.pires.me.playersdatabase.entity.PlayerDetails
import filipe.pires.me.playersdatabase.io.DatabaseCallback

interface PlayerDetailsContract {
    interface View {
        fun displayName(name: String)
        fun displayDescription(description: String)
        fun displayConfirmationDialog(playerId: String)
    }

    interface Business {
        fun onCreate(playerId: String)
        fun onOptionsItemSelected(itemId: Int, playerId: String)
        fun onConfirmation(playerId: String)

        interface GetPlayerDetails {
            fun fetchPlayerWithId(playerId: String, callback: DatabaseCallback<PlayerDetails>)
        }

        interface RemovePlayer {
            fun deletePlayer(playerId: String, callback: DatabaseCallback<Any>)
        }
    }

    interface Presentation {
        fun presentPlayerDetails(playerDetails: PlayerDetails)
        fun presentConfirmationDialog(playerId: String)
    }

    interface Routes {
        fun routeToEditPlayer(playerDetails: PlayerDetails)
        fun routeToMain()
    }
}
