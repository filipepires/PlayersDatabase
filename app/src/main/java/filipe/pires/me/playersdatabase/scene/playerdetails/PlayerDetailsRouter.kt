package filipe.pires.me.playersdatabase.scene.playerdetails

import android.content.Context
import android.content.Intent
import filipe.pires.me.playersdatabase.entity.PlayerDetails
import filipe.pires.me.playersdatabase.scene.editplayer.EditPlayerActivity
import filipe.pires.me.playersdatabase.scene.main.MainActivity

class PlayerDetailsRouter(private val context: Context) : PlayerDetailsContract.Routes {

    override fun routeToEditPlayer(playerDetails: PlayerDetails) {
        val intent = Intent(context, EditPlayerActivity::class.java)
        intent.putExtra(EditPlayerActivity.EXTRA_PLAYER_DETAILS, playerDetails)
        context.startActivity(intent)
    }

    override fun routeToMain() {
        val intent = Intent(context, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        context.startActivity(intent)
    }
}
