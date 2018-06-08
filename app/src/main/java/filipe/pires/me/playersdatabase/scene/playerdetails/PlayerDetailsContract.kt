package filipe.pires.me.playersdatabase.scene.playerdetails

interface PlayerDetailsContract {
    interface View {

    }

    interface Business {
        fun onCreate(playerId: String)

        interface DataManager {
            fun fetchPlayerWithId(playerId: String)
        }
    }

    interface Presentation {

    }
}
