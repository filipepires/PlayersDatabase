package filipe.pires.me.playersdatabase.scene.main

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import filipe.pires.me.playersdatabase.R
import filipe.pires.me.playersdatabase.entity.Player
import filipe.pires.me.playersdatabase.io.DatabaseWorker


class MainActivity : AppCompatActivity(), MainContract.View {

    private val interactor: MainContract.Business by lazy { MainInteractor(MainPresenter(this), DatabaseWorker()) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onResume() {
        super.onResume()
        interactor.onResume()
    }

    override fun displayEmptyListMessage() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun displayPlayers(players: List<Player>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}