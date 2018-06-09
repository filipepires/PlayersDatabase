package filipe.pires.me.playersdatabase.scene.createplayer

import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import filipe.pires.me.playersdatabase.io.DatabaseCallback
import org.junit.Test

class CreatePlayerInteractorTest {

    private val presenter = mock<CreatePlayerContract.Presentation>()
    private val createPlayerWorker = mock<CreatePlayerContract.Business.DataManager>()
    private val router = mock<CreatePlayerContract.Routes>()
    private val interactor = CreatePlayerInteractor(presenter, createPlayerWorker, router)

    @Test
    fun `when user tries to save player without name, show error message`() {
        interactor.onSaveClicked("", "some description")
        verify(presenter).presenterMandatoryNameError()
    }

    @Test
    fun `when save is clicked, save player`() {
        whenever(createPlayerWorker.createPlayer(any(), any(), any())).thenAnswer {
            val playersCallback = it.arguments[2] as DatabaseCallback<Any>
            playersCallback.onSuccess(mock())
        }
        interactor.onSaveClicked("some name", "some description")
        verify(router).routeToMain()
    }

    @Test
    fun `when save fails, present general message`() {
        whenever(createPlayerWorker.createPlayer(any(), any(), any())).thenAnswer {
            val playersCallback = it.arguments[2] as DatabaseCallback<Any>
            playersCallback.onFailure()
        }
        interactor.onSaveClicked("some name", "some description")
        verify(presenter).presentGeneralError()
    }
}