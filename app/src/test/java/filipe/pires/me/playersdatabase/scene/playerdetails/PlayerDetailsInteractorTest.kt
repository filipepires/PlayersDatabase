package filipe.pires.me.playersdatabase.scene.playerdetails

import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.eq
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import org.junit.Test

class PlayerDetailsInteractorTest {

    private val presenter = mock<PlayerDetailsContract.Presentation>()
    private val playerDetailsWorker = mock<PlayerDetailsContract.Business.DataManager>()
    private val interactor = PlayerDetailsInteractor(presenter, playerDetailsWorker)

    @Test
    fun `when entering player details, fetch player details`() {
        interactor.onCreate("some id")
        verify(playerDetailsWorker).fetchPlayerWithId(eq("some id"), any())
    }
}