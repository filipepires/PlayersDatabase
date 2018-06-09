package filipe.pires.me.playersdatabase.scene.editplayer

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import filipe.pires.me.playersdatabase.R
import filipe.pires.me.playersdatabase.entity.PlayerDetails
import filipe.pires.me.playersdatabase.io.EditPlayerWorker
import kotlinx.android.synthetic.main.activity_edit_player.*

class EditPlayerActivity : AppCompatActivity(), EditPlayerContract.View {

    companion object {
        const val EXTRA_PLAYER_DETAILS = "extra.player_details"
    }

    private val interactor: EditPlayerContract.Business by lazy {
        EditPlayerInteractor(
                EditPlayerPresenter(this),
                EditPlayerWorker(),
                EditPlayerRouter(this)
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_player)
        interactor.onCreate(getPlayer())
        save.setOnClickListener { interactor.onSaveClicked(getPlayer().id, name.text.toString(), description.text.toString()) }
    }

    private fun getPlayer(): PlayerDetails = intent.getParcelableExtra(EXTRA_PLAYER_DETAILS)

    override fun displayReceivedPlayer(playerDetails: PlayerDetails) {
        name.setText(playerDetails.name)
        description.setText(playerDetails.description)
    }

    override fun displayMandatoryNameError() {
        Toast.makeText(applicationContext, getString(R.string.mandatory_error_message), Toast.LENGTH_SHORT).show()
    }

    override fun displayGeneralError() {
        Toast.makeText(applicationContext, getString(R.string.general_error_message), Toast.LENGTH_SHORT).show()
    }
}
