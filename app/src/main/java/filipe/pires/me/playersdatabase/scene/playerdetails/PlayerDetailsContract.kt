package filipe.pires.me.playersdatabase.scene.playerdetails

import filipe.pires.me.playersdatabase.entity.PlayerDetails
import filipe.pires.me.playersdatabase.io.DatabaseCallback

interface PlayerDetailsContract {
    interface View {

    }

    interface Business {
        fun onCreate(playerId: String)

        interface DataManager {
            fun fetchPlayerWithId(playerId: String, callback: DatabaseCallback<PlayerDetails>)
        }
    }

    interface Presentation {
        fun presentPlayerDetails(playerDetails: PlayerDetails)
    }
}
