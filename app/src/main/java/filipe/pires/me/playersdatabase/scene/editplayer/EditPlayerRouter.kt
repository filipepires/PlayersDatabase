package filipe.pires.me.playersdatabase.scene.editplayer

import android.content.Context
import android.content.Intent
import filipe.pires.me.playersdatabase.scene.main.MainActivity

class EditPlayerRouter(private val context: Context) : EditPlayerContract.Routes {

    override fun routeToMain() {
        val intent = Intent(context, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        context.startActivity(intent)
    }
}
