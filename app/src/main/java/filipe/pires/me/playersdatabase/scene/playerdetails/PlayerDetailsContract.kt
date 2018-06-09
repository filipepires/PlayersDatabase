package filipe.pires.me.playersdatabase.scene.playerdetails

import filipe.pires.me.playersdatabase.entity.PlayerDetails
import filipe.pires.me.playersdatabase.io.DatabaseCallback

interface PlayerDetailsContract {
    interface View {
        fun displayName(name: String)
        fun displayDescription(description: String)

    }

    interface Business {
        fun onCreate(playerId: String)
        fun onOptionsItemSelected(itemId: Int, playerId: String)

        interface DataManager {
            fun fetchPlayerWithId(playerId: String, callback: DatabaseCallback<PlayerDetails>)
        }
    }

    interface Presentation {
        fun presentPlayerDetails(playerDetails: PlayerDetails)
    }

    interface Routes {
        fun routeToEditPlayer(playerDetails: PlayerDetails)
    }
}
