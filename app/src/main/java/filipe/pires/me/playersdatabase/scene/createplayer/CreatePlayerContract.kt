package filipe.pires.me.playersdatabase.scene.createplayer

import filipe.pires.me.playersdatabase.io.DatabaseCallback


interface CreatePlayerContract {

    interface View {
        fun displayMandatoryNameError()
        fun displayGeneralError()
    }

    interface Business {
        fun onSaveClicked(name: String, description: String)

        interface DataManager {
            fun createPlayer(name: String, description: String, callback: DatabaseCallback<Any>)
        }
    }

    interface Presentation {
        fun presenterMandatoryNameError()
        fun presentGeneralError()
    }

    interface Routes {
        fun routeToMain()
    }
}