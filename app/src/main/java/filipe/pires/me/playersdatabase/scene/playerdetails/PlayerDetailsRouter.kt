package filipe.pires.me.playersdatabase.scene.playerdetails

import android.app.Activity
import android.content.Intent
import filipe.pires.me.playersdatabase.entity.PlayerDetails
import filipe.pires.me.playersdatabase.scene.editplayer.EditPlayerActivity
import filipe.pires.me.playersdatabase.scene.main.MainActivity

class PlayerDetailsRouter(private val activity: Activity) : PlayerDetailsContract.Routes {

    override fun routeToEditPlayer(playerDetails: PlayerDetails) {
        val intent = Intent(activity, EditPlayerActivity::class.java)
        intent.putExtra(EditPlayerActivity.EXTRA_PLAYER_DETAILS, playerDetails)
        activity.startActivity(intent)
    }

    override fun routeToMain() {
        val intent = Intent(activity, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        activity.startActivity(intent)
    }
}
