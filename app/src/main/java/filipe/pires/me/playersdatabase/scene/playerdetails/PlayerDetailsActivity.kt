package filipe.pires.me.playersdatabase.scene.playerdetails

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import filipe.pires.me.playersdatabase.R


class PlayerDetailsActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_ID = "extra.id"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_player_details)
    }
}