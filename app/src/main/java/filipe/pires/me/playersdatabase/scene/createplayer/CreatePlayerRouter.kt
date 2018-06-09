package filipe.pires.me.playersdatabase.scene.createplayer

import android.app.Activity
import android.content.Intent
import filipe.pires.me.playersdatabase.scene.main.MainActivity


class CreatePlayerRouter(private val activity: Activity) : CreatePlayerContract.Routes {

    override fun routeToMain() {
        val intent = Intent(activity, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        activity.startActivity(intent)
    }
}