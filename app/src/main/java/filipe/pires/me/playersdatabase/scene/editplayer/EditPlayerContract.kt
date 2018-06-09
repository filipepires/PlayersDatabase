package filipe.pires.me.playersdatabase.scene.editplayer

import filipe.pires.me.playersdatabase.entity.PlayerDetails

interface EditPlayerContract {

    interface View {
        fun displayReceivedPlayer(playerDetails: PlayerDetails)

    }

    interface Business {
        fun onCreate(playerDetails: PlayerDetails)
    }

    interface Presentation {
        fun presentReceivedPlayer(playerDetails: PlayerDetails)

    }
}
