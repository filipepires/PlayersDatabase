package filipe.pires.me.playersdatabase.scene.playerdetails

import filipe.pires.me.playersdatabase.R
import filipe.pires.me.playersdatabase.entity.PlayerDetails
import filipe.pires.me.playersdatabase.utils.StringProvider

class PlayerDetailsPresenter(
        private val view: PlayerDetailsContract.View,
        private val stringProvider: StringProvider
) : PlayerDetailsContract.Presentation {

    override fun presentPlayerDetails(playerDetails: PlayerDetails) {
        view.displayName(playerDetails.name)
        if (playerDetails.description.isEmpty())
            view.displayDescription(stringProvider.getString(R.string.default_description))
        else
            view.displayDescription(playerDetails.description)
    }
}
