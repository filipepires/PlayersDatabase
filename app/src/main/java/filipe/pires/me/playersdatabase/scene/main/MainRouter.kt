package filipe.pires.me.playersdatabase.scene.main

import android.content.Context
import android.content.Intent
import filipe.pires.me.playersdatabase.scene.createplayer.CreatePlayerActivity
import filipe.pires.me.playersdatabase.scene.playerdetails.PlayerDetailsActivity

class MainRouter(private val context: Context) : MainContract.Routes {
    override fun routeToPlayerDetailsWith(id: String) {
        val intent = Intent(context, PlayerDetailsActivity::class.java)
        intent.putExtra(PlayerDetailsActivity.EXTRA_ID, id)
        context.startActivity(intent)
    }

    override fun routeToCreatePlayer() {
        val intent = Intent(context, CreatePlayerActivity::class.java)
        context.startActivity(intent)
    }
}
