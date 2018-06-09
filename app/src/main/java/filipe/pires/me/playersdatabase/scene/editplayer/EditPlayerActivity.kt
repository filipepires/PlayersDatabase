package filipe.pires.me.playersdatabase.scene.editplayer

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import filipe.pires.me.playersdatabase.R
import filipe.pires.me.playersdatabase.entity.PlayerDetails
import kotlinx.android.synthetic.main.activity_edit_player.*

class EditPlayerActivity : AppCompatActivity(), EditPlayerContract.View {

    companion object {
        const val EXTRA_PLAYER_DETAILS = "extra.player_details"
    }

    private val editPlayerInteractor: EditPlayerContract.Business by lazy {
        EditPlayerInteractor(
                EditPlayerPresenter(this)
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_player)
        editPlayerInteractor.onCreate(intent.getParcelableExtra<PlayerDetails>(EXTRA_PLAYER_DETAILS))
    }

    override fun displayReceivedPlayer(playerDetails: PlayerDetails) {
        name.setText(playerDetails.name)
        description.setText(playerDetails.description)
    }
}
