package filipe.pires.me.playersdatabase.scene.playerdetails

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import filipe.pires.me.playersdatabase.R
import filipe.pires.me.playersdatabase.io.PlayerDetailsWorker
import filipe.pires.me.playersdatabase.utils.DefaultStringProvider
import kotlinx.android.synthetic.main.activity_player_details.*


class PlayerDetailsActivity : AppCompatActivity(), PlayerDetailsContract.View {

    companion object {
        const val EXTRA_ID = "extra.id"
    }

    private val interactor: PlayerDetailsContract.Business by lazy {
        PlayerDetailsInteractor(
                PlayerDetailsPresenter(this, DefaultStringProvider(applicationContext)),
                PlayerDetailsWorker()
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_player_details)
        interactor.onCreate(intent.getStringExtra(EXTRA_ID))
    }

    override fun displayName(name: String) {
        this.name.text = name
    }

    override fun displayDescription(description: String) {
        this.description.text = description
    }
}