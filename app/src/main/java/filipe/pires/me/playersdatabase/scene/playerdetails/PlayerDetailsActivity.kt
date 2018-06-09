package filipe.pires.me.playersdatabase.scene.playerdetails

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
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
                PlayerDetailsWorker(),
                PlayerDetailsRouter(applicationContext)
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

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.player_options, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        interactor.onOptionsItemSelected(item.itemId, intent.getStringExtra(EXTRA_ID))
        return super.onOptionsItemSelected(item);
    }
}
