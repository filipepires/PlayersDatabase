package filipe.pires.me.playersdatabase.scene.createplayer

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import filipe.pires.me.playersdatabase.R
import filipe.pires.me.playersdatabase.io.CreatePlayerWorker
import kotlinx.android.synthetic.main.activity_create_player.*


class CreatePlayerActivity : AppCompatActivity(), CreatePlayerContract.View {

    private val interactor: CreatePlayerContract.Business by lazy {
        CreatePlayerInteractor(
                CreatePlayerPresenter(this),
                CreatePlayerWorker(),
                CreatePlayerRouter(this)
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_player)
        save.setOnClickListener { interactor.onSaveClicked(name.text.toString(), description.text.toString()) }
    }

    override fun displayMandatoryNameError() {
        Toast.makeText(applicationContext, getString(R.string.mandatory_error_message), Toast.LENGTH_SHORT).show()
    }

    override fun displayGeneralError() {
        Toast.makeText(applicationContext, getString(R.string.general_error_message), Toast.LENGTH_SHORT).show()
    }
}