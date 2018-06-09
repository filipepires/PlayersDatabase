package filipe.pires.me.playersdatabase.scene.editplayer

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import filipe.pires.me.playersdatabase.R

class EditPlayerActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_PLAYER_DETAILS = "extra.player_details"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_player)
    }
}
