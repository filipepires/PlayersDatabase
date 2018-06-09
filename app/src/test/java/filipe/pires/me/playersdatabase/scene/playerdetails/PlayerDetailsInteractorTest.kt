package filipe.pires.me.playersdatabase.scene.playerdetails

import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import filipe.pires.me.playersdatabase.R
import filipe.pires.me.playersdatabase.entity.PlayerDetails
import filipe.pires.me.playersdatabase.io.DatabaseCallback
import org.junit.Test

class PlayerDetailsInteractorTest {

    private val presenter = mock<PlayerDetailsContract.Presentation>()
    private val playerDetailsWorker = mock<PlayerDetailsContract.Business.GetPlayerDetails>()
    private val router = mock<PlayerDetailsContract.Routes>()
    private val deletePlayerWorker = mock<PlayerDetailsContract.Business.RemovePlayer>()
    private val interactor = PlayerDetailsInteractor(presenter, playerDetailsWorker, router, deletePlayerWorker)

    @Test
    fun `when entering player details, fetch player details`() {
        val response = PlayerDetails("id", "name", "description")
        whenever(playerDetailsWorker.fetchPlayerWithId(any(), any())).thenAnswer {
            val playersCallback = it.arguments[1] as DatabaseCallback<PlayerDetails>
            playersCallback.onSuccess(response)
        }
        interactor.onCreate("some id")
        verify(presenter).presentPlayerDetails(response)
    }

    @Test
    fun `when player details fails, present general error`() {
        whenever(playerDetailsWorker.fetchPlayerWithId(any(), any())).thenAnswer {
            val playersCallback = it.arguments[1] as DatabaseCallback<PlayerDetails>
            playersCallback.onFailure()
        }
        interactor.onCreate("some id")
        verify(presenter).presentGeneralError()
    }

    @Test
    fun `when user selects edit option, move to edit with player details`() {
        interactor.playerDetails = mock()
        interactor.onOptionsItemSelected(R.id.menu_edit, "some id")
        verify(router).routeToEditPlayer(interactor.playerDetails)
    }

    @Test
    fun `when user selects delete option, show confirmation dialog`() {
        interactor.onOptionsItemSelected(R.id.menu_delete, "some id")
        verify(presenter).presentConfirmationDialog("some id")
    }

    @Test
    fun `when player is deleted, route to main`() {
        whenever(deletePlayerWorker.deletePlayer(any(), any())).thenAnswer {
            val playersCallback = it.arguments[1] as DatabaseCallback<Any>
            playersCallback.onSuccess(mock())
        }
        interactor.onConfirmation("some id")
        verify(router).routeToMain()
    }

    @Test
    fun `when player cannot be deleted, present general error`() {
        whenever(deletePlayerWorker.deletePlayer(any(), any())).thenAnswer {
            val playersCallback = it.arguments[1] as DatabaseCallback<Any>
            playersCallback.onFailure()
        }
        interactor.onConfirmation("some id")
        verify(presenter).presentGeneralError()
    }
}