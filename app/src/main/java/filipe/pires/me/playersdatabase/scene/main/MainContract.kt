package filipe.pires.me.playersdatabase.scene.main

import filipe.pires.me.playersdatabase.entity.Player
import filipe.pires.me.playersdatabase.io.DatabaseCallback

interface MainContract {

    interface View {
        fun displayEmptyListMessage()
        fun displayPlayers(players: List<Player>)

    }

    interface Business {
        fun onResume()
        fun onPlayerClicked(id: String)

        interface DataManager {
            fun fetchPlayers(callback: DatabaseCallback<List<Player>>)
        }
    }

    interface Presentation {
        fun presentPlayers(players: List<Player>)

    }

    interface Routes{
        fun routeToPlayerDetailsWith(id: String)
    }
}
