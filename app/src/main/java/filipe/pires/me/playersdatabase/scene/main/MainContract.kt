package filipe.pires.me.playersdatabase.scene.main

import filipe.pires.me.playersdatabase.entity.Player
import filipe.pires.me.playersdatabase.io.DatabaseCallback

interface MainContract {

    interface View {

    }

    interface Business {
        fun onResume()

        interface DataManager {
            fun fetchPlayers(callback: DatabaseCallback<List<Player>>)
        }
    }

    interface Presentation {
        fun presentPlayers(response: List<Player>)

    }

}
