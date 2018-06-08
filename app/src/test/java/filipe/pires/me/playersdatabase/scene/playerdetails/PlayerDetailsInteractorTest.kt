package filipe.pires.me.playersdatabase.scene.playerdetails

import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import filipe.pires.me.playersdatabase.entity.PlayerDetails
import filipe.pires.me.playersdatabase.io.DatabaseCallback
import org.junit.Test

class PlayerDetailsInteractorTest {

    private val presenter = mock<PlayerDetailsContract.Presentation>()
    private val playerDetailsWorker = mock<PlayerDetailsContract.Business.DataManager>()
    private val interactor = PlayerDetailsInteractor(presenter, playerDetailsWorker)

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
}