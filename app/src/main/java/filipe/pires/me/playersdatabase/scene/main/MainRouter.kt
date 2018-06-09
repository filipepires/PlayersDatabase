package filipe.pires.me.playersdatabase.scene.main

import android.app.Activity
import android.content.Intent
import filipe.pires.me.playersdatabase.scene.createplayer.CreatePlayerActivity
import filipe.pires.me.playersdatabase.scene.playerdetails.PlayerDetailsActivity

class MainRouter(private val activity: Activity) : MainContract.Routes {
    override fun routeToPlayerDetailsWith(id: String) {
        val intent = Intent(activity, PlayerDetailsActivity::class.java)
        intent.putExtra(PlayerDetailsActivity.EXTRA_ID, id)
        activity.startActivity(intent)
    }

    override fun routeToCreatePlayer() {
        val intent = Intent(activity, CreatePlayerActivity::class.java)
        activity.startActivity(intent)
    }
}
