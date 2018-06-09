package filipe.pires.me.playersdatabase.scene.main

import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.Toast
import filipe.pires.me.playersdatabase.R
import filipe.pires.me.playersdatabase.entity.Player
import filipe.pires.me.playersdatabase.io.PlayersWorker
import filipe.pires.me.playersdatabase.scene.main.recycler.DefaultItemDecorator
import filipe.pires.me.playersdatabase.scene.main.recycler.PlayersAdapter
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(), MainContract.View {

    private val interactor: MainContract.Business by lazy {
        MainInteractor(
                MainPresenter(this),
                PlayersWorker(),
                MainRouter(applicationContext)
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        add.setOnClickListener { interactor.onAddClicked() }
    }

    override fun onResume() {
        super.onResume()
        interactor.onResume()
        player_list.layoutManager = LinearLayoutManager(applicationContext)
        player_list.addItemDecoration(DefaultItemDecorator(
                resources.getDimensionPixelSize(R.dimen.default_padding),
                ContextCompat.getDrawable(applicationContext, R.drawable.divider)
        ))
    }

    override fun displayEmptyListMessage() {
        player_list.visibility = GONE
        empty_list_message.visibility = VISIBLE
    }

    override fun displayPlayers(players: List<Player>) {
        player_list.visibility = VISIBLE
        empty_list_message.visibility = GONE

        val onItemClickListeners = ArrayList<View.OnClickListener>()
        players.forEach { player ->
            onItemClickListeners.add(
                    View.OnClickListener {
                        interactor.onPlayerClicked(player.id)
                    })
        }
        player_list.adapter = PlayersAdapter(players.map { it.name }, onItemClickListeners)
    }

    override fun displayGeneralError() {
        Toast.makeText(applicationContext, getString(R.string.general_error_message), Toast.LENGTH_SHORT).show()
    }
}