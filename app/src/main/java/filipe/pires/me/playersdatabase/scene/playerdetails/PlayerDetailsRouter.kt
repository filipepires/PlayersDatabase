package filipe.pires.me.playersdatabase.scene.playerdetails

import android.content.Context
import android.content.Intent
import filipe.pires.me.playersdatabase.entity.PlayerDetails
import filipe.pires.me.playersdatabase.scene.editplayer.EditPlayerActivity

class PlayerDetailsRouter(private val context: Context) : PlayerDetailsContract.Routes {
    override fun routeToEditPlayer(playerDetails: PlayerDetails) {
        val intent = Intent(context, EditPlayerActivity::class.java)
        intent.putExtra(EditPlayerActivity.EXTRA_PLAYER_DETAILS, playerDetails)
        context.startActivity(intent)
    }

}
