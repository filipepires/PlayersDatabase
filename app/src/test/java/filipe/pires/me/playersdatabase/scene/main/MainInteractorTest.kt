package filipe.pires.me.playersdatabase.scene.main

import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import filipe.pires.me.playersdatabase.entity.Player
import filipe.pires.me.playersdatabase.io.DatabaseCallback
import org.junit.Test
import java.util.*

class MainInteractorTest {

    private val presenter = mock<MainContract.Presentation>()
    private val playerWorker = mock<MainContract.Business.DataManager>()
    private val interactor = MainInteractor(presenter, playerWorker)

    @Test
    fun `when resuming the view, fetch players`(){
        val callback = mock<DatabaseCallback<List<Player>>>()
        val player = Player("some id", "some name")
        val response = Arrays.asList(player)
        whenever(playerWorker.fetchPlayers(any())).thenAnswer {
            val playersCallback = it.arguments[0] as DatabaseCallback<List<Player>>
            playersCallback.onSuccess(response)
        }
        interactor.onResume()
        verify(presenter).presentPlayers(response)
    }
}