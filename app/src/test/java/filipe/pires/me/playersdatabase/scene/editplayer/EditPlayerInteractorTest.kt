package filipe.pires.me.playersdatabase.scene.editplayer

import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import filipe.pires.me.playersdatabase.entity.PlayerDetails
import filipe.pires.me.playersdatabase.io.DatabaseCallback
import org.junit.Test

class EditPlayerInteractorTest {

    private val presenter = mock<EditPlayerContract.Presentation>()
    private val editPlayerWorker = mock<EditPlayerContract.Business.DataManager>()
    private val router = mock<EditPlayerContract.Routes>()
    private val interactor = EditPlayerInteractor(presenter, editPlayerWorker, router)

    @Test
    fun `when entering the view, present player details`() {
        val playerDetails = mock<PlayerDetails>()
        interactor.onCreate(playerDetails)
        verify(presenter).presentReceivedPlayer(playerDetails)
    }

    @Test
    fun `when user tries to save player without name, show error message`() {
        interactor.onSaveClicked("some player id", "", "some description")
        verify(presenter).presenterMandatoryNameError()
    }

    @Test
    fun `when user clicks save, save player details`() {
        whenever(editPlayerWorker.updatePlayerDetails(any(), any())).thenAnswer {
            val playersCallback = it.arguments[1] as DatabaseCallback<Any>
            playersCallback.onSuccess(mock())
        }
        interactor.onSaveClicked("some id", "some name", "some description")
        verify(router).routeToMain()
    }

    @Test
    fun `when save fails, present general error message`() {
        whenever(editPlayerWorker.updatePlayerDetails(any(), any())).thenAnswer {
            val playersCallback = it.arguments[1] as DatabaseCallback<Any>
            playersCallback.onFailure()
        }
        interactor.onSaveClicked("some id", "some name", "some description")
        verify(presenter).presentGeneralError()
    }
}