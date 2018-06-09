package filipe.pires.me.playersdatabase.scene.editplayer

import filipe.pires.me.playersdatabase.entity.PlayerDetails
import filipe.pires.me.playersdatabase.io.DatabaseCallback

interface EditPlayerContract {

    interface View {
        fun displayReceivedPlayer(playerDetails: PlayerDetails)
        fun displayMandatoryNameError()
        fun displayGeneralError()

    }

    interface Business {
        fun onCreate(playerDetails: PlayerDetails)
        fun onSaveClicked(playerId: String, name: String, description: String)
        interface DataManager {
            fun updatePlayerDetails(playerDetails: PlayerDetails, callback: DatabaseCallback<Any>)
        }
    }

    interface Presentation {
        fun presentReceivedPlayer(playerDetails: PlayerDetails)
        fun presenterMandatoryNameError()
        fun presentGeneralError()
    }

    interface Routes {
        fun routeToMain()
    }
}
