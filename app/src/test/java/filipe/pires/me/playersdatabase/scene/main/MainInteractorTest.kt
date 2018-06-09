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
    private val router = mock<MainContract.Routes>()
    private val interactor = MainInteractor(presenter, playerWorker, router)

    @Test
    fun `when resuming the view, fetch players`() {
        val player = Player("some id", "some name")
        val response = Arrays.asList(player)
        whenever(playerWorker.fetchPlayers(any())).thenAnswer {
            val playersCallback = it.arguments[0] as DatabaseCallback<List<Player>>
            playersCallback.onSuccess(response)
        }
        interactor.onResume()
        verify(presenter).presentPlayers(response)
    }

    @Test
    fun `when a player is selected, route to player details with id`() {
        interactor.onPlayerClicked("some id")
        verify(router).routeToPlayerDetailsWith("some id")
    }

    @Test
    fun `when players cannot be fetch, present general error`() {
        whenever(playerWorker.fetchPlayers(any())).thenAnswer {
            val playersCallback = it.arguments[0] as DatabaseCallback<List<Player>>
            playersCallback.onFailure()
        }
        interactor.onResume()
        verify(presenter).presentGeneralError()
    }
}